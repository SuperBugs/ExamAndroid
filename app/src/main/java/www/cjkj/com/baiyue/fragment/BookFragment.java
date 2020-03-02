package www.cjkj.com.baiyue.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import www.cjkj.com.baiyue.R;
import www.cjkj.com.baiyue.activity.BookCatalogViewActivity;


public class BookFragment extends Fragment {
    private View mMainView;
    private Activity mActivity;
    private ImageView mBookGongGongIv;
    private ImageView mBookDaoLuIv;
    private ImageView mBookJiaoTongIv;
    private ImageView mBookQiaoLiangIv;
    private int mScreamWidth;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mActivity=getActivity();
        mMainView=inflater.inflate(R.layout.fragment_book, container, false);
        WindowManager wm = (WindowManager) mActivity
                .getSystemService(Context.WINDOW_SERVICE);
        assert wm != null;
        mScreamWidth = wm.getDefaultDisplay().getWidth();
        mBookDaoLuIv=mMainView.findViewById(R.id.iv_home_bottom_dao_lu);
        mBookGongGongIv=mMainView.findViewById(R.id.iv_home_bottom_gong_gong);
        mBookJiaoTongIv=mMainView.findViewById(R.id.iv_home_bottom_jiao_tong);
        mBookQiaoLiangIv=mMainView.findViewById(R.id.iv_home_bottom_qiao_liang);
        setBottomViews();
        setOnClickListener();
        return  mMainView;
    }
    private void setBottomViews(){
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(mScreamWidth/20*4/3, 0, 0, 0);
        lp.height = mScreamWidth/20*8*180/132;
        lp.width = mScreamWidth/20*8;
        mBookGongGongIv.setLayoutParams(lp);
        LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp1.setMargins(mScreamWidth/20*4/3, 0, 0, 0);
        lp1.height = mScreamWidth/20*8*180/132;
        lp1.width = mScreamWidth/20*8;
        mBookDaoLuIv.setLayoutParams(lp1);
        LinearLayout.LayoutParams lp3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp3.setMargins(mScreamWidth/20*4/3, 0, 0, 0);
        lp3.height = mScreamWidth/20*8*180/132;
        lp3.width = mScreamWidth/20*8;
        mBookJiaoTongIv.setLayoutParams(lp3);
        LinearLayout.LayoutParams lp4 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp4.setMargins(mScreamWidth/20*4/3, 0, 0, 0);
        lp4.height = mScreamWidth/20*8*180/132;
        lp4.width = mScreamWidth/20*8;
        mBookQiaoLiangIv.setLayoutParams(lp4);
    }
    private void setOnClickListener(){
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
    }
}
