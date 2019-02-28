package com.example.sqlitepart1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "User.db";
    public static final String TABLE_NAME = "TBL_USER";
    public static final int DB_VERION = 1;

    private final String TAG = getClass().getSimpleName();


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " ( " +
                TBL_USER.id + " INTEGER PRIMARY KEY , " +
                TBL_USER.name + " TEXT, " +
                TBL_USER.age + " INTEGER, " +
                TBL_USER.gender + " TEXT )";
        Log.d(TAG, sql +" databse");
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public String insertDB(User user) {

        SQLiteDatabase db = this.getWritableDatabase();
        //dua du lieu truyen vao
        ContentValues values = new ContentValues();
        values.put(TBL_USER.name, user.getName());
        values.put(TBL_USER.age, user.getAge());
        values.put(TBL_USER.gender, user.getGender());

        long isSuccess = db.insert(TABLE_NAME, null, values);
        if (isSuccess > 0) {
            return "Success";
        } else {
            return "Fail";
        }
    }

    public List<User> getAllUser() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME;
        List<User> users = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                User user = new User();
                user.setName(cursor.getString(cursor.getColumnIndex(TBL_USER.name)));
                user.setGender(cursor.getString(cursor.getColumnIndex(TBL_USER.gender)));
                user.setName(cursor.getString(cursor.getColumnIndex(TBL_USER.name)));
                user.setMid(cursor.getInt(cursor.getColumnIndex(TBL_USER.id)));
                user.setAge(cursor.getInt(cursor.getColumnIndex(TBL_USER.age)));
                users.add(user);
            } while (cursor.moveToNext());

        }

        return users;
    }

   public  String updateUser(User user){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(TBL_USER.name,user.getName());
        values.put(TBL_USER.age,user.getAge());
        long isSuccess=db.update(TABLE_NAME,values,"id="+user.getMid(),null);
        Log.d(TAG,isSuccess +" ok");
        if (isSuccess >0){
            return  " Update Success";
        }
        else {
            return  " Fail";
        }
   }
   public String delete(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        long issucces=db.delete(TABLE_NAME,"id ="+id,null);
        if(issucces>0){
            return  "Delete suceess";
        }
        else {
            return  "Faill";
        }
   }

}
