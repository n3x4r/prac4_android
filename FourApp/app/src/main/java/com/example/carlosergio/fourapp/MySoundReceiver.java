package com.example.carlosergio.fourapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Dark on 3/3/2017.
 */

public class MySoundReceiver extends BroadcastReceiver{



    @Override
    public void onReceive(Context context, Intent intent) {

            Toast.makeText(context, "Intent recibido - Inicio reproduccion", Toast.LENGTH_LONG).show();

        String select = intent.getExtras().getString("STOP");
        System.out.println("fdsfdsf"+select);
        Intent sound = new Intent(context, TheService.class);
        if(select.equals("false")) {

            int id = intent.getExtras().getInt("PLAY");
            String msn = intent.getExtras().getString("SOUND");

            sound.putExtra("ID", id);
            sound.putExtra("SOUND", msn);
            context.startService(sound);
        }else{

            context.stopService(sound);
        }
    }
}
