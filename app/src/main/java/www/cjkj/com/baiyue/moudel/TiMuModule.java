package www.cjkj.com.baiyue.moudel;

import android.database.Cursor;

/**
 * Created by 秦超军 on 2018/1/5.
 */

public class TiMuModule {
    Module mModule;

    public TiMuModule() {
        mModule = new Module("Wenti");
    }

    //按照题目编号获取题目数据
    public Cursor getTiMuData(int tiMuId) {
        return mModule.oneIdSelect("tmID", "tm" + tiMuId);
    }

    //按照题目编号设置练习答案

    /**
     *
     * @param tiMuId 题目id
     * @param tiMuAnswer 题目答案 ABCD
     * @param tiMuAnswerState 练习状态 0为未填写，1为已填写
     * @param tiMuAnswerResult  练习答案结果 0为正确，1为错误
     */
    public void setTiMuLianXi(int tiMuId, String tiMuAnswer,String tiMuAnswerState,String tiMuAnswerResult) {
        mModule.changeValue("tmID", "tm" + tiMuId, "userdaan_lx", tiMuAnswer,"daan_state", tiMuAnswerState,"cuoti_lxState", tiMuAnswerResult);
    }

    //按照题目编号设置考试答案
    /**
     *
     * @param tiMuId 题目id
     * @param tiMuAnswer 题目答案 ABCD
     * @param tiMuAnswerState 考试状态 0为未填写，1为已填写
     * @param tiMuAnswerResult  考试答案结果 0为正确，1为错误
     */
    public void setTiMuKaoShi(int tiMuId, String tiMuAnswer,String tiMuAnswerState,String tiMuAnswerResult) {
        mModule.changeValue("tmID", "tm" + tiMuId, "userdaan_ks", tiMuAnswer,"sc_state", tiMuAnswerState,"cuoti_ksState", tiMuAnswerResult);
    }

    //按照题目编号设置考试答案
    public void setTiMuKaoShiAnswer(int tiMuId, String tiMuAnswer) {
        mModule.changeOneValue("tmID", "tm" + tiMuId, "userdaan_ks", tiMuAnswer);
    }

    //按照题目编号设置考试题答案是否填写，0为未填写，1为已填写
    public void setTiMuKaoShiAnswerState(int tiMuId, String tiMuAnswerState) {
        mModule.changeOneIntValue("tmID", "tm" + tiMuId, "sc_state", tiMuAnswerState);
    }

    //按照题目编号设置考试题答案是否正确，0为正确，1为错误
    public void setTiMuKaoShiAnswerResult(int tiMuId, String tiMuAnswerResult) {
        mModule.changeOneIntValue("tmID", "tm" + tiMuId, "cuoti_ksState", tiMuAnswerResult);
    }
    //测试专用，设置所有题目答案为A
    public void setTiMuDaAn(int tiMuId){
        mModule.changeOneValue("tmID","tm"+tiMuId,"daan","A");
    }
    public Cursor getYiDaShiJuanData(){
        return mModule.getOneMoreThanTwo("tmcount_yida", "0");
    }
}
