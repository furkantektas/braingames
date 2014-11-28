package com.furkantektas.braingames.ui;

import android.app.Activity;
import android.content.Intent;
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


public class MainActivity extends Activity {
    public static final String PREF_KEY = "prefs";
    private static SFX mSFX;
    private ImageButton mToggleVolume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        Button color_match = (Button) findViewById(R.id.button_color_match);
        color_match.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),GameColorMatchActivity.class);
                startActivity(i);
            }
        });


        Button shape_match = (Button) findViewById(R.id.button_shape_match);
        shape_match.setOnClickListener(new View.OnClickListener() {
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

        Button memory_matrix = (Button) findViewById(R.id.button_memory_matrix);
        memory_matrix.setOnClickListener(new View.OnClickListener() {
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

        Button graph = (Button) findViewById(R.id.button_graph);
        graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),GraphActivity.class);
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

    private void init() {
        if(mSFX == null)
            mSFX = new SFX(getApplicationContext());
        mToggleVolume = (ImageButton) findViewById(R.id.toggle_volume);

        mToggleVolume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSFX.setVolumePref(!mSFX.isVolumeOn());
                updateVolumeToggle();

                if (mSFX.isVolumeOn())
                    mSFX.pop(); // TODO: sometimes pop doesn't play
            }
        });

        updateVolumeToggle();
    }

    private void updateVolumeToggle() {
        mToggleVolume.setImageResource(mSFX.isVolumeOn() ? R.drawable.ic_volume_up : R.drawable.ic_volume_mute);

    }
}
