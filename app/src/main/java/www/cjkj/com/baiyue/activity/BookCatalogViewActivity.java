package www.cjkj.com.baiyue.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import www.cjkj.com.baiyue.R;
import www.cjkj.com.baiyue.datas.BookDatas;
import www.cjkj.com.baiyue.view.CommonView;

public class BookCatalogViewActivity extends Activity implements AdapterView.OnItemClickListener{
    private Intent mDataIntent;
    private TextView mTitle;
    private String mBookName;
    private Activity mActivity;
    private ImageButton mBackIb;
    private ListView mListView;
    private LinearLayout mTitleLy;
    int book_type;
    private static final String TAG = "BookCatalogViewActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_catalog_view);
        mActivity=this;
        getViewDatas();
        getViews();
        setViews();
        setOnClickListener();
    }
    private void getViewDatas(){
        mDataIntent=getIntent();
    }
    private void getViews(){
        mTitle=findViewById(R.id.titles);
        mBackIb=findViewById(R.id.back);
        mListView=findViewById(R.id.lv_book_catalog_list_view);
        mTitleLy=findViewById(R.id.title);
    }
    private void setViews(){
        setTitle();
        setListView();
    }
    private void setListView(){
        mListView.setAdapter(new ArrayAdapter<String>(this, R.layout.book_catalog_list_item,getData()));
        mListView.setOnItemClickListener(this);
    }
    private List<String> getData(){

        List<String> data = new ArrayList<String>();
        for(int i=0;i< BookDatas.mGongGongJiChuCatalogArr.length;i++){
            data.add(BookDatas.mGongGongJiChuCatalogArr[i]);
        }
        return data;
    }
    private void setTitle(){
        CommonView mCommonView;
        mCommonView=new CommonView(mActivity);
        mCommonView.setTitle();
        book_type=mDataIntent.getIntExtra("book_type",1);
        switch(book_type)
        {
            case 1:
                mBookName="jichu.pdf";
                mTitle.setText("《公共基础》");
                break;
            case 2:
                mBookName="jichu.pdf";
                mTitle.setText("《道路工程》");
                break;
            case 3:
                mBookName="jichu.pdf";
                mTitle.setText("《交通工程》");
                break;
            case 4:
                mBookName="jichu.pdf";
                mTitle.setText("《桥梁隧道》");
                break;
            default:
                break;
        }
    }
    private void setOnClickListener(){
        mBackIb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent ptfIntent = new Intent(mActivity, PDFViewsActivity.class);
        ptfIntent.putExtra("book_type",book_type);
        ptfIntent.putExtra("book_start_page",BookDatas.mBookGongGongJiChuStartPageArr[i]-1);
        startActivity(ptfIntent);
    }
}
