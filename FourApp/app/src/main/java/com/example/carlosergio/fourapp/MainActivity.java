package com.example.carlosergio.fourapp;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {
    private Intent sound;
    private Intent song;
    private final String SOUND = "SOUND";
    private final String STOP = "STOP";
    private final String PLAY= "PLAY";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sound1 = (Button) findViewById(R.id.sound1);
        Button sound2 = (Button) findViewById(R.id.sound2);
        Button stop = (Button) findViewById(R.id.stop);

        sound1.setOnClickListener(this);
        sound2.setOnClickListener(this);
        stop.setOnClickListener(this);

    }
    //add canciones adb push nombredecanciones emulator
    //android device monitor para  copiar canciones  en data / media

    public void onClick(View src) {
        switch (src.getId()) {
            case R.id.sound1:
                sound = new Intent(this, MySoundReceiver.class);
                sound.putExtra(PLAY, R.raw.train);
                sound.putExtra(SOUND, "Servicio Sonido iniciado");
                sound.putExtra(STOP, "false");

                sendBroadcast(sound);
                break;
            case R.id.sound2:
                song = new Intent(this, MySoundReceiver.class);
                song.putExtra(PLAY, R.raw.getlucky);
                song.putExtra(SOUND, "Servicio Cancion iniciado");
                song.putExtra(STOP, "false");

                sendBroadcast(song);
                break;
            case R.id.stop:
                sendBroadcast(new Intent(this,MySoundReceiver.class).putExtra(STOP,"true"));
                break;
        }
    }
}
