package com.furkantektas.braingames.data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.furkantektas.braingames.R;
import com.furkantektas.braingames.datatypes.ColorMatch;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Furkan Tektas on 11/15/14.
 */
public class ColorMatchAdapter extends BaseAdapter {
    private List<ColorMatch> dataSet;
    private View.OnClickListener parentSameOnClickListener;
    private View.OnClickListener parentDiffOnClickListener;
    private View.OnClickListener rightFullSameOnClickListener;
    private View.OnClickListener wrongFullSameOnClickListener;
    private View.OnClickListener rightFullDiffOnClickListener;
    private View.OnClickListener wrongFullDiffOnClickListener;

    private int correctResults = 0;
    /**
     * Initializes colorMatch Questions adapter with size elements.
     * @param size
     */
    public ColorMatchAdapter(int size, final View.OnClickListener sameListener, View.OnClickListener diffListener) {
        parentSameOnClickListener = sameListener;
        parentDiffOnClickListener = diffListener;
        dataSet = new ArrayList<ColorMatch>((int)(size*1.5));
        for(int i = 0; i < size; ++i) {
            ColorMatch c;
            if(i%3 == 0)
                c = new ColorMatch(ColorMatch.ColorNames.BLACK, ColorMatch.Color.BLUE);
            else if (i%3 == 1)
                c = new ColorMatch(ColorMatch.ColorNames.ORANGE, ColorMatch.Color.RED);
            else
                c = new ColorMatch(ColorMatch.ColorNames.GREEN, ColorMatch.Color.GREEN);

            dataSet.add(c);
        }

        // for performance reasons, predefining onclicklisteners
        rightFullSameOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ++correctResults;
                parentSameOnClickListener.onClick(view);
            }
        };

        wrongFullSameOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentSameOnClickListener.onClick(view);
            }
        };

        rightFullDiffOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ++correctResults;
                parentDiffOnClickListener.onClick(view);
            }
        };

        wrongFullDiffOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentDiffOnClickListener.onClick(view);
            }
        };
    }

    @Override
    public int getCount() {
        return dataSet.size();
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

        final ColorMatch c = dataSet.get(i);
        vh.mColorName.setText(c.colorName.getResId());
        vh.mColorName.setBackgroundResource(c.color.getResId());
        vh.mSameButton.setOnClickListener((c.isTrue() ? rightFullSameOnClickListener : wrongFullSameOnClickListener));
        vh.mDifferentButton.setOnClickListener((!c.isTrue() ? rightFullDiffOnClickListener: wrongFullDiffOnClickListener));
        return view;
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
