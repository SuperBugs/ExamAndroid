package www.cjkj.com.baiyue.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Created by 秦超军 on 2018/1/4.
 */

public class CommonFunction {
    //绘制圆形图像
    public Bitmap makeCirculaImage(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        //正方形的边长
        int r;
        //取得图片的宽和高
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        //设置正方形边长，取短的一边作为边长
        if (width >= height) {
            r = width;
        } else {
            r = height;
        }
        //构建一个bitmap
        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        //new一个Canvas，在output上画图
        Canvas canvas = new Canvas(output);
        Paint paint = new Paint();
        //正方形
        RectF rectF = new RectF(0, 0, r, r);
        //抗锯齿
        paint.setAntiAlias(true);
        //画圆角矩形（当x和y方向上的长度相等时，就是一个圆）
        canvas.drawRoundRect(rectF, r / 2, r / 2, paint);
        //设置当两个图形相交时的模式为SRC_IN，代表保留相交部分的上层，去掉其余部分
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //将图片画在output上
        canvas.drawBitmap(bitmap, null, rectF, paint);
        return output;
    }
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public  int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public  int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
    /*
    * 获取控件宽
    */
    public static int getWidth(View view)
    {
        int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        return (view.getMeasuredWidth());
    }
    /*
     * 获取控件高
     */
    public static int getHeight(View view)
    {
        int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        return (view.getMeasuredHeight());
    }

    /*
     * 设置控件所在的位置X，并且不改变宽高，
     * X为绝对位置，此时Y可能归0
     */
    public static void setLayoutX(View view,int x)
    {
        ViewGroup.MarginLayoutParams margin=new ViewGroup.MarginLayoutParams(view.getLayoutParams());
        margin.setMargins(x,margin.topMargin, x+margin.width, margin.bottomMargin);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(margin);
        view.setLayoutParams(layoutParams);
    }
    /*
     * 设置控件所在的位置Y，并且不改变宽高，
     * Y为绝对位置，此时X可能归0
     */
    public static void setLayoutY(View view, int y)
    {
        ViewGroup.MarginLayoutParams margin=new ViewGroup.MarginLayoutParams(view.getLayoutParams());
        margin.setMargins(margin.leftMargin,y, margin.rightMargin, y+margin.height);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(margin);
        view.setLayoutParams(layoutParams);
    }
    /*
     * 设置控件所在的位置YY，并且不改变宽高，
     * XY为绝对位置
     */
    public static void setLayout(View view,int x,int y)
    {
        ViewGroup.MarginLayoutParams margin=new ViewGroup.MarginLayoutParams(view.getLayoutParams());
        margin.setMargins(x,y, x+margin.width, y+margin.height);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(margin);
        view.setLayoutParams(layoutParams);
    }
    //文件保存对象数据
    public static void putDate(String dateName, Object date, Context context) {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream in = null;
        try {
            fileOutputStream = context.openFileOutput(dateName, Context.MODE_PRIVATE);
            in = new ObjectOutputStream(fileOutputStream);
            in.writeObject(date);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //文件读取obj数据
    public static Object getDate(String dateName, Context context) {
        Object date = null;
        FileInputStream fileInputStream = null;
        ObjectInputStream in = null;
        try {
            fileInputStream = context.openFileInput(dateName);
            in = new ObjectInputStream(fileInputStream);
            date = in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return date;
    }
    public static boolean listContain(List<Integer> arr, int targetValue) {
        for(int s: arr){
            if(s==targetValue)
                return true;
        }
        return false;
    }
}
