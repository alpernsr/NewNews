package com.alperen.newnews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity {
    private EditText editTextSignupEmail;
    private EditText editTextSignupPassword;
    private Button signup1, back;

    private FirebaseAuthManager firebaseAuthManager;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firebaseAuthManager = new FirebaseAuthManager();

        editTextSignupEmail = findViewById(R.id.email);
        editTextSignupPassword = findViewById(R.id.password);
        signup1 = findViewById(R.id.signup1);
        back = findViewById(R.id.back);

        signup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextSignupEmail.getText().toString().trim();
                String password = editTextSignupPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(SignupActivity.this, "Email and password cannot be empty", Toast.LENGTH_SHORT).show();
                } else {
                    firebaseAuthManager.createUserWithEmailAndPassword(email, password, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignupActivity.this, "Signup successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(SignupActivity.this, "Signup failed", Toast.LENGTH_SHORT).show();
                                Log.e("SignupActivity", "Error: " + task.getException());
                            }
                        }
                    });
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}