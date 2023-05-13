package com.android.btl.adapter;

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
import com.android.btl.model.RviewBannerCategoryItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RviewBannerCategory extends RecyclerView.Adapter<RviewBannerCategory.HomeViewHolder>{
    // list la data cho r_view
    private List<RviewBannerCategoryItem> list;
    // ItemListener de lang nghe su kien chon
    private ItemListener itemListener;
    // contructor
    public RviewBannerCategory() {
        list= new ArrayList<>();
    }

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    // ham xu ly khi list data thay doi
    public void setList(List<RviewBannerCategoryItem> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public RviewBannerCategoryItem getItem(int position){
        return list.get(position);
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_item_rview_category,parent,false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        RviewBannerCategoryItem item = list.get(position);
        holder.image.setImageResource(item.getImage());
        holder.title.setText(item.getTitle());
        holder.bg_view.setBackgroundResource(item.getBg());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView title;
        private ImageView image;
        private View bg_view;
        public HomeViewHolder(@NonNull View view) {
            super(view);
            image = view.findViewById(R.id.imageView6);
            title = view.findViewById(R.id.tv_category);
            bg_view = view.findViewById(R.id.view);
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
