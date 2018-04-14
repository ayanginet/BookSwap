package com.example.lenovo.bookswap.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.lenovo.bookswap.R;
import com.example.lenovo.bookswap.interfaces.RecyclerViewClickListener;
import com.example.lenovo.bookswap.models.Comment;
import com.example.lenovo.bookswap.models.MyBook;
import com.example.lenovo.bookswap.models.User;
import com.example.lenovo.bookswap.other.CircleTransform;

import java.util.List;

/**
 * Created by Lenovo on 12.04.2018.
 */

public class CommentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_NEWCOMMENT = 0;
    private static final int TYPE_COMMENT = 1;

    private static RecyclerViewClickListener itemListener;
    static List<Comment> comments;
    private String profileUrl, userId;
    public CommentsAdapter(List<Comment> comments, String userId, String profileUrl, RecyclerViewClickListener itemListener){
        this.comments = comments;
        this.itemListener = itemListener;
        this.userId = userId;
        this.profileUrl = profileUrl;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("Viewgroup Type", String.valueOf(viewType));
        if(viewType == TYPE_NEWCOMMENT){
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_addcomment, parent, false);
            return new NewCommentViewHolder(v);
        } else if(viewType == TYPE_COMMENT){
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
            return new CommentViewHolder(v);
        }
        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

        if(viewHolder instanceof  NewCommentViewHolder){
            final NewCommentViewHolder holder = (NewCommentViewHolder) viewHolder;

            holder.comment.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if(charSequence.length()>1){
                        if(holder.progress.getVisibility() != View.VISIBLE)
                            holder.sendBtn.setVisibility(View.VISIBLE);
                    }else{
                        holder.sendBtn.setVisibility(View.GONE);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {
                }
            });
            holder.sendBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.sendBtn.setVisibility(View.GONE);
                    holder.progress.setVisibility(View.VISIBLE);
                }
            });
            Glide.with(holder.profile.getContext()).load(profileUrl)
                    .crossFade()
                    .thumbnail(0.5f)
                    .bitmapTransform(new CircleTransform(holder.profile.getContext()))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.profile);
        }else
        if(viewHolder instanceof  CommentViewHolder){
            i-=1;
            final CommentViewHolder holder = (CommentViewHolder) viewHolder;
            holder.name.setText(comments.get(i).getUserName());
            holder.comment.setText(comments.get(i).getText());
            holder.time.setText(comments.get(i).getDate());
            Glide.with(holder.profile.getContext()).load(comments.get(i).getProfileUrl())
                    .crossFade()
                    .thumbnail(0.5f)
                    .bitmapTransform(new CircleTransform(holder.profile.getContext()))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.profile);
        }
    }
    @Override
    public int getItemViewType(int position) {
        if(position == 0) return TYPE_NEWCOMMENT;
        return TYPE_COMMENT;
    }
    @Override
    public int getItemCount() {
        return comments.size()+1;
    }

    public static class NewCommentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        Button sendBtn;
        TextView comment;
        ImageView profile;
        ProgressBar progress;

        NewCommentViewHolder(View itemView) {
            super(itemView);
            comment = (TextView) itemView.findViewById(R.id.comment);
            profile = (ImageView) itemView.findViewById(R.id.profileImg);
            sendBtn = (Button) itemView.findViewById(R.id.sendBtn);
            progress =(ProgressBar) itemView.findViewById(R.id.progressBar);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            //itemListener.recyclerViewListClicked(view, this.getAdapterPosition());
        }

    }
    public static class CommentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView comment, name, time;
        ImageView profile;

        CommentViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.userName);
            comment=(TextView) itemView.findViewById(R.id.comment);
            time=(TextView) itemView.findViewById(R.id.time);
            profile = (ImageView) itemView.findViewById(R.id.profileImg);

            profile.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
               itemListener.recyclerViewListClicked(view, getAdapterPosition());
        }

    }


}