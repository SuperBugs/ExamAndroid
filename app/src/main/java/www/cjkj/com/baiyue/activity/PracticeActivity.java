package www.cjkj.com.baiyue.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
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
import www.cjkj.com.baiyue.fragment.PracticeDuoXuanTiFragment;
import www.cjkj.com.baiyue.fragment.PracticeFragment;
import www.cjkj.com.baiyue.fragment.ShiJuanCatalogFragment;
import www.cjkj.com.baiyue.moudel.TiMuModule;
import www.cjkj.com.baiyue.view.CommonView;

public class PracticeActivity extends FragmentActivity {
    private static final String TAG = "PracticeActivity";
    private ImageButton mBackBtn;
    private ViewPager mPractiveVp;
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
        setContentView(R.layout.activity_practice);
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
        if (ShiJuanDetailesActivity.mIsVip) {
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
        } else {
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
        mPractiveVp = findViewById(R.id.vp_pratice);
        mDaTiKaIv = findViewById(R.id.iv_practice_datika);
        mTitle = findViewById(R.id.titles);
    }

    private void setViews() {
        setTitle();
        setViewPagerView();
    }

    private void setViewPagerView() {
        mFragments = new ArrayList<Fragment>();// 将要分页显示的View装入数组中
        Log.d(TAG, "答案试卷名"+mShiJuanCursor.getString(mShiJuanCursor.getColumnIndex("sjname"))+mShiJuanCursor.getString(mShiJuanCursor.getColumnIndex("sjID")));
        for (int i = 0; i < mTiMuCursorArr.length; i++) {
            mTiMuCursorArr[i].moveToFirst();
            if (mTiMuCursorArr[i].getString(mTiMuCursorArr[i].getColumnIndex("class_timu")).equals("单选题")) {
                PracticeFragment fragment = new PracticeFragment();
                fragment.setSubjectsIndex(i, mPage);
                mFragments.add(fragment);
                mPage++;
                Log.d(TAG, mPage+"答案单选"+mTiMuIds[i]);
            }
        }
        for (int i = 0; i < mTiMuCursorArr.length; i++) {
            if (mTiMuCursorArr[i].getString(mTiMuCursorArr[i].getColumnIndex("class_timu")).equals("判断题")) {
                PracticeFragment fragment = new PracticeFragment();
                fragment.setSubjectsIndex(i, mPage);
                mFragments.add(fragment);
                mPage++;
                Log.d(TAG, mPage+"答案判断"+mTiMuIds[i]);
            }
        }
        for (int i = 0; i < mTiMuCursorArr.length; i++) {
            if (mTiMuCursorArr[i].getString(mTiMuCursorArr[i].getColumnIndex("class_timu")).equals("多选题")) {
                PracticeDuoXuanTiFragment fragment = new PracticeDuoXuanTiFragment();
                fragment.setSubjectsIndex(i, mPage);
                mFragments.add(fragment);
                mPage++;
                Log.d(TAG, mPage+"答案多选"+mTiMuIds[i]);
            }
        }
        mPracticeVpAdapter = new PracticeViewPagerAdapter(getSupportFragmentManager(), mFragments);
        mPractiveVp.setAdapter(mPracticeVpAdapter);
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
                Intent intent = new Intent(PracticeActivity.this, PracticeDaTiKaActivity.class);
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
            mPractiveVp.setCurrentItem(page);
        }

    }

    public void setUpPage(int page) {
        if ((page - 2) >= 0) {
            mPractiveVp.setCurrentItem(page - 2);
        } else {
            Toast.makeText(activity, "已是第一题", Toast.LENGTH_SHORT).show();
        }
    }

    public void setNextPage(int page) {
        if (page <= mPage - 1) {
            mPractiveVp.setCurrentItem(page);
        } else {
            Toast.makeText(activity, "已是最后一题", Toast.LENGTH_SHORT).show();
        }
    }

    public void setTiMuTitle(String tiMuType) {
        mTitle.setText(tiMuType);
    }
}
