package com.android.btl;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.btl.dal.SQLiteHelper;
import com.android.btl.model.Post;
import com.android.btl.model.RViewPostItem;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

public class UpdateAndDeletePostActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText tv_title,tv_img,tv_video,tv_content,tv_date;

    private Spinner spCategory;
    private Button btn_update,btn_delete;
    protected FirebaseAuth mAuth;
    private SQLiteHelper sqLiteHelper;

    private RViewPostItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_and_delete_post);
        getSupportActionBar().hide();
        getIntentData();
        initView();
    }

    private void getIntentData() {
        Intent intent=getIntent();
        item = (RViewPostItem) intent.getSerializableExtra("postItem");
    }

    private void initView() {
        tv_title = findViewById(R.id.tv_title);
        tv_img = findViewById(R.id.tv_img);
        tv_video = findViewById(R.id.tv_video);
        tv_content = findViewById(R.id.tv_content);
        tv_date = findViewById(R.id.tv_date);
        btn_update = findViewById(R.id.btn_update);
        btn_delete = findViewById(R.id.btn_delete);
        spCategory = findViewById(R.id.spCategory);
        sqLiteHelper = new SQLiteHelper(UpdateAndDeletePostActivity.this);
        mAuth = FirebaseAuth.getInstance();

        spCategory.setAdapter(new ArrayAdapter<String>(this,R.layout.item_spinner,
                getResources().getStringArray(R.array.category)));

        spCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String cate=spCategory.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        tv_title.setText(item.getTitle());
        tv_img.setText(item.getImage());
        tv_video.setText(item.getVideo());
        tv_content.setText(item.getContent());
        tv_date.setText(item.getDate());
        int p=0;
        for(int i=0; i<spCategory.getCount();i++){
            if(spCategory.getItemAtPosition(i).toString().equalsIgnoreCase(item.getCategory())){
                p=i;
                break;
            }
        }
        spCategory.setSelection(p);


        btn_update.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        tv_date.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v==btn_update){
            String username = mAuth.getCurrentUser().getEmail();
            String title = tv_title.getText().toString();
            String image =tv_img.getText().toString();
            String video = tv_video.getText().toString();
            String category = spCategory.getSelectedItem().toString();
            String content = tv_content.getText().toString();
            String date = tv_date.getText().toString();
            sqLiteHelper.updatePost(new Post(item.getId(),username, title, image,video, category, content, date));
            Toast.makeText(this, "Cập nhật bài viết thành công", Toast.LENGTH_SHORT).show();
            finish();
        }
        if(v==btn_delete){
            AlertDialog.Builder builder= new AlertDialog.Builder(UpdateAndDeletePostActivity.this);
            builder.setTitle("Thong bao xoa");
            builder.setMessage("Ban co chac muon xoa bai viet nay khong?");
            builder.setIcon(R.drawable.baseline_delete_forever_24);
            builder.setPositiveButton("Co", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String username = mAuth.getCurrentUser().getEmail();
                    String title = tv_title.getText().toString();
                    String image =tv_img.getText().toString();
                    String video = tv_video.getText().toString();
                    String category = spCategory.getSelectedItem().toString();
                    String content = tv_content.getText().toString();
                    String date = tv_date.getText().toString();
                    sqLiteHelper.deletePost(new Post(item.getId(),username, title, image,video, category, content, date));
                    Toast.makeText(UpdateAndDeletePostActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
            builder.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();

        }
        if(v==tv_date){
            tv_date.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Calendar c = Calendar.getInstance();
                    int year = c.get(Calendar.YEAR);
                    int month = c.get(Calendar.MONTH);
                    int day = c.get(Calendar.DAY_OF_MONTH);
                    DatePickerDialog dialog = new DatePickerDialog(UpdateAndDeletePostActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                            String date = "";
                            if (m > 8) {
                                date = d + "/" + (m + 1) + "/" + y;
                            } else {
                                date = d + "/0" + (m + 1) + "/" + y;
                            }
                            tv_date.setText(date);
                        }
                    }, year, month, day);
                    dialog.show();
                }
            });
        }
    }
}