package com.furkantektas.braingames.datatypes;

import java.util.Random;

/**
 * Created by Furkan Tektas on 11/22/14.
 */
public class MemorizeMatrix {
    private int mSize;
    private int[] mFilledCells;
    public static final int MIN_SIZE = 4;
    public static final int MAX_SIZE = 8;

    private MemorizeMatrix(int size, int[] filledCells) {
        setSize(size);
        setFilledCells(filledCells);
    }

    public static MemorizeMatrix MemoryMatrixBuilder(int size) {
        int[] cells = new int[size];
        Random rand = new Random();
        int maxIndex = size*size - 1;
        for(int i = 0; i < size; ++i) {
            int randInt = rand.nextInt(maxIndex);
            System.out.println("randInt: "+randInt);
            while(isFilled(randInt,cells)) {
                randInt = rand.nextInt(maxIndex);
                System.out.println("new randInt: "+randInt);
            }
            cells[i] = randInt;
        }
        return new MemorizeMatrix(size,cells);
    }

    public boolean isFilled(int index) {
        return isFilled(index,getFilledCells());
    }

    private static boolean isFilled(int index, int[] arr) {
        for(int i=0; i < arr.length; ++i)
            if(index == arr[i])
                return true;
        return false;
    }

    public int getSize() {
        return mSize;
    }

    public void setSize(int mSize) {
        this.mSize = mSize;
    }

    public int[] getFilledCells() {
        return mFilledCells;
    }

    public void setFilledCells(int[] mFilledCells) {
        this.mFilledCells = mFilledCells;
    }
}
