package com.android.btl.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.btl.R;
import com.android.btl.model.Comment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class RViewCommentAdapter extends RecyclerView.Adapter<RViewCommentAdapter.HomeViewHolder>{
    // list la data cho r_view
    private List<Comment> list;
    protected FirebaseAuth mAuth;
    // contructor
    public RViewCommentAdapter() {
        list= new ArrayList<>();
    }


    // ham xu ly khi list data thay doi
    public void setList(List<Comment> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public Comment getItem(int position){
        return list.get(position);
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_r_view_comment,parent,false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        Comment item = list.get(position);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user.getEmail().equals(item.getUsername())){
            holder.tv_username.setText("Tôi");
        }
        else{
            holder.tv_username.setText(item.getUsername());
        }

        holder.tv_content.setText(item.getContent());
        holder.tv_date.setText(item.getDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_username,tv_date,tv_content;
        public HomeViewHolder(@NonNull View view) {
            super(view);
            tv_username = view.findViewById(R.id.tv_username);
            tv_date = view.findViewById(R.id.tv_date);
            tv_content = view.findViewById(R.id.tv_content);
        }

    }

}
