package com.android.btl;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
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
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

public class AddPostActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText tv_title,tv_img,tv_video,tv_content,tv_date;

    private Spinner spCategory;
    private Button btn_add;
    protected FirebaseAuth mAuth;
    private SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        initView();
    }

    private void initView() {
        tv_title = findViewById(R.id.tv_title);
        tv_img = findViewById(R.id.tv_img);
        tv_video = findViewById(R.id.tv_video);
        tv_content = findViewById(R.id.tv_content);
        tv_date = findViewById(R.id.tv_date);
        btn_add = findViewById(R.id.btn_add);
        spCategory = findViewById(R.id.spCategory);
        sqLiteHelper = new SQLiteHelper(AddPostActivity.this);
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
        btn_add.setOnClickListener(this);
        tv_date.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==btn_add){
            String username = mAuth.getCurrentUser().getEmail();
            String title = tv_title.getText().toString();
            String image =tv_img.getText().toString();
            String video = tv_video.getText().toString();
            String category = spCategory.getSelectedItem().toString();
            String content = tv_content.getText().toString();
            String date = tv_date.getText().toString();
            sqLiteHelper.addPost(new Post(username, title, image,video, category, content, date));
            Toast.makeText(this, "Thêm bài viết thành công", Toast.LENGTH_SHORT).show();
            finish();
        }
        if(v==tv_date){
            tv_date.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Calendar c = Calendar.getInstance();
                    int year = c.get(Calendar.YEAR);
                    int month = c.get(Calendar.MONTH);
                    int day = c.get(Calendar.DAY_OF_MONTH);
                    DatePickerDialog dialog = new DatePickerDialog(AddPostActivity.this, new DatePickerDialog.OnDateSetListener() {
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