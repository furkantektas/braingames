package com.furkantektas.braingames.datatypes;

import com.furkantektas.braingames.R;

import java.util.Random;

/**
 * Created by Sinan NAR on 26/11/14.
 */
public class ComparisonOperation {
    public Comparison mComparison;
    public MathOperation firstOperation;
    public MathOperation secondOperation;
    public boolean result;

    public boolean isComparisonCorrect(boolean b){
        return b == result;
    }

    public ComparisonOperation(Comparison c){
        this.mComparison = c;
        Random r = new Random();
        if(r.nextInt(2)==0){ //addition multiplication
            int number1 = r.nextInt(100);//clean these magic numbers
            int number2 = r.nextInt(100);
            int number3 = r.nextInt(10);
            int number4 = r.nextInt(10);
            firstOperation = new MathOperation(MathOperation.Operation.ADDITION,number1,number2);
            secondOperation = new MathOperation(MathOperation.Operation.MULTIPLICATION,number3,number4);
        }
        else { //extraction division
            int number1 = r.nextInt(100);//clean these magic numbers
            int number2 = r.nextInt(100);
            int number3 = r.nextInt(100);
            int number4 = r.nextInt(10);
            while(number4 == 0)
                number4 = r.nextInt(10);
            firstOperation = new MathOperation(MathOperation.Operation.EXTRACTION,number1,number2);
            secondOperation = new MathOperation(MathOperation.Operation.DIVISION,number3,number4);
        }
        switch (mComparison){
            case LOWERTHEN:{
                result = (firstOperation.getResult()<secondOperation.getResult())?true:false;
            }break;
            case HIGHERTHEN:{
                result = (firstOperation.getResult()<secondOperation.getResult())?false:true;
            }break;
        }

    }


    public static enum Comparison {
        LOWERTHEN(R.string.lowerthen),
        HIGHERTHEN(R.string.higherthen);

        private int resId;

        private Comparison(int resId) {
            this.resId = resId;
        }

        public int getResId() {
            return resId;
        }
    }


    public static Comparison generateOperation(int ind) {
        int i = 0;
        Comparison[] operations = Comparison.values();

        if(ind >= operations.length)
            ind = (ind + operations.length) % operations.length;

        return operations[ind];
    }

}
