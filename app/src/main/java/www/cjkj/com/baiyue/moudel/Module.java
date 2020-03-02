package www.cjkj.com.baiyue.moudel;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import www.cjkj.com.baiyue.common.MyApplication;

/**
 * Created by 秦超军 on 2018/1/5.
 */

public class Module {
    private SQLiteDatabase db;
    private String mTableName;

    Module(String tableName) {
        SQLdm s = new SQLdm();
        db = s.openDatabase(MyApplication.getContext());
        mTableName = tableName;
    }

    public Cursor twoIdSelect(String idOne, String valueOne, String idTwo, String valueTwo) {
        return db.rawQuery("select * from " + mTableName + " where " + idOne + "=?" + " and " + idTwo + "=?", new String[]{valueOne, valueTwo});
    }

    public Cursor oneIdSelect(String idOne, String valueOne) {
        return db.rawQuery("select * from " + mTableName + " where " + idOne + "=?", new String[]{valueOne});
    }

    public void changeOneValue(String idOne, String valueOne, String changeId, String changeValue) {
        db.execSQL("update " + mTableName + " set " + changeId + "='" + changeValue + "'" + " where " + idOne + "='" + valueOne + "'");
    }

    public void changeOneIntValue(String idOne, String valueOne, String changeId, String changeValue) {
        db.execSQL("update " + mTableName + " set " + changeId + "=" + changeValue + " where " + idOne + "='" + valueOne + "'");
    }

    public Cursor getOneMoreThanTwo(String id, String intValue) {
        return db.rawQuery("select * from " + mTableName + " where " + id + ">?", new String[]{intValue});
    }

    public void changeValue(String idOne, String valueOne, String changeIdOne, String changeValueOne, String changeIdTwo, String changeValueTwo, String changeIdThree, String changeValueThree) {
        //db.execSQL("update " + mTableName + " set " + changeIdOne + "='" + changeValueOne + "' " + changeIdOne + "='" + changeValueOne + "'" + " where " + idOne + "='" + valueOne + "'");
        ContentValues values = new ContentValues();
        //在values中添加内容
        values.put(changeIdOne, changeValueOne);
        values.put(changeIdTwo, changeValueTwo);
        values.put(changeIdThree, changeValueThree);
        //修改条件
        String whereClause = idOne+"=?";
        //修改添加参数
        String[] whereArgs = {valueOne};
        db.update(mTableName, values, whereClause, whereArgs);
    }
    public void changeValue(String idOne, String valueOne, String changeIdOne, String changeValueOne, String changeIdTwo, String changeValueTwo) {
        //db.execSQL("update " + mTableName + " set " + changeIdOne + "='" + changeValueOne + "' " + changeIdOne + "='" + changeValueOne + "'" + " where " + idOne + "='" + valueOne + "'");
        ContentValues values = new ContentValues();
        //在values中添加内容
        values.put(changeIdOne, changeValueOne);
        values.put(changeIdTwo, changeValueTwo);
        //修改条件
        String whereClause = idOne+"=?";
        //修改添加参数
        String[] whereArgs = {valueOne};
        db.update(mTableName, values, whereClause, whereArgs);
    }
}
