package com.furkantektas.braingames.ui.games;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterViewFlipper;

import com.furkantektas.braingames.R;
import com.furkantektas.braingames.data.ColorMatchAdapter;
import com.furkantektas.braingames.datatypes.GameType;
import com.furkantektas.braingames.ui.games.tuts.TutColorMatchActivity;

public class GameColorMatchActivity extends AbstractTimerGameActivity {
    private String mGameName;
    private GameType mGameType = GameType.COLOR_MATCH;

    private AdapterViewFlipper mAdapterFlipper;
    private ColorMatchAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // show tutorial if user plays this game for the first time
        if(getShowTutorial()) {
            Intent i = new Intent(getApplicationContext(),TutColorMatchActivity.class);
            startActivity(i);
        }
        setContentView(R.layout.activity_game_color_match);


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
        getMenuInflater().inflate(R.menu.menu_game_color_match, menu);

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

            if(isTimerRunning())
                stopTimer();
            else
                startTimer();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public String getGameName() {
        if(mGameName == null)
            mGameName = getApplicationContext().getResources().getString(R.string.game_color_match);
        return mGameName;
    }

    @Override
    public void setGameName(String name) {
        mGameName = name;
    }

    @Override
    public GameType getGameType() {
        return mGameType;
    }

    @Override
    public void setGameType(GameType type) {
        mGameType = type;
    }
}
