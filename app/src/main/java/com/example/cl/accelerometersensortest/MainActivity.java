package com.example.cl.accelerometersensortest;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {

    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(Listener,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(sensorManager != null){
            sensorManager.unregisterListener(Listener);
        }
    }
   private SensorEventListener Listener = new SensorEventListener() {
       @Override
       public void onSensorChanged(SensorEvent sensorEvent) {
           //加速度可能会是负值，所以要取它们的绝对值
           float xValue = Math.abs(sensorEvent.values[0]);
           float yValue = Math.abs(sensorEvent.values[1]);
           float zValue = Math.abs(sensorEvent.values[2]);
           if (xValue >15 || yValue > 15 || zValue > 15){
               //认为用户摇动了手机，触发摇一摇逻辑
               Toast.makeText(MainActivity.this,"摇一摇",Toast.LENGTH_SHORT).show();
           }
       }

       @Override
       public void onAccuracyChanged(Sensor sensor, int i) {

       }
   };



}
