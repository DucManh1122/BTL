package com.android.btl;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements  View.OnClickListener{
    private final static int REQUEST_CODE_REGISTER = 10000;
    Button btnLogin;
    EditText txtemail, txtpassword;
    TextView resetPassword, register;
    protected FirebaseAuth mAuth;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {

        btnLogin = findViewById(R.id.btnLogin);
        txtemail = findViewById(R.id.txtemail);
        txtpassword = findViewById(R.id.txtpassword);
        resetPassword = findViewById(R.id.resetpassword);
        register = findViewById(R.id.register);
        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progessbar);
        progressBar.setVisibility(View.GONE);

        btnLogin.setOnClickListener(this);
        resetPassword.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }

    @Override
    public void onClick(View v) {
        if (v == btnLogin){
            progressBar.setVisibility(View.VISIBLE);
            String email = txtemail.getText().toString().trim();
            String password = txtpassword.getText().toString().trim();
            if(TextUtils.isEmpty(email)){
                progressBar.setVisibility(View.GONE);
                Toast.makeText(LoginActivity.this, "Enter email", Toast.LENGTH_SHORT).show();
                return;
            }
            if(TextUtils.isEmpty(password)){
                progressBar.setVisibility(View.GONE);
                Toast.makeText(LoginActivity.this, "Enter password", Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(LoginActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
        }
        else if (v == register) {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivityForResult(intent, REQUEST_CODE_REGISTER);
        }
        else if (v == resetPassword) {
            mAuth.sendPasswordResetEmail(txtemail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, "An email has been sent to you.", Toast.LENGTH_LONG).show();
                    } else {

                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_REGISTER) {
            if(resultCode == Activity.RESULT_OK) {
                final String email = data.getStringExtra("email");
                final String password = data.getStringExtra("pass");
                FirebaseUser user = mAuth.getCurrentUser();
                txtemail.setText(email);
                txtpassword.setText(password);
            } else {
                // DetailActivity không thành công, không có data trả về.
            }
        }
    }
}