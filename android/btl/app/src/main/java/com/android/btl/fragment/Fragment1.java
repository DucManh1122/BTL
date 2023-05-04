package com.android.btl.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.btl.PostDetailActivity;
import com.android.btl.R;
import com.android.btl.adapter.RViewPostAdapter;
import com.android.btl.dal.SQLiteHelper;
import com.android.btl.model.RViewPostItem;
import com.android.btl.model.Views;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;


public class Fragment1 extends Fragment implements RViewPostAdapter.ItemListener{

    private RecyclerView r_view_post;
    protected FirebaseAuth mAuth;
    private SQLiteHelper sqLiteHelper;
    private RViewPostAdapter post_adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_1,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {

        r_view_post = view.findViewById(R.id.r_view_post);
        mAuth = FirebaseAuth.getInstance();
        sqLiteHelper = new SQLiteHelper(getContext());


        sqLiteHelper.initDataPost();
        sqLiteHelper.initDataRating();
        sqLiteHelper.initDataViews();
        sqLiteHelper.initDataComment();




        post_adapter= new RViewPostAdapter();
        List<RViewPostItem> list = sqLiteHelper.getAllRViewPosts();
        post_adapter.setList(list);
        // set layout cho r_view
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        r_view_post.setLayoutManager(manager);
        r_view_post.setAdapter(post_adapter);
        // set listener event
        post_adapter.setItemListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        List<RViewPostItem> list = sqLiteHelper.getAllRViewPosts();
        post_adapter.setList(list);
        post_adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(View view, int position) {

        RViewPostItem item = post_adapter.getItem(position);
        sqLiteHelper.addView(new Views(mAuth.getCurrentUser().getEmail(),item.getId()));
        Intent intent = new Intent(getActivity(), PostDetailActivity.class);
        intent.putExtra("postItem", item);
        startActivity(intent);
    }
}
