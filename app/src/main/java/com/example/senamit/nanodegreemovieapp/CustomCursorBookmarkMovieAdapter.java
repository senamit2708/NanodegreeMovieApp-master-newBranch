package com.example.senamit.nanodegreemovieapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.senamit.nanodegreemovieapp.Data.MovieContract.*;
import com.squareup.picasso.Picasso;

public class CustomCursorBookmarkMovieAdapter extends RecyclerView.Adapter<CustomCursorBookmarkMovieAdapter.ViewHolder> {

    public static final String LOG_TAG = CustomCursorBookmarkMovieAdapter.class.getSimpleName();

    Context context;
    Cursor mCursor;
    int idIndex;
    ListItemClickListener listItemClick;


    public CustomCursorBookmarkMovieAdapter(Context context) {
        this.context = context;
    }

    public CustomCursorBookmarkMovieAdapter(Context context, ListItemClickListener listItemClick) {
        this.context = context;
        this.listItemClick = listItemClick;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.activity_movie_bookmark_recylcer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        idIndex = mCursor.getColumnIndex(WishListMovie._ID);
        int moviePosterIndex = mCursor.getColumnIndex(WishListMovie.COLUMN_MOVIE_POSTER);
        mCursor.moveToPosition(position);
        final int id = mCursor.getInt(idIndex);
        String moviePoster = mCursor.getString(moviePosterIndex);
        Picasso.with(context).load(moviePoster).placeholder(R.drawable.testimage1).error(R.drawable.testimage1).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if (mCursor == null) {
            return 0;
        }
        return mCursor.getCount();
    }

    public Cursor swapCursor(Cursor cursor) {
        if (mCursor == cursor) {
            return null;
        }
        Cursor tempCursor = mCursor;
        this.mCursor = cursor;
        if (cursor != null) {
            this.notifyDataSetChanged();
        }
        return tempCursor;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedItemIndex = getAdapterPosition();
            mCursor.moveToPosition(clickedItemIndex);
            int idIndex = mCursor.getColumnIndex(WishListMovie._ID);
            int id = mCursor.getInt(idIndex);
            listItemClick.onListItemClick(clickedItemIndex, id);
        }
    }

    public interface ListItemClickListener {
        void onListItemClick(int clikcedItemIndex, int id);
    }

}
