package www.cjkj.com.baiyue.common;

import android.content.Context;
import android.view.WindowManager;

/**
 * Created by 秦超军 on 2018/1/4.
 */

public class CommonConfig {
    public static String HostAddress="http://123.207.255.187/index.php?";
    public static String AppVersionUrl=HostAddress + "c=BaiYueApp&f=getVersion";
    public static int AppVersion=1;
    public static String CacheDir = "data/data/www.cjkj.com.baiyue";
    public static String ActiveCrouseUrl=HostAddress+"c=BaiYueAppActivateVip&f=check";
    public static String ActiveCrouseParamOne="phone=";
    public static String ActiveCrouseParamTwo="jihuoma=";
    public static  String FeadBackUrl=HostAddress+"c=FeedBack&f=suggestion";
    public static String FeadBackParamOne="account=";
    public static String FeadBackParamTwo="content=";
    public static String FeadBackParamThree="contact=";
}
