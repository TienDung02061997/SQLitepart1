package com.example.sqlitepart1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=new DBHelper(this);
        //Insert user
        InsertUser();

        //get data user
        getalluser();

        //update user
        updateuser();
        
        //delete user
        deleteuser();
    }

    private void deleteuser() {
        db.delete(1);

    }

    private void updateuser() {
        User user=new User();
        user.setMid(4);
        user.setName("Nguyen  van tuan cap nhat");
        user.setAge(28);
        String me=db.updateUser(user);
        Log.d("update",me);
        getalluser();
    }

    private void getalluser() {
        List<User> userList=db.getAllUser();
        for(User user :userList){
            Log.d("get all ", "/n Name :" +user.getName() +"/n id : "+user.getMid()+ "/n age :"+user.getAge()+"/n gender : "+user.getGender());
        }
    }

    private void InsertUser() {
        User user =new User("Nguyen Van A",28,"gender");
        String mesage=db.insertDB(user);
        Log.d("inset   :  ",mesage);
    }
}
