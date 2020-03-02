package www.cjkj.com.baiyue.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import www.cjkj.com.baiyue.R;
import www.cjkj.com.baiyue.activity.BookCatalogViewActivity;
import www.cjkj.com.baiyue.activity.PDFViewsActivity;
import www.cjkj.com.baiyue.activity.ShiJuanCatalogViewActivity;
import www.cjkj.com.baiyue.adapter.HomeViewPagerAdapter;
import www.cjkj.com.baiyue.common.CommonFunction;


public class MainFragment extends Fragment {
    private Activity mActivity;
    private View mMainView;
    private int mScreamWidth;
    private ViewPager mViewpager;
    private LinearLayout mPointGroup;
    private LinearLayout mCenterLy;
    private CommonFunction mCommonFunction;
    private HomeViewPagerAdapter mHomeViewPagerAdapter;
    private ImageView mGongGongIv;
    private ImageView mDaoLuIv;
    private ImageView mJiaoTongIv;
    private ImageView mQiaoLiangIv;
    private TextView mGongGongTv;
    private TextView mDaoLuTv;
    private TextView mJiaoTongTv;
    private TextView mQiaoLiangTv;
    private ImageView mBookGongGongIv;
    private ImageView mBookDaoLuIv;
    private ImageView mBookJiaoTongIv;
    private ImageView mBookQiaoLiangIv;
    //当前viewPager的位置
    private int postion;
    //上一个小点的位置
    private int lastPosition = 2;
    private Handler mChangeViewPagerPageHandler;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity=getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mMainView=inflater.inflate(R.layout.fragment_main, container, false);
        initObj();
        getViews();
        setViews();
        setClickListener();
        return mMainView;
    }
    private void initObj(){
        mCommonFunction=new CommonFunction();
        WindowManager wm = (WindowManager) mActivity
                .getSystemService(Context.WINDOW_SERVICE);
        assert wm != null;
        mScreamWidth = wm.getDefaultDisplay().getWidth();
    }

    private void getViews(){
        mGongGongIv=mMainView.findViewById(R.id.iv_home_center_gong_gong);
        mGongGongTv=mMainView.findViewById(R.id.tv_home_center_gong_gong);
        mDaoLuIv=mMainView.findViewById(R.id.iv_home_center_dao_lu);
        mDaoLuTv=mMainView.findViewById(R.id.tv_home_center_dao_lu);
        mJiaoTongIv=mMainView.findViewById(R.id.iv_home_center_jiao_tong);
        mJiaoTongTv=mMainView.findViewById(R.id.tv_home_center_jiao_tong);
        mQiaoLiangIv=mMainView.findViewById(R.id.iv_home_center_qiao_liang);
        mQiaoLiangTv=mMainView.findViewById(R.id.tv_home_center_qiao_liang);
        mCenterLy=mMainView.findViewById(R.id.ly_home_center);
        mViewpager=mMainView.findViewById(R.id.vp_home);
        mPointGroup=mMainView.findViewById(R.id.point_group);
        mBookDaoLuIv=mMainView.findViewById(R.id.iv_home_bottom_dao_lu);
        mBookGongGongIv=mMainView.findViewById(R.id.iv_home_bottom_gong_gong);
        mBookJiaoTongIv=mMainView.findViewById(R.id.iv_home_bottom_jiao_tong);
        mBookQiaoLiangIv=mMainView.findViewById(R.id.iv_home_bottom_qiao_liang);
    }

    private void setViews(){
        setViewPagerView();
        setCenterViews();
        setBottomViews();
    }
    private void setBottomViews(){

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(mScreamWidth/20*8/3, 0, 0, 0);
        lp.height = mScreamWidth/20*6*180/132;
        lp.width = mScreamWidth/20*6;
        mBookGongGongIv.setLayoutParams(lp);
        LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp1.setMargins(mScreamWidth/20*8/3, 0, 0, 0);
        lp1.height = mScreamWidth/20*6*180/132;
        lp1.width = mScreamWidth/20*6;
        mBookDaoLuIv.setLayoutParams(lp1);
        LinearLayout.LayoutParams lp3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp3.setMargins(mScreamWidth/20*8/3, 0, 0, 0);
        lp3.height = mScreamWidth/20*6*180/132;
        lp3.width = mScreamWidth/20*6;
        mBookJiaoTongIv.setLayoutParams(lp3);
        LinearLayout.LayoutParams lp4 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp4.setMargins(mScreamWidth/20*8/3, 0, 0, 0);
        lp4.height = mScreamWidth/20*6*180/132;
        lp4.width = mScreamWidth/20*6;
        mBookQiaoLiangIv.setLayoutParams(lp4);
    }
    private void setCenterViews(){
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.height = mScreamWidth/24*4;
        mCenterLy.setLayoutParams(lp);
        mGongGongIv.setImageBitmap(BitmapFactory
                .decodeResource(getResources(), R.drawable.jichu));

        LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp1.setMargins(mScreamWidth/21*9/8, mScreamWidth/24/2, 0, 0);
        lp1.height = mScreamWidth/21*3;
        lp1.width = mScreamWidth/21*3;
        mGongGongIv.setLayoutParams(lp1);
        mDaoLuIv.setImageBitmap(BitmapFactory
                .decodeResource(getResources(), R.drawable.daolu));
        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp2.setMargins(mScreamWidth/21*9/4, mScreamWidth/24/2, 0, 0);
        lp2.height = mScreamWidth/21*3;
        lp2.width = mScreamWidth/21*3;
        mDaoLuIv.setLayoutParams(lp2);
        mJiaoTongIv.setImageBitmap(BitmapFactory
                .decodeResource(getResources(), R.drawable.jiaotong));
        LinearLayout.LayoutParams lp3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp3.setMargins(mScreamWidth/21*9/4, mScreamWidth/24/2, 0, 0);
        lp3.height = mScreamWidth/21*3;
        lp3.width = mScreamWidth/21*3;
        mJiaoTongIv.setLayoutParams(lp3);
        mQiaoLiangIv.setImageBitmap(BitmapFactory
                .decodeResource(getResources(), R.drawable.qiaoliang));
        LinearLayout.LayoutParams lp4 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp4.setMargins(mScreamWidth/21*9/4, mScreamWidth/24/2, 0, 0);
        lp4.height = mScreamWidth/21*3;
        lp4.width = mScreamWidth/21*3;
        mQiaoLiangIv.setLayoutParams(lp4);
    }

    private void setViewPagerView(){
        initViewPagerView();
        makeViewPagerAutoSlide();//自动滑动
    }
    //初始化ViewPager
    private void initViewPagerView() {
        mHomeViewPagerAdapter = new HomeViewPagerAdapter(mActivity);
        mViewpager.setAdapter(mHomeViewPagerAdapter);
        //添加小点
        for (int i = 0; i < 5; i++) {
            ImageView point = new ImageView(mActivity);
            point.setBackgroundResource(R.drawable.point_bg);
            //设置圆点填充布局
            LinearLayout.LayoutParams params = new LinearLayout.
                    LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            //设置左右边距为5像素
            params.rightMargin = 10;
            params.leftMargin = 10;
            point.setLayoutParams(params);
            point.setEnabled(false);
            mPointGroup.addView(point);
        }
        mPointGroup.getChildAt(2).setEnabled(true);
        //为viewPager设置监听事件
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //滑动时调用
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            //页面切换后调用
            @Override
            public void onPageSelected(int position) {
                postion = position;
                //改变原点状态
                if (0 < position && position < 6) {
                    mPointGroup.getChildAt(position - 1).setEnabled(true);
                    mPointGroup.getChildAt(lastPosition).setEnabled(false);
                    //保存当前圆点状态
                    lastPosition = position - 1;
                }
                if (position == 0 && lastPosition == 0) {
                    mPointGroup.getChildAt(0).setEnabled(false);
                    lastPosition = 4;
                    mViewpager.setCurrentItem(5);
                    mPointGroup.getChildAt(4).setEnabled(true);
                }

            }

            //滑动时调用
            @Override
            public void onPageScrollStateChanged(int state) {
                if (postion == 6) {
                    mViewpager.setCurrentItem(1, false);
                } else if (postion == 0) {
                    mViewpager.setCurrentItem(6, false);
                }
            }
        });
        //设置viewPager的7个界面不会被销毁
        mViewpager.setOffscreenPageLimit(7);
        //设置当前是第几张图片
        mViewpager.setCurrentItem(4, true);
        mViewpager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.finish();
            }
        });
    }

    @SuppressLint("HandlerLeak")
    private void makeViewPagerAutoSlide() {
        //定时循环Handler，让广告条自动滑动
        mChangeViewPagerPageHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        mViewpager.setCurrentItem(mViewpager.getCurrentItem() + 1, true);
                        //五秒后发送Handler消息
                        mChangeViewPagerPageHandler.sendEmptyMessageDelayed(0, 4000);
                        break;
                }
            }
        };
        //循环播放开始
        mChangeViewPagerPageHandler.sendEmptyMessageDelayed(0, 4000);
    }

    private void setClickListener(){
        final Intent ftpIntent = new Intent(mActivity, BookCatalogViewActivity.class);
        mBookGongGongIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ftpIntent.putExtra("book_type",1);
                startActivity(ftpIntent);
            }
        });
        mBookDaoLuIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ftpIntent.putExtra("book_type",2);
                startActivity(ftpIntent);
            }
        });
        mBookJiaoTongIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ftpIntent.putExtra("book_type",3);
                startActivity(ftpIntent);
            }
        });
        mBookQiaoLiangIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ftpIntent.putExtra("book_type",4);
                startActivity(ftpIntent);
            }
        });
        final Intent shiJuanIntent=new Intent(mActivity, ShiJuanCatalogViewActivity.class);
        mGongGongIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shiJuanIntent.putExtra("shijuan_kemu",1);
                startActivity(shiJuanIntent);
            }
        });
        mDaoLuIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shiJuanIntent.putExtra("shijuan_kemu",2);
                startActivity(shiJuanIntent);
            }
        });
        mJiaoTongIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shiJuanIntent.putExtra("shijuan_kemu",3);
                startActivity(shiJuanIntent);
            }
        });
        mQiaoLiangIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shiJuanIntent.putExtra("shijuan_kemu",4);
                startActivity(shiJuanIntent);
            }
        });
        mGongGongTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shiJuanIntent.putExtra("shijuan_kemu",1);
                startActivity(shiJuanIntent);
            }
        });
        mDaoLuTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shiJuanIntent.putExtra("shijuan_kemu",2);
                startActivity(shiJuanIntent);
            }
        });
        mJiaoTongTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shiJuanIntent.putExtra("shijuan_kemu",3);
                startActivity(shiJuanIntent);
            }
        });
        mQiaoLiangTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shiJuanIntent.putExtra("shijuan_kemu",4);
                startActivity(shiJuanIntent);
            }
        });
    }

}
