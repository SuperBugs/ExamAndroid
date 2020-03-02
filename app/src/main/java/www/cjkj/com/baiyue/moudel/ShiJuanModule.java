package www.cjkj.com.baiyue.moudel;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by 秦超军 on 2018/1/5.
 */

public class ShiJuanModule {
    Module mModule;
    public ShiJuanModule(){
        mModule=new Module("Shijuan");
    }
    public Cursor getShiJuanTableViewData(int shiJuanType, int shiJuanKeMu){
        String mShiJuanType;
        String mShiJuanKeMu;
        switch (shiJuanType) {
            case 1:
                mShiJuanType="模拟";
                break;
            case 2:
                mShiJuanType="章节";
                break;
            case 3:
                mShiJuanType="历年真题";
                break;
            case 4:
                mShiJuanType="练习";
                break;
            default:
                mShiJuanType="模拟";
        }
        switch (shiJuanKeMu) {
            case 1:
                mShiJuanKeMu="公共基础";
                break;
            case 2:
                mShiJuanKeMu="道路工程";
                break;
            case 3:
                mShiJuanKeMu="交通工程";
                break;
            case 4:
                mShiJuanKeMu="桥梁隧道工程";
                break;
            default:
                mShiJuanKeMu="公共基础";
        }
        if(mShiJuanType.equals("练习")){
            return mModule.getOneMoreThanTwo("tmcount_yida","0");
        }else{
            return mModule.twoIdSelect("class_kemu",mShiJuanKeMu,"class_shijuan",mShiJuanType);
        }

    }
    public void setUserExamResult(String shiJuanId, String shiJuanScore,String shiJuanYiDaCount) {
        mModule.changeValue("sjID", shiJuanId, "userfs", shiJuanScore,"tmcount_yida", shiJuanYiDaCount);
    }
}
