package www.cjkj.com.baiyue.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PracticeViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> list;

    public PracticeViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public PracticeViewPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int postion) {
        return list.get(postion);
    }

    @Override
    public int getCount() {
        return list.size();
    }

}