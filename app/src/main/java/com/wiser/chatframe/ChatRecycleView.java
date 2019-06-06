package com.wiser.chatframe;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class ChatRecycleView extends RecyclerView implements View.OnTouchListener {

    public ChatRecycleView(@NonNull Context context) {
        super(context);
    }

    public ChatRecycleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
