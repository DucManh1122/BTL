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
import com.android.btl.adapter.RViewBannerPostAdapter;
import com.android.btl.adapter.RViewPostAdapter;
import com.android.btl.adapter.RviewBannerCategory;
import com.android.btl.dal.SQLiteHelper;
import com.android.btl.model.RViewPostItem;
import com.android.btl.model.RviewBannerCategoryItem;
import com.android.btl.model.Views;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Fragment1 extends Fragment {

    private RecyclerView r_view_post,r_view_category,r_view_banner_post;
    protected FirebaseAuth mAuth;
    private SQLiteHelper sqLiteHelper;
    private RViewPostAdapter post_adapter;
    private RviewBannerCategory banner_adapter;
    private RViewBannerPostAdapter banner_post_adapter;
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
        r_view_category = view.findViewById(R.id.r_view_category);
        r_view_banner_post = view.findViewById(R.id.r_view_banner_post);
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
        post_adapter.setItemListener(new RViewPostAdapter.ItemListener() {
            @Override
            public void onItemClick(View view, int position) {
                RViewPostItem item = post_adapter.getItem(position);
                sqLiteHelper.addView(new Views(mAuth.getCurrentUser().getEmail(), item.getId()));
                Intent intent = new Intent(getActivity(), PostDetailActivity.class);
                intent.putExtra("postItem", item);
                startActivity(intent);
            }
        });

        banner_adapter = new RviewBannerCategory();
        List<RviewBannerCategoryItem> lst_cate = getListCategory();
        banner_adapter.setList(lst_cate);
        LinearLayoutManager manager_cate = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        r_view_category.setLayoutManager(manager_cate);
        r_view_category.setAdapter(banner_adapter);
        banner_adapter.setItemListener(new RviewBannerCategory.ItemListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });

        banner_post_adapter = new RViewBannerPostAdapter();
        banner_post_adapter.setList(getTopPosts(list));
        LinearLayoutManager manager_banner_post = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        r_view_banner_post.setLayoutManager(manager_banner_post);
        r_view_banner_post.setAdapter(banner_post_adapter);
        // set listener event
        banner_post_adapter.setItemListener(new RViewBannerPostAdapter.ItemListener() {
            @Override
            public void onItemClick(View view, int position) {
                RViewPostItem item = post_adapter.getItem(position);
                sqLiteHelper.addView(new Views(mAuth.getCurrentUser().getEmail(), item.getId()));
                Intent intent = new Intent(getActivity(), PostDetailActivity.class);
                intent.putExtra("postItem", item);
                startActivity(intent);
            }
        });

    }

    private List<RViewPostItem> getTopPosts(List<RViewPostItem> list){
        List<RViewPostItem> lst = new ArrayList<>();
        lst.addAll(list);
        Collections.sort(lst, new Comparator<RViewPostItem>() {
            @Override
            public int compare(RViewPostItem lhs, RViewPostItem rhs) {
                // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                return lhs.getRtbar() > rhs.getRtbar() ? -1 : (lhs.getRtbar() < rhs.getRtbar()) ? 1 : 0;
            }
        });
        return lst.subList(0,9);
    }

    private List<RviewBannerCategoryItem> getListCategory() {
        List<RviewBannerCategoryItem> lst = new ArrayList<>();
        lst.add(new RviewBannerCategoryItem("Món ngon Việt Nam",R.drawable.danh_muc_1,R.drawable.banner_bg_2));
        lst.add(new RviewBannerCategoryItem("Món ngon Châu Á",R.drawable.danh_muc_2,R.drawable.banner_bg));
        lst.add(new RviewBannerCategoryItem("Món ngon Châu Âu",R.drawable.danh_muc_3,R.drawable.banner_bg_1));

        return lst;
    }

    @Override
    public void onResume() {
        super.onResume();
        List<RViewPostItem> list = sqLiteHelper.getAllRViewPosts();
        post_adapter.setList(list);
        post_adapter.notifyDataSetChanged();
        banner_post_adapter.setList(getTopPosts(list));
        banner_post_adapter.notifyDataSetChanged();
    }
}
