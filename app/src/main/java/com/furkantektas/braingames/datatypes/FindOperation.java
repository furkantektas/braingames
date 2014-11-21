package com.furkantektas.braingames.datatypes;

import com.furkantektas.braingames.R;

/**
 * Created by Sinan NAR on 21/11/14.
 */
public class FindOperation {

    public Operation mOperation;
    private int firstNumber;
    private int secondNumber;
    private double result;

    public FindOperation(Operation operation,int firstNumber,int secondNumber) {
        this.mOperation = operation;
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        switch (mOperation.getResId()){
            case R.string.addition:{result = (double)firstNumber + secondNumber;}break;
            case R.string.extraction:{result = (double)firstNumber - secondNumber;}break;
            case R.string.multiplication:{result = (double)firstNumber * secondNumber;}break;
            case R.string.division:{result = (double)firstNumber / secondNumber;}break;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (!(o instanceof Operation))return false;
        FindOperation sOther = (FindOperation)o;
        return (sOther.mOperation.resId == mOperation.resId);
    }

    public boolean isTrue(Operation o){
        return mOperation.getResId() == o.getResId();
    }

    public int getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(int firstNumber) {
        this.firstNumber = firstNumber;
    }

    public int getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(int secondNumber) {
        this.secondNumber = secondNumber;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public static enum Operation {
        ADDITION(R.string.addition),
        EXTRACTION(R.string.extraction),
        MULTIPLICATION(R.string.multiplication),
        DIVISION(R.string.division);

        private int resId;

        private Operation(int resId) {
            this.resId = resId;
        }

        public int getResId() {
            return resId;
        }
    }

    public static Operation generateOperation(int ind) {
        int i = 0;
        Operation[] operations = Operation.values();

        if(ind >= operations.length)
            ind = (ind + operations.length) % operations.length;

        return operations[ind];
    }

}
