package com.example.carlosergio.fourapp;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends Activity implements View.OnClickListener {
    private Intent sound;
    private Intent song;
    private Intent URL;
    private Intent Choose;
    private static final int MYPERMISSIONS_EX_STORAGE = 0 ;
    private static final int MYPERMISSIONS_EX_MEDIA = 1;
    private static final int RESULT_LOAD_SONG = 0;
    private final String SOUND = "SOUND";
    private final String STOP = "STOP";
    private final String PLAY= "PLAY";
    private static final Uri ALBUMART_URI = Uri.parse("content://media/external/audio/media/57");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sound1 = (Button) findViewById(R.id.sound1);
        Button sound2 = (Button) findViewById(R.id.sound2);
        Button stop = (Button) findViewById(R.id.stop);
        Button uri = (Button) findViewById(R.id.uri);
        Button choose = (Button) findViewById(R.id.select_songs);

        sound1.setOnClickListener(this);
        sound2.setOnClickListener(this);
        stop.setOnClickListener(this);
        uri.setOnClickListener(this);
        choose.setOnClickListener(this);
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
            case R.id.uri:

                if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    accessResources(Manifest.permission.READ_EXTERNAL_STORAGE,MYPERMISSIONS_EX_STORAGE);
                }else {


                    Log.i("AQUI","oooo");
                    URL = new Intent(this, TheService.class);
                    //URL.putExtra("URI", ALBUMART_URI);
                    URL.setData(ALBUMART_URI);
                    URL.setType("audio/*");
                    startService(URL);
                }

                //MediaPlayer mp = MediaPlayer.create(this,ALBUMART_URI);
                //mp.start();

                break;
            case R.id.select_songs:
                if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    accessResources(Manifest.permission.READ_EXTERNAL_STORAGE,MYPERMISSIONS_EX_MEDIA);
                }else {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, RESULT_LOAD_SONG);
                }
                break;

        }
    }

    public void accessResources(String typeAccess, int constantReference){

        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                typeAccess)) {

            // Show an explanation to the user *asynchronously* -- don't block
            // this thread waiting for the user's response! After the user
            // sees the explanation, try again to request the permission.

        } else {

            // No explanation needed, we can request the permission.

            ActivityCompat.requestPermissions(this,
                    new String[]{typeAccess},
                    constantReference);

            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
        }

    }

    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MYPERMISSIONS_EX_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    Log.i("AQUI","AQUIIIIIIIIIIIIIIIIIIIIIIII");
                    URL = new Intent(this, TheService.class);
                    //URL.putExtra("URI", ALBUMART_URI);
                    URL.setData(ALBUMART_URI);
                    startService(URL);

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            case MYPERMISSIONS_EX_MEDIA:{
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, RESULT_LOAD_SONG);

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;

            }


        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_SONG && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            Log.i("CANCION", selectedImage.getPath());
            Intent newIntent = new Intent(this, TheService.class);
            newIntent.setData(selectedImage);
            startService(newIntent);

    }

}
}

