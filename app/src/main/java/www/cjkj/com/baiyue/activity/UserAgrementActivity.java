package www.cjkj.com.baiyue.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import www.cjkj.com.baiyue.R;
import www.cjkj.com.baiyue.view.CommonView;

public class UserAgrementActivity extends Activity {
    private ImageButton mBackIb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_agrement);
        getViews();
        setViews();
        setOnClickListener();
    }

    private void getViews() {
        mBackIb = findViewById(R.id.back);
    }

    private void setViews() {
        setTitle();
    }

    private void setTitle() {
        CommonView mCommonView;
        mCommonView = new CommonView(this);
        mCommonView.setTitle();
    }

    private void setOnClickListener() {
        mBackIb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}