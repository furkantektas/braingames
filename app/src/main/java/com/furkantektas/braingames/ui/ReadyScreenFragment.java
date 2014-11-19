package com.furkantektas.braingames.ui;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.furkantektas.braingames.R;
import com.furkantektas.braingames.ui.games.Game;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReadyScreenFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReadyScreenFragment extends Fragment {
    private Game mGame;
    private Button mButton;

    public static ReadyScreenFragment newInstance(Game g) {
        ReadyScreenFragment fragment = new ReadyScreenFragment();
        fragment.setGame(g); // game should be bundled
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public ReadyScreenFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ready_screen, container, false);
        mButton = (Button) v.findViewById(R.id.play_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGame.hideReadyScreen();
                mGame.positiveSound();
            }
        });
        return v;
    }


    public Game getGame() {
        return mGame;
    }

    public void setGame(Game game) {
        this.mGame = game;
    }
}
