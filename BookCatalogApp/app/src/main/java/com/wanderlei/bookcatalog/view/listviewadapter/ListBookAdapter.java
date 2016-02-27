package com.wanderlei.bookcatalog.view.listviewadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import com.wanderlei.bookcatalog.R;
import com.wanderlei.bookcatalog.model.entity.Book;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wanderlei on 12/02/16.
 */
public class ListBookAdapter extends RecyclerView.Adapter<ListBookAdapter.ViewHolder> implements View.OnClickListener {


    private List<Book> bookList;
    private OnItemClickListener<Book> onItemClickListener;


    public ListBookAdapter(List<Book> bookList, OnItemClickListener<Book> onItemClickListener) {
        this.bookList = bookList;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void onClick(View v) {
        Book book = (Book) v.getTag();
        onItemClickListener.onClick(book);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_books, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Book book = bookList.get(position);
        holder.itemView.setTag(book);

        holder.bookTitle.setText(book.getVolumeInfo().getTitle());

        holder.bookReleaseDate.setText(book.getVolumeInfo().getPublishedDate());

        holder.bookAuthor.setText( Arrays.toString(book.getVolumeInfo().getAuthor()));

        if (book.getVolumeInfo().getImage() != null && book.getVolumeInfo().getImage().getSmallThumbnail() != null) {
            Picasso.with(holder.bookThumbnail.getContext()).load(book.getVolumeInfo().getImage().getSmallThumbnail()).placeholder(R.drawable.noimagebook).into(holder.bookThumbnail);
        } else {
            holder.bookThumbnail.setImageDrawable(holder.bookThumbnail.getContext().getResources().getDrawable(R.drawable.noimagebook));
        }

    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.bookTitle)
        TextView bookTitle;

        @Bind(R.id.bookReleaseDate)
        TextView bookReleaseDate;

        @Bind(R.id.bookAuthor)
        TextView bookAuthor;

        @Bind(R.id.bookThumbnail)
        ImageView bookThumbnail;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
