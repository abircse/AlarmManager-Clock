package com.coxtunes.abir.salahslient;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;


public class RingToneService extends Service {


    MediaPlayer mediatone ;



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);

        ////-----Create a instance of media player---//
        mediatone = MediaPlayer.create(this,R.raw.sound);
        mediatone.start();


        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        // Cancel the persistent notification.


        // Tell the user we stopped.
        Toast.makeText(this, "On destry call", Toast.LENGTH_SHORT).show();
    }



}