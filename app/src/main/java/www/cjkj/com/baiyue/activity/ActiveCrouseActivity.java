package www.cjkj.com.baiyue.activity;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import www.cjkj.com.baiyue.R;
import www.cjkj.com.baiyue.common.CommonFunction;
import www.cjkj.com.baiyue.internet.CheckActiveResult;
import www.cjkj.com.baiyue.view.CommonView;

import static android.content.ContentValues.TAG;

public class ActiveCrouseActivity extends Activity {
    private static final String TAG = "ActiveCrouseActivity";
    private ImageButton mBackIb;
    private Button mActive;
    private Button mTaoBao;
    private Button mWeiXin;
    private Handler handler1;
    private EditText mPhoneEdt;
    private EditText mActiveEdt;
    private Activity mActivity;
    private String phone;
    private String jihuoma;
    private Message message1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_crouse);
        mActivity = this;
        initObj();
        getViews();
        setViews();
        setOnClickListener();
    }

    private void initObj() {
        handler1 = new android.os.Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        Toast.makeText(mActivity, "请输入激活码或手机号", Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        Toast.makeText(mActivity, "服务器返回数据异常", Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        Toast.makeText(mActivity, "激活成功", Toast.LENGTH_LONG).show();
                        break;
                    case 3:
                        Toast.makeText(mActivity, "激活失败", Toast.LENGTH_LONG).show();
                }
            }
        };
    }

    private void getViews() {
        mBackIb = findViewById(R.id.back);
        mActive = findViewById(R.id.btn_active_crouse_jihuo);
        mTaoBao = findViewById(R.id.btn_active_crouse_taobao);
        mWeiXin = findViewById(R.id.btn_active_crouse_weixin);
        mActiveEdt = findViewById(R.id.edt_active_crouse_num);
        mPhoneEdt = findViewById(R.id.ed_active_crouse_phone);
    }

    private void setViews() {
        setTitle();
    }

    private void setTitle() {
        CommonView mCommonView;
        mCommonView = new CommonView(this);
        mCommonView.setTitle();
    }

    private void setOnClickListener() {
        mBackIb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mTaoBao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.setType("text/*");
                sendIntent.putExtra(Intent.EXTRA_TEXT, "http://www.taobao.com");
                startActivity(sendIntent);
            }
        });
        mActive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phone = mPhoneEdt.getText().toString();
                jihuoma = mActiveEdt.getText().toString();
                Log.d(TAG, "激活" + phone + "|" + jihuoma);
                message1 = new Message();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if ((phone.equals("")) || (jihuoma.equals(""))) {
                            message1.what = 0;
                        } else {
                            CheckActiveResult checkActiveResult = new CheckActiveResult();
                            String activeAcrouseResult = checkActiveResult.check(phone, jihuoma);
                            if (activeAcrouseResult.equals("")) {
                                message1.what = 1;
                            }
                            if (activeAcrouseResult.contains("false")) {
                                message1.what = 3;
                            }
                            if (activeAcrouseResult.contains("1")) {
                                CommonFunction.putDate("公共基础vip", true, mActivity);
                                message1.what = 2;
                            }
                            if (activeAcrouseResult.contains("2")) {
                                CommonFunction.putDate("道路工程vip", true, mActivity);
                                message1.what = 2;
                            }
                            if (activeAcrouseResult.contains("3")) {
                                CommonFunction.putDate("交通工程vip", true, mActivity);
                                message1.what = 2;
                            }
                            if (activeAcrouseResult.contains("4")) {
                                CommonFunction.putDate("交通工程vip", true, mActivity);
                                message1.what = 2;
                            }
                        }
                        handler1.sendMessage(message1);
                    }
                }).start();
            }
        });
        mWeiXin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取剪贴板管理器：
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 创建普通字符型ClipData
                ClipData mClipData = ClipData.newPlainText("WeiXin", "weixinhao123");
                // 将ClipData内容放到系统剪贴板里。
                cm.setPrimaryClip(mClipData);
                Toast.makeText(ActiveCrouseActivity.this, "微信号已复制，进入微信添加好友", Toast.LENGTH_LONG);
            }
        });
    }
}
