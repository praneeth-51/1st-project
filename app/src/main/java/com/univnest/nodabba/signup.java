package com.univnest.nodabba;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {

    EditText enter_name,enter_email,enter_password;
    Button register;
    FirebaseAuth authenticate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.signup);

        authenticate = FirebaseAuth.getInstance();

        enter_email= findViewById(R.id.input_email);
        enter_name = findViewById(R.id.input_name);
        enter_password = findViewById(R.id.input_password);
        register=findViewById(R.id.register_btn);

        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String enterName,enterEmail,enterPassword;
                enterEmail= enter_email.getText().toString();
                enterPassword = enter_password.getText().toString();
                enterName = enter_name.getText().toString();

                if(!enterName.isEmpty() && !enterEmail.isEmpty() && !enterPassword.isEmpty()){
                    UserData ud = new UserData(enterName,enterPassword,enterEmail,null);
                    authenticate.createUserWithEmailAndPassword(enterEmail,enterPassword)
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    Toast.makeText(signup.this, "success", Toast.LENGTH_SHORT).show();
                                    ud.setUserID(authenticate.getUid());
                                    userDataStore(ud);
                                    startActivity(new Intent(getApplicationContext(), HomePageActivity.class));
                                    finish();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(signup.this, "error : "+ e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }

            }
        });
    }
    private void userDataStore(UserData uData){
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("users");
        db.child(uData.getUserID()).setValue(uData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(signup.this, "data saved to database", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(signup.this, "error db : "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}