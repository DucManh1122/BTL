package com.android.btl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.btl.adapter.RViewPostAdapter;
import com.android.btl.dal.SQLiteHelper;
import com.android.btl.model.RViewPostItem;
import com.android.btl.model.Views;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class RatingHistoryActivity extends AppCompatActivity implements RViewPostAdapter.ItemListener{

    private RecyclerView r_view_post;
    protected FirebaseAuth mAuth;
    private SQLiteHelper sqLiteHelper;
    private RViewPostAdapter post_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_history);
        getSupportActionBar().hide();
        initView();
    }
    private void initView() {
        r_view_post = findViewById(R.id.r_view_post);
        sqLiteHelper = new SQLiteHelper(RatingHistoryActivity.this);
        mAuth = FirebaseAuth.getInstance();

        post_adapter= new RViewPostAdapter();
        List<RViewPostItem> list = sqLiteHelper.getRViewPostsByUserRatingHistory(mAuth.getCurrentUser().getEmail());
        post_adapter.setList(list);
        LinearLayoutManager manager = new LinearLayoutManager(RatingHistoryActivity.this,RecyclerView.VERTICAL,false);
        r_view_post.setLayoutManager(manager);
        r_view_post.setAdapter(post_adapter);
        // set listener event
        post_adapter.setItemListener(this);
    }
    @Override
    public void onResume() {
        super.onResume();
        List<RViewPostItem> list = sqLiteHelper.getRViewPostsByUserRatingHistory(mAuth.getCurrentUser().getEmail());
        post_adapter.setList(list);
        post_adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(View view, int position) {
        RViewPostItem item = post_adapter.getItem(position);
        sqLiteHelper.addView(new Views(mAuth.getCurrentUser().getEmail(),item.getId()));
        Intent intent = new Intent(RatingHistoryActivity.this, PostDetailActivity.class);
        intent.putExtra("postItem", item);
        startActivity(intent);
    }
}