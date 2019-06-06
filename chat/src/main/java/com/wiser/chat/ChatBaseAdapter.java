package com.wiser.chat;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

@SuppressWarnings("unchecked")
public abstract class ChatBaseAdapter<T, V extends ChatBaseHolder> extends RecyclerView.Adapter<V> {

    private LayoutInflater mInflater;

    private Context context;

    /**
     * 数据
     */
    private List<T> mItems;

    public ChatBaseAdapter(Context context) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }

    public void setItems(List<T> mItems) {
        this.mItems = mItems;
        notifyDataSetChanged();
    }

    public List<T> getItems() {
        return mItems;
    }

    public T getItem(int position) {
        if (mItems == null) return null;
        return mItems.get(position);
    }

    public void addItem(int position, T t) {
        if (t == null || getItems() == null || position < 0 || position > getItems().size()) {
            return;
        }
        mItems.add(position, t);
        notifyItemInserted(position);
    }

    public void addList(int position, List<T> list) {
        if (list == null || list.size() < 1 || getItems() == null || position < 0 || position > getItems().size()) {
            return;
        }
        mItems.addAll(position, list);
        notifyItemRangeInserted(position, list.size());
    }

    public void addList(List<T> list) {
        if (list == null || list.size() < 1 || getItems() == null) {
            return;
        }
        int position = getItemCount();
        mItems.addAll(list);
        notifyItemRangeInserted(position, list.size());
    }

    public void updateList(int position, T t) {
        getItems().set(position, t);
        notifyItemChanged(position);
    }

    public void delete(int position) {
        if (getItems() == null || position < 0 || getItems().size() < position) {
            return;
        }
        mItems.remove(position);
        notifyItemRemoved(position);
    }

    public void delete(List<T> list) {
        if (list == null || list.size() < 1 || getItems() == null) {
            return;
        }
        int position = getItemCount();
        mItems.removeAll(list);
        notifyItemRangeRemoved(position, list.size());
    }

    public void delete(int position, List<T> list) {
        if (list == null || list.size() < 1 || getItems() == null) {
            return;
        }
        mItems.removeAll(list);
        notifyItemRangeRemoved(position, list.size());
    }

    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }

    protected View inflate(ViewGroup viewGroup, int layoutId) {
        return mInflater.inflate(layoutId, viewGroup, false);
    }

    // WiserHolder对象
    public abstract V newViewHolder(ViewGroup viewGroup, int type);

    @NonNull
    @Override
    public V onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return newViewHolder(viewGroup, i);
    }

    @Override
    public void onBindViewHolder(@NonNull V v, int i) {
        v.setAdapter(this);
        v.bindData(getItem(i), i);
    }

    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }
}
