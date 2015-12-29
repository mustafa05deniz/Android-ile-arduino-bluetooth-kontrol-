package com.example.ledonoff;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class mutfak extends Activity {

    Button Ac,Ev,Ayarlar,Gecmis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salon);

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
