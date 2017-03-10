package com.example.carlosergio.fourapp;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Created by CarloSergio on 03/03/2017.
 */

public class TheService extends Service {

    private MediaPlayer player;
    List<MediaPlayer> mMediaPlayerList = new ArrayList<>();
    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        Toast.makeText(this, R.string.service_create, Toast.LENGTH_LONG).show();
        super.onCreate();
        //player = MediaPlayer.create(this, R.raw.train);
        //player.setLooping(true);
    }

    @Override
    public void onDestroy() {

        Toast.makeText(this, R.string.service_destroy, Toast.LENGTH_LONG).show();
        if(!mMediaPlayerList.isEmpty()) {
            for (MediaPlayer player : mMediaPlayerList) {
                player.stop();
            }
        }else{
            player.stop();
        }
        //player.stop();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startid) {
        try{
            player.stop();
        }catch(Exception ex){


        }finally {


            Uri URL = intent.getData();

            if (URL != null) {
                Log.i("AQUI2", URL.getPath());
                player = MediaPlayer.create(this, URL);
            } else {
                int id = intent.getExtras().getInt("ID");
                String mss = intent.getExtras().getString("SOUND");
                player = MediaPlayer.create(this, id);
            }
        /*
        int id = intent.getExtras().getInt("ID");
        String mss= intent.getExtras().getString("SOUND");
        player = MediaPlayer.create(this, id);
        player.start();
        player.setLooping(true);
        mMediaPlayerList.add(player);
        Toast.makeText(this, R.string.service_init, Toast.LENGTH_LONG).show();
        Toast.makeText(this, mss, Toast.LENGTH_LONG).show();*/


            player.start();
            player.setLooping(true);
            mMediaPlayerList.add(player);
        }
        return startid;
    }


}
