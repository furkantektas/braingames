package com.furkantektas.braingames.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;

import com.furkantektas.braingames.R;
import com.furkantektas.braingames.ui.MainActivity;

import java.util.HashMap;

/**
 * Created by Furkan Tektas on 11/16/14.
 * Reference: http://www.androidsnippets.com/playing-sound-fx-for-a-game
 */
public class SFX {
    public static final int SFX_POP = 1;
    public static final int SFX_DENY = 2;
    public static final String VOLUME_PREF_KEY = "volume";

    private SoundPool mSoundPool;
    private HashMap<Integer, Integer> mSoundPoolMap;
    private Context mContext;
    private SharedPreferences mPrefs;
    private boolean mIsVolumeOn;

    public SFX(Context context) {
        this.mContext = context;
        mSoundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 75);
        mSoundPoolMap = new HashMap<Integer, Integer>();
        mSoundPoolMap.put(SFX_POP, mSoundPool.load(context, R.raw.pop, 1));
        mSoundPoolMap.put(SFX_DENY, mSoundPool.load(context, R.raw.deny, 1));
        mPrefs = context.getSharedPreferences(MainActivity.PREF_KEY, Context.MODE_PRIVATE);
        mIsVolumeOn = mPrefs.getBoolean(VOLUME_PREF_KEY,true);
    }

    public void play(int sound) {
    /* Updated: The next 4 lines calculate the current volume in a scale of 0.0 to 1.0 */
        if(isVolumeOn()) {
            AudioManager mgr = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
            float streamVolumeCurrent = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
            float streamVolumeMax = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            float volume = streamVolumeCurrent / streamVolumeMax;

            /* Play the sound with the correct volume */
            mSoundPool.play(mSoundPoolMap.get(sound), volume, volume, 1, 0, 1f);
        }
    }

    public void pop() {
        play(SFX_POP);
    }

    public void deny() {
        play(SFX_DENY);
    }

    public void setVolumePref(boolean currentStatus) {
        mIsVolumeOn = currentStatus;
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putBoolean(VOLUME_PREF_KEY, mIsVolumeOn).commit();
    }

    public boolean isVolumeOn() {
        return mIsVolumeOn;
    }
}
