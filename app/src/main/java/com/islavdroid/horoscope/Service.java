package com.islavdroid.horoscope;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.islavdroid.horoscope.api.ApiClient;
import com.islavdroid.horoscope.model.Horoscope;
import com.islavdroid.horoscope.point.TodayPoint;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Service extends IntentService  {
    public static final String EXTRA_MESSAGE = "message";
    public static final String EXTRA_HOUR = "hour";
    public static final String EXTRA_MINUTE = "minute";
     private String redactHoroscopeText;
    public static final int NOTIFICATION_ID = 999;
    private String sunSign;
    private int hour;
    private int minute;
    private Calendar calendar;
    public Service() {
        super("Service");
    }

    @Override
    protected void onHandleIntent(Intent intent) {


         sunSign = intent.getStringExtra(EXTRA_MESSAGE);
        hour=intent.getIntExtra(EXTRA_HOUR,0);
        minute=intent.getIntExtra(EXTRA_MINUTE,0);
        Log.v("tag",hour+"");
        Log.v("tag",minute+"");
        calendar=Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,hour);
        calendar.set(Calendar.MINUTE,minute);

        final TodayPoint apiService = ApiClient.getHoroscope().create(TodayPoint.class);
        Call<Horoscope> call =apiService.getTodayHoroscope(sunSign);
        call.enqueue(new Callback<Horoscope>() {
            @Override
            public void onResponse(Call<Horoscope> call, Response<Horoscope> response) {
                Horoscope today =response.body();
                String horoscopeText = today.getHoroscope();
                 redactHoroscopeText =horoscopeText.replace("['","").replace("[u'","").
                        replace("Ganesha","Astrologer").replace("\\u"," ").replace("..",".");


                showHoroscope(redactHoroscopeText);

            }

            @Override
            public void onFailure(Call<Horoscope> call, Throwable t) {

            }
        });


    }


    private void showHoroscope( String horoscope) {


        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("sunsign",sunSign);
        // Объект TaskStackBuilder позволяет работать с историей активностей, используемой кнопкой Назад
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        //Эти строки обеспечивают правильную работу кнопки Назад при запуске активности.
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(intent);
        //Если подходящий отложенный интент уже существует, оставить его и заменить дополнительные данные данными из нового интента
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        //строим notification

        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(getString(R.string.app_name))
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_MAX)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(pendingIntent)
                .setContentText(horoscope)
                .build();

        //Вывести уведомление с использованием службы уведомлений Android
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, notification);



    }
}
