package www.cjkj.com.baiyue.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import www.cjkj.com.baiyue.R;
import www.cjkj.com.baiyue.activity.ExamActivity;
import www.cjkj.com.baiyue.common.CommonFunction;

import static www.cjkj.com.baiyue.activity.ExamActivity.mTiMuCursorArr;
import static www.cjkj.com.baiyue.activity.ExamActivity.mTiMuIds;
import static www.cjkj.com.baiyue.activity.ExamActivity.mTiMuModule;


public class ExamDuoXuanTiFragment extends Fragment {
    private int index;
    private View ly;
    private int mPage;
    private ExamActivity examActivity;
    private CheckBox radioButtonA;
    private CheckBox radioButtonB;
    private CheckBox radioButtonC;
    private CheckBox radioButtonD;
    private CheckBox radioButtonE;
    private CheckBox radioButtonF;
    private static final String TAG = "ExamDuoXuanTiFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ly = inflater.inflate(R.layout.fragment_practice_duo_xuan_ti, container, false);
        examActivity = (ExamActivity) getActivity();
        setTitle();
        if (mTiMuCursorArr[index].getInt(mTiMuCursorArr[index].getColumnIndex("sc_state")) == 1) {
            Log.d(TAG, "测试"+"yes");
            setDidView();
        } else {
            Log.d(TAG, "测试"+"no");
            addViewPagerSonView();
        }
        return ly;
    }

    private void setTitle() {
        String tiMuType = mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("class_timu"));
        if (tiMuType.equals("单选题")) {
            examActivity.setTiMuTitle("单选题");
        }
        if (tiMuType.equals("判断题")) {
            examActivity.setTiMuTitle("判断题");
        }
        if (tiMuType.equals("多选题")) {
            examActivity.setTiMuTitle("多选题");
        }
    }

    private void addViewPagerSonView() {
        TextView tiMuTv = ly.findViewById(R.id.tv_practice_duoxuan_timu);
        mTiMuCursorArr[index].moveToFirst();
        tiMuTv.setText(mPage + "." + mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("timu")));
        String userDaAn = ExamActivity.mTiMuCursorArr[index].getString(ExamActivity.mTiMuCursorArr[index].getColumnIndex("userdaan_ks"));
        RadioGroup radioGroup = ly.findViewById(R.id.rg_practice_answer);
        radioButtonA = ly.findViewById(R.id.cb_practice_answer_a);
        radioButtonA.setText(mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("xuanzeA")));
        radioButtonA.setButtonDrawable(R.drawable.abc_btn_check_material);
        radioButtonB = ly.findViewById(R.id.cb_practice_answer_b);
        radioButtonB.setText(mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("xuanzeB")));
        radioButtonB.setButtonDrawable(R.drawable.abc_btn_check_material);
        radioButtonC = ly.findViewById(R.id.cb_practice_answer_c);
        radioButtonC.setText(mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("xuanzeC")));
        radioButtonC.setButtonDrawable(R.drawable.abc_btn_check_material);
        radioButtonD = ly.findViewById(R.id.cb_practice_answer_d);
        radioButtonD.setText(mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("xuanzeD")));
        radioButtonD.setButtonDrawable(R.drawable.abc_btn_check_material);
        radioButtonE = ly.findViewById(R.id.cb_practice_answer_e);
        if (mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("xuanzeE")) != null) {
            radioButtonE.setText(mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("xuanzeE")));
            radioButtonE.setButtonDrawable(R.drawable.abc_btn_check_material);
            radioButtonE.setVisibility(View.VISIBLE);
            setAnswerListener(radioButtonE);
        }
        radioButtonF = ly.findViewById(R.id.cb_practice_answer_f);
        if (mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("xuanzeF")) != null) {
            radioButtonF.setText(mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("xuanzeF")));
            radioButtonF.setButtonDrawable(R.drawable.abc_btn_check_material);
            radioButtonF.setVisibility(View.VISIBLE);
            setAnswerListener(radioButtonF);
        }
        setAnswerListener(radioButtonA);
        setAnswerListener(radioButtonB);
        setAnswerListener(radioButtonC);
        setAnswerListener(radioButtonD);
        View up = ly.findViewById(R.id.ly_bottom_up);
        View check = ly.findViewById(R.id.ly_bottom_check);
        View collect = ly.findViewById(R.id.ly_bottom_collect);
        Button submit=ly.findViewById(R.id.btn_shijuan_pratice);
        check.setVisibility(View.GONE);
        collect.setVisibility(View.GONE);
        View next = ly.findViewById(R.id.ly_bottom_next);
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "提示up");
                examActivity.setUpPage(mPage);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                examActivity.setNextPage(mPage);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "提示点击");
                examActivity.submitShiJuan();
            }
        });
    }

    private void check() {
        String answer = "";
        CheckBox radioButtonA = ly.findViewById(R.id.cb_practice_answer_a);
        CheckBox radioButtonB = ly.findViewById(R.id.cb_practice_answer_b);
        CheckBox radioButtonC = ly.findViewById(R.id.cb_practice_answer_c);
        CheckBox radioButtonD = ly.findViewById(R.id.cb_practice_answer_d);
        RadioGroup radioGroup = ly.findViewById(R.id.rg_practice_answer);
        disableRadioGroup(radioGroup);
        if (radioButtonA.isChecked()) {
            answer = answer + "A";
        }
        if (radioButtonB.isChecked()) {
            answer = answer + "B";
        }
        if (radioButtonC.isChecked()) {
            answer = answer + "C";
        }
        if (radioButtonD.isChecked()) {
            answer = answer + "D";
        }
        if (mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("xuanzeE")) != null) {
            CheckBox radioButtonE = ly.findViewById(R.id.cb_practice_answer_e);
            radioButtonE.setText(mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("xuanzeE")));
            radioButtonE.setButtonDrawable(R.drawable.abc_btn_check_material);
            radioButtonE.setVisibility(View.VISIBLE);
            if (radioButtonE.isChecked()) {
                answer = answer + "E";
            }
        }
        if (mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("xuanzeF")) != null) {
            CheckBox radioButtonF = ly.findViewById(R.id.cb_practice_answer_f);
            radioButtonF.setText(mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("xuanzeF")));
            radioButtonF.setButtonDrawable(R.drawable.abc_btn_check_material);
            radioButtonF.setVisibility(View.VISIBLE);
            if (radioButtonF.isChecked()) {
                answer = answer + "F";
            }
        }
        String result = "1";
        if (mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("daan")).equals(answer)) {
            result = "0";
        }
        mTiMuModule.setTiMuLianXi(ExamActivity.mTiMuIds[index], answer, "1", result);
        ExamActivity.changeTiMuCursorArrData(index);
        View ry = ly.findViewById(R.id.rl_answer_result);
        ry.setVisibility(View.VISIBLE);
        View answerStatusIv = ry.findViewById(R.id.iv_practice_answer_status);
        TextView answerStatusTv = ry.findViewById(R.id.tv_practice_answer_status);
        TextView rightAnswerTv = ry.findViewById(R.id.iv_practice_right_answer);
        TextView tiMuJieXiTitleTv = ly.findViewById(R.id.tv_jiexi);
        TextView tiMuJieXiTv = ly.findViewById(R.id.iv_practice_answer_analyze);
        if (result.equals("0")) {
            answerStatusTv.setText("答对了");
            answerStatusIv.setBackgroundResource(R.drawable.right40);
        } else {
            answerStatusTv.setText("答错了");
            answerStatusIv.setBackgroundResource(R.drawable.wrong40);
        }
        rightAnswerTv.setText(mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("daan")));
        tiMuJieXiTitleTv.setVisibility(View.VISIBLE);
        tiMuJieXiTv.setVisibility(View.VISIBLE);
        if (mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("jiexi"))!=null) {
            tiMuJieXiTv.setText(mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("jiexi")));
        } else {
            tiMuJieXiTv.setText("暂无解析");
        }
        Log.d(TAG, "index" + mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("userdaan_ks")));
    }

    public void setSubjectsIndex(int index, int page) {
        this.index = index;
        this.mPage = page;
    }

    public void disableRadioGroup(RadioGroup testRadioGroup) {
        for (int i = 0; i < testRadioGroup.getChildCount(); i++) {
            testRadioGroup.getChildAt(i).setEnabled(false);
        }
    }

    private void setDidView() {
        TextView tiMuTv = ly.findViewById(R.id.tv_practice_duoxuan_timu);
        mTiMuCursorArr[index].moveToFirst();
        tiMuTv.setText(mPage + "." + mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("timu")));
        String userDaAn = mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("userdaan_ks"));
        RadioGroup radioGroup = ly.findViewById(R.id.rg_practice_answer);
        radioButtonA = ly.findViewById(R.id.cb_practice_answer_a);
        radioButtonA.setText(mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("xuanzeA")));
        radioButtonA.setButtonDrawable(R.drawable.abc_btn_check_material);
        radioButtonB = ly.findViewById(R.id.cb_practice_answer_b);
        radioButtonB.setText(mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("xuanzeB")));
        radioButtonB.setButtonDrawable(R.drawable.abc_btn_check_material);
        radioButtonC = ly.findViewById(R.id.cb_practice_answer_c);
        radioButtonC.setText(mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("xuanzeC")));
        radioButtonC.setButtonDrawable(R.drawable.abc_btn_check_material);
        radioButtonD = ly.findViewById(R.id.cb_practice_answer_d);
        radioButtonD.setText(mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("xuanzeD")));
        radioButtonD.setButtonDrawable(R.drawable.abc_btn_check_material);
        if (mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("xuanzeE")) != null) {
            radioButtonE = ly.findViewById(R.id.cb_practice_answer_e);
            radioButtonE.setText(mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("xuanzeE")));
            radioButtonE.setButtonDrawable(R.drawable.abc_btn_check_material);
            radioButtonE.setVisibility(View.VISIBLE);
            if (userDaAn.contains("E")) {
                radioButtonE.setChecked(true);
            }
        }
        if (mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("xuanzeF")) != null) {
            radioButtonF = ly.findViewById(R.id.cb_practice_answer_f);
            radioButtonF.setText(mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("xuanzeF")));
            radioButtonF.setButtonDrawable(R.drawable.abc_btn_check_material);
            radioButtonF.setVisibility(View.VISIBLE);
            if (userDaAn.contains("F")) {
                radioButtonF.setChecked(true);
            }
        }
        if (userDaAn.contains("A")) {
            radioButtonA.setChecked(true);
        }
        if (userDaAn.contains("B")) {
            radioButtonB.setChecked(true);
        }
        if (userDaAn.contains("C")) {
            radioButtonC.setChecked(true);
        }
        if (userDaAn.contains("D")) {
            radioButtonD.setChecked(true);
        }
        View up = ly.findViewById(R.id.ly_bottom_up);
        View collect = ly.findViewById(R.id.ly_bottom_collect);
        View check = ly.findViewById(R.id.ly_bottom_check);
        Button submit=ly.findViewById(R.id.btn_shijuan_pratice);
        View next = ly.findViewById(R.id.ly_bottom_next);
        collect.setVisibility(View.GONE);
        check.setVisibility(View.GONE);
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                examActivity.setUpPage(mPage);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                examActivity.setNextPage(mPage);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                examActivity.submitShiJuan();
            }
        });
    }



    //    public void enableRadioGroup(RadioGroup testRadioGroup) {
//        for (int i = 0; i < testRadioGroup.getChildCount(); i++) {
//            testRadioGroup.getChildAt(i).setEnabled(true);
//        }
//    }
    private void setAnswerListener(CheckBox checkBox) {
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String answer = "";
                if (radioButtonA.isChecked()) {
                    answer = answer + "A";
                }
                if (radioButtonB.isChecked()) {
                    answer = answer + "B";
                }
                if (radioButtonC.isChecked()) {
                    answer = answer + "C";
                }
                if (radioButtonD.isChecked()) {
                    answer = answer + "D";
                }
                if (radioButtonE.isChecked()) {
                    answer = answer + "E";
                }
                if (radioButtonF.isChecked()) {
                    answer = answer + "F";
                }
                String result = "1";
                if (answer.equals("")) {
                    mTiMuModule.setTiMuKaoShi(ExamActivity.mTiMuIds[index], answer, "0", result);
                    ExamActivity.changeTiMuCursorArrData(index);
                } else {
                    if (mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("daan")).equals(answer)) {
                        result = "0";
                    }
                    mTiMuModule.setTiMuKaoShi(ExamActivity.mTiMuIds[index], answer, "1", result);
                    ExamActivity.changeTiMuCursorArrData(index);
                }
            }
        });
    }
}