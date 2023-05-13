package com.android.btl;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.btl.adapter.RViewCommentAdapter;
import com.android.btl.dal.SQLiteHelper;
import com.android.btl.model.Comment;
import com.android.btl.model.RViewPostItem;
import com.android.btl.model.Rating;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostDetailActivity extends AppCompatActivity {
    private TextView title,content,date,user,views,category;
    protected FirebaseAuth mAuth;
    private EditText tv_comment;
    private Button btn_send,btn_back,btn_rating;
    private ImageView image;
    private RatingBar rtbar,rt_bar_1;

    private RecyclerView r_view_comment;
    private RViewCommentAdapter comment_adapter;

    private SQLiteHelper sqLiteHelper;
    
    private RViewPostItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        getSupportActionBar().hide();
        getIntentData();
        initView();
    }

    private void initView() {
        image = findViewById(R.id.img);
        title = findViewById(R.id.tv_title);
        content = findViewById(R.id.tv_content);
        date = findViewById(R.id.tv_date);
        user = findViewById(R.id.tv_user);
        views = findViewById(R.id.tv_view);
        category = findViewById(R.id.tv_category);
        rtbar = findViewById(R.id.rt_bar);
        tv_comment = findViewById(R.id.tv_comment);
        btn_send = findViewById(R.id.btn_send);
        btn_back = findViewById(R.id.btn_back);
        mAuth = FirebaseAuth.getInstance();
        btn_rating = findViewById(R.id.btn_rating);
        rt_bar_1 = findViewById(R.id.rt_bar_1);
        sqLiteHelper = new SQLiteHelper(this);
        item = sqLiteHelper.getRViewPostItem(item.getId());

//        video.setVideoPath("https://player.vimeo.com/video/231571928");
//        MediaController mediaController = new MediaController(this);
//        mediaController.setAnchorView(video);
//        video.setMediaController(mediaController);
//        video.start();

        Picasso.get().load(item.getImage()).into(image);
        title.setText(item.getTitle());
        content.setText(item.getContent());
        date.setText("Ngày đăng: "+item.getDate());
        user.setText(item.getUser());
        views.setText(item.getViews());
        category.setText(item.getCategory());
        rtbar.setRating(item.getRtbar());
        Rating rt_old = sqLiteHelper.findRatings(mAuth.getCurrentUser().getEmail(),item.getId());
        if(rt_old!=null){
            rt_bar_1.setRating(rt_old.getRating());
        }
        r_view_comment = findViewById(R.id.r_view_comment);
        comment_adapter= new RViewCommentAdapter();
        List<Comment> list = sqLiteHelper.getCommnetByIdPost(item.getId());

        comment_adapter.setList(list);
        // set layout cho r_view
        LinearLayoutManager manager = new LinearLayoutManager(PostDetailActivity.this,RecyclerView.VERTICAL,false);
        r_view_comment.setLayoutManager(manager);
        r_view_comment.setAdapter(comment_adapter);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = tv_comment.getText().toString().trim();
                if(comment.isEmpty()){
                    Toast.makeText(PostDetailActivity.this, "Vui lòng nhập bình luận", Toast.LENGTH_SHORT).show();
                }
                else{
                    FirebaseUser username = mAuth.getCurrentUser();
                    sqLiteHelper.addComment(new Comment(username.getEmail(),comment,new SimpleDateFormat("dd/MM/yyyy").format(new Date()),item.getId()));
                    tv_comment.setText("");
                    List<Comment> list = sqLiteHelper.getCommnetByIdPost(item.getId());
                    comment_adapter.setList(list);
                    Toast.makeText(PostDetailActivity.this, "Đã thêm bình luận", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(item.getVideo())));
            }
        });

        btn_rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Rating rt_old = sqLiteHelper.findRatings(mAuth.getCurrentUser().getEmail(),item.getId());
                AlertDialog.Builder builder= new AlertDialog.Builder(PostDetailActivity.this);
                builder.setTitle("Thong bao: ");
                if(rt_old != null){
                    builder.setMessage("Bạn đã đánh giá "+rt_old.getRating()+" sao cho bài viết, đánh giá lại?");
                }
                else{
                    builder.setMessage("Bạn có muốn đánh giá bài viết?");
                }
                builder.setIcon(R.drawable.baseline_delete_forever_24);
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if(rt_old!=null){
                            rt_old.setRating(rt_bar_1.getRating());
                            sqLiteHelper.updateRatings(rt_old);
                        }
                        else{
                            Rating rtt = new Rating(mAuth.getCurrentUser().getEmail(),item.getId(),rt_bar_1.getRating());
                            sqLiteHelper.addRating(rtt);
                        }
                        item = sqLiteHelper.getRViewPostItem(item.getId());
                        rtbar.setRating(item.getRtbar());

                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    private void getIntentData() {
        Intent intent=getIntent();
        item = (RViewPostItem) intent.getSerializableExtra("postItem");
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Comment> list = sqLiteHelper.getCommnetByIdPost(item.getId());
        comment_adapter.setList(list);
    }
}