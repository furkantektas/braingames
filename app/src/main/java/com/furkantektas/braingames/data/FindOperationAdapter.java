package com.furkantektas.braingames.data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.furkantektas.braingames.R;
import com.furkantektas.braingames.datatypes.MathOperation;
import com.furkantektas.braingames.datatypes.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Sinan NAR on 21/11/14.
 */
public class FindOperationAdapter  extends BaseAdapter{
    private List<MathOperation> mDataSet;
    private Game mGame;

    private int mCorrectResults = 0;
    private int mAnsweredQuestionCount = 0;
    private int mInitialSize = 1;

    private View.OnClickListener parentRightOnClickListener;
    private View.OnClickListener parentWrongOnClickListener;

    private View.OnClickListener additionRightOnClickListener;
    private View.OnClickListener additionWrongOnClickListener;
    private View.OnClickListener extractionRightOnClickListener;
    private View.OnClickListener extractionWrongOnClickListener;
    private View.OnClickListener multiplicationRightOnClickListener;
    private View.OnClickListener multiplicationWrongOnClickListener;
    private View.OnClickListener divisionRightOnClickListener;
    private View.OnClickListener divisionWorngOnClickListener;

    public FindOperationAdapter(int size, Game game, final View.OnClickListener rightListener, View.OnClickListener wrongListener) {
        this.mGame = game;
        mInitialSize = size;
        parentRightOnClickListener = rightListener;
        parentWrongOnClickListener = wrongListener;
        mDataSet = new ArrayList<MathOperation>((int)(size*1.5));
        generateDataset(size);
        initListeners();
    }

    private void initListeners(){
        additionRightOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ++mCorrectResults;
                parentRightOnClickListener.onClick(view);
            }
        };
        extractionRightOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ++mCorrectResults;;
                parentRightOnClickListener.onClick(view);
            }
        };
        multiplicationRightOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ++mCorrectResults;
                parentRightOnClickListener.onClick(view);
            }
        };
        divisionRightOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ++mCorrectResults;
                parentRightOnClickListener.onClick(view);
            }
        };

        additionWrongOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentWrongOnClickListener.onClick(view);
            }
        };
        extractionWrongOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentWrongOnClickListener.onClick(view);
            }
        };
        multiplicationWrongOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentWrongOnClickListener.onClick(view);
            }
        };
        divisionWorngOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentWrongOnClickListener.onClick(view);
            }
        };

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
        FindOperationViewHolder vh;
        ++mAnsweredQuestionCount;

        if(view == null) {
            view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.find_operation_card, viewGroup, false);

            vh = new FindOperationViewHolder(view);
            view.setTag(vh);
        } else {
            vh = (FindOperationViewHolder) view.getTag();
        }
        System.out.println("i="+i);

        final MathOperation c = mDataSet.get(i);
        vh.mOperation.setText(c.getFirstNumber()+" ◯ "+c.getSecondNumber()+" = " + c.getResult());
//        vh.mTextView.setText();
        if((i+1) == getCount()){
            vh.mAdditionButton.setOnClickListener(c.isOperation(MathOperation.Operation.ADDITION)? new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    additionRightOnClickListener.onClick(view);
                    getGame().finishGame();
                }
            } : new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    additionWrongOnClickListener.onClick(view);
                    getGame().finishGame();
                }
            });

            vh.mExtractionButton.setOnClickListener(c.isOperation(MathOperation.Operation.EXTRACTION)? new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    extractionRightOnClickListener.onClick(view);
                    getGame().finishGame();
                }
            } : new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    extractionWrongOnClickListener.onClick(view);
                    getGame().finishGame();
                }
            });

            vh.mMultiplicationButton.setOnClickListener(c.isOperation(MathOperation.Operation.MULTIPLICATION)? new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    multiplicationRightOnClickListener.onClick(view);
                    getGame().finishGame();
                }
            } : new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    multiplicationWrongOnClickListener.onClick(view);
                    getGame().finishGame();
                }
            });

            vh.mDivisionButton.setOnClickListener(c.isOperation(MathOperation.Operation.DIVISION)? new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    divisionWorngOnClickListener.onClick(view);
                    getGame().finishGame();
                }
            } : new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    divisionRightOnClickListener.onClick(view);
                    getGame().finishGame();
                }
            });
        }
        else{
            vh.mAdditionButton.setOnClickListener(c.isOperation(MathOperation.Operation.ADDITION)?additionRightOnClickListener:additionWrongOnClickListener);
            vh.mExtractionButton.setOnClickListener(c.isOperation(MathOperation.Operation.EXTRACTION)?extractionRightOnClickListener:extractionWrongOnClickListener);
            vh.mMultiplicationButton.setOnClickListener(c.isOperation(MathOperation.Operation.MULTIPLICATION)?multiplicationRightOnClickListener:multiplicationWrongOnClickListener);
            vh.mDivisionButton.setOnClickListener(c.isOperation(MathOperation.Operation.DIVISION)?divisionRightOnClickListener:divisionWorngOnClickListener);
        }


        return view;
    }


    /**
     * Extends current dataset by size.
     * @param size
     */
    private void generateDataset(int size) {
        Random r = new Random();

        for(int i = 0; i < size; ++i) {
            MathOperation c;
            int rand1 = r.nextInt(MathOperation.Operation.values().length);
            c = new MathOperation(MathOperation.generateOperation(rand1));
            mDataSet.add(c);
        }
    }
    public Game getGame() {
        return mGame;
    }

    public void setGame(Game mGame) {
        this.mGame = mGame;
    }

    public static class FindOperationViewHolder {
        public TextView mOperation;
        public Button mAdditionButton;
        public Button mExtractionButton;
        public Button mMultiplicationButton;
        public Button mDivisionButton;

        public FindOperationViewHolder(View v) {
            mOperation = (TextView)v.findViewById(R.id.operation);
            mAdditionButton = (Button) v.findViewById(R.id.addition);
            mExtractionButton = (Button) v.findViewById(R.id.extraction);
            mMultiplicationButton = (Button) v.findViewById(R.id.multiplication);
            mDivisionButton = (Button) v.findViewById(R.id.division);
        }
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
