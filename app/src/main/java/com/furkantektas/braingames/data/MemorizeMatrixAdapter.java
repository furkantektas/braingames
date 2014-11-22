package com.furkantektas.braingames.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.furkantektas.braingames.R;
import com.furkantektas.braingames.datatypes.MemorizeMatrix;
import com.furkantektas.braingames.ui.MemoryMatrixItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Furkan Tektas on 11/22/14.
 */
public class MemorizeMatrixAdapter extends BaseAdapter {
    private Context mContext;
    private MemorizeMatrix mMemoryMatrix;
    private int mSize;
    private int mTotalAnswers = 0;
    private int mCorrectResultNum = 0;
    private View.OnClickListener mRightOnClickListener;
    private View.OnClickListener mWrongOnClickListener;
    /**
     * Unknown active views are the right answers that cannot be found by the user.
     */
    private List<MemoryMatrixItem> mUnknownActiveViews = new ArrayList<MemoryMatrixItem>();
    public MemorizeMatrixAdapter(Context context, int size,
                                 final View.OnClickListener parentRightOnClickListener,
                                 final View.OnClickListener parentWrongOnClickListener,
                                 final View.OnClickListener parentFinishOnClickListener) {
        mContext = context;
        mSize = size;
        mMemoryMatrix = MemorizeMatrix.MemoryMatrixBuilder(mSize);
        initListeners(parentRightOnClickListener,parentWrongOnClickListener,parentFinishOnClickListener);
    }

    private void initListeners(final View.OnClickListener parentRightOnClickListener,
                               final View.OnClickListener parentWrongOnClickListener,
                               final View.OnClickListener parentFinishOnClickListener) {
        mRightOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTotalAnswers(getTotalAnswers() + 1);
                setCorrectResultNum(getCorrectResultNum() + 1);
                parentRightOnClickListener.onClick(view);
                mUnknownActiveViews.remove(view);
                if(isFinished()) {
                    finishGame();
                    parentFinishOnClickListener.onClick(view);
                }
                MemoryMatrixItem item = (MemoryMatrixItem) view;
                item.activate();
            }
        };

        mWrongOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTotalAnswers(getTotalAnswers() + 1);
                parentWrongOnClickListener.onClick(view);
                if(isFinished()) {
                    finishGame();
                    parentFinishOnClickListener.onClick(view);
                }
                MemoryMatrixItem item = (MemoryMatrixItem) view;
                item.error();
            }
        };
    }

    private void finishGame() {
        for(MemoryMatrixItem item : mUnknownActiveViews)
            item.activate();
    }

    @Override
    public int getCount() {
        int size = mMemoryMatrix.getSize();
        return size*size;
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
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (view == null) {
            gridView = inflater.inflate(R.layout.grid_item, null);
            MemoryMatrixItem button = (MemoryMatrixItem) gridView.findViewById(R.id.item);
            if(mMemoryMatrix.isFilled(i)) {
                button.blink();
                button.lateSetOnClickListener(mRightOnClickListener);
                mUnknownActiveViews.add(button);
            } else
                button.lateSetOnClickListener(mWrongOnClickListener);
//                button.setBackgroundResource(R.color.blue);
        } else {
            gridView = view;
        }

        return gridView;
    }

    private boolean isFinished() {
        return mSize == getTotalAnswers();
    }

    public int getTotalAnswers() {
        return mTotalAnswers;
    }

    public void setTotalAnswers(int mTotalAnswers) {
        this.mTotalAnswers = mTotalAnswers;
    }

    public int getCorrectResultNum() {
        return mCorrectResultNum;
    }

    public void setCorrectResultNum(int mCorrectResultNum) {
        this.mCorrectResultNum = mCorrectResultNum;
    }
}
