package www.cjkj.com.baiyue.view;

import android.app.Activity;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import www.cjkj.com.baiyue.R;

/**
 * Created by 秦超军 on 2018/1/3.
 */

public class CommonView {
    private Activity mActivity;

    public  CommonView(Activity activity){
        mActivity=activity;
    }

     public  void setTitle() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = mActivity.getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if (true) {
                winParams.flags |= bits;
            } else {
                winParams.flags &= ~bits;
            }
            win.setAttributes(winParams);
        }

        SystemBarTintManager tintManager = new SystemBarTintManager(mActivity);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.title_blue);//通知栏所需颜色
    }

}
