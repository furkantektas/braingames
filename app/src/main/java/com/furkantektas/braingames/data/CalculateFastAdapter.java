package com.furkantektas.braingames.data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.furkantektas.braingames.R;
import com.furkantektas.braingames.datatypes.Game;
import com.furkantektas.braingames.datatypes.MathOperation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Sinan NAR on 21/11/14.
 */
public class CalculateFastAdapter   extends BaseAdapter {

    private List<MathOperation> mDataSet;
    private Game mGame;

    private int mCorrectResults = 0;
    private int mAnsweredQuestionCount = 0;
    private int mInitialSize = 1;

    private View.OnClickListener parentRightOnClickListener;
    private View.OnClickListener parentWrongOnClickListener;

    private View.OnClickListener result1RightOnClickListener;
    private View.OnClickListener result1WrongOnClickListener;
    private View.OnClickListener result2RightOnClickListener;
    private View.OnClickListener result2WrongOnClickListener;
    private View.OnClickListener result3RightOnClickListener;
    private View.OnClickListener result3WrongOnClickListener;
    private View.OnClickListener result4RightOnClickListener;
    private View.OnClickListener result4WrongOnClickListener;


    public CalculateFastAdapter(int size, Game game, final View.OnClickListener rightListener, View.OnClickListener wrongListener) {
        this.mGame = game;
        mInitialSize = size;
        parentRightOnClickListener = rightListener;
        parentWrongOnClickListener = wrongListener;
        mDataSet = new ArrayList<MathOperation>((int)(size*1.5));
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

    @Override
    public long getItemId(int i) {
        return i;
    }

    private void generateDataset(int size) {
        Random r = new Random();

        for(int i = 0; i < size; ++i) {
            MathOperation c;
            int rand1 = r.nextInt(MathOperation.Operation.values().length);
            c = new MathOperation(MathOperation.generateOperation(rand1));
            mDataSet.add(c);
        }
    }

    private void initListeners(){
        result1RightOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ++mCorrectResults;
                parentRightOnClickListener.onClick(view);
            }
        };
        result2RightOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ++mCorrectResults;
                parentRightOnClickListener.onClick(view);
            }
        };
        result3RightOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ++mCorrectResults;
                parentRightOnClickListener.onClick(view);
            }
        };
        result4RightOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ++mCorrectResults;
                parentRightOnClickListener.onClick(view);
            }
        };

        result1WrongOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentWrongOnClickListener.onClick(view);
            }
        };
        result2WrongOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentWrongOnClickListener.onClick(view);
            }
        };
        result3WrongOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentWrongOnClickListener.onClick(view);
            }
        };
        result4WrongOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentWrongOnClickListener.onClick(view);
            }
        };
    }

    public static class CalculateFastViewHolder {
        public TextView mNumber1;
        public TextView mNumber2;
        public TextView mOperation;
        public Button mResult1;
        public Button mResult2;
        public Button mResult3;
        public Button mResult4;

        public CalculateFastViewHolder(View v) {
            mNumber1 = (TextView)v.findViewById(R.id.number1);
            mNumber2 = (TextView)v.findViewById(R.id.number2);
            mOperation = (TextView)v.findViewById(R.id.operation);
            mResult1 = (Button) v.findViewById(R.id.result1);
            mResult2 = (Button) v.findViewById(R.id.result2);
            mResult3 = (Button) v.findViewById(R.id.result3);
            mResult4 = (Button) v.findViewById(R.id.result4);
        }
    }

    public Game getGame() {
        return mGame;
    }

    public void setGame(Game mGame) {
        this.mGame = mGame;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CalculateFastViewHolder vh;

        if(view == null) {
            view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.calculate_fast_card, viewGroup, false);

            vh = new CalculateFastViewHolder(view);
            view.setTag(vh);
        } else {
            vh = (CalculateFastViewHolder) view.getTag();
        }
        System.out.println("i="+i);
        final MathOperation c = mDataSet.get(i);
        vh.mNumber1.setText(""+c.getFirstNumber());
        vh.mNumber2.setText(""+c.getSecondNumber());
        switch (c.mOperation){
            case ADDITION:vh.mOperation.setText("+");break;
            case EXTRACTION:vh.mOperation.setText("-");break;
            case MULTIPLICATION:vh.mOperation.setText("*");break;
            case DIVISION:vh.mOperation.setText("/");break;
        }

        ArrayList<Integer> results = c.generateResults();
        vh.mResult1.setText(""+results.get(0));
        vh.mResult2.setText(""+results.get(1));
        vh.mResult3.setText(""+results.get(2));
        vh.mResult4.setText(""+results.get(3));


        if((i+1) == getCount()){
            vh.mResult1.setOnClickListener(c.isResult(results.get(0).intValue())?
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            result1RightOnClickListener.onClick(view);
                            getGame().finishGame();
                        }
                    }
                    :
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            result1WrongOnClickListener.onClick(view);
                            getGame().finishGame();
                        }
                    });
            vh.mResult2.setOnClickListener(c.isResult(results.get(1).intValue())?
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            result2RightOnClickListener.onClick(view);
                            getGame().finishGame();
                        }
                    }
                    :
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            result2WrongOnClickListener.onClick(view);
                            getGame().finishGame();
                        }
                    });
            vh.mResult3.setOnClickListener(c.isResult(results.get(2).intValue())?
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            result3RightOnClickListener.onClick(view);
                            getGame().finishGame();
                        }
                    }
                    :
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            result3WrongOnClickListener.onClick(view);
                            getGame().finishGame();
                        }
                    });
            vh.mResult4.setOnClickListener(c.isResult(results.get(3).intValue())?
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            result4RightOnClickListener.onClick(view);
                            getGame().finishGame();
                        }
                    }
                    :
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            result4WrongOnClickListener.onClick(view);
                            getGame().finishGame();
                        }
                    });
        }
        else{

            vh.mResult1.setOnClickListener(c.isResult(results.get(0).intValue())?result1RightOnClickListener:result1WrongOnClickListener);
            vh.mResult2.setOnClickListener(c.isResult(results.get(1).intValue())?result2RightOnClickListener:result2WrongOnClickListener);
            vh.mResult3.setOnClickListener(c.isResult(results.get(2).intValue())?result3RightOnClickListener:result3WrongOnClickListener);
            vh.mResult4.setOnClickListener(c.isResult(results.get(3).intValue())?result4RightOnClickListener:result4WrongOnClickListener);
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
