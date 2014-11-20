package com.furkantektas.braingames.ui.games;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;

import com.furkantektas.braingames.BuildConfig;
import com.furkantektas.braingames.R;
import com.furkantektas.braingames.ui.CountDownTimerWidget;

/**
 * Created by Furkan Tektas on 11/20/14.
 */
public abstract class AbstractCountDownTimerGameActivity extends AbstractGameActivity {
    private CountDownTimerWidget mCountDownTimer;
    private static final String LOG_NAME = "AbstractCountDownTimerGameActivity";

    private void findTimer() {
        mCountDownTimer = (CountDownTimerWidget) findViewById(R.id.countdown);
        mCountDownTimer.setListener(new CountDownTimerWidget.CountdownTimerInterface() {
            @Override
            public void onTick(long l) {
            }

            @Override
            public void onFinish() {
                finishGame();
            }
        });
    }

    public void startTimer() {
        if(mCountDownTimer == null)
            findTimer();
        mCountDownTimer.start();
    }

    public void pauseTimer() {
        if(mCountDownTimer == null)
            findTimer();
        mCountDownTimer.pause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        pauseTimer();
        if(BuildConfig.DEBUG)
            Log.i(LOG_NAME, "stop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        pauseTimer();
        if(BuildConfig.DEBUG)
            Log.i(LOG_NAME,"pause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        startTimer();
        if(BuildConfig.DEBUG)
            Log.i(LOG_NAME,"resume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        startTimer();
        if(BuildConfig.DEBUG)
            Log.i(LOG_NAME,"restart");
    }
}
