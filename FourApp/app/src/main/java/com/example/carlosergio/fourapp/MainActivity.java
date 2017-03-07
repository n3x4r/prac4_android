package com.example.carlosergio.fourapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {
    private Intent sound;
    private Intent song;
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

    public void onClick(View src) {
        switch (src.getId()) {
            case R.id.sound1:
                sound = new Intent(this, TheService.class);
                sound.putExtra(TheService.SONG_ID, R.raw.train);
                startService(sound);
                //startService(new Intent(this,  TheService.class));
                break;
            case R.id.sound2:
                song = new Intent(this, TheService.class);
                song.putExtra(TheService.SONG_ID, R.raw.getlucky);
                startService(song);
                break;
            case R.id.stop:
                stopService(new Intent(this, TheService.class));
                break;
        }
    }
}
