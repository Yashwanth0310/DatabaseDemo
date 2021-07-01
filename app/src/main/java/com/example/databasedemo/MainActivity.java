package com.example.databasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try{
            SQLiteDatabase myDatabase = this.openOrCreateDatabase("Events",MODE_PRIVATE,null);
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS Events(eventName VARCHAR, Year INT(4))");
            myDatabase.execSQL("INSERT INTO Events(eventName,Year) VALUES('Corona Virus',2020)");
            myDatabase.execSQL("INSERT INTO Events(eventName,Year) VALUES('Mumbai Attack',2007)");
            Cursor c = myDatabase.rawQuery("SELECT * FROM Events",null);
            int nameIndex = c.getColumnIndex("eventName");
            int yearIndex = c.getColumnIndex("Year");
            c.moveToFirst();
            while (!c.isAfterLast()){
                Log.i("eventName",c.getString(nameIndex));
                Log.i("Year",Integer.toString(c.getInt(yearIndex)));
                c.moveToNext();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}