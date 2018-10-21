package com.coxtunes.abir.salahslient;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class Alarm_Receiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        //------got intent for call ringtone---------//
        Intent service_intent = new Intent(context,RingToneService.class);
        context.startService(service_intent);

    }
}
