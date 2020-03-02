package www.cjkj.com.baiyue.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import www.cjkj.com.baiyue.R;
import www.cjkj.com.baiyue.activity.ActiveCrouseActivity;
import www.cjkj.com.baiyue.activity.AdviceActivity;
import www.cjkj.com.baiyue.activity.UserAgrementActivity;
import www.cjkj.com.baiyue.update.UpdateApp;


public class SetFragment extends Fragment {
    private View mView;
    private LinearLayout mActiveCrouse;
    private LinearLayout mUserArgument;
    private LinearLayout mCheckUpdata;
    private LinearLayout mAdvice;
    private LinearLayout mShare;
    private Activity mActivity;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.fragment_set, container, false);
        mActivity=getActivity();
        mActiveCrouse=mView.findViewById(R.id.ly_active_crouse);
        mUserArgument=mView.findViewById(R.id.ly_user_argument);
        mCheckUpdata=mView.findViewById(R.id.ly_check_updata);
        mAdvice=mView.findViewById(R.id.ly_advice);
        mShare=mView.findViewById(R.id.ly_share);
        setOnClickListener();
        return mView;
    }
    private void setOnClickListener(){
        mActiveCrouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mActivity, ActiveCrouseActivity.class);
                startActivity(intent);
            }
        });
        mUserArgument.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mActivity, UserAgrementActivity.class);
                startActivity(intent);
            }
        });
        mCheckUpdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //检查更新
                UpdateApp updateApp = new UpdateApp();
                updateApp.cheakApp(getActivity(), true);
            }
        });
        mAdvice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mActivity, AdviceActivity.class);
                startActivity(intent);
            }
        });
        mShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.setType("text/*");
                sendIntent.putExtra(Intent.EXTRA_TEXT, "百跃APP祝您逢考必过，下载链接:http://tuankaly.cn/baiyue.apk");
                startActivity(sendIntent);
            }
        });
    }

}
