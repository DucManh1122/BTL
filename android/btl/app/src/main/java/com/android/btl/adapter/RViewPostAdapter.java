package com.android.btl.adapter;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.btl.R;
import com.android.btl.model.RViewPostItem;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RViewPostAdapter extends RecyclerView.Adapter<RViewPostAdapter.HomeViewHolder>{
    // list la data cho r_view
    private List<RViewPostItem> list;
    // ItemListener de lang nghe su kien chon
    private ItemListener itemListener;
    // contructor
    public RViewPostAdapter() {
        list= new ArrayList<>();
    }

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    // ham xu ly khi list data thay doi
    public void setList(List<RViewPostItem> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public RViewPostItem getItem(int position){
        return list.get(position);
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_r_view_post,parent,false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        RViewPostItem item = list.get(position);

        Picasso.get().load(item.getImage()).into(holder.image);

        try{
            holder.title.setText(item.getTitle().substring(0,40)+"...");
        }catch (Exception e){
            holder.title.setText(item.getTitle());
        }
        try {
            holder.content.setText("Nội dung: "+item.getContent().substring(0,80)+"...");
        }catch (Exception e){
            holder.content.setText("Nội dung: "+item.getContent());
        }

        holder.date.setText("Ngày: "+item.getDate());
        holder.user.setText(item.getUser());
        holder.views.setText(item.getViews());
        holder.category.setText(item.getCategory());
        holder.rtbar.setRating(item.getRtbar());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView title,content,date,user,views,category;
        private ImageView image;
        private RatingBar rtbar;
        public HomeViewHolder(@NonNull View view) {
            super(view);
            image = view.findViewById(R.id.img);
            title = view.findViewById(R.id.tv_title);
            content = view.findViewById(R.id.tv_content);
            date = view.findViewById(R.id.tv_date);
            user = view.findViewById(R.id.tv_user);
            views = view.findViewById(R.id.tv_view);
            category = view.findViewById(R.id.tv_category);
            rtbar = view.findViewById(R.id.rt_bar);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(itemListener!=null){
                itemListener.onItemClick(view,getAdapterPosition());
            }
        }
    }
    public interface ItemListener{
        void onItemClick(View view, int position);
    }
}
