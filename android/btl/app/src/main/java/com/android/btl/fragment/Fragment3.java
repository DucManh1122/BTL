package com.android.btl.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.btl.MyPostActivity;
import com.android.btl.R;
import com.android.btl.RatingHistoryActivity;
import com.android.btl.ViewHistoryActivity;


public class Fragment3 extends Fragment implements View.OnClickListener{
    private Button btn_my_post,btn_my_rating,btn_view_history;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_3,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        btn_my_post = view.findViewById(R.id.btn_my_post);
        btn_my_rating = view.findViewById(R.id.btn_my_rating);
        btn_view_history = view.findViewById(R.id.btn_view_history);

        btn_my_post.setOnClickListener(this);
        btn_my_rating.setOnClickListener(this);
        btn_view_history.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        if(v == btn_my_post){
            Intent intent = new Intent(getActivity(), MyPostActivity.class);
            startActivity(intent);
        }
        if(v==btn_my_rating){
            Intent intent = new Intent(getActivity(), RatingHistoryActivity.class);
            startActivity(intent);
        }
        if(v==btn_view_history){
            Intent intent = new Intent(getActivity(), ViewHistoryActivity.class);
            startActivity(intent);
        }
    }
}
