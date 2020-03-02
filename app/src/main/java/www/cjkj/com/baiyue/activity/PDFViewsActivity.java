package www.cjkj.com.baiyue.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnDrawListener;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.shockwave.pdfium.PdfDocument;

import java.util.List;

import www.cjkj.com.baiyue.R;
import www.cjkj.com.baiyue.view.CommonView;

public class PDFViewsActivity extends Activity implements OnPageChangeListener
        , OnLoadCompleteListener, OnDrawListener {
    private static final String TAG = "PDFViewsActivity";
    private PDFView mPDFView;
    private Intent mDataIntent;
    private String mBookName;
    private TextView mTitle;
    private int mStartPage;
    private Activity mActivity;
    private ImageButton mBackIb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfviews);
        mActivity=this;
        getDatas();
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
    private void getDatas() {
        mDataIntent=getIntent();
        mStartPage=mDataIntent.getIntExtra("book_start_page",0);
    }

    private void getViews() {
        mPDFView = findViewById(R.id.pdfView);
        mTitle=findViewById(R.id.title);
        mBackIb=findViewById(R.id.back);
    }

    private void setViews() {
        setTitle();
        mPDFView.fromAsset(mBookName)
                .defaultPage(mStartPage)
                .onPageChange(this)
                .swipeHorizontal(false)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .load();

        Toast.makeText(this,""+mStartPage,Toast.LENGTH_LONG);
    }
    private void setTitle(){
        CommonView mCommonView;
        mCommonView=new CommonView(mActivity);
        mCommonView.setTitle();
        int book_type=mDataIntent.getIntExtra("book_type",1);
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

    @Override
    public void loadComplete(int nbPages) {

    }

    @Override
    public void onPageChanged(int page, int pageCount) {

    }

    @Override
    public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {

    }
}
