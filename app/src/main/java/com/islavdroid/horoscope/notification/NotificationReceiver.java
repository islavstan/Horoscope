package com.islavdroid.horoscope.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.islavdroid.horoscope.DetailActivity;
import com.islavdroid.horoscope.MainActivity;
import com.islavdroid.horoscope.R;
import com.islavdroid.horoscope.SettingActivity;
import com.islavdroid.horoscope.api.ApiClient;
import com.islavdroid.horoscope.model.Horoscope;
import com.islavdroid.horoscope.point.TodayPoint;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NotificationReceiver  extends BroadcastReceiver {
    String redactHoroscopeText;




    @Override
    public void onReceive(Context context, Intent intent) {


        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent repeating_intent = new Intent(context,MainActivity.class);
        repeating_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent =PendingIntent.getActivity(context,100,repeating_intent,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context).
                setContentIntent(pendingIntent).setSmallIcon(R.drawable.aries).
                setContentTitle("Рак").setContentText("ghbff");
        notificationManager.notify(100,builder.build());


    }
}
