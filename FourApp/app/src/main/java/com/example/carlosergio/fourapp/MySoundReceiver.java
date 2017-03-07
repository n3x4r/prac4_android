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
        if(intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)){
            Toast.makeText(context, "Intent recibido - Inicio reproduccion", Toast.LENGTH_LONG).show();
            //context.startService()

        }
    }
}
