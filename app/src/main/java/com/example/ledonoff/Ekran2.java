package com.example.ledonoff;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public class Ekran2 extends Activity {


    Button Ac,Ev,Ayarlar,Gecmis;















    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ekran2);

        Ac = (Button) findViewById(R.id.Ac);
        Ev = (Button) findViewById(R.id.Ev);
        Ayarlar = (Button) findViewById(R.id.Ayarlar);
        Gecmis = (Button) findViewById(R.id.Gecmis);





        Ev.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LEDOnOff.class);
                startActivity(intent);
            }
        });






    }


}



