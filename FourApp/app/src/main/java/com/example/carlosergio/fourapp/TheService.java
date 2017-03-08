package com.example.carlosergio.fourapp;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CarloSergio on 03/03/2017.
 */

public class TheService extends Service {

    private MediaPlayer player;
    public static String SONG_ID = null;
    List<MediaPlayer> mMediaPlayerList = new ArrayList<>();
    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        Toast.makeText(this, "Servicio Creado", Toast.LENGTH_LONG).show();
        super.onCreate();
        //player = MediaPlayer.create(this, R.raw.train);
        //player.setLooping(true);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Servicio Detenido", Toast.LENGTH_LONG).show();
        for (MediaPlayer player : mMediaPlayerList){
            player.stop();
        }
        //player.stop();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startid) {
        int id = intent.getExtras().getInt("ID");
        String mss= intent.getExtras().getString("SOUND");
        player = MediaPlayer.create(this, id);
        player.start();
        player.setLooping(true);
        mMediaPlayerList.add(player);
        Toast.makeText(this, "Servicio Iniciado", Toast.LENGTH_LONG).show();
        Toast.makeText(this, mss, Toast.LENGTH_LONG).show();

        return startid;
    }


}
