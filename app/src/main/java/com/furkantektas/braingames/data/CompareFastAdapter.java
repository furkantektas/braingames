package com.furkantektas.braingames.data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.furkantektas.braingames.R;
import com.furkantektas.braingames.datatypes.ComparisonOperation;
import com.furkantektas.braingames.datatypes.Game;
import com.furkantektas.braingames.datatypes.MathOperation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Sinan NAR on 26/11/14.
 */
public class CompareFastAdapter    extends BaseAdapter {
    private List<ComparisonOperation> mDataSet;
    private Game mGame;

    private int mCorrectResults = 0;
    private int mAnsweredQuestionCount = 0;
    private int mInitialSize = 1;

    private View.OnClickListener parentRightOnClickListener;
    private View.OnClickListener parentWrongOnClickListener;

    private View.OnClickListener trueRightOnClickListener;
    private View.OnClickListener trueWrongOnClickListener;
    private View.OnClickListener falseRightOnClickListener;
    private View.OnClickListener falseWrongOnClickListener;


    public CompareFastAdapter(int size, Game game, final View.OnClickListener rightListener, View.OnClickListener wrongListener) {
        this.mGame = game;
        mInitialSize = size;
        parentRightOnClickListener = rightListener;
        parentWrongOnClickListener = wrongListener;
        mDataSet = new ArrayList<ComparisonOperation>((int)(size*1.5));
        generateDataset(size);
        initListeners();
    }




    @Override
    public int getCount() {
        return mDataSet.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    public long getItemId(int i) {
        return i;
    }

    private void generateDataset(int size) {
        Random r = new Random();

        for(int i = 0; i < size; ++i) {
            ComparisonOperation c;
            int rand1 = r.nextInt(ComparisonOperation.Comparison.values().length);
            c = new ComparisonOperation(ComparisonOperation.generateOperation(rand1));
            mDataSet.add(c);
        }
    }


    public static class CompareFastViewHolder {
        public TextView question;
        public Button bTrue;
        public Button bFalse;

        public CompareFastViewHolder(View v) {
            question = (TextView) v.findViewById(R.id.question);
            bTrue = (Button) v.findViewById(R.id.bTrue);
            bFalse = (Button) v.findViewById(R.id.bFalse);
        }
    }

    public Game getGame() {
        return mGame;
    }

    public void setGame(Game mGame) {
        this.mGame = mGame;
    }

    private void initListeners() {
        trueRightOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ++mCorrectResults;
                parentRightOnClickListener.onClick(view);
            }
        };
        falseRightOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ++mCorrectResults;
                parentRightOnClickListener.onClick(view);
            }
        };
        trueWrongOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentWrongOnClickListener.onClick(view);
            }
        };
        falseWrongOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentWrongOnClickListener.onClick(view);
            }
        };
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CompareFastViewHolder vh;
        mAnsweredQuestionCount++;


        if(view == null) {
            view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.compare_fast_card, viewGroup, false);

            vh = new CompareFastViewHolder(view);
            view.setTag(vh);
        } else {
            vh = (CompareFastViewHolder) view.getTag();
        }
        System.out.println("i="+i);

        final ComparisonOperation c = mDataSet.get(i);

        StringBuilder operation = new StringBuilder().append(c.firstOperation.getFirstNumber()).append("");
        switch (c.firstOperation.mOperation){
            case ADDITION:operation.append("+");break;
            case EXTRACTION:operation.append("-");break;
            case MULTIPLICATION:operation.append("*");break;
            case DIVISION:operation.append("/");break;
        }
        operation.append(c.firstOperation.getSecondNumber());
        switch (c.mComparison){
            case LOWERTHEN:operation.append(" < ");break;
            case HIGHERTHEN:operation.append(" > ");break;
        }
        operation.append(c.secondOperation.getFirstNumber());
        switch (c.secondOperation.mOperation){
            case ADDITION:operation.append("+");break;
            case EXTRACTION:operation.append("-");break;
            case MULTIPLICATION:operation.append("*");break;
            case DIVISION:operation.append("/");break;
        }
        operation.append(c.secondOperation.getSecondNumber());
        vh.question.setText(operation);

        if((i+1) == getCount()){
            vh.bTrue.setOnClickListener(c.isComparisonCorrect(true)?
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            trueRightOnClickListener.onClick(view);
                            getGame().finishGame();
                        }
                    }
                    :
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            trueWrongOnClickListener.onClick(view);
                            getGame().finishGame();
                        }
                    });
            vh.bFalse.setOnClickListener(c.isComparisonCorrect(false)?
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            falseRightOnClickListener.onClick(view);
                            getGame().finishGame();
                        }
                    }
                    :
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            falseWrongOnClickListener.onClick(view);
                            getGame().finishGame();
                        }
                    });

        }else{

            vh.bTrue.setOnClickListener(c.isComparisonCorrect(true)?trueRightOnClickListener:trueWrongOnClickListener);
            vh.bFalse.setOnClickListener(c.isComparisonCorrect(false)?falseRightOnClickListener:falseWrongOnClickListener);
        }


        return view;
    }

    public int getAnsweredQuestionCount() {
        return mAnsweredQuestionCount;
    }
    public void setAnsweredQuestionCount(int mAnsweredQuestionCount) {
        this.mAnsweredQuestionCount = mAnsweredQuestionCount;
    }

    public int getCorrectResults() {
        return mCorrectResults;
    }


}
