package com.example.ledonoff;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import com.example.ledonoff.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class LEDOnOff extends Activity {




    Button salon_button,mutfak_button, btnOda1,btnOda2,btnOda3,btnOda4;
    private GoogleApiClient client;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.main);

        salon_button = (Button) findViewById(R.id.salon_button);
        mutfak_button = (Button) findViewById(R.id.mutfak_button);

        btnOda1 = (Button) findViewById(R.id.Oda1);
        btnOda2 = (Button) findViewById(R.id.Oda2);
        btnOda3 = (Button) findViewById(R.id.Oda3);
        btnOda4 = (Button) findViewById(R.id.Oda4);




        salon_button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),salon. class);
                startActivity(intent);
            }
        });



        mutfak_button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),mutfak. class);
                startActivity(intent);
            }
        });

        btnOda1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Ekran2. class);
                startActivity(intent);
            }
        });

        btnOda2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Ekran3. class);
                startActivity(intent);
            }
        });

        btnOda3.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Ekran4. class);
                startActivity(intent);
            }
        });

        btnOda4.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Ekran5. class);
                startActivity(intent);
            }
        });






        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }




}
