package com.furkantektas.braingames.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.furkantektas.braingames.R;
import com.furkantektas.braingames.data.SFX;
import com.furkantektas.braingames.ui.games.GameCalculateFastActivity;
import com.furkantektas.braingames.ui.games.GameColorMatchActivity;
import com.furkantektas.braingames.ui.games.GameCompareFastActivity;
import com.furkantektas.braingames.ui.games.GameFindOperationActivity;
import com.furkantektas.braingames.ui.games.GameMemoryMatrix;
import com.furkantektas.braingames.ui.games.GameShapeMatchActivity;
import com.furkantektas.braingames.utils.GameStatManager;


public class MainActivity extends Activity {
    public static final String PREF_KEY = "prefs";
    public static final String VOLUME_PREF_KEY = "volume";

    private static SFX mSFX;
    private static GameStatManager mGameStatManager;
    private static boolean mIsVolumeOn = true;
    private static boolean mIsInitialized = false;

    private SharedPreferences prefs;
    private ImageButton toggle_volume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!mIsInitialized)
            init();

        Button b = (Button) findViewById(R.id.button_color_match);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),GameColorMatchActivity.class);
                startActivity(i);
            }
        });


        Button b2 = (Button) findViewById(R.id.button_shape_match);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),GameShapeMatchActivity.class);
                startActivity(i);
            }
        });

        Button find_operation = (Button) findViewById(R.id.button_find_operation);
        find_operation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent find_operation_intent = new Intent(getApplicationContext(),GameFindOperationActivity.class);
                startActivity(find_operation_intent);
            }
        });

        Button calculate_fast = (Button) findViewById(R.id.button_calculate_fast);
        calculate_fast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent calculate_fast_intent = new Intent(getApplicationContext(),GameCalculateFastActivity.class);
                startActivity(calculate_fast_intent);
            }
        });

        Button b5 = (Button) findViewById(R.id.button_memory_matrix);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),GameMemoryMatrix.class);
                startActivity(i);
            }
        });

        Button compare_fast = (Button) findViewById(R.id.button_compare_fast);
        compare_fast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),GameCompareFastActivity.class);
                startActivity(i);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_about:
                Intent i = new Intent(getApplicationContext(),AboutActivity.class);
                startActivity(i);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public static SFX getSFX() {
        return mSFX;
    }

    public static void setSFX(SFX mSFX) {
        MainActivity.mSFX = mSFX;
    }


    public static GameStatManager getGameStatManager() {
        return mGameStatManager;
    }

    public static void setGameStatManager(GameStatManager mGameStatManager) {
        MainActivity.mGameStatManager = mGameStatManager;
    }

    private void init() {
        System.out.println("Initializing main");
        mIsInitialized = true;
        setSFX(new SFX(getApplicationContext()));
        setGameStatManager(new GameStatManager(getApplicationContext()));
        prefs = getApplicationContext().getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE);
        initVolumePref();
        toggle_volume = (ImageButton) findViewById(R.id.toggle_volume);
        updateVolumeToggle();

        toggle_volume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIsVolumeOn = !isVolumeOn();
                setVolumePref(isVolumeOn());
                updateVolumeToggle();

                if(mIsVolumeOn)
                    mSFX.pop(); // TODO: sometimes pop doesn't play
            }
        });
    }

    private void updateVolumeToggle() {
        toggle_volume.setImageResource(isVolumeOn() ? R.drawable.ic_volume_up : R.drawable.ic_volume_mute);

    }

    private void setVolumePref(boolean currentStatus) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(VOLUME_PREF_KEY, isVolumeOn()).commit();
    }

    private void initVolumePref() {
        mIsVolumeOn = prefs.getBoolean(VOLUME_PREF_KEY,true);
    }


    public static boolean isVolumeOn() {
        return mIsVolumeOn;
    }
}
