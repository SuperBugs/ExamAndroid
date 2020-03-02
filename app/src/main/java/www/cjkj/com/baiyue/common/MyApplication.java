package www.cjkj.com.baiyue.common;

/**
 * Created by 秦超军 on 2018/1/5.
 */

import android.app.Application;
import android.content.Context;
public class MyApplication extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
    public static Context getContext() {
        return context;
    }
}