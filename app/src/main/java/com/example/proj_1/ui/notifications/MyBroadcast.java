package com.example.proj_1.ui.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

import com.example.proj_1.R;

public class MyBroadcast extends BroadcastReceiver {
    MediaPlayer mp;
    @Override
    public void onReceive(Context context, Intent intent)
    {
        mp=MediaPlayer.create(context, R.raw.alarm);
        mp.start();
        Toast t=Toast.makeText(context,"...Drink Water...",Toast.LENGTH_LONG);
        t.show();
    }

}
