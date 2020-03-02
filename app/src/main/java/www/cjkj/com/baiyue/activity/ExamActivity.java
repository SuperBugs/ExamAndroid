package www.cjkj.com.baiyue.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import www.cjkj.com.baiyue.R;
import www.cjkj.com.baiyue.adapter.PracticeViewPagerAdapter;
import www.cjkj.com.baiyue.fragment.ExamDuoXuanTiFragment;
import www.cjkj.com.baiyue.fragment.ExamFragment;

import www.cjkj.com.baiyue.fragment.ShiJuanCatalogFragment;
import www.cjkj.com.baiyue.moudel.TiMuModule;
import www.cjkj.com.baiyue.view.CommonView;

public class ExamActivity extends FragmentActivity {
    private static final String TAG = "ExamActivity";
    private ImageButton mBackBtn;
    private ViewPager mExamVp;
    private ImageView mDaTiKaIv;
    private TextView mTitle;
    private PracticeViewPagerAdapter mPracticeVpAdapter;
    private List<Fragment> mFragments;
    public static int[] mTiMuIds;
    private Cursor mShiJuanCursor;
    public static Cursor[] mTiMuCursorArr;
    public static TiMuModule mTiMuModule;
    private int mPage = 1;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        activity = this;
        getObjData();
        getViews();
        setViews();
        setOnClickListener();
    }
    private void getObjData() {
        mShiJuanCursor = ShiJuanCatalogFragment.gShiJuanCursor;
        String shiJuanTiMuIdString = mShiJuanCursor.getString(mShiJuanCursor.getColumnIndex("tmIDstr"));
        String[] shiJuanTiMuIdStringArr = shiJuanTiMuIdString.split(",tm");
        shiJuanTiMuIdStringArr[0] = shiJuanTiMuIdStringArr[0].substring(2, shiJuanTiMuIdStringArr[0].length());
        if(ShiJuanDetailesActivity.mIsVip){
            mTiMuIds = new int[shiJuanTiMuIdStringArr.length];
            for (int i = 0; i < shiJuanTiMuIdStringArr.length; i++) {
                mTiMuIds[i] = Integer.parseInt(shiJuanTiMuIdStringArr[i]);
            }
            Arrays.sort(mTiMuIds);
            mTiMuModule = new TiMuModule();
            mTiMuCursorArr = new Cursor[mTiMuIds.length];
            for (int i = 0; i < mTiMuIds.length; i++) {
                mTiMuCursorArr[i] = mTiMuModule.getTiMuData(mTiMuIds[i]);
            }
        }else{
            mTiMuIds = new int[5];
            for (int i = 0; i < 5; i++) {
                mTiMuIds[i] = Integer.parseInt(shiJuanTiMuIdStringArr[i]);
            }
            Arrays.sort(mTiMuIds);
            mTiMuModule = new TiMuModule();
            mTiMuCursorArr = new Cursor[mTiMuIds.length];
            for (int i = 0; i < mTiMuIds.length; i++) {
                mTiMuCursorArr[i] = mTiMuModule.getTiMuData(mTiMuIds[i]);
            }
        }

    }
    private void getViews() {
        mBackBtn = findViewById(R.id.back);
        mExamVp = findViewById(R.id.vp_exam);
        mDaTiKaIv = findViewById(R.id.iv_exam_datika);
        mTitle = findViewById(R.id.titles);
    }

    private void setViews() {
        setTitle();
        setViewPagerView();
    }

    private void setViewPagerView() {
        mFragments = new ArrayList<Fragment>();// 将要分页显示的View装入数组中
        for (int i = 0; i < mTiMuCursorArr.length; i++) {
            mTiMuCursorArr[i].moveToFirst();
            if (mTiMuCursorArr[i].getString(mTiMuCursorArr[i].getColumnIndex("class_timu")).equals("单选题")) {
                ExamFragment fragment = new ExamFragment();
                fragment.setSubjectsIndex(i, mPage);
                mFragments.add(fragment);
                mPage++;
            }
        }
        for (int i = 0; i < mTiMuCursorArr.length; i++) {
            if (mTiMuCursorArr[i].getString(mTiMuCursorArr[i].getColumnIndex("class_timu")).equals("判断题")) {
                ExamFragment fragment = new ExamFragment();
                fragment.setSubjectsIndex(i, mPage);
                mFragments.add(fragment);
                mPage++;
            }
        }
        for (int i = 0; i < mTiMuCursorArr.length; i++) {
            if (mTiMuCursorArr[i].getString(mTiMuCursorArr[i].getColumnIndex("class_timu")).equals("多选题")) {
                ExamDuoXuanTiFragment fragment = new ExamDuoXuanTiFragment();
                fragment.setSubjectsIndex(i, mPage);
                mFragments.add(fragment);
                mPage++;
            }
        }
        mPracticeVpAdapter = new PracticeViewPagerAdapter(getSupportFragmentManager(), mFragments);
        mExamVp.setAdapter(mPracticeVpAdapter);
    }

    private void setTitle() {
        CommonView mCommonView;
        mCommonView = new CommonView(this);
        mCommonView.setTitle();
    }

    private void setOnClickListener() {
        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mDaTiKaIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExamActivity.this, ExamDaTiKaActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    public static void changeTiMuCursorArrData(int i) {
        mTiMuCursorArr[i] = mTiMuModule.getTiMuData(mTiMuIds[i]);
        mTiMuCursorArr[i].moveToFirst();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 0) {
            int page = data.getIntExtra("page", 0);
            Log.d(TAG, "页数" + page);
            mExamVp.setCurrentItem(page);
        }

    }
    public void submitShiJuan(){
        Log.d(TAG, "提示");
        new AlertDialog.Builder(activity)
                .setTitle("交卷提示")
                .setMessage("是否立即提交该试卷")
                .setCancelable(false)//设置不能按返回键返回
                .setPositiveButton("继续答题", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setNegativeButton("立即提交", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent(ExamActivity.this,ExameResultActivity.class);
                startActivity(intent);
                dialog.dismiss();
            }
        }).setOnKeyListener(new DialogInterface.OnKeyListener() {  //屏蔽按键
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_SEARCH) {
                    dialog.dismiss();
                    return true;
                } else {
                    return false; //默认返回 false
                }
            }
        }).create().show();
    }
    public void setUpPage(int page) {

        if ((page - 2) >= 0) {
            mExamVp.setCurrentItem(page - 2);
        } else {
            Toast.makeText(activity, "已是第一题", Toast.LENGTH_SHORT).show();
        }

    }

    public void setNextPage(int page) {

        if (page <= mPage - 1) {
            mExamVp.setCurrentItem(page);
        } else {
            Toast.makeText(activity, "已是最后一题", Toast.LENGTH_SHORT).show();
        }
    }

    public void setTiMuTitle(String tiMuType) {
        mTitle.setText(tiMuType);
    }
}
