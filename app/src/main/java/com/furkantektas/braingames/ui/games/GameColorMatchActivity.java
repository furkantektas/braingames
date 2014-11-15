package com.furkantektas.braingames.ui.games;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.furkantektas.braingames.R;
import com.furkantektas.braingames.datatypes.GameType;

public class GameColorMatchActivity extends AbstractTimerGameActivity {

    private String mGameName;
    private GameType mGameType = GameType.COLOR_MATCH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_color_match);
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
