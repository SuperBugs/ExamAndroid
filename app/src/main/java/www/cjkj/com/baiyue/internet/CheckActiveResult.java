package www.cjkj.com.baiyue.internet;



import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

import www.cjkj.com.baiyue.common.CommonConfig;
import www.cjkj.com.baiyue.common.MyHttpUrlConnectionGet;

//激活课程
public class CheckActiveResult {
    private String serviceData="";
    private String phone;
    private String num;
    public String check(String phone,String num) {
        this.phone=phone;
        this.num=num;
        get();
        return serviceData;
    }

    private void get() {
        String shareAddress = CommonConfig.ActiveCrouseUrl+"&"+CommonConfig.ActiveCrouseParamOne
                +phone+"&"+CommonConfig.ActiveCrouseParamTwo+num;
        serviceData = MyHttpUrlConnectionGet.sendHttpRequest(shareAddress);
    }

}
