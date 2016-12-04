package com.islavdroid.horoscope;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.islavdroid.horoscope.notification.NotificationReceiver;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity  extends AppCompatActivity {
    @BindView(R.id.saveBtn) Button saveBtn;
    @BindView(R.id.spinner) Spinner spinner;
    @BindView(R.id.timeEdit) EditText timeEdit;


    TimePickerDialog mTimePicker;
    int mHour;
    int mMinute;
    Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        String[] data = {"capricorn", "aquarius", "pisces", "aries", "taurus","gemini","cancer","leo","virgo","libra","scorpio","sagittarius"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        calendar =Calendar.getInstance();


    }




    @OnClick(R.id.timeEdit)
    public void setTime(){
      int hour = calendar.get(Calendar.HOUR_OF_DAY);
      int  minute = calendar.get(Calendar.MINUTE);
        mTimePicker = new TimePickerDialog(SettingActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                timeEdit.setText( selectedHour + ":" + selectedMinute);
                mHour=selectedHour;
                mMinute=selectedMinute;


            }
        }, hour, minute, true);//с помощью hour и minute мы задаём  время отображаемое в диалоге,true - 24 часа
       // mTimePicker.setTitle("Select Time");


        mTimePicker.show();
    }

    @OnClick(R.id.saveBtn)
    public void pushOn(){

        //возвращает объект класса Calendar для региональных данных и часового пояса по умолчанию


        calendar.set(Calendar.HOUR_OF_DAY,mHour);
        calendar.set(Calendar.MINUTE,mMinute);


        Intent intent =new Intent(getApplicationContext(),NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),100,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        //Служба Alarm Service используется для отправки пользователю разовых или повторяющихся сообщений в заданное время
        AlarmManager alarmManager =(AlarmManager)getSystemService(ALARM_SERVICE);
        //setRepeating() - задаёт повторяющиеся сигнализации с фиксированным временным интервалом
        //RTC_WAKEUP - выводит устройство из режима ожидания для запуска ожидающего намерения в указанное время.
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);


    }



}
