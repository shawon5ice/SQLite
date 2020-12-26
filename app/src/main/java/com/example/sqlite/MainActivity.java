package com.example.sqlite;

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

        try {
            SQLiteDatabase database = this.openOrCreateDatabase("Event",MODE_PRIVATE,null);
            database.execSQL("CREATE TABLE IF NOT EXISTS events(name VARCHAR,year INT(4))");
            database.execSQL("INSERT INTO events (name, year) VALUES ('Wedding',2023)");
            database.execSQL("INSERT INTO events (name, year) VALUES ('Hangout',2021)");
            Cursor cursor = database.rawQuery("SELECT * FROM events",null);
            int nameInt = cursor.getColumnIndex("name");
            int yearInt = cursor.getColumnIndex("year");

            cursor.moveToFirst();
            while (cursor!=null){
                Log.i("name", "onCreate: "+cursor.getString(nameInt)+" in "+String.valueOf(cursor.getInt(yearInt)));
                cursor.moveToNext();
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}