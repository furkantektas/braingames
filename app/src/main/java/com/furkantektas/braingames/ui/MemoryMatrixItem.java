package com.furkantektas.braingames.ui;

import android.content.Context;
import android.graphics.drawable.TransitionDrawable;
import android.util.AttributeSet;

import com.furkantektas.braingames.R;

/**
 * Created by Furkan Tektas on 11/22/14.
 */
public class MemoryMatrixItem extends SquareButton {
    public MemoryMatrixItem(Context context) {
        super(context);
    }

    public MemoryMatrixItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MemoryMatrixItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void hide() {
        post(new Runnable() {
            @Override
            public void run() {
                hideTransition();
            }
        });
    }

    public void activate() {
        post(new Runnable() {
            @Override
            public void run() {
                activateTransition();
            }
        });
    }

    public void error() {
        post(new Runnable() {
            @Override
            public void run() {
                errorTransition();
            }
        });
    }

    private void activateTransition() {
        setBackgroundResource(R.drawable.memory_matrix_active_transition);
        TransitionDrawable tr = (TransitionDrawable) getBackground();
        tr.startTransition(300);
    }

    private void errorTransition() {
        setBackgroundResource(R.drawable.memory_matrix_error_transition);
        TransitionDrawable tr = (TransitionDrawable) getBackground();
        tr.startTransition(300);
    }

    private void hideTransition() {
        setBackgroundResource(R.drawable.memory_matrix_hide_transition);
        TransitionDrawable tr = (TransitionDrawable) getBackground();
        tr.startTransition(300);
    }

    public void blink() {
        postDelayed(new Runnable() {
            @Override
            public void run() {
                activateTransition();
            }
        },300);

        postDelayed(new Runnable() {
            @Override
            public void run() {
                hideTransition();
            }
        },2300);
    }

    /**
     * Sets on click listener after animations are completed.
     * @param listener
     */
    public void lateSetOnClickListener(final OnClickListener listener) {
        postDelayed(new Runnable() {
            @Override
            public void run() {
                setOnClickListener(listener);
            }
        },2300);
    }
}
