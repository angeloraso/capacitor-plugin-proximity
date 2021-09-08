package com.angeloraso.plugins.proximity;

import static android.content.Context.POWER_SERVICE;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.PowerManager;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class Proximity implements SensorEventListener {
    private final Context mContext;
    private final AppCompatActivity mActivity;
    private final SensorManager mSensorManager;
    private final PowerManager mPowerManager;
    private PowerManager.WakeLock mWakeLock;
    private SensorResult mSensorResult;
    private SensorStatus mSensorStatus;
    private Sensor mSensor;
    private final long TIMEOUT = 30000;
    private long mLastAccessTime;
    private long mTimeStamp;

    Proximity(final AppCompatActivity activity, final Context context) {
        mActivity = activity;
        mContext = context;
        mSensorManager = (SensorManager) mActivity.getSystemService(Context.SENSOR_SERVICE);
        mPowerManager = (PowerManager) mActivity.getSystemService(POWER_SERVICE);
        mWakeLock = null;
        mSensorStatus = SensorStatus.STOPPED;
        mTimeStamp = 0;
    }

    public SensorResult getProximity() {
        if (mSensorStatus != SensorStatus.RUNNING) {
            return null;
        }

        return mSensorResult;
    }

    public SensorStatus getStatus() {
        return mSensorStatus;
    }

    public void start() {
        // If already starting or running, then just return
        if ((mSensorStatus == SensorStatus.RUNNING) || (mSensorStatus == SensorStatus.STARTING)) {
            return;
        }

        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        mSensorManager.registerListener((SensorEventListener) this, this.mSensor, SensorManager.SENSOR_DELAY_NORMAL);
        mLastAccessTime = System.currentTimeMillis();
        mSensorStatus = SensorStatus.STARTING;

        Log.d("ProximitySensorListener", "XXX enableProximityScreenOff");
        if(mWakeLock != null) {
            mWakeLock.release();
        }
        mWakeLock = mPowerManager.newWakeLock(PowerManager.PROXIMITY_SCREEN_OFF_WAKE_LOCK, Proximity.this.toString());
        mWakeLock.acquire();
    }

    public void stop() {
        if(mWakeLock != null) {
            mWakeLock.release();
            mWakeLock = null;
        }

        if (mSensorStatus != SensorStatus.STOPPED) {
            mSensorManager.unregisterListener((SensorEventListener) this);
        }

        mSensorStatus = SensorStatus.STOPPED;
    }

    public void onDestroy() {
        stop();
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

    public void onSensorChanged(SensorEvent event) {
        SensorResult proximity;

        Log.d("ProximitySensorListener", "XXX onSensorChanged sensor length -> " + event.values.length);
        Log.d("ProximitySensorListener", "XXX onSensorChanged [0]-> " + event.values[0] + " [1]-> " + event.values[1] + " [2]-> " + event.values[2]);

        if (event.values[0] == 0) {
            proximity = SensorResult.NEAR;
        } else {
            proximity = SensorResult.FAR;
        }

        // Save proximity
        mTimeStamp = System.currentTimeMillis();
        mSensorResult = proximity;
        mSensorStatus = SensorStatus.RUNNING;

        // If proximity hasn't been read for TIMEOUT time, then turn off sensor to save power
        if ((mTimeStamp - mLastAccessTime) > this.TIMEOUT) {
            stop();
        }
    }
}
