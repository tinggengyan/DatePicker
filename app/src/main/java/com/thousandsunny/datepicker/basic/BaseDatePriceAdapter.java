package com.thousandsunny.datepicker.basic;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.thousandsunny.datepicker.AdapterItem;


/**
 * Created by yantinggeng on 2016/10/25.
 */

public abstract class BaseDatePriceAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    protected RecyclerView recyclerView;
    protected OnAdapterItemClickListener onItemClickListener;
    private int scrollPosition = 0;

    protected abstract T getSelected();

    public int getScrollPosition() {
        return scrollPosition;
    }

    public void setScrollPosition(int posistion) {
        this.scrollPosition = posistion;
    }


    public void setOnItemClickListener(OnAdapterItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public boolean isGroupTitle(int position) {
        return getItemViewType(position) == AdapterItem.TYPE_GROUP;
    }

    public interface OnAdapterItemClickListener {

        void onItemClick(RecyclerView parent, View view, int position, long id);
    }


}
