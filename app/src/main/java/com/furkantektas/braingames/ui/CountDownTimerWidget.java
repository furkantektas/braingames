package com.furkantektas.braingames.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.widget.TextView;

import com.furkantektas.braingames.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Custom CountDownTimer widget.
 * Created by Furkan Tektas on 11/20/14.
 */
public class CountDownTimerWidget extends TextView {
    private CountDownTimer mCountDownTimer;
    private long mTimeUntilFinished = 1000l; // in msec
    private int mUpdateInterval = 1000; // in msec
    private CountdownTimerInterface mListener;
    private String mFormat = "%s"; // just like Chronometer format
    private boolean mIsPaused = false;
    private SimpleDateFormat mTimeFormatter = new SimpleDateFormat("m:ss");
    private boolean mIsRunning = false;

    public CountDownTimerWidget(Context context) {
        super(context);
        initTimer();
    }

    public CountDownTimerWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        extractAttrs(context,attrs);
        initTimer();
    }

    public CountDownTimerWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        extractAttrs(context,attrs);
        initTimer();
    }

    private void extractAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.CountDownTimerWidget,
                0, 0);

        try {
            mTimeUntilFinished = (long) a.getInt(R.styleable.CountDownTimerWidget_time, 1000);
            mUpdateInterval = a.getInt(R.styleable.CountDownTimerWidget_update_interval, 1000);
            setFormat(a.getString(R.styleable.CountDownTimerWidget_format));
        } finally {
            a.recycle();
        }
        updateText();
    }

    public void initTimer() {
        mCountDownTimer = new CountDownTimer(getTimeUntilFinished(), getUpdateInterval()) {
            @Override
            public void onTick(long l) {
                CountDownTimerWidget.this.onTick(l);
            }

            @Override
            public void onFinish() {
                CountDownTimerWidget.this.onFinish();
            }
        };
    }

    public void onTick(long l) {
        mTimeUntilFinished  = l;
        if(mListener != null)
            mListener.onTick(l);
        updateText();
    }

    public void onFinish() {
        mIsRunning = false;
        if(mListener != null)
            mListener.onFinish();
    }

    public void pause() {
        if (mIsRunning) {
            mCountDownTimer.cancel();
            mIsPaused = true;
            mIsRunning = false;
            System.out.println("Countdown timer - Cancel");
        }
    }

    public void start(boolean restart) {
        if(mIsPaused) {
            mIsPaused = false;
            initTimer();
        }
        if(!mIsRunning) {
            mCountDownTimer.start();
            mIsRunning = true;
        }
        System.out.println("Countdown timer - Start");
    }

    /**
     * Starts or resumes the countdown
     */
    public void start() {
        if(mIsRunning)
            start(true);
        else
            start(false);
    }

    public CountdownTimerInterface getListener() {
        return mListener;
    }

    public void setListener(CountdownTimerInterface mListener) {
        this.mListener = mListener;
    }

    public long getTimeUntilFinished() {
        return mTimeUntilFinished;
    }

    public void setTimeUntilFinished(long mTimeUntilFinished) {
        this.mTimeUntilFinished = mTimeUntilFinished;
        mCountDownTimer.cancel();
        initTimer();
        updateText();
    }

    public int getUpdateInterval() {
        return mUpdateInterval;
    }

    public void setUpdateInterval(int mUpdateInterval) {
        this.mUpdateInterval = mUpdateInterval;
        mCountDownTimer.cancel();
        initTimer();
    }

    public String getFormat() {
        return mFormat;
    }

    public void setFormat(String mFormat) {
        if(mFormat == null)
            mFormat = "%s";
        this.mFormat = mFormat;
    }

    public interface CountdownTimerInterface {
        public void onTick(long l);
        public void onFinish();
    }

    private void updateText() {
        Date d = new Date(mTimeUntilFinished);
        setText(String.format(mFormat, mTimeFormatter.format(d)));
    }
}
