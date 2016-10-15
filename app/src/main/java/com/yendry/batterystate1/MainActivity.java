package com.yendry.batterystate1;




import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    public TextView txt;


    public MainActivity(){
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = (TextView) findViewById(R.id.txt);

        SharedPreferences prefs = getSharedPreferences("key", MODE_PRIVATE);
        long restoredText = prefs.getLong("startTime", 0);
        txt.setText("\nDays: "+prefs.getLong("totaldays",0)+
                    "\nHours: "+prefs.getLong("totalhours",0)+
                    "\nMins: "+prefs.getLong("totalmins",0)+
                    "\nSec: "+prefs.getLong("totalsec",0));
    }

}
