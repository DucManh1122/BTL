package com.android.btl.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

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


public class Fragment2 extends Fragment implements RViewPostAdapter.ItemListener{
    private SearchView searchView;

    protected FirebaseAuth mAuth;
    private RecyclerView r_view_post;
    private SQLiteHelper sqLiteHelper;
    private RViewPostAdapter post_adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_2,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        searchView = view.findViewById(R.id.search);
        r_view_post = view.findViewById(R.id.r_view_post);
        sqLiteHelper = new SQLiteHelper(getContext());
        mAuth = FirebaseAuth.getInstance();

        post_adapter= new RViewPostAdapter();
        List<RViewPostItem> list = new ArrayList<>();
        post_adapter.setList(list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        r_view_post.setLayoutManager(manager);
        r_view_post.setAdapter(post_adapter);
        // set listener event
        post_adapter.setItemListener(this);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //get list from database
                System.out.println(query);
                List<RViewPostItem> list = sqLiteHelper.searchByCategoryandTitle(query);
                post_adapter.setList(list);
                post_adapter.notifyDataSetChanged();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                //get list from database
//                List<RViewPostItem> list = sqLiteHelper.searchByCategoryandTitle(s);
//                post_adapter.setList(list);
//                post_adapter.notifyDataSetChanged();
//                return true;
                return false;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        String query = searchView.getQuery().toString();
        List<RViewPostItem> list = sqLiteHelper.searchByCategoryandTitle(query);
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
