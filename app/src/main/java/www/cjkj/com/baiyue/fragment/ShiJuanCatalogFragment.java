package www.cjkj.com.baiyue.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import www.cjkj.com.baiyue.R;
import www.cjkj.com.baiyue.activity.PDFViewsActivity;
import www.cjkj.com.baiyue.activity.ShiJuanCatalogViewActivity;
import www.cjkj.com.baiyue.activity.ShiJuanDetailesActivity;
import www.cjkj.com.baiyue.datas.BookDatas;
import www.cjkj.com.baiyue.moudel.ShiJuanModule;
import www.cjkj.com.baiyue.view.CommonView;


public class ShiJuanCatalogFragment extends Fragment implements AdapterView.OnItemClickListener {
    public static ShiJuanModule mShiJuanModule;
    private Cursor mCursor;
    private int mShiJuanKeMu;
    private int mShiJuanType;
    private View mMainView;
    private ListView mListView;
    public static Cursor gShiJuanCursor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mMainView = inflater.inflate(R.layout.fragment_shijuan_catalog, container, false);
        initObj();
        getViewsData();
        getViews();
        setViews();
        return mMainView;
    }

    private void initObj() {
        mShiJuanModule = new ShiJuanModule();
    }

    private void getViewsData() {
        mCursor = mShiJuanModule.getShiJuanTableViewData(mShiJuanType, mShiJuanKeMu);
        gShiJuanCursor=mCursor;
    }

    private void getViews() {
        mListView = mMainView.findViewById(R.id.lv_shijuan_catalog_list_view);
    }

    public void setViews() {
        setListView();
    }

    private void setListView() {
        mListView.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.shijuan_catalog_list_item, getData()));
        mListView.setOnItemClickListener(this);
    }

    private List<String> getData() {
        List<String> data = new ArrayList<String>();
        if (mCursor.moveToFirst()) {
            do {
                data.add(mCursor.getString(mCursor.getColumnIndex("sjname")));
            } while (mCursor.moveToNext());
        }
        return data;
    }

    public void setShiJuanData(int shiJuanKeMu, int shiJuanType) {
        mShiJuanKeMu = shiJuanKeMu;
        mShiJuanType = shiJuanType;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent sjIntent = new Intent(getActivity(), ShiJuanDetailesActivity.class);
        mCursor = mShiJuanModule.getShiJuanTableViewData(mShiJuanType, mShiJuanKeMu);
        gShiJuanCursor=mCursor;
        gShiJuanCursor.moveToPosition(i);
        startActivity(sjIntent);
    }
}
