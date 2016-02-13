package com.wanderlei.bookcatalog.view.listviewadapter;

import android.view.View;

/**
 * Created by wanderlei on 12/02/16.
 */
public interface RecyclerViewOnClickListenerHack {
    public void onClickListener(View view, int position);
    public void onLongPressClickListener(View view, int position);
}
