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


public class Ekran3 extends Activity {

    private static final String TAG = "LEDOnOff";

    Button Ev,Ayarlar,Gecmis,ac_button,kapat_button,calkala_button;

    private static final int REQUEST_ENABLE_BT = 1;
    private BluetoothAdapter btAdapter = null;
    private BluetoothSocket btSocket = null;
    public OutputStream outStream = null;

    private static String address = "20:15:07:24:16:79";
    private GoogleApiClient client;

    private static final UUID MY_UUID =
            UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekran3);



        btAdapter = BluetoothAdapter.getDefaultAdapter();
        checkBTState();


        Ev = (Button) findViewById(R.id.Ev);
        Ayarlar = (Button) findViewById(R.id.Ayarlar);
        Gecmis = (Button) findViewById(R.id.Gecmis);

        ac_button = (Button) findViewById(R.id.ac_button);
        kapat_button = (Button) findViewById(R.id.kapat_button);
        calkala_button = (Button) findViewById(R.id.calkala_button);

        Ev.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LEDOnOff.class);
                startActivity(intent);
            }
        });

        ac_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData("1");
                Toast msg = Toast.makeText(getBaseContext(),
                        "Açtın", Toast.LENGTH_SHORT);
                msg.show();
            }
        });
        kapat_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData("0");
                Toast msg = Toast.makeText(getBaseContext(),
                        "Açtın", Toast.LENGTH_SHORT);
                msg.show();
            }
        });
        calkala_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    sendData("1");
                    Thread.sleep(50);
                    sendData("0");
                    Thread.sleep(50);
                    sendData("1");
                    Thread.sleep(50);
                    sendData("0");
                    Thread.sleep(50);
                    sendData("1");
                    Thread.sleep(50);
                    sendData("0");
                    Thread.sleep(50);
                    sendData("1");
                    Thread.sleep(50);
                    sendData("0");
                    Thread.sleep(50);
                    sendData("1");
                    Thread.sleep(50);
                    sendData("0");
                    Thread.sleep(50);
                    sendData("1");
                    Thread.sleep(50);
                    sendData("0");
                    Thread.sleep(50);
                    sendData("1");
                    Thread.sleep(50);
                    sendData("0");
                    Thread.sleep(50);
                    sendData("1");
                    Thread.sleep(50);
                    sendData("0");
                    Thread.sleep(50);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Toast msg = Toast.makeText(getBaseContext(),
                        "Çalkaladın ", Toast.LENGTH_SHORT);
                msg.show();
            }
        });





        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.d(TAG, "...In onResume - Attempting client connect...");
        BluetoothDevice device = btAdapter.getRemoteDevice(address);
        try {
            btSocket = device.createRfcommSocketToServiceRecord(MY_UUID);
        } catch (IOException e) {
            errorExit("Fatal Error", "In onResume() and socket create failed: " + e.getMessage() + ".");
        }

        // Discovery is resource intensive.  Make sure it isn't going on
        // when you attempt to connect and pass your message.
        btAdapter.cancelDiscovery();

        // Establish the connection.  This will block until it connects.
        Log.d(TAG, "...Connecting to Remote...");
        try {
            btSocket.connect();
            Log.d(TAG, "...Connection established and data link opened...");
        } catch (IOException e) {
            try {
                btSocket.close();
            } catch (IOException e2) {
                errorExit("Fatal Error", "In onResume() and unable to close socket during connection failure" + e2.getMessage() + ".");
            }
        }

        // Create a data stream so we can talk to server.
        Log.d(TAG, "...Creating Socket...");

        try {
            outStream = btSocket.getOutputStream();
        } catch (IOException e) {
            errorExit("Fatal Error", "In onResume() and output stream creation failed:" + e.getMessage() + ".");
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        Log.d(TAG, "...In onPause()...");

        if (outStream != null) {
            try {
                outStream.flush();
            } catch (IOException e) {
                errorExit("Fatal Error", "In onPause() and failed to flush output stream: " + e.getMessage() + ".");
            }
        }

        try {
            btSocket.close();
        } catch (IOException e2) {
            errorExit("Fatal Error", "In onPause() and failed to close socket." + e2.getMessage() + ".");
        }
    }

    private void checkBTState() {
        // Check for Bluetooth support and then check to make sure it is turned on

        // Emulator doesn't support Bluetooth and will return null
        if (btAdapter == null) {
            errorExit("Fatal Error", "Bluetooth Not supported. Aborting.");
        } else {
            if (btAdapter.isEnabled()) {
                Log.d(TAG, "...Bluetooth is enabled...");
            } else {
                //Prompt user to turn on Bluetooth
                Intent enableBtIntent = new Intent(btAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        }
    }

    private void errorExit(String title, String message) {
        Toast msg = Toast.makeText(getBaseContext(),
                title + " - " + message, Toast.LENGTH_SHORT);
        msg.show();
        finish();
    }

    private void sendData(String message) {

        byte[] msgBuffer = message.getBytes();

        Log.d(TAG, "...Sending data: " + message + "...");

        try {
            outStream.write(msgBuffer);
        } catch (IOException e) {
            String msg = "durduruldu: " + e.getMessage();
            if (address.equals("20:15:07:24:16:79"))
                msg = msg + ".\n\nUpdate your server address from 20:15:07:24:16:79 to the correct address on line 37 in the java code";
            msg = msg + ".\n\nCheck that the SPP UUID: " + MY_UUID.toString() + " exists on server.\n\n";

            errorExit("Fatal Error", msg);
        }
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "LEDOnOff Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.ledonoff/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "LEDOnOff Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.ledonoff/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
