package www.cjkj.com.baiyue.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import www.cjkj.com.baiyue.R;
import www.cjkj.com.baiyue.internet.PostSuggestionData;
import www.cjkj.com.baiyue.view.CommonView;

public class AdviceActivity extends Activity {
    private ImageButton mBackIb;
    private EditText mContentEt;
    private EditText mContactEt;
    private String mContent;
    private String mContact;
    private String mAccount="123";
    private Button mSubmitBtn;
    private Handler mHandler;
    private ProgressDialog diolog;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice);
        activity=this;
        getViews();
        setViews();
        setHandler();
        setOnClickListener();
    }

    private void getViews() {
        mBackIb = findViewById(R.id.back_btn);
        mContentEt = (EditText) findViewById(R.id.f_feedback_et);
        mContactEt = (EditText) findViewById(R.id.f_feedback_contact_et);
        mSubmitBtn = (Button) findViewById(R.id.f_feedback_submit_btn);
    }

    private void setViews() {
        setTitle();
    }
    private void setHandler() {
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        diolog.dismiss();
                        Toast.makeText(activity, "提交失败,请稍后再试!", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        diolog.dismiss();
                        Toast.makeText(activity, "提交成功!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(activity, MainActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                }
            }
        };
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
        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContact = mContactEt.getText().toString();
                mContent = mContentEt.getText().toString();
                diolog = ProgressDialog.show(activity, null, getString(R.string.f_share_dialog_now), false, true);
                postData();//发送数据
            }
        });
    }
    private void postData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                PostSuggestionData postSuggestionData = new PostSuggestionData();
                if (postSuggestionData.postSuggestionData(mContent, mContact, mAccount)) {
                    Message message = new Message();
                    message.what = 1;
                    mHandler.sendMessage(message);
                } else {
                    Message message = new Message();
                    message.what = 0;
                    mHandler.sendMessage(message);
                }
            }
        }).start();
    }
}