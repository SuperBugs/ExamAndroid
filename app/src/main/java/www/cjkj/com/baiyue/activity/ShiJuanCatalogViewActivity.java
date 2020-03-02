package www.cjkj.com.baiyue.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import www.cjkj.com.baiyue.R;
import www.cjkj.com.baiyue.adapter.WrongSubjectsFragmentAdapter;
import www.cjkj.com.baiyue.common.CommonFunction;
import www.cjkj.com.baiyue.fragment.ShiJuanCatalogFragment;
import www.cjkj.com.baiyue.moudel.ShiJuanModule;
import www.cjkj.com.baiyue.view.CommonView;

public class ShiJuanCatalogViewActivity extends FragmentActivity {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private ShiJuanCatalogFragment mShiJuanCatalogFragment;
    private ShiJuanCatalogFragment mShiJuanCatalogTwoFragment;
    private ShiJuanCatalogFragment mShiJuanCatalogThreeFragment;
    private ShiJuanCatalogFragment mShiJuanCatalogFourFragment;
    private Activity mActivity;
    private int mShiJuanKeMu;
    private Intent mDataIntent;
    private TextView mTitle;
    private ImageButton mBackIb;
    private LinearLayout mTitleLy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shi_juan_catalog);
        mActivity = this;
        initObj();
        setObjData();
        getViews();
        setViews();
        setOnClickListener();
    }
    private void setOnClickListener(){
        mBackIb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void setViews() {
        setTitle();
        initViewPager();
    }

    private void setTitle() {
        mTitle.setText("试卷详情");
        CommonView mCommonView;
        mCommonView = new CommonView(mActivity);
        mCommonView.setTitle();
    }
    private void getViews() {
        mTabLayout = findViewById(R.id.tabs);
        mViewPager = findViewById(R.id.viewpager);
        mTitle = findViewById(R.id.titles);
        mBackIb = findViewById(R.id.back);
        mTitleLy=findViewById(R.id.title);
    }

    private void setObjData() {
        mDataIntent = getIntent();
        mShiJuanKeMu= mDataIntent.getIntExtra("shijuan_kemu", 1);
        mShiJuanCatalogFragment.setShiJuanData(mShiJuanKeMu,1);
        mShiJuanCatalogTwoFragment.setShiJuanData(mShiJuanKeMu,2);
        mShiJuanCatalogThreeFragment.setShiJuanData(mShiJuanKeMu,3);
        mShiJuanCatalogFourFragment.setShiJuanData(mShiJuanKeMu,4);
    }

    private void initObj() {
        mShiJuanCatalogFragment = new ShiJuanCatalogFragment();
        mShiJuanCatalogTwoFragment = new ShiJuanCatalogFragment();
        mShiJuanCatalogThreeFragment = new ShiJuanCatalogFragment();
        mShiJuanCatalogFourFragment = new ShiJuanCatalogFragment();
        
    }

    private void initViewPager() {
        List<String> titles = new ArrayList<>();
        titles.add("模拟试卷");
        titles.add("章节练习");
        titles.add("历年真题");
        titles.add("最近练习");
        for (int i = 0; i < titles.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(i)));
        }
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(mShiJuanCatalogFragment);
        fragments.add(mShiJuanCatalogTwoFragment);
        fragments.add(mShiJuanCatalogThreeFragment);
        fragments.add(mShiJuanCatalogFourFragment);
        WrongSubjectsFragmentAdapter mFragmentAdapteradapter =
                new WrongSubjectsFragmentAdapter(this.getSupportFragmentManager(), fragments, titles);
        //给ViewPager设置适配器
        mViewPager.setAdapter(mFragmentAdapteradapter);
        //将TabLayout和ViewPager关联起来。
        mTabLayout.setupWithViewPager(mViewPager);
        //给TabLayout设置适配器
        mTabLayout.setTabsFromPagerAdapter(mFragmentAdapteradapter);
    }

}
