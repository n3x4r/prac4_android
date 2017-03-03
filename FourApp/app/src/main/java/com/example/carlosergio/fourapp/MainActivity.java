package com.example.carlosergio.fourapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnInicio = (Button) findViewById(R.id.inicio);
        Button btnFin = (Button) findViewById(R.id.stop);

        btnInicio.setOnClickListener(this);
        btnFin.setOnClickListener(this);
    }

    public void onClick(View src) {
        switch (src.getId()) {
            case R.id.inicio:
                startService(new Intent(this,  TheService.class));
                break;
            case R.id.stop:
                stopService(new Intent(this, TheService.class));
                break;
        }
    }
}
