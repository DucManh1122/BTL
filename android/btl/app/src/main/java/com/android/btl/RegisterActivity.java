package com.android.btl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnRegister,btnCancel;
    EditText txtemail,txtpassword;
    protected FirebaseAuth mAuth;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        btnRegister = findViewById(R.id.btnRegister);
        txtemail = findViewById(R.id.txtemail);
        btnCancel = findViewById(R.id.btnCancel);
        txtpassword = findViewById(R.id.txtpassword);
        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progessbar);
        progressBar.setVisibility(View.GONE);
        btnCancel.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == btnCancel){
            finish();
        }
        else if(v == btnRegister){
            progressBar.setVisibility(View.VISIBLE);
            String email = txtemail.getText().toString().trim();
            String password = txtpassword.getText().toString().trim();
            if(TextUtils.isEmpty(email)){
                progressBar.setVisibility(View.GONE);
                Toast.makeText(RegisterActivity.this, "Enter email", Toast.LENGTH_SHORT).show();
                return;
            }
            if(TextUtils.isEmpty(password)){
                progressBar.setVisibility(View.GONE);
                Toast.makeText(RegisterActivity.this, "Enter password", Toast.LENGTH_SHORT).show();
                return;
            }
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                            if (task.isSuccessful()) {

                                Toast.makeText(RegisterActivity.this, "Đăng ký thành công.", Toast.LENGTH_SHORT).show();

                                Intent data = new Intent();
                                data.putExtra("email", txtemail.getText().toString());
                                data.putExtra("pass", txtpassword.getText().toString());

                                // Đặt resultCode là Activity.RESULT_OK
                                setResult(Activity.RESULT_OK, data);

                                finish();

                            } else {

                                Toast.makeText(RegisterActivity.this, "Đăng ký thất bại.",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}