package com.example.interfacespeech;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Save extends AppCompatActivity {
    EditText editText3u,editText4p;
    SQLiteDatabase db=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        editText3u=(EditText)findViewById(R.id.editText3);
        editText4p=(EditText)findViewById(R.id.editText4);
        db=openOrCreateDatabase("college",MODE_PRIVATE,null);
        db.execSQL("create table if not exists user_master(name varchar(30),password varchar(30))");
    }
    public void save(View v)
    {

        String n=editText3u.getText().toString();
        String pass=editText4p.getText().toString();
        db.execSQL("insert into user_master values('"+n+"','"+pass+"')");
        Toast.makeText(this,"OK",Toast.LENGTH_LONG).show();
        Intent in=new Intent(getApplication(),MainActivity.class);
        startActivity(in);

    }
}
