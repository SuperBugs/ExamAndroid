package www.cjkj.com.baiyue.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import www.cjkj.com.baiyue.R;
import www.cjkj.com.baiyue.activity.CollectSubjectsActivity;

import www.cjkj.com.baiyue.common.CommonFunction;



public class CollectSubjectsFragment extends Fragment {
    private View mView;
    private TextView mGongGongJiChu;
    private TextView mDaoLuGongCheng;
    private TextView mQiaoLiangSuiDao;
    private TextView mJiaoTong;
    private Activity mActivity;
    private List<Integer> mCollectArrOne;
    private List<Integer> mCollectArrTwo;
    private List<Integer> mCollectArrThree;
    private List<Integer> mCollectArrFour;
    public static int[] mCollectTiMuIds;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.fragment_collect, container, false);
        initObj();
        getViews();
        setViews();
        setOnClickListener();
        return mView;
    }
    public void initObj(){
        mActivity=getActivity();
        mCollectArrOne=(List<Integer>) CommonFunction.getDate( "公共基础collect", mActivity);
        mCollectArrTwo=(List<Integer>) CommonFunction.getDate( "道路工程collect", mActivity);
        mCollectArrThree=(List<Integer>) CommonFunction.getDate( "桥梁隧道工程collect", mActivity);
        mCollectArrFour=(List<Integer>) CommonFunction.getDate( "交通工程collect", mActivity);
    }
    private void getViews(){
        mGongGongJiChu=mView.findViewById(R.id.tv_collect_one);
        mDaoLuGongCheng=mView.findViewById(R.id.tv_collect_two);
        mQiaoLiangSuiDao=mView.findViewById(R.id.tv_collect_three);
        mJiaoTong=mView.findViewById(R.id.tv_collect_four);
    }
    public void setViews(){
        if(mCollectArrOne!=null){
            mGongGongJiChu.setText("公共基础 已收藏"+mCollectArrOne.size()+"题");
        }
        if(mCollectArrTwo!=null){
            mDaoLuGongCheng.setText("道路工程 已收藏"+mCollectArrTwo.size()+"题");
        }
        if(mCollectArrThree!=null){
            mQiaoLiangSuiDao.setText("桥梁隧道工程 已收藏"+mCollectArrThree.size()+"题");
        }
        if(mCollectArrFour!=null){
            mJiaoTong.setText("交通工程 已收藏"+mCollectArrFour.size()+"题");
        }
    }
    public void setOnClickListener(){
        if(mCollectArrOne!=null){
            mGongGongJiChu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCollectTiMuIds=new int[mCollectArrOne.size()];
                    for(int i=0;i<mCollectArrOne.size();i++){
                        mCollectTiMuIds[i]=mCollectArrOne.get(i);
                    }
                    Intent intent=new Intent(mActivity, CollectSubjectsActivity.class);
                    startActivity(intent);
                }
            });
        }
        if(mCollectArrTwo!=null){
            mDaoLuGongCheng.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCollectTiMuIds=new int[mCollectArrTwo.size()];
                    for(int i=0;i<mCollectArrTwo.size();i++){
                        mCollectTiMuIds[i]=mCollectArrTwo.get(i);
                    }
                    Intent intent=new Intent(mActivity, CollectSubjectsActivity.class);
                    startActivity(intent);
                }
            });
        }
        if(mCollectArrThree!=null){
            mQiaoLiangSuiDao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCollectTiMuIds=new int[mCollectArrThree.size()];
                    for(int i=0;i<mCollectArrThree.size();i++){
                        mCollectTiMuIds[i]=mCollectArrThree.get(i);
                    }
                    Intent intent=new Intent(mActivity, CollectSubjectsActivity.class);
                    startActivity(intent);
                }
            });
        }
        if(mCollectArrFour!=null){
            mJiaoTong.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCollectTiMuIds=new int[mCollectArrFour.size()];
                    for(int i=0;i<mCollectArrFour.size();i++){
                        mCollectTiMuIds[i]=mCollectArrFour.get(i);
                    }
                    Intent intent=new Intent(mActivity, CollectSubjectsActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

}
