package com.example.lenovo.bookswap.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.lenovo.bookswap.models.Book;
import com.example.lenovo.bookswap.R;
import com.example.lenovo.bookswap.interfaces.RecyclerViewClickListener;
import com.example.lenovo.bookswap.models.MyBook;
import com.example.lenovo.bookswap.models.User;
import com.example.lenovo.bookswap.other.CircleTransform;

import java.util.List;

/**
 * Created by Lenovo on 12.04.2018.
 */

public class ProfileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_PROGILE = 0;
    private static final int TYPE_BOOK = 1;

    private static RecyclerViewClickListener itemListener;
    static List<MyBook> books;
    User user;
    public ProfileAdapter(User user, RecyclerViewClickListener itemListener){
        this.books = user.getBooks();
        this.itemListener = itemListener;
        this.user = user;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("Viewgroup Type", String.valueOf(viewType));
        if(viewType == TYPE_PROGILE){
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile, parent, false);
            return new UserViewHolder(v);
        } else if(viewType == TYPE_BOOK){
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mybook, parent, false);
            return new BookViewHolder(v);
        }
        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

        if(viewHolder instanceof  UserViewHolder){
            final UserViewHolder holder = (UserViewHolder) viewHolder;
            holder.name.setText(user.getName());
            holder.email.setText(user.getEmail());

            Glide.with(holder.profile.getContext()).load(user.getProfileUrl())
                    .crossFade()
                    .thumbnail(0.5f)
                    .bitmapTransform(new CircleTransform(holder.profile.getContext()))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.profile);
        }else
        if(viewHolder instanceof  BookViewHolder){
            i-=1;
            final BookViewHolder holder = (BookViewHolder) viewHolder;
            holder.title.setText(books.get(i).getBook().getTitle());
            holder.author.setText(books.get(i).getBook().getAuthor());

            if(books.get(i).getStatus() == 0){
                holder.activePanel.setVisibility(View.VISIBLE);
                holder.returnedPanel.setVisibility(View.GONE);
                holder.returnBtn.setVisibility(View.VISIBLE);
                holder.deadline.setText(books.get(i).getDeadline());
                holder.layout.setAlpha(1);
            }else if(books.get(i).getStatus() == 1){
                holder.activePanel.setVisibility(View.GONE);
                holder.returnedPanel.setVisibility(View.VISIBLE);
                holder.returnBtn.setVisibility(View.INVISIBLE);
                holder.returnDate.setText(books.get(i).getReturnDate());
                holder.layout.setAlpha((float) 0.7);
            }
        }
    }
    @Override
    public int getItemViewType(int position) {
        if(position == 0) return TYPE_PROGILE;
        return TYPE_BOOK;
    }
    @Override
    public int getItemCount() {
        return books.size()+1;
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView cv;
        TextView name, email;
        ImageView profile;

        UserViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            name = (TextView) itemView.findViewById(R.id.name);
            email = (TextView) itemView.findViewById(R.id.email);
            profile = (ImageView) itemView.findViewById(R.id.profileImg);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            //itemListener.recyclerViewListClicked(view, this.getAdapterPosition());
        }

    }
    public static class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title, author, deadline, returnDate;
        ImageView cover, returnBtn;
        LinearLayout activePanel, returnedPanel, layout;

        BookViewHolder(View itemView) {
            super(itemView);
            layout = (LinearLayout) itemView.findViewById(R.id.mybook);
            title = (TextView) itemView.findViewById(R.id.title);
            author = (TextView) itemView.findViewById(R.id.author);
            deadline = (TextView) itemView.findViewById(R.id.deadline);
            returnDate = (TextView) itemView.findViewById(R.id.returnDate);
            cover = (ImageView) itemView.findViewById(R.id.cover);
            returnBtn = (ImageView) itemView.findViewById(R.id.returnBtn);
            activePanel = (LinearLayout) itemView.findViewById(R.id.activePanel);
            returnedPanel = (LinearLayout) itemView.findViewById(R.id.returnedPanel);
            itemView.setOnClickListener(this);
            returnBtn.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
               itemListener.recyclerViewListClicked(view, getAdapterPosition());
        }

    }


}