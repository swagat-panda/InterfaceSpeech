package com.example.interfacespeech;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.Locale;

public class Speak extends AppCompatActivity implements TextToSpeech.OnInitListener {
    private TextToSpeech tts;
    ImageButton buttonSpeak;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speak);
        tts = new TextToSpeech(this, this);
        buttonSpeak=(ImageButton)findViewById(R.id.button1);
        editText = (EditText) findViewById(R.id.editText1);
        buttonSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut();
            }
        });
    }
    @Override
    public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                buttonSpeak.setEnabled(true);
                speakOut();
            }
        } else {
            Log.e("TTS", "initilization Failed");
        }
    }


    private void speakOut() {
        String text = editText.getText().toString();
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        // tts.speak(text,TextToSpeech.QUEUE_FLUSH,null);
    }




}
