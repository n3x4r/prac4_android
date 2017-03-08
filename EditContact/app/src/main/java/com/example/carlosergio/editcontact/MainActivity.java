package com.example.carlosergio.editcontact;

import android.Manifest;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnSend;
    private static final int MYPERMISSIONS_READCONTACT = 1;
    public Cursor mCursor;
    // The index of the lookup key column in the cursor
    public int mLookupKeyIndex;
    // The index of the contact's _ID value
    public int mIdIndex;
    // The lookup key from the Cursor
    public String mCurrentLookupKey;
    // The _ID value from the Cursor
    public long mCurrentId;
    // A content URI pointing to the contact
    Uri mSelectedContactUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSend  = (Button)findViewById(R.id.button);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                    accessResources(Manifest.permission.SEND_SMS,MYPERMISSIONS_READCONTACT);
                }else {

                    Uri contactUri = ContentUris.parseId()


                    Intent intent = new Intent(Intent.ACTION_EDIT);
                    intent.setData(contactUri);
                    intent.putExtra(ContactsContract.Intents.Insert.EMAIL, "Sergi0.cach0@hotmail.com");
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }
                }

            }

        });
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

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MYPERMISSIONS_READCONTACT: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    button7();

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }



        }
    }
}
