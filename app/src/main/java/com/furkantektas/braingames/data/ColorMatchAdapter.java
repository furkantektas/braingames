package com.furkantektas.braingames.data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.furkantektas.braingames.R;
import com.furkantektas.braingames.datatypes.ColorMatch;
import com.furkantektas.braingames.ui.games.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Furkan Tektas on 11/15/14.
 */
public class ColorMatchAdapter extends BaseAdapter {
    private List<ColorMatch> mDataSet;
    private Game mGame;
    private View.OnClickListener parentRightOnClickListener;
    private View.OnClickListener parentWrongOnClickListener;
    private View.OnClickListener rightFullSameOnClickListener;
    private View.OnClickListener wrongFullSameOnClickListener;
    private View.OnClickListener rightFullDiffOnClickListener;
    private View.OnClickListener wrongFullDiffOnClickListener;

    private int correctResults = 0;
    /**
     * Initializes colorMatch Questions adapter with size elements.
     * @param size
     */
    public ColorMatchAdapter(int size, Game game, final View.OnClickListener rightListener, View.OnClickListener wrongListener) {
        this.mGame = game;
        parentRightOnClickListener = rightListener;
        parentWrongOnClickListener = wrongListener;
        mDataSet = new ArrayList<ColorMatch>((int)(size*1.5));
        generateDataset(size);
        initListeners();
    }

    /**
     * Generate button's OnClickListeners
     */
    private void initListeners() {
        // for performance reasons, predefining onclicklisteners
        rightFullSameOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ++correctResults;
                parentRightOnClickListener.onClick(view);
            }
        };

        wrongFullSameOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentWrongOnClickListener.onClick(view);
            }
        };

        rightFullDiffOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ++correctResults;
                parentRightOnClickListener.onClick(view);
            }
        };

        wrongFullDiffOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentWrongOnClickListener.onClick(view);
            }
        };
    }

    /**
     * Extends current dataset by size.
     * @param size
     */
    private void generateDataset(int size) {
        Random r = new Random();

        for(int i = 0; i < size; ++i) {
            ColorMatch c;
            int rand1 = r.nextInt(ColorMatch.Color.values().length);
            int rand2 = r.nextInt(ColorMatch.Color.values().length);
            boolean isTrue = rand1 % 2 == 1;

            if(isTrue)
                c = new ColorMatch(ColorMatch.generateColorName(rand1), ColorMatch.generateColor(rand1));
            else
                c = new ColorMatch(ColorMatch.generateColorName(rand1), ColorMatch.generateColor(rand2));
            mDataSet.add(c);
        }
    }

    @Override
    public int getCount() {
        return mDataSet.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ColorMatchViewHolder vh;
        if(view == null) {
            view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.color_match_card, viewGroup, false);

            vh = new ColorMatchViewHolder(view);
            view.setTag(vh);
        } else {
            vh = (ColorMatchViewHolder) view.getTag();
        }
        System.out.println("i="+i);

        final ColorMatch c = mDataSet.get(i);
        vh.mColorName.setText(c.colorName.getResId());
        vh.mColorName.setBackgroundResource(c.color.getResId());

        if((i+1) == getCount()) {
            vh.mSameButton.setOnClickListener((c.isTrue() ? new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    rightFullSameOnClickListener.onClick(view);
                    getGame().finishGame();
                }
            } : new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFullSameOnClickListener.onClick(view);
                    getGame().finishGame();
                }
            }));
            vh.mDifferentButton.setOnClickListener((!c.isTrue() ? new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    rightFullDiffOnClickListener.onClick(view);
                    getGame().finishGame();
                }
            }: new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFullDiffOnClickListener.onClick(view);
                    getGame().finishGame();
                }
            }));
        } else {
            vh.mSameButton.setOnClickListener((c.isTrue() ? rightFullSameOnClickListener : wrongFullSameOnClickListener));
            vh.mDifferentButton.setOnClickListener((!c.isTrue() ? rightFullDiffOnClickListener : wrongFullDiffOnClickListener));
        }

        return view;
    }

    public Game getGame() {
        return mGame;
    }

    public void setGame(Game mGame) {
        this.mGame = mGame;
    }

    public static class ColorMatchViewHolder {
        public TextView mColorName;
        public Button mSameButton;
        public Button mDifferentButton;

        public ColorMatchViewHolder(View v) {
            mColorName = (TextView) v.findViewById(R.id.color_name);
            mSameButton = (Button) v.findViewById(R.id.button_yes);
            mDifferentButton = (Button) v.findViewById(R.id.button_no);
        }
    }

    public int getCorrectResults() {
        return correctResults;
    }
}
