package com.furkantektas.braingames.ui.games.tuts;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterViewFlipper;

import com.furkantektas.braingames.R;
import com.furkantektas.braingames.data.ColorMatchAdapter;

public class TutColorMatchActivity extends Activity {

    private AdapterViewFlipper mAdapterFlipper;
    private ColorMatchAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tut_color_match);

        mAdapterFlipper = (AdapterViewFlipper)findViewById(R.id.flipper);
        mAdapter = new ColorMatchAdapter(100, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAdapterFlipper.showNext();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAdapterFlipper.showPrevious();
            }
        });

        mAdapterFlipper.setAdapter(mAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tut_color_match, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
