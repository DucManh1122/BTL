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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RViewBannerPostAdapter extends RecyclerView.Adapter<RViewBannerPostAdapter.HomeViewHolder>{
    private List<RViewPostItem> list;
    // ItemListener de lang nghe su kien chon
    private ItemListener itemListener;
    // contructor
    public RViewBannerPostAdapter() {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_item_rview_post,parent,false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        RViewPostItem item = list.get(position);
        try{
            holder.title.setText(item.getTitle().substring(0,60)+"...");
        }catch (Exception e){
            holder.title.setText(item.getTitle());
        }

        Picasso.get().load(item.getImage()).into(holder.image);
        holder.rtbar.setRating(item.getRtbar());
        holder.rt_text.setText("Stars: "+String.valueOf(item.getRtbar()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView title,rt_text;
        private ImageView image;
        private RatingBar rtbar;
        public HomeViewHolder(@NonNull View view) {
            super(view);
            image = view.findViewById(R.id.imageView4);
            title = view.findViewById(R.id.textView5);
            rtbar = view.findViewById(R.id.ratingBar);
            rt_text = view.findViewById(R.id.textView6);
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
