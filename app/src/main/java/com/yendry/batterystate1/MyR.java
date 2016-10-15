package com.yendry.batterystate1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by User on 10/14/2016.
 */

public class MyR extends BroadcastReceiver {
    private String MyPREFERENCES = "key";

    @Override
    public void onReceive(Context context, Intent intent) {

        SharedPreferences.Editor editor = context.getSharedPreferences(MyPREFERENCES, context.MODE_PRIVATE).edit();
        SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, context.MODE_PRIVATE);



        if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)){
            long curreenInMilisec = System.currentTimeMillis();
            editor.putLong("startTime", curreenInMilisec);
            editor.commit();
            Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show();

        }else  if (intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)){
            long curreenInMilisec = System.currentTimeMillis();

            long startTime = prefs.getLong("startTime",0);

            editor.putLong("timeRecord", prefs.getLong("timeRecord",0)+(curreenInMilisec-startTime)).commit();
            //editor.putLong("timeRecord",0).commit();

            long timeRecord = prefs.getLong("timeRecord",0);

            long[] result = printDifference(timeRecord);


            editor.putLong("totalday",result[0]);
            editor.putLong("totalhours",result[1]);
            editor.putLong("totalmins",result[2]);
            editor.putLong("totalsec",result[3]);

            editor.commit();

            Toast.makeText(context, "Disconnected ", Toast.LENGTH_SHORT).show();
        }




    }

    public long[] printDifference(long different){

        //milliseconds
        //long different = endDate - startDate;




        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        Log.d("la hora", ""+elapsedDays+" "+elapsedHours+" "+elapsedMinutes+" "+elapsedSeconds);
        long[] arr = new long[]{elapsedDays, elapsedHours, elapsedMinutes, elapsedSeconds};
        return arr;
    }
}
