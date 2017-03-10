package com.example.carlosergio.fourapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import java.io.IOError;
import java.io.IOException;

/**
 * Created by Dark on 3/3/2017.
 */

public class MySoundReceiver extends BroadcastReceiver{



    @Override
    public void onReceive(Context context, Intent intent) {
        Intent sound = new Intent(context, TheService.class);

        try{
            String select = intent.getExtras().getString("STOP");
            if (select.equals("false")) {
                int id = intent.getExtras().getInt("PLAY");
                String msn = intent.getExtras().getString("SOUND");
                sound.putExtra("ID", id);
                sound.putExtra("SOUND", msn);
                context.startService(sound);
            } else {
                context.stopService(sound);
            }
        }catch(Exception ex){
            if (intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)){
                Toast.makeText(context, R.string.broadcast_start, Toast.LENGTH_LONG).show();
                sound.putExtra("ID", R.raw.getlucky);
                sound.putExtra("SOUND", R.raw.getlucky);
                context.startService(sound);
            }else{
                Toast.makeText(context, R.string.broadcast_stop, Toast.LENGTH_LONG).show();
                context.stopService(sound);
            }


        }
    }
}
