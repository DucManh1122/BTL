package com.android.btl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.btl.adapter.RViewPostAdapter;
import com.android.btl.dal.SQLiteHelper;
import com.android.btl.model.RViewPostItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MyPostActivity extends AppCompatActivity implements RViewPostAdapter.ItemListener{

    private RecyclerView r_view_post;

    private Button btn_back;
    private FloatingActionButton fab;
    protected FirebaseAuth mAuth;
    private SQLiteHelper sqLiteHelper;
    private RViewPostAdapter post_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_post);
        initView();
    }

    private void initView() {
        r_view_post = findViewById(R.id.r_view_post);
        btn_back = findViewById(R.id.btn_back);
        fab = findViewById(R.id.fab);
        sqLiteHelper = new SQLiteHelper(MyPostActivity.this);
        mAuth = FirebaseAuth.getInstance();

        post_adapter= new RViewPostAdapter();
        List<RViewPostItem> list = sqLiteHelper.getRViewPostsByUser(mAuth.getCurrentUser().getEmail());
        post_adapter.setList(list);
        LinearLayoutManager manager = new LinearLayoutManager(MyPostActivity.this,RecyclerView.VERTICAL,false);
        r_view_post.setLayoutManager(manager);
        r_view_post.setAdapter(post_adapter);
        // set listener event
        post_adapter.setItemListener(this);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MyPostActivity.this, AddPostActivity.class);
                startActivity(intent);
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        List<RViewPostItem> list = sqLiteHelper.getRViewPostsByUser(mAuth.getCurrentUser().getEmail());
        post_adapter.setList(list);
        post_adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(View view, int position) {
        RViewPostItem item = post_adapter.getItem(position);
        Intent intent = new Intent(MyPostActivity.this, MyPostDetailActivity.class);
        intent.putExtra("postItem", item);
        startActivity(intent);
    }
}