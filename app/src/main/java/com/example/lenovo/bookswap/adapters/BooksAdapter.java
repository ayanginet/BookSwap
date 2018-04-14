package com.example.lenovo.bookswap.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.lenovo.bookswap.models.Book;
import com.example.lenovo.bookswap.R;
import com.example.lenovo.bookswap.interfaces.RecyclerViewClickListener;

import java.util.List;

/**
 * Created by Lenovo on 12.04.2018.
 */

public class BooksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static RecyclerViewClickListener itemListener;
    List<Book> books;
    public BooksAdapter(List<Book> books, RecyclerViewClickListener itemListener){
        this.books = books;
        this.itemListener = itemListener;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("Viewgroup Type", String.valueOf(viewType));
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);

        return new BookViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        final BookViewHolder holder = (BookViewHolder) viewHolder;
        holder.title.setText(books.get(i).getTitle());
        holder.author.setText(books.get(i).getAuthor());
        holder.year.setText(books.get(i).getYear());
        int status = books.get(i).getStatus();
        if (status == 0){
            holder.statusBusy.setVisibility(View.GONE);
            holder.statusFree.setVisibility(View.VISIBLE);
        }else if(status == 1){
            holder.statusBusy.setVisibility(View.VISIBLE);
            holder.statusFree.setVisibility(View.GONE);
        }

         Glide.with(holder.cover.getContext()).load(books.get(i).getCoverUrl())
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.cover);

    }
    public Book getItem(int i){
        return books.get(i);
    }
    @Override
    public int getItemCount() {
        return books.size();
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView cv;
        TextView title, author, statusFree, statusBusy, year;
        ImageView cover;

        BookViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            title = (TextView) itemView.findViewById(R.id.bookTitle);
            author = (TextView) itemView.findViewById(R.id.author);
            statusFree = (TextView) itemView.findViewById(R.id.status_free);
            statusBusy = (TextView) itemView.findViewById(R.id.status_busy);
            year = (TextView) itemView.findViewById(R.id.year);
            cover = (ImageView) itemView.findViewById(R.id.cover);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            itemListener.recyclerViewListClicked(view, this.getAdapterPosition());
        }

    }



}