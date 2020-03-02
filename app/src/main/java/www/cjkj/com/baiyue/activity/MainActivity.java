package www.cjkj.com.baiyue.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import www.cjkj.com.baiyue.R;
import www.cjkj.com.baiyue.common.CommonFunction;
import www.cjkj.com.baiyue.fragment.BookFragment;
import www.cjkj.com.baiyue.fragment.CollectSubjectsFragment;
import www.cjkj.com.baiyue.fragment.MainFragment;
import www.cjkj.com.baiyue.fragment.SetFragment;
import www.cjkj.com.baiyue.fragment.WrongSubjectsFragment;
import www.cjkj.com.baiyue.moudel.TiMuModule;
import www.cjkj.com.baiyue.update.UpdateApp;
import www.cjkj.com.baiyue.view.CommonView;

public class MainActivity extends FragmentActivity {
    private static final String TAG = "MainActivity";
    private Activity mActivity;
    private ImageView mHomeIv;
    private ImageView mWrongIv;
    private ImageView mCollectIv;
    private ImageView mBookIv;
    private ImageView mSetIv;
    private TextView mHomeTv;
    private TextView mWrongTv;
    private TextView mCollectTv;
    private TextView mBookTv;
    private TextView mSetTv;
    private TextView mTitle;
    private MainFragment mMainFragment;
    private WrongSubjectsFragment mWrongFragment;
    private CollectSubjectsFragment mCollectFragment;
    private BookFragment mBookFragment;
    private SetFragment mSetFragment;
    private FragmentManager mFragmentManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActivity=this;
        initObj();
        getViews();
        addClickListener();
        setupViews();
        initApp();
    }
    private void initApp(){
        UpdateApp updateApp = new UpdateApp();
        updateApp.cheakApp(this, false);
    }
    private void initObj(){
        mFragmentManger = getSupportFragmentManager();
        mMainFragment=new MainFragment();
        mWrongFragment=new WrongSubjectsFragment();
        mCollectFragment=new CollectSubjectsFragment();
        mBookFragment=new BookFragment();
        mSetFragment=new SetFragment();
    }
    private void getViews(){
        mHomeTv=findViewById(R.id.tv_home_bottom_home);
        mHomeIv=findViewById(R.id.iv_home_bottom_home);
        mWrongTv=findViewById(R.id.tv_home_bottom_wrong);
        mWrongIv=findViewById(R.id.iv_home_bottom_wrong);
        mCollectTv=findViewById(R.id.tv_home_bottom_collect);
        mCollectIv=findViewById(R.id.iv_home_bottom_collect);
        mBookTv=findViewById(R.id.tv_home_bottom_book);
        mBookIv=findViewById(R.id.iv_home_bottom_book);
        mSetTv=findViewById(R.id.tv_home_bottom_set);
        mSetIv=findViewById(R.id.iv_home_bottom_set);
        mTitle=findViewById(R.id.title);
    }
    private void addClickListener(){
        mHomeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTitle.setText(mActivity.getResources().getString(R.string.main_title));
                replaceMainFragment();
            }
        });
        mHomeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTitle.setText(mActivity.getResources().getString(R.string.main_title));
                replaceMainFragment();
            }
        });
        mWrongIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTitle.setText(mActivity.getResources().getString(R.string.ac_main_bottom_two));
                replaceWrongFragment();
            }
        });
        mWrongTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTitle.setText(mActivity.getResources().getString(R.string.ac_main_bottom_two));
                replaceWrongFragment();
            }
        });
        mCollectIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTitle.setText(mActivity.getResources().getString(R.string.ac_main_bottom_three));
                replaceCollectFragment();
            }
        });
        mCollectTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTitle.setText(mActivity.getResources().getString(R.string.ac_main_bottom_three));
                replaceCollectFragment();
            }
        });
        mBookIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTitle.setText(mActivity.getResources().getString(R.string.ac_main_bottom_four));
                replaceBookFragment();
            }
        });
        mBookTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTitle.setText(mActivity.getResources().getString(R.string.ac_main_bottom_four));
                replaceBookFragment();
            }
        });
        mSetIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTitle.setText(mActivity.getResources().getString(R.string.ac_main_bottom_five));
                replaceSetFragment();
            }
        });
        mSetTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTitle.setText(mActivity.getResources().getString(R.string.ac_main_bottom_five));
                replaceSetFragment();
            }
        });
    }
    private void setupViews(){
        CommonView mCommonView;
        mCommonView=new CommonView(mActivity);
        mCommonView.setTitle();
        addFragments();
    }
    private void addFragments() {
        FragmentTransaction fragmentTransaction = mFragmentManger.beginTransaction();
        fragmentTransaction
                .add(R.id.fragment_container, mMainFragment)
                .add(R.id.fragment_container, mWrongFragment)
                .add(R.id.fragment_container, mCollectFragment)
                .add(R.id.fragment_container, mBookFragment)
                .add(R.id.fragment_container, mSetFragment)
                .hide(mWrongFragment)
                .hide(mCollectFragment)
                .hide(mBookFragment)
                .hide(mSetFragment)
                .show(mMainFragment).commit();
        mHomeIv.setBackgroundResource(R.drawable.homepress);
        mHomeTv.setTextColor(mActivity.getResources().getColor(R.color.ac_main_bottom_tv_press));
        mWrongIv.setBackgroundResource(R.drawable.wrong_subjects_lift);
        mWrongTv.setTextColor(mActivity.getResources().getColor(R.color.ac_main_bottom_tv_lift));
        mCollectIv.setBackgroundResource(R.drawable.collect_subjects_lift);
        mCollectTv.setTextColor(mActivity.getResources().getColor(R.color.ac_main_bottom_tv_lift));
        mBookIv.setBackgroundResource(R.drawable.book_lift);
        mBookTv.setTextColor(mActivity.getResources().getColor(R.color.ac_main_bottom_tv_lift));
        mSetIv.setBackgroundResource(R.drawable.setlift);
        mSetTv.setTextColor(mActivity.getResources().getColor(R.color.ac_main_bottom_tv_lift));
    }
    private void replaceMainFragment() {
        FragmentTransaction fragmentTransaction = mFragmentManger.beginTransaction();
        fragmentTransaction
                .show(mMainFragment)
                .hide(mWrongFragment)
                .hide(mCollectFragment)
                .hide(mBookFragment)
                .hide(mSetFragment)
                .commit();
        mHomeIv.setBackgroundResource(R.drawable.homepress);
        mHomeTv.setTextColor(mActivity.getResources().getColor(R.color.ac_main_bottom_tv_press));
        mWrongIv.setBackgroundResource(R.drawable.wrong_subjects_lift);
        mWrongTv.setTextColor(mActivity.getResources().getColor(R.color.ac_main_bottom_tv_lift));
        mCollectIv.setBackgroundResource(R.drawable.collect_subjects_lift);
        mCollectTv.setTextColor(mActivity.getResources().getColor(R.color.ac_main_bottom_tv_lift));
        mBookIv.setBackgroundResource(R.drawable.book_lift);
        mBookTv.setTextColor(mActivity.getResources().getColor(R.color.ac_main_bottom_tv_lift));
        mSetIv.setBackgroundResource(R.drawable.setlift);
        mSetTv.setTextColor(mActivity.getResources().getColor(R.color.ac_main_bottom_tv_lift));
    }

    public void replaceBookFragment() {
        FragmentTransaction fragmentTransaction = mFragmentManger.beginTransaction();
        fragmentTransaction
                .show(mBookFragment)
                .hide(mMainFragment)
                .hide(mCollectFragment)
                .hide(mWrongFragment)
                .hide(mSetFragment)
                .commit();
        mHomeIv.setBackgroundResource(R.drawable.homelift);
        mHomeTv.setTextColor(mActivity.getResources().getColor(R.color.ac_main_bottom_tv_lift));
        mWrongIv.setBackgroundResource(R.drawable.wrong_subjects_lift);
        mWrongTv.setTextColor(mActivity.getResources().getColor(R.color.ac_main_bottom_tv_lift));
        mCollectIv.setBackgroundResource(R.drawable.collect_subjects_lift);
        mCollectTv.setTextColor(mActivity.getResources().getColor(R.color.ac_main_bottom_tv_lift));
        mBookIv.setBackgroundResource(R.drawable.book_press);
        mBookTv.setTextColor(mActivity.getResources().getColor(R.color.ac_main_bottom_tv_press));
        mSetIv.setBackgroundResource(R.drawable.setlift);
        mSetTv.setTextColor(mActivity.getResources().getColor(R.color.ac_main_bottom_tv_lift));

    }

    private void replaceWrongFragment() {
        mWrongFragment.initObj();
        mWrongFragment.setViews();
        mWrongFragment.setOnClickListener();
        FragmentTransaction fragmentTransaction = mFragmentManger.beginTransaction();
        fragmentTransaction
                .hide(mMainFragment)
                .hide(mBookFragment)
                .hide(mCollectFragment)
                .hide(mSetFragment)
                .show(mWrongFragment)
                .commit();
        mHomeIv.setBackgroundResource(R.drawable.homelift);
        mHomeTv.setTextColor(mActivity.getResources().getColor(R.color.ac_main_bottom_tv_lift));
        mWrongIv.setBackgroundResource(R.drawable.wrong_subjects_press);
        mWrongTv.setTextColor(mActivity.getResources().getColor(R.color.ac_main_bottom_tv_press));
        mCollectIv.setBackgroundResource(R.drawable.collect_subjects_lift);
        mCollectTv.setTextColor(mActivity.getResources().getColor(R.color.ac_main_bottom_tv_lift));
        mBookIv.setBackgroundResource(R.drawable.book_lift);
        mBookTv.setTextColor(mActivity.getResources().getColor(R.color.ac_main_bottom_tv_lift));
        mSetIv.setBackgroundResource(R.drawable.setlift);
        mSetTv.setTextColor(mActivity.getResources().getColor(R.color.ac_main_bottom_tv_lift));
    }

    private void replaceCollectFragment() {
        mCollectFragment.initObj();
        mCollectFragment.setViews();
        mCollectFragment.setOnClickListener();
        FragmentTransaction fragmentTransaction = mFragmentManger.beginTransaction();
        fragmentTransaction
                .hide(mMainFragment)
                .hide(mBookFragment)
                .hide(mWrongFragment)
                .hide(mSetFragment)
                .show(mCollectFragment).commit();
        mHomeIv.setBackgroundResource(R.drawable.homelift);
        mHomeTv.setTextColor(mActivity.getResources().getColor(R.color.ac_main_bottom_tv_lift));
        mWrongIv.setBackgroundResource(R.drawable.wrong_subjects_lift);
        mWrongTv.setTextColor(mActivity.getResources().getColor(R.color.ac_main_bottom_tv_lift));
        mCollectIv.setBackgroundResource(R.drawable.collect_subjects_press);
        mCollectTv.setTextColor(mActivity.getResources().getColor(R.color.ac_main_bottom_tv_press));
        mBookIv.setBackgroundResource(R.drawable.book_lift);
        mBookTv.setTextColor(mActivity.getResources().getColor(R.color.ac_main_bottom_tv_lift));
        mSetIv.setBackgroundResource(R.drawable.setlift);
        mSetTv.setTextColor(mActivity.getResources().getColor(R.color.ac_main_bottom_tv_lift));
    }


    private void replaceSetFragment() {
        FragmentTransaction fragmentTransaction = mFragmentManger.beginTransaction();
        fragmentTransaction
                .hide(mMainFragment)
                .hide(mWrongFragment)
                .hide(mBookFragment)
                .hide(mCollectFragment)
                .show(mSetFragment).commit();
        mHomeIv.setBackgroundResource(R.drawable.homelift);
        mHomeTv.setTextColor(mActivity.getResources().getColor(R.color.ac_main_bottom_tv_lift));
        mWrongIv.setBackgroundResource(R.drawable.wrong_subjects_lift);
        mWrongTv.setTextColor(mActivity.getResources().getColor(R.color.ac_main_bottom_tv_lift));
        mCollectIv.setBackgroundResource(R.drawable.collect_subjects_lift);
        mCollectTv.setTextColor(mActivity.getResources().getColor(R.color.ac_main_bottom_tv_lift));
        mBookIv.setBackgroundResource(R.drawable.book_lift);
        mBookTv.setTextColor(mActivity.getResources().getColor(R.color.ac_main_bottom_tv_lift));
        mSetIv.setBackgroundResource(R.drawable.setpress);
        mSetTv.setTextColor(mActivity.getResources().getColor(R.color.ac_main_bottom_tv_press));
    }
}
