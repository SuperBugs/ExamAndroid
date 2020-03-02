package www.cjkj.com.baiyue.adapter;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import java.util.ArrayList;
import java.util.HashMap;

import www.cjkj.com.baiyue.R;


public class HomeViewPagerAdapter extends PagerAdapter {
    private Activity activity;
    public static int mViewPagerPostion;
    HashMap<String, String> hashMap;

    public HomeViewPagerAdapter(Activity activity) {
        this.activity = activity;
    }
    /**
     * 页面总数
     *
     * @return
     */
    @Override
    public int getCount() {
        return 7;
    }

    //判断要显示的view与返回的object是否相等
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        object = null;
    }

    /**
     * 定义每个pager要显示的内容
     *
     * @param container view的容器，其实就是viewPager自身
     * @param position  相应的图片
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(activity);
        if (position == 0) {
            position = 5;
        }
        if (position == 6) {
            position = 1;
        }
        mViewPagerPostion = position - 1;
        //设置ImageView填充父容器
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setBackgroundResource(R.drawable.two);
        container.addView(imageView);
        return imageView;

    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
