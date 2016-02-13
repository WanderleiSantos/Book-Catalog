package com.wanderlei.bookcatalog.view.listviewadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.wanderlei.bookcatalog.model.entity.Book;

import java.util.List;

/**
 * Created by wanderlei on 12/02/16.
 */
public class ListBookAdapter extends RecyclerView.Adapter<ListBookAdapter.ViewHolder> implements View.OnClickListener  {

    private Context mContext;
    private List<Book> bookList;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void onClick(View v) {

    }

    public class ViewHolder  extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
