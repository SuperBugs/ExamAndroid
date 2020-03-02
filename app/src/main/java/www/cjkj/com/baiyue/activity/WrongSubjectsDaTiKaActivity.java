package www.cjkj.com.baiyue.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import www.cjkj.com.baiyue.R;
import www.cjkj.com.baiyue.common.CommonFunction;
import www.cjkj.com.baiyue.view.CommonView;

import static www.cjkj.com.baiyue.activity.WrongSubjectsActivity.mTiMuCursorArr;


public class WrongSubjectsDaTiKaActivity extends Activity {
    private static final String TAG = "WrongSubjectsDaTiKaActivity";
    private int mNowHeight;
    private int mNowWidth;
    private int mScreamWidth;
    private CommonFunction mCommonFunction;
    private ImageButton mBack;
    private int mIndex;
    private int mTiMuNum = 0;
    private TextView mTitleOne;
    private TextView mTitleTwo;
    private TextView mTitleThree;
    private GridLayout mItemOneGl;
    private GridLayout mItemTwoGl;
    private GridLayout mItemThreeGl;
    private int mDuanXuanCount = 0;
    private int mPanDuanCount = 0;
    private int mDuoXuanCount = 0;
    private int mIdsLength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_da_ti_ka);
        initObj();
        getViews();
        setViews();
        setClickListener();
    }

    private void setViews() {
        setTitle();
        mNowHeight = 1;//行
        mNowWidth = 1;//列
        for (int index = 0; index < mIdsLength; index++) {
            if (mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("class_timu")).equals("单选题")) {
                mDuanXuanCount++;
            }
            if (mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("class_timu")).equals("判断题")) {
                mPanDuanCount++;
            }
            if (mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("class_timu")).equals("多选题")) {
                mDuoXuanCount++;
            }
        }
        mItemOneGl.setRowCount(mDuanXuanCount / 5 + 2);
        mItemOneGl.setColumnCount(6);
        mItemTwoGl.setRowCount(mPanDuanCount / 5 + 2);
        mItemTwoGl.setColumnCount(6);
        mItemThreeGl.setRowCount(mDuoXuanCount / 5 + 2);
        mItemThreeGl.setColumnCount(6);
        for (int index = 0; index < mIdsLength; index++) {
            if (mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("class_timu")).equals("单选题")) {
                mIndex = index;
                addBoxView(mNowHeight, mNowWidth, 1);
            }
        }
        mNowHeight = 1;//行
        mNowWidth = 1;//列
        for (int index = 0; index < mIdsLength; index++) {
            if (mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("class_timu")).equals("判断题")) {
                mIndex = index;
                addBoxView(mNowHeight, mNowWidth, 2);
            }
        }
        mNowHeight = 1;//行
        mNowWidth = 1;//列
        for (int index = 0; index < mIdsLength; index++) {
            if (mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("class_timu")).equals("多选题")) {
                mIndex = index;
                addBoxView(mNowHeight, mNowWidth, 3);
            }
        }
    }

    private void addBoxView(int i, int j, int gl) {
        mTiMuNum = mTiMuNum + 1;
        if (mNowWidth == 5) {
            mNowHeight++;
            mNowWidth = 0;
        }
        final TextView textView = new TextView(this);
        textView.setGravity(Gravity.CENTER);
        textView.setBackgroundColor(getResources().getColor(R.color.height_gray));
        textView.setText(mTiMuNum + "");
        textView.setBackgroundResource(R.drawable.text_view_border_gray);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.putExtra("page",Integer.parseInt(textView.getText().toString())-1);
                setResult(0,intent);
                finish();
            }
        });
        GridLayout.Spec rowSpec = GridLayout.spec(i);     //设置它的行和列
        GridLayout.Spec columnSpec = GridLayout.spec(j);
        GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, columnSpec);
        params.height = mScreamWidth / 10;
        params.width = mScreamWidth / 10;
        params.setMargins(mScreamWidth / 20, 10, mScreamWidth / 20, 10);
        params.setGravity(Gravity.LEFT);
        if (gl == 1) {
            mItemOneGl.addView(textView, params);
        }
        if (gl == 2) {
            mItemTwoGl.addView(textView, params);
        }
        if (gl == 3) {
            mItemThreeGl.addView(textView, params);
        }
        mNowWidth++;
    }

    private void initObj() {
        mCommonFunction = new CommonFunction();
        WindowManager wm = (WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE);
        assert wm != null;
        mScreamWidth = wm.getDefaultDisplay().getWidth();
        mIdsLength = WrongSubjectsActivity.mTiMuIds.length;
    }

    private void getViews() {
        mItemOneGl = findViewById(R.id.gl_item_one);
        mItemTwoGl = findViewById(R.id.gl_item_two);
        mItemThreeGl = findViewById(R.id.gl_item_three);
        mBack = findViewById(R.id.back);
        mTitleOne = findViewById(R.id.tv_title_one);
        mTitleTwo = findViewById(R.id.tv_title_two);
        mTitleThree = findViewById(R.id.tv_title_three);
    }

    private void setTitle() {
        CommonView mCommonView;
        mCommonView = new CommonView(this);
        mCommonView.setTitle();
    }

    private void initEvents(final TextView tv) {
        tv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(WrongSubjectsDaTiKaActivity.this, tv.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setClickListener() {
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.putExtra("page",0);
                setResult(1,intent);
                finish();
            }
        });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent intent=new Intent();
            intent.putExtra("page",0);
            setResult(1,intent);
            finish();
            return false;
        }else {
            return super.onKeyDown(keyCode, event);
        }

    }

}
