package com.wanderlei.bookcatalog.view.listviewadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.wanderlei.bookcatalog.R;
import com.wanderlei.bookcatalog.model.entity.Book;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by wanderlei on 12/02/16.
 */
public class ListBookAdapter extends BaseAdapter {

    private Context mContext;
    private List<Book> bookList;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

    public ListBookAdapter( Context mContext) {
        this.mContext = mContext;
        this.mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setBooks(List<Book> listBoks) {
        this.bookList = listBoks;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return bookList != null ? bookList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return bookList != null ? bookList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if (convertView == null){

            viewHolder = new ViewHolder();
            int layout = R.layout.list_books;
            convertView = mLayoutInflater.inflate(layout, null);
            convertView.setTag(viewHolder);

            viewHolder.bookTitle = (TextView) convertView.findViewById(R.id.bookTitle);
            viewHolder.bookReleaseDate = (TextView) convertView.findViewById(R.id.bookReleaseDate);
            viewHolder.bookAuthor = (TextView) convertView.findViewById(R.id.bookAuthor);
            viewHolder.bookThumbnail = (ImageView) convertView.findViewById(R.id.bookThumbnail);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Book book = bookList.get(position);
        viewHolder.bookTitle.setText(book.getVolumeInfo().getTitle());
        viewHolder.bookReleaseDate.setText(book.getVolumeInfo().getPublishedDate());

        viewHolder.bookAuthor.setText( Arrays.toString(book.getVolumeInfo().getAuthor()));

        if (book.getVolumeInfo().getImage() != null && book.getVolumeInfo().getImage().getSmallThumbnail() != null) {
            Picasso.with(viewHolder.bookThumbnail.getContext()).load(book.getVolumeInfo().getImage().getSmallThumbnail()).placeholder(R.drawable.noimagebook).into(viewHolder.bookThumbnail);
        } else {
            viewHolder.bookThumbnail.setImageDrawable(viewHolder.bookThumbnail.getContext().getResources().getDrawable(R.drawable.noimagebook));
        }

        return convertView;
    }

    static class ViewHolder{
        TextView bookTitle;
        TextView bookReleaseDate;
        TextView bookAuthor;
        ImageView bookThumbnail;
    }
}
