package www.cjkj.com.baiyue.activity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import www.cjkj.com.baiyue.R;
import www.cjkj.com.baiyue.fragment.ShiJuanCatalogFragment;
import www.cjkj.com.baiyue.moudel.TiMuModule;
import www.cjkj.com.baiyue.view.CommonView;

import static www.cjkj.com.baiyue.activity.ExamActivity.mTiMuCursorArr;

public class ExameResultActivity extends Activity {
    private ImageButton mBackIb;
    private Dialog mDialog;
    private Handler mDialogHandler;
    private int mScore=0;
    private int mYiDaCount=0;
    private TextView mYiDaCountTv;
    private TextView mScoreTv;
    private TextView mTotalScoreTv;
    private TextView mPassScoreTv;
    private Button mButton;
    public static int[] mWrongTiMuIds;
    public static Cursor[] mWrongTiMuCursorArr;
    private List<Integer> mWrongTiMuListIds;
    private List<Cursor> mWrongTiMuCursorList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kao_shi_result);
        initObj();
        getViews();
        setViews();
        startThread();
        setOnClickListener();
    }
    private void initObj(){
        mDialogHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                setUserExamResultToDatabase();
                refershViews();
                mDialog.dismiss();
            }
        };
        mWrongTiMuListIds=new ArrayList<Integer>();
        mWrongTiMuCursorList=new ArrayList<Cursor>();
    }
    private void setUserExamResultToDatabase(){
        ShiJuanCatalogFragment.mShiJuanModule.setUserExamResult(ShiJuanCatalogFragment.gShiJuanCursor.getString(ShiJuanCatalogFragment.gShiJuanCursor.getColumnIndex("sjID")),mScore+"",mYiDaCount+"");
    }
    private void startThread(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<ExamActivity.mTiMuIds.length;i++){
                    if(mTiMuCursorArr[i].getInt(mTiMuCursorArr[i].getColumnIndex("sc_state")) == 1){
                        mYiDaCount++;
                        if(mTiMuCursorArr[i].getInt(mTiMuCursorArr[i].getColumnIndex("cuoti_ksState")) == 0){
                            mScore=mScore+mTiMuCursorArr[i].getInt(mTiMuCursorArr[i].getColumnIndex("tmfenshu"));
                        }else{
                            //添加错误题目
                            mWrongTiMuListIds.add(ExamActivity.mTiMuIds[i]);
                            mWrongTiMuCursorList.add(mTiMuCursorArr[i]);
                        }
                    }
                }
                int num=mWrongTiMuListIds.size();
                mWrongTiMuIds =new int[num];
                mWrongTiMuCursorArr=new Cursor[num];
                for(int i=0;i<num;i++){
                    mWrongTiMuIds[i]=mWrongTiMuListIds.get(i);
                    mWrongTiMuCursorArr[i]=mWrongTiMuCursorList.get(i);
                }
                Message message = new Message();
                message.what = 1;
                mDialogHandler.sendMessage(message);
            }
        }).start();
    }
    private void refershViews(){
        mYiDaCountTv.setText(mYiDaCount+"/"+ShiJuanCatalogFragment.gShiJuanCursor.getInt(ShiJuanCatalogFragment.gShiJuanCursor.getColumnIndex("tmcount")));
        mTotalScoreTv.setText(ShiJuanCatalogFragment.gShiJuanCursor.getInt(ShiJuanCatalogFragment.gShiJuanCursor.getColumnIndex("fenshu")) + "");
        mPassScoreTv.setText(ShiJuanCatalogFragment.gShiJuanCursor.getInt(ShiJuanCatalogFragment.gShiJuanCursor.getColumnIndex("jigefen")) + "");
        mScoreTv.setText(mScore + "");
    }
    private void getViews() {
        mBackIb = findViewById(R.id.back);
        mYiDaCountTv = findViewById(R.id.tv_shijuan_yida);
        mScoreTv = findViewById(R.id.tv_shijuan_score);
        mTotalScoreTv = findViewById(R.id.tv_shijuan_total_score);
        mPassScoreTv = findViewById(R.id.tv_shijuan_pass_score);
        mButton=findViewById(R.id.btn_shijuan_pratice);
    }

    private void setViews() {
        setTitle();
        mDialog = ProgressDialog.show(ExameResultActivity.this, null, "分数计算中...", false, true);
        mDialog.show();
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
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ExameResultActivity.this,ExamWrongSubjectsActivity.class);
                startActivity(intent);
            }
        });
    }
}