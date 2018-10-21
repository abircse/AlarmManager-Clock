package com.coxtunes.abir.salahslient;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {


    ///-----Initialize Object-----//
    AlarmManager alarmManager;
    TimePicker alarm_timepicker;
    TextView update_text;
    Context context;
    Button alarmstartbutton,alarmendbutton;
    PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //-----Initialize UI to JAVA-------//
        alarmstartbutton = findViewById(R.id.start);
        alarmendbutton = findViewById(R.id.end);
        alarm_timepicker = findViewById(R.id.mytimepicker);
        update_text = findViewById(R.id.shows);
        this.context = this;

        ///--------create a instance for calender---------///
        final Calendar calendar = Calendar.getInstance();
        ///--------Initialize Alarm manager---------//
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);


        ///------------Create a alarm Intent---------///
        final Intent my_intent = new Intent(this.context,Alarm_Receiver.class);


        ////-------------alarm start button---------------///
        alarmstartbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /////-----------start picking time from calender help by timepicker-------/
                calendar.set(Calendar.HOUR_OF_DAY, alarm_timepicker.getHour());
                calendar.set(Calendar.MINUTE, alarm_timepicker.getMinute());

                ////------After get hour & minit we set it in different int from picker---/
                int MYHOUR = alarm_timepicker.getHour();
                int MYMINITE = alarm_timepicker.getMinute();

                ///----convert int 2 data to string------------//
                String HOUR = String.valueOf(MYHOUR);
                String MINITE = String.valueOf(MYMINITE);

                //-----Check time will convert 24 hour Time to 12Hour time-----------//
                if (MYHOUR > 12)
                {
                    HOUR = String.valueOf(MYHOUR-12);
                }
                if (MYMINITE < 10)
                {
                    MINITE = "0" + String.valueOf(MYMINITE);
                }

                update_text.setText("ALARM WORK ON "+HOUR+":"+MINITE);


                ///------------Start Pending Intent for alarm reveicer-----------///
                pendingIntent = PendingIntent.getBroadcast(MainActivity.this,0,
                         my_intent, PendingIntent.FLAG_UPDATE_CURRENT);

                //-------------Set Alarm to alam manager--------------//
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),pendingIntent);




            }
        });


        alarmendbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alarmManager.cancel(pendingIntent);
                update_text.setText("ALAM OFF NOW");


            }
        });




    }
}
