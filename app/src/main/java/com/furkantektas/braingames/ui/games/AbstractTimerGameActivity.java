package com.furkantektas.braingames.ui.games;

import android.os.SystemClock;
import android.util.Log;
import android.widget.Chronometer;

import com.furkantektas.braingames.BuildConfig;
import com.furkantektas.braingames.R;

/**
 * AbstractTimerGameActivity class is the predecessor class of all games using timer.
 * It handles all of the timer states.
 * Created by Furkan Tektas on 11/14/14.
 */
public abstract class AbstractTimerGameActivity extends AbstractGameActivity {
    private Chronometer mChronometer;
    private static final String LOG_NAME = "AbstractTimerGameActivity";
    private boolean mIsTimerRunning = false;
    private boolean mIsTimerInitialized = false;
    private long mTimeWhenStopped = 0l;


    protected void resetTimer() {
        if(mChronometer == null)
            mChronometer = (Chronometer) findViewById(R.id.chronometer);
        mChronometer.setBase(SystemClock.elapsedRealtime());
    }

    protected void startTimer() {
        startTimer(false);
    }

    protected void startTimer(boolean reset) {
        if(mChronometer == null)
            mChronometer = (Chronometer) findViewById(R.id.chronometer);

        if(!mIsTimerRunning) {
            // resetting or initializing timer
            if(reset || !mIsTimerInitialized) {
                resetTimer();
                mIsTimerInitialized = true;
            } else // resuming
                mChronometer.setBase(SystemClock.elapsedRealtime() - mTimeWhenStopped);
            mChronometer.start();
            mIsTimerRunning = true;
        }
    }

    protected void stopTimer() {
        if(mChronometer == null)
            mChronometer = (Chronometer) findViewById(R.id.chronometer);
        if(mIsTimerRunning) {
            mChronometer.stop();
            mTimeWhenStopped = getElapsedTime();
            mIsTimerRunning = false;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopTimer();
        if(BuildConfig.DEBUG)
            Log.i(LOG_NAME,"stop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopTimer();
        if(BuildConfig.DEBUG)
            Log.i(LOG_NAME,"pause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mIsTimerInitialized)
            startTimer();
        if(BuildConfig.DEBUG)
            Log.i(LOG_NAME,"resume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(mIsTimerInitialized)
            startTimer();
        if(BuildConfig.DEBUG)
            Log.i(LOG_NAME,"restart");
    }

    public boolean isTimerRunning() {
        return mIsTimerRunning;
    }

    public long getElapsedTime() {
        return SystemClock.elapsedRealtime() - mChronometer.getBase();
    }
}
