package com.example.lenovo.bookswap.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.lenovo.bookswap.R;
import com.example.lenovo.bookswap.adapters.CommentsAdapter;
import com.example.lenovo.bookswap.interfaces.RecyclerViewClickListener;
import com.example.lenovo.bookswap.models.Book;
import com.example.lenovo.bookswap.models.Comment;
import com.example.lenovo.bookswap.other.CircleTransform;
import com.example.lenovo.bookswap.other.SimpleDividerItemDecoration;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class BookDetailsActivity extends AppCompatActivity  implements RecyclerViewClickListener {
    ImageView profile;
    RecyclerView rvComments;
    List<Comment> comments;
    Book book;
    TextView title, author, statusFree, statusBusy, year, info;
    ImageView cover;
    LinearLayout llFree, llBusy ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        book = (Book) getIntent().getSerializableExtra("currentBook");
        cover = (ImageView) findViewById(R.id.cover);
        title = (TextView) findViewById(R.id.bookTitle);
        author = (TextView) findViewById(R.id.author);
        year = (TextView) findViewById(R.id.year);
        statusFree = (TextView) findViewById(R.id.status_free);
        statusBusy = (TextView) findViewById(R.id.status_busy);
        llFree = (LinearLayout) findViewById(R.id.llFree);
        llBusy = (LinearLayout) findViewById(R.id.llBusy);
        info = (TextView) findViewById(R.id.info);
        renderViews();

        rvComments = (RecyclerView) findViewById(R.id.rvComments);
        profile = (ImageView) findViewById(R.id.profileImg);
        rvComments.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvComments.setLayoutManager(llm);
        rvComments.addItemDecoration(new SimpleDividerItemDecoration(this));

        initializeData();
        Glide.with(this).load(R.mipmap.ic_launcher)
                .crossFade()
                .thumbnail(0.5f)
                .bitmapTransform(new CircleTransform(this))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(profile);
    }

    public void renderViews(){
        int status = book.getStatus();
        if (status == 0){
            statusBusy.setVisibility(View.GONE);
            statusFree.setVisibility(View.VISIBLE);
            llBusy.setVisibility(View.GONE);
            llFree.setVisibility(View.VISIBLE);
        }else if(status == 1){
            statusBusy.setVisibility(View.VISIBLE);
            statusFree.setVisibility(View.GONE);
            llBusy.setVisibility(View.VISIBLE);
            llFree.setVisibility(View.GONE);
        }
        title.setText(book.getTitle());
        author.setText(book.getAuthor());
        year.setText(book.getYear());
        info.setText(book.getInfo());
        Glide.with(this).load(book.getCoverUrl())
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(cover);

    }

    public void initializeData(){
        comments = new ArrayList<>();
        for (int i = 0; i < 8; i++){
            Comment comment = new Comment("The best book I have ever read!", "userId1", "http://www.leadershipgeeks.com/wp-content/uploads/mark-zuckerberg-bio3.png","13.04.2018","Mark Bro");
            comments.add(comment);
        }
        initializeAdapter();

    }
    private void initializeAdapter(){
        CommentsAdapter adapter = new CommentsAdapter(comments, "userId2", "https://s-media-cache-ak0.pinimg.com/originals/a3/2b/aa/a32baa5414296b7cef86096c8ed91aad.jpg", this);
        rvComments.setAdapter(adapter);
    }

    @Override
    public void recyclerViewListClicked(View v, int position) {

    }
}
