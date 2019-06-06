package com.wiser.chat;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class ChatBaseHolder<T> extends RecyclerView.ViewHolder {

    private ChatBaseAdapter adapter;

    public ChatBaseHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void setAdapter(ChatBaseAdapter adapter) {
        this.adapter = adapter;
    }

    public ChatBaseAdapter adapter() {
        return adapter;
    }

    public abstract void bindData(T t, int position);
}
