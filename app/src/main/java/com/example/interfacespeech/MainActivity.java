package com.example.interfacespeech;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText u,p;

    SQLiteDatabase db=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        u=(EditText)findViewById(R.id.editText);
        p=(EditText)findViewById(R.id.editText2);
        db=openOrCreateDatabase("college",MODE_PRIVATE,null);
    }
    public void regis(View v)
    {
        Intent in=new Intent(getApplication(),Save.class);
        startActivity(in);
    }
    public void login(View v)
    {
        String name=u.getText().toString();
        String pass=p.getText().toString();
        Cursor c=db.rawQuery("select * from user_master where name='"+name+"'and password='"+pass+"'",null);
        boolean b=c.moveToNext();
        if(b)
        {

            String n=c.getString(c.getColumnIndex("name"));
            if(name.equals(n))
            {
                Intent in=new Intent(getApplication(),Speak.class);
                startActivity(in);
            }
            else
            {
                Toast.makeText(this,"INVALID",Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            Toast.makeText(this,"INVALID",Toast.LENGTH_LONG).show();
        }

    }
}
