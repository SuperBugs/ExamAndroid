package www.cjkj.com.baiyue.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.media.tv.TvContentRating;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import www.cjkj.com.baiyue.R;
import www.cjkj.com.baiyue.common.CommonFunction;
import www.cjkj.com.baiyue.fragment.ShiJuanCatalogFragment;
import www.cjkj.com.baiyue.moudel.TiMuModule;
import www.cjkj.com.baiyue.view.CommonView;

public class ShiJuanDetailesActivity extends Activity {
    private ImageButton mBackIb;
    private TextView mYiDaCountTv;
    private TextView mScoreTv;
    private TextView mTotalScoreTv;
    private TextView mPassScoreTv;
    private TextView mShiJuanNameTv;
    private Button mClearExamBtn;
    private Button mClearPraticeBtn;
    private Button mPracticeBtn;
    private Button mExameBtn;
    private Intent mDataIntent;
    private Cursor mShiJuanCursor;
    private Dialog mDialog;
    private Handler mDialogHandler;
    public static boolean mIsVip = false;
    public Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shi_juan_detailes);
        mActivity=this;
        getObjData();
        getViews();
        setViews();
        setOnClickListener();
    }

    private void getObjData() {
        mDataIntent = getIntent();
        mShiJuanCursor = ShiJuanCatalogFragment.gShiJuanCursor;
        mDialogHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                mDialog.dismiss();
            }
        };
    }
    private void getIsVip(){
        String mShiJuanKeMu = mShiJuanCursor.getString(mShiJuanCursor.getColumnIndex("class_kemu"));
        if (CommonFunction.getDate(mShiJuanKeMu + "vip", this) != null) {
            mIsVip = (boolean) CommonFunction.getDate(mShiJuanKeMu + "vip", this);
        }
    }

    private void getViews() {
        mBackIb = findViewById(R.id.back);
        mYiDaCountTv = findViewById(R.id.tv_shijuan_yida);
        mScoreTv = findViewById(R.id.tv_shijuan_score);
        mTotalScoreTv = findViewById(R.id.tv_shijuan_total_score);
        mPassScoreTv = findViewById(R.id.tv_shijuan_pass_score);
        mShiJuanNameTv = findViewById(R.id.tv_shijuan_name);
        mClearExamBtn = findViewById(R.id.btn_shijuan_clear_exam);
        mClearPraticeBtn = findViewById(R.id.btn_shijuan_clear_pratice);
        mPracticeBtn = findViewById(R.id.btn_shijuan_pratice);
        mExameBtn = findViewById(R.id.btn_shijuan_exam);
    }

    private void setViews() {
        mShiJuanNameTv.setText(mShiJuanCursor.getString(mShiJuanCursor.getColumnIndex("sjname")));
        mYiDaCountTv.setText(mShiJuanCursor.getInt(mShiJuanCursor.getColumnIndex("tmcount_yida")) + "/" + mShiJuanCursor.getInt(mShiJuanCursor.getColumnIndex("tmcount")));
        mTotalScoreTv.setText(mShiJuanCursor.getInt(mShiJuanCursor.getColumnIndex("tmcount")) + "");
        mPassScoreTv.setText(mShiJuanCursor.getInt(mShiJuanCursor.getColumnIndex("jigefen")) + "");
        mScoreTv.setText(mShiJuanCursor.getDouble(mShiJuanCursor.getColumnIndex("userfs")) + "");
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
        mPracticeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getIsVip();
                if (mIsVip) {
                    Intent praticeIntent = new Intent(ShiJuanDetailesActivity.this, PracticeActivity.class);
                    startActivity(praticeIntent);
                } else {
                    new AlertDialog.Builder(mActivity)
                            .setTitle("温馨提示")
                            .setMessage("该科目暂未激活,仅能测试5题!")
                            .setCancelable(false)//设置不能按返回键返回
                            .setPositiveButton("立即激活", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent=new Intent(mActivity,ActiveCrouseActivity.class);
                                    startActivity(intent);
                                }
                            }).setNegativeButton("继续试用", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            Intent praticeIntent = new Intent(ShiJuanDetailesActivity.this, PracticeActivity.class);
                            startActivity(praticeIntent);
                        }
                    }).setOnKeyListener(new DialogInterface.OnKeyListener() {  //屏蔽按键
                        @Override
                        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                            if (keyCode == KeyEvent.KEYCODE_SEARCH) {
                                return true;
                            } else {
                                return false; //默认返回 false
                            }
                        }
                    }).create().show();
                }

            }
        });
        mExameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getIsVip();
                if(mIsVip){
                    Intent examIntent = new Intent(ShiJuanDetailesActivity.this, ExamActivity.class);
                    startActivity(examIntent);
                }else{
                    new AlertDialog.Builder(mActivity)
                            .setTitle("温馨提示")
                            .setMessage("该科目暂未激活,仅能测试5题!")
                            .setCancelable(false)//设置不能按返回键返回
                            .setPositiveButton("立即激活", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent=new Intent(mActivity,ActiveCrouseActivity.class);
                                    startActivity(intent);
                                }
                            }).setNegativeButton("继续试用", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            Intent examIntent = new Intent(ShiJuanDetailesActivity.this, ExamActivity.class);
                            startActivity(examIntent);
                        }
                    }).setOnKeyListener(new DialogInterface.OnKeyListener() {  //屏蔽按键
                        @Override
                        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                            if (keyCode == KeyEvent.KEYCODE_SEARCH) {
                                return true;
                            } else {
                                return false; //默认返回 false
                            }
                        }
                    }).create().show();
                }

            }
        });
        mClearExamBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog = ProgressDialog.show(ShiJuanDetailesActivity.this, null, "清除记录中...", false, true);
                mDialog.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int[] mTiMuIds;
                        Cursor mShiJuanCursor;
                        TiMuModule mTiMuModule;
                        mShiJuanCursor = ShiJuanCatalogFragment.gShiJuanCursor;
                        String shiJuanTiMuIdString = mShiJuanCursor.getString(mShiJuanCursor.getColumnIndex("tmIDstr"));
                        String[] shiJuanTiMuIdStringArr = shiJuanTiMuIdString.split(",tm");
                        shiJuanTiMuIdStringArr[0] = shiJuanTiMuIdStringArr[0].substring(2, shiJuanTiMuIdStringArr[0].length());
                        mTiMuIds = new int[shiJuanTiMuIdStringArr.length];
                        for (int i = 0; i < shiJuanTiMuIdStringArr.length; i++) {
                            mTiMuIds[i] = Integer.parseInt(shiJuanTiMuIdStringArr[i]);
                        }
                        mTiMuModule = new TiMuModule();
                        for (int i = 0; i < mTiMuIds.length; i++) {
                            mTiMuModule.setTiMuKaoShi(mTiMuIds[i], "", "0", "0");
                        }
                        Message message = new Message();
                        message.what = 1;
                        mDialogHandler.sendMessage(message);
                    }
                }).start();
            }
        });
        mClearPraticeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog = ProgressDialog.show(ShiJuanDetailesActivity.this, null, "清除记录中...", false, true);
                mDialog.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int[] mTiMuIds;
                        Cursor mShiJuanCursor;
                        TiMuModule mTiMuModule;
                        mShiJuanCursor = ShiJuanCatalogFragment.gShiJuanCursor;
                        String shiJuanTiMuIdString = mShiJuanCursor.getString(mShiJuanCursor.getColumnIndex("tmIDstr"));
                        String[] shiJuanTiMuIdStringArr = shiJuanTiMuIdString.split(",tm");
                        shiJuanTiMuIdStringArr[0] = shiJuanTiMuIdStringArr[0].substring(2, shiJuanTiMuIdStringArr[0].length());
                        mTiMuIds = new int[shiJuanTiMuIdStringArr.length];
                        for (int i = 0; i < shiJuanTiMuIdStringArr.length; i++) {
                            mTiMuIds[i] = Integer.parseInt(shiJuanTiMuIdStringArr[i]);
                        }
                        mTiMuModule = new TiMuModule();
                        for (int i = 0; i < mTiMuIds.length; i++) {
                            mTiMuModule.setTiMuLianXi(mTiMuIds[i], "", "0", "0");
                        }
                        Message message = new Message();
                        message.what = 1;
                        mDialogHandler.sendMessage(message);
                    }
                }).start();
            }
        });
    }

}
