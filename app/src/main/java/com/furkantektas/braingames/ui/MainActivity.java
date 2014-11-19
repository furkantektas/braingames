package com.furkantektas.braingames.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.furkantektas.braingames.R;
import com.furkantektas.braingames.data.SFX;
import com.furkantektas.braingames.ui.games.GameColorMatchActivity;


public class MainActivity extends Activity {
    private static SFX mSFX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSFX(new SFX(getApplicationContext()));

        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),GameColorMatchActivity.class);
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
}
