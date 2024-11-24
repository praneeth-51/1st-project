package com.univnest.nodabba;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class login extends AppCompatActivity {

    EditText loginemail, login_password;
    Button login;
    TextView signupbtn;
    FirebaseAuth authenticate;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login);

        loginemail = findViewById(R.id.input_email);
        login_password = findViewById(R.id.input_password);
        login = findViewById(R.id.login_btn);
        signupbtn=findViewById(R.id.goto_signup);
        authenticate = FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginEmail, loginPassword;
                loginEmail = String.valueOf(loginemail.getText());
                loginPassword = String.valueOf(login_password.getText());
                if (TextUtils.isEmpty(loginEmail)) {
                    Toast.makeText(com.univnest.nodabba.login.this, "enter email", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(loginPassword)) {
                    Toast.makeText(com.univnest.nodabba.login.this, "enter password", Toast.LENGTH_SHORT).show();
                    return;

                }
                authenticate.signInWithEmailAndPassword(loginEmail,loginPassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(com.univnest.nodabba.login.this, "authenticate success", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), HomePageActivity.class));
                                    finish();
                                } else {
                                    Toast.makeText(com.univnest.nodabba.login.this, "authenticate failed : "+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


            }
        });
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(login.this, signup.class);
                startActivity(intent);
                finish();
            }
        });


    }


}

