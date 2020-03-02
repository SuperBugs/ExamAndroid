package www.cjkj.com.baiyue.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import www.cjkj.com.baiyue.R;
import www.cjkj.com.baiyue.activity.ExamActivity;
import www.cjkj.com.baiyue.common.CommonFunction;

import static www.cjkj.com.baiyue.activity.ExamActivity.mTiMuCursorArr;
import static www.cjkj.com.baiyue.activity.ExamActivity.mTiMuModule;


public class ExamFragment extends Fragment {
    private int index;
    private View ly;
    private int mPage;
    private static final String TAG = "ExamFragment";
    private ExamActivity examActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ly = inflater.inflate(R.layout.practice_xuanze_view, container, false);
        examActivity = (ExamActivity) getActivity();
        setTitle();
        if (mTiMuCursorArr[index].getInt(mTiMuCursorArr[index].getColumnIndex("sc_state")) == 1) {
            setDidView();
        } else {
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
        TextView tiMuTv = ly.findViewById(R.id.tv_practice_subject);
        mTiMuCursorArr[index].moveToFirst();
        tiMuTv.setText(mPage + "." + mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("timu")));
        String tiMuType = mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("class_timu"));
        if (tiMuType.equals("单选题")) {
            RadioGroup radioGroup = ly.findViewById(R.id.rg_practice_option);
            final RadioButton radioButtonA = ly.findViewById(R.id.rb_practice_option_a);
            radioButtonA.setText(mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("xuanzeA")));
            radioButtonA.setButtonDrawable(R.drawable.radiobtn_bg_selector);
            final RadioButton radioButtonB = ly.findViewById(R.id.rb_practice_option_b);
            radioButtonB.setText(mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("xuanzeB")));
            radioButtonB.setButtonDrawable(R.drawable.radiobtn_bg_selector);
            final RadioButton radioButtonC = ly.findViewById(R.id.rb_practice_option_c);
            radioButtonC.setText(mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("xuanzeC")));
            radioButtonC.setButtonDrawable(R.drawable.radiobtn_bg_selector);
            final RadioButton radioButtonD = ly.findViewById(R.id.rb_practice_option_d);
            radioButtonD.setText(mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("xuanzeD")));
            radioButtonD.setButtonDrawable(R.drawable.radiobtn_bg_selector);
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    if (i == radioButtonA.getId()) {
                        checkDanXuan(ly, "A");
                    }
                    if (i == radioButtonB.getId()) {
                        checkDanXuan(ly, "B");
                    }
                    if (i == radioButtonC.getId()) {
                        checkDanXuan(ly, "C");
                    }
                    if (i == radioButtonD.getId()) {
                        checkDanXuan(ly, "D");
                    }
                }
            });
        }
        if (tiMuType.equals("判断题")) {
            RadioGroup radioGroup = ly.findViewById(R.id.rg_practice_option);
            final RadioButton radioButtonA = ly.findViewById(R.id.rb_practice_option_a);
            radioButtonA.setText(mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("xuanzeA")));
            radioButtonA.setButtonDrawable(R.drawable.radiobtn_bg_selector);
            final RadioButton radioButtonB = ly.findViewById(R.id.rb_practice_option_b);
            radioButtonB.setText(mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("xuanzeB")));
            radioButtonB.setButtonDrawable(R.drawable.radiobtn_bg_selector);
            final RadioButton radioButtonC = ly.findViewById(R.id.rb_practice_option_c);
            final RadioButton radioButtonD = ly.findViewById(R.id.rb_practice_option_d);
            radioButtonC.setVisibility(View.GONE);
            radioButtonD.setVisibility(View.GONE);
            TextView mlinec = ly.findViewById(R.id.rb_practice_line_c);
            mlinec.setVisibility(View.GONE);
            TextView mlined = ly.findViewById(R.id.rb_practice_line_d);
            mlined.setVisibility(View.GONE);
            TextView mlinee = ly.findViewById(R.id.rb_practice_line_e);
            mlinee.setVisibility(View.GONE);
            TextView mlinef = ly.findViewById(R.id.rb_practice_line_f);
            mlinef.setVisibility(View.GONE);
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    if (i == radioButtonA.getId()) {
                        checkDanXuan(ly, "正确");
                    }
                    if (i == radioButtonB.getId()) {
                        checkDanXuan(ly, "错误");
                    }

                }
            });
        }
        View up = ly.findViewById(R.id.ly_bottom_up);
        Button submit=ly.findViewById(R.id.btn_shijuan_pratice);
        View check = ly.findViewById(R.id.ly_bottom_check);
        View collect = ly.findViewById(R.id.ly_bottom_collect);
        View next = ly.findViewById(R.id.ly_bottom_next);
        check.setVisibility(View.GONE);
        collect.setVisibility(View.GONE);
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

    private void checkDanXuan(View ly, String answer) {
        String result = "1";
        if (mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("daan")).equals(answer)) {
            result = "0";
        }
        mTiMuModule.setTiMuKaoShi(ExamActivity.mTiMuIds[index], answer, "1", result);
        ExamActivity.changeTiMuCursorArrData(index);
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
        TextView tiMuTv = ly.findViewById(R.id.tv_practice_subject);
        mTiMuCursorArr[index].moveToFirst();
        tiMuTv.setText(mPage + "." + mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("timu")));
        String tiMuType = mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("class_timu"));
        String userDaAn = mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("userdaan_ks"));
        if (tiMuType.equals("单选题")) {
            RadioGroup radioGroup = ly.findViewById(R.id.rg_practice_option);
            final RadioButton radioButtonA = ly.findViewById(R.id.rb_practice_option_a);
            radioButtonA.setText(mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("xuanzeA")));
            radioButtonA.setButtonDrawable(R.drawable.radiobtn_bg_selector);
            final RadioButton radioButtonB = ly.findViewById(R.id.rb_practice_option_b);
            radioButtonB.setText(mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("xuanzeB")));
            radioButtonB.setButtonDrawable(R.drawable.radiobtn_bg_selector);
            final RadioButton radioButtonC = ly.findViewById(R.id.rb_practice_option_c);
            radioButtonC.setText(mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("xuanzeC")));
            radioButtonC.setButtonDrawable(R.drawable.radiobtn_bg_selector);
            final RadioButton radioButtonD = ly.findViewById(R.id.rb_practice_option_d);
            radioButtonD.setText(mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("xuanzeD")));
            radioButtonD.setButtonDrawable(R.drawable.radiobtn_bg_selector);

            if (userDaAn.equals("A")) {
                radioButtonA.setChecked(true);
            }
            if (userDaAn.equals("B")) {
                radioButtonB.setChecked(true);
            }
            if (userDaAn.equals("C")) {
                radioButtonC.setChecked(true);
            }
            if (userDaAn.equals("D")) {
                radioButtonD.setChecked(true);
            }
        }
        if (tiMuType.equals("判断题")) {
            RadioGroup radioGroup = ly.findViewById(R.id.rg_practice_option);
            final RadioButton radioButtonA = ly.findViewById(R.id.rb_practice_option_a);
            radioButtonA.setText(mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("xuanzeA")));
            radioButtonA.setButtonDrawable(R.drawable.radiobtn_bg_selector);
            final RadioButton radioButtonB = ly.findViewById(R.id.rb_practice_option_b);
            radioButtonB.setText(mTiMuCursorArr[index].getString(mTiMuCursorArr[index].getColumnIndex("xuanzeB")));
            radioButtonB.setButtonDrawable(R.drawable.radiobtn_bg_selector);
            final RadioButton radioButtonC = ly.findViewById(R.id.rb_practice_option_c);
            final RadioButton radioButtonD = ly.findViewById(R.id.rb_practice_option_d);
            radioButtonC.setVisibility(View.GONE);
            radioButtonD.setVisibility(View.GONE);
            if (userDaAn.equals("正确")) {
                radioButtonA.setChecked(true);
            }
            if (userDaAn.equals("错误")) {
                radioButtonB.setChecked(true);
            }
            TextView mlinec = ly.findViewById(R.id.rb_practice_line_c);
            mlinec.setVisibility(View.GONE);
            TextView mlined = ly.findViewById(R.id.rb_practice_line_d);
            mlined.setVisibility(View.GONE);
            TextView mlinee = ly.findViewById(R.id.rb_practice_line_e);
            mlinee.setVisibility(View.GONE);
            TextView mlinef = ly.findViewById(R.id.rb_practice_line_f);
            mlinef.setVisibility(View.GONE);
        }
        View up = ly.findViewById(R.id.ly_bottom_up);
        View collect = ly.findViewById(R.id.ly_bottom_collect);
        View check=ly.findViewById(R.id.ly_bottom_check);
        View next = ly.findViewById(R.id.ly_bottom_next);
        Button submit=ly.findViewById(R.id.btn_shijuan_pratice);
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

}