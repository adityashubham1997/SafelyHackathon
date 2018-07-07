package com.example.admin.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by ankan on 04/05/18.
 */

    public class Automatic extends AppCompatActivity implements SensorEventListener {

        private static final String TAG = "Automatic";
        private SensorManager sensorManager;
        private Sensor accelerometer, mGyro, mMagno, mLight, mPressure, mTemp, mHumid;
        public double acc, threshold;
        String phone;
        String DEFAULT = "N/A";
        int j=0;

        TextView xValue, yValue, zValue, xGyroValue, yGyroValue, zGyroValue, xMagnoValue, yMagnoValue, zMagnoValue, light, temp, pressure, humi, acceleration;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.automatic_content);

            xValue = (TextView)findViewById(R.id.xValue);
            yValue = (TextView)findViewById(R.id.yValue);
            zValue = (TextView)findViewById(R.id.zValue);

            xGyroValue = (TextView)findViewById(R.id.xGyroValue);
            yGyroValue = (TextView)findViewById(R.id.yGyroValue);
            zGyroValue = (TextView)findViewById(R.id.zGyroValue);

            xMagnoValue = (TextView)findViewById(R.id.xMagnoValue);
            yMagnoValue = (TextView)findViewById(R.id.yMagnoValue);
            zMagnoValue = (TextView)findViewById(R.id.zMagnoValue);

            light = (TextView)findViewById(R.id.light);
            temp = (TextView)findViewById(R.id.temp);
            pressure = (TextView)findViewById(R.id.pressure);
            humi = (TextView)findViewById(R.id.humi);
            acceleration = (TextView)findViewById(R.id.acceleration);

            //Movetasktoback(true);


            Log.d(TAG, "onCreate: Initializing Sensor Services");

            sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);

            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            if(accelerometer !=null){
                sensorManager.registerListener(Automatic.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
                Log.d(TAG, "onCreate: Registered accelerometer listener");
            }
            else {
                xValue.setText("Accelerometer is not supported");
                yValue.setText("Accelerometer is not supported");
                zValue.setText("Accelerometer is not supported");
                acceleration.setText("Accelerometer is not supported");
            }

            mGyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
            if(mGyro !=null){
                sensorManager.registerListener(Automatic.this, mGyro, SensorManager.SENSOR_DELAY_NORMAL);
                Log.d(TAG, "onCreate: Registered Gyroscope listener");
            }
            else {
                xGyroValue.setText("Gyroscope is not supported");
                yGyroValue.setText("Gyroscope is not supported");
                zGyroValue.setText("Gyroscope is not supported");
            }

            mMagno = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
            if(mMagno !=null){
                sensorManager.registerListener(Automatic.this, mMagno, SensorManager.SENSOR_DELAY_NORMAL);
                Log.d(TAG, "onCreate: Registered Magno listener");
            }
            else {
                xMagnoValue.setText("Magno is not supported");
                yMagnoValue.setText("Magno is not supported");
                zMagnoValue.setText("Magno is not supported");
            }

            mLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
            if(accelerometer !=null){
                sensorManager.registerListener(Automatic.this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
                Log.d(TAG, "onCreate: Registered Light listener");
            }
            else {
                light.setText("Light Sensor is not supported");
            }

            mPressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
            if(mPressure !=null){
                sensorManager.registerListener(Automatic.this, mPressure, SensorManager.SENSOR_DELAY_NORMAL);
                Log.d(TAG, "onCreate: Registered Pressure listener");
            }
            else {
                pressure.setText("Barometer is not supported");
            }

            mTemp = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
            if(mTemp !=null){
                sensorManager.registerListener(Automatic.this, mTemp, SensorManager.SENSOR_DELAY_NORMAL);
                Log.d(TAG, "onCreate: Registered temperature listener");
            }
            else {
                temp.setText("Temperature is not supported");
            }

            mHumid = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
            if(mHumid !=null){
                sensorManager.registerListener(Automatic.this, mHumid, SensorManager.SENSOR_DELAY_NORMAL);
                Log.d(TAG, "onCreate: Registered Humidity listener");
            }
            else {
                humi.setText("Humidity sensor is not supported");
            }


        }
        //@Nullable
        //@Override
        //public IBinder onBind(Intent intent)
        //{
         //return null;
        //}

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
    public void backToMain(View view)
    {
        Intent intent = new Intent(this, com.example.admin.myapplication.MainActivity.class);
        startActivity(intent);
        //finish();
    }



    @Override
        public void onSensorChanged(SensorEvent sensorEvent) {


            Sensor sensor = sensorEvent.sensor;
            if(sensor.getType() == Sensor.TYPE_ACCELEROMETER){
                Log.d(TAG, "onSensorChanged: X:" + sensorEvent.values[0] + "Y:" + sensorEvent.values[1] + "Z:" + sensorEvent.values[2]);
                xValue.setText("xValue: "+sensorEvent.values[0]);
                yValue.setText("yValue: "+sensorEvent.values[1]);
                zValue.setText("zValue: "+sensorEvent.values[2]);
                acc = Math.sqrt((sensorEvent.values[0]*sensorEvent.values[0])+ (sensorEvent.values[1]*sensorEvent.values[1])+ (sensorEvent.values[2]*sensorEvent.values[2]));
                acc = acc/(9.8);
                threshold = 1.5;
                acceleration.setText("acceleration: " +acc + "G"
                );
                if (j<=2 && acc>=threshold)
                {
                    getdata3();
                    j++;
                    Uri uri=Uri.parse("Hey, I am in trouble at http://maps.google.com/?q=");
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phone, null, uri.toString(), null, null);
                    if (ActivityCompat.checkSelfPermission(Automatic.this,
                            android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED)
                    {
                        ActivityCompat.requestPermissions(Automatic.this, new String[]{android.Manifest.permission.SEND_SMS}, 1);
                        return;
                    }
                }
            }

            else if(sensor.getType() == Sensor.TYPE_GYROSCOPE){
                xGyroValue.setText("xGyroValue: "+sensorEvent.values[0]);
                yGyroValue.setText("xGyroValue: "+sensorEvent.values[1]);
                zGyroValue.setText("xGyroValue: "+sensorEvent.values[2]);
            }

            else if(sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
                xMagnoValue.setText("xMagnoValue: "+sensorEvent.values[0]);
                yMagnoValue.setText("xMagnoValue: "+sensorEvent.values[1]);
                zMagnoValue.setText("xMagnoValue: "+sensorEvent.values[2]);
            }

            else if(sensor.getType() == Sensor.TYPE_LIGHT){
                light.setText("Light: "+sensorEvent.values[0]);
            }

            else if(sensor.getType() == Sensor.TYPE_PRESSURE){
                pressure.setText("Pressure: "+sensorEvent.values[0]);
            }

            else if(sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE){
                temp.setText("Temperature: "+sensorEvent.values[0]);
            }

            else if(sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY){
                humi.setText("Humidity: "+sensorEvent.values[0]);
            }

        }

    private void getdata3()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        phone = sharedPreferences.getString("emergencycontactnumber",DEFAULT);
    }


}


