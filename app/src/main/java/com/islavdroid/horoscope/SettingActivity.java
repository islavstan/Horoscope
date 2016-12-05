package com.islavdroid.horoscope;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.islavdroid.horoscope.api.ApiClient;
import com.islavdroid.horoscope.model.Horoscope;
import com.islavdroid.horoscope.notification.NotificationReceiver;
import com.islavdroid.horoscope.point.TodayPoint;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingActivity  extends AppCompatActivity {
    @BindView(R.id.saveBtn) Button saveBtn;
    @BindView(R.id.spinner) Spinner spinner;
    @BindView(R.id.timeEdit) EditText timeEdit;
    @BindView(R.id.notifySwitch) Switch notifySwitch;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.sunsignTextview) TextView sunsignTextview;
    @BindView(R.id.timeTextview) TextView timeTextview;


    TimePickerDialog mTimePicker;
    int mHour;
    int mMinute;
    Calendar calendar;
    boolean notifyOn=false;
    public static String horoscopeText;
    PreferenceHelper preferenceHelper;
    String sunSign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Notification");
        spinner.setEnabled(false);
        timeEdit.setEnabled(false);
        saveBtn.setEnabled(false);


        PreferenceHelper.getInstance().init(getApplicationContext());
        preferenceHelper = PreferenceHelper.getInstance();
        notifySwitch.setChecked(preferenceHelper.getBoolean(PreferenceHelper.NOTIFICATION));
      //  toolbar.getMenu().clear();
        String[] data = {"capricorn", "aquarius", "pisces", "aries", "taurus","gemini","cancer","leo","virgo","libra","scorpio","sagittarius"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        calendar =Calendar.getInstance();





    }


  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(notifyOn) {
            getMenuInflater().inflate(R.menu.menu_setting, menu);

        }
        return true;
    }*/


   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.save){

        }
        return true;
    }*/

    @OnCheckedChanged(R.id.notifySwitch)
    public void onNotify(){
        if(notifySwitch.isChecked()){
            //сохраняем состояние флага
            preferenceHelper.putBoolean(PreferenceHelper.NOTIFICATION,notifySwitch.isChecked());
           // Toast.makeText(this,"notification enabled",Toast.LENGTH_SHORT).show();
            notifyOn=true;
            sunsignTextview.setTextColor(getResources().getColor(R.color.black));
            timeTextview.setTextColor(getResources().getColor(R.color.black));
            spinner.setEnabled(true);
            timeEdit.setEnabled(true);
            saveBtn.setEnabled(true);

        }else{
         //   Toast.makeText(this,"notification disabled",Toast.LENGTH_SHORT).show();
            notifyOn=false;
            sunsignTextview.setTextColor(getResources().getColor(R.color.gray));
            timeTextview.setTextColor(getResources().getColor(R.color.gray));
            spinner.setEnabled(false);
            timeEdit.setEnabled(false);
            saveBtn.setEnabled(false);
        }

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

        sunSign = spinner.getSelectedItem().toString();
        calendar.set(Calendar.HOUR_OF_DAY,mHour);
        calendar.set(Calendar.MINUTE,mMinute);
        Intent intent=new Intent(getApplicationContext(),Service.class);
        intent.putExtra(Service.EXTRA_MESSAGE,
                sunSign);
        intent.putExtra(Service.EXTRA_HOUR,mHour);
        intent.putExtra(Service.EXTRA_MINUTE,mMinute);
        //startService(intent);
      //  Intent intent =new Intent(getApplicationContext(),NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getService(getApplicationContext(),100,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        //Служба Alarm Service используется для отправки пользователю разовых или повторяющихся сообщений в заданное время
       AlarmManager alarmManager =(AlarmManager)getSystemService(ALARM_SERVICE);
        //setRepeating() - задаёт повторяющиеся сигнализации с фиксированным временным интервалом
        //RTC_WAKEUP - выводит устройство из режима ожидания для запуска ожидающего намерения в указанное время.
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);


    }



}
