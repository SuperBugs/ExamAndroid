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
import www.cjkj.com.baiyue.activity.WrongSubjectsActivity;

import www.cjkj.com.baiyue.common.CommonFunction;



public class WrongSubjectsFragment extends Fragment {
    private View mView;
    private TextView mGongGongJiChu;
    private TextView mDaoLuGongCheng;
    private TextView mQiaoLiangSuiDao;
    private TextView mJiaoTong;
    private Activity mActivity;
    private List<Integer> mWrongArrOne;
    private List<Integer> mWrongArrTwo;
    private List<Integer> mWrongArrThree;
    private List<Integer> mWrongArrFour;
    public static int[] mWrongTiMuIds;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.fragment_wrong_subjects, container, false);
        initObj();
        getViews();
        setViews();
        setOnClickListener();
        return mView;
    }
    public void initObj(){
        mActivity=getActivity();
        mWrongArrOne=(List<Integer>) CommonFunction.getDate( "公共基础wrong", mActivity);
        mWrongArrTwo=(List<Integer>) CommonFunction.getDate( "道路工程wrong", mActivity);
        mWrongArrThree=(List<Integer>) CommonFunction.getDate( "桥梁隧道工程wrong", mActivity);
        mWrongArrFour=(List<Integer>) CommonFunction.getDate( "交通工程wrong", mActivity);
    }
    private void getViews(){
        mGongGongJiChu=mView.findViewById(R.id.tv_collect_one);
        mDaoLuGongCheng=mView.findViewById(R.id.tv_collect_two);
        mQiaoLiangSuiDao=mView.findViewById(R.id.tv_collect_three);
        mJiaoTong=mView.findViewById(R.id.tv_collect_four);
    }
    public void setViews(){
        if(mWrongArrOne!=null){
            mGongGongJiChu.setText("公共基础 答错"+mWrongArrOne.size()+"题");
        }
        if(mWrongArrTwo!=null){
            mDaoLuGongCheng.setText("道路工程 答错"+mWrongArrTwo.size()+"题");
        }
        if(mWrongArrThree!=null){
            mQiaoLiangSuiDao.setText("桥梁隧道工程 答错"+mWrongArrThree.size()+"题");
        }
        if(mWrongArrFour!=null){
            mJiaoTong.setText("交通工程 答错"+mWrongArrFour.size()+"题");
        }
    }
    public void setOnClickListener(){
        if(mWrongArrOne!=null){
            mGongGongJiChu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mWrongTiMuIds=new int[mWrongArrOne.size()];
                    for(int i=0;i<mWrongArrOne.size();i++){
                        mWrongTiMuIds[i]=mWrongArrOne.get(i);
                    }
                    Intent intent=new Intent(mActivity, WrongSubjectsActivity.class);
                    startActivity(intent);
                }
            });
        }
        if(mWrongArrTwo!=null){
            mDaoLuGongCheng.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mWrongTiMuIds=new int[mWrongArrTwo.size()];
                    for(int i=0;i<mWrongArrTwo.size();i++){
                        mWrongTiMuIds[i]=mWrongArrTwo.get(i);
                    }
                    Intent intent=new Intent(mActivity, WrongSubjectsActivity.class);
                    startActivity(intent);
                }
            });
        }
        if(mWrongArrThree!=null){
            mQiaoLiangSuiDao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mWrongTiMuIds=new int[mWrongArrThree.size()];
                    for(int i=0;i<mWrongArrThree.size();i++){
                        mWrongTiMuIds[i]=mWrongArrThree.get(i);
                    }
                    Intent intent=new Intent(mActivity, WrongSubjectsActivity.class);
                    startActivity(intent);
                }
            });
        }
        if(mWrongArrFour!=null){
            mJiaoTong.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mWrongTiMuIds=new int[mWrongArrFour.size()];
                    for(int i=0;i<mWrongArrFour.size();i++){
                        mWrongTiMuIds[i]=mWrongArrFour.get(i);
                    }
                    Intent intent=new Intent(mActivity, WrongSubjectsActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

}
