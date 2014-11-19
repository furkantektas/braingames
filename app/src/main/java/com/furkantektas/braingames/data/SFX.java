package com.furkantektas.braingames.data;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.furkantektas.braingames.R;

import java.util.HashMap;

/**
 * Created by Furkan Tektas on 11/16/14.
 * Reference: http://www.androidsnippets.com/playing-sound-fx-for-a-game
 */
public class SFX {
    public static final int SFX_POP = 1;
    public static final int SFX_DENY = 2;

    private SoundPool soundPool;
    private HashMap<Integer, Integer> soundPoolMap;
    private Context context;

    public SFX(Context context) {
        this.context = context;
        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 75);
        soundPoolMap = new HashMap<Integer, Integer>();
        soundPoolMap.put(SFX_POP, soundPool.load(context, R.raw.pop, 1));
        soundPoolMap.put(SFX_DENY, soundPool.load(context, R.raw.deny, 1));
    }

    public void play(int sound) {
    /* Updated: The next 4 lines calculate the current volume in a scale of 0.0 to 1.0 */
        AudioManager mgr = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        float streamVolumeCurrent = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
        float streamVolumeMax = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        float volume = streamVolumeCurrent / streamVolumeMax;

        /* Play the sound with the correct volume */
        soundPool.play(soundPoolMap.get(sound), volume, volume, 1, 0, 1f);
    }

    public void pop() {
        play(SFX_POP);
        System.out.println("pop");
    }

    public void deny() {
        play(SFX_DENY);
        System.out.println("pop");
    }
}
