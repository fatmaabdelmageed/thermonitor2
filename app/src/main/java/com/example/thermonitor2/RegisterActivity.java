package com.example.thermonitor2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;


public class RegisterActivity<email, password> extends AppCompatActivity {

        public EditText emailId, passwd;
        public Button btnSignUp; //register button
        public Button login;
        public TextView signIn, signUp; //login
        FirebaseAuth firebaseAuth;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);
            FirebaseApp.initializeApp(this);

            firebaseAuth = FirebaseAuth.getInstance();
            emailId = findViewById(R.id.username);
            passwd = findViewById(R.id.password);
            btnSignUp = findViewById(R.id.register);
            signUp = findViewById(R.id.signup);
            login= findViewById(R.id.login);
            signIn = findViewById(R.id.signin);
            btnSignUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String emailID = emailId.getText().toString();
                    String paswd = passwd.getText().toString();
                    if (emailID.isEmpty()) {
                        emailId.setError("Provide your Email first!");
                        emailId.requestFocus();
                    } else if (paswd.isEmpty()) {
                        passwd.setError("Set your password");
                        passwd.requestFocus();
                    } else if (emailID.isEmpty() && paswd.isEmpty()) {
                        Toast.makeText(RegisterActivity.this, "Fields Empty!", Toast.LENGTH_SHORT).show();
                    } else if (!(emailID.isEmpty() && paswd.isEmpty())) {
                        firebaseAuth.createUserWithEmailAndPassword(emailID, paswd).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener() {
                            @Override
                            public void onComplete(@NonNull Task task) {

                                if (!task.isSuccessful()) {
                                    Toast.makeText(RegisterActivity.this.getApplicationContext(),
                                            "SignUp unsuccessful: " + task.getException().getMessage(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    startActivity(new Intent(RegisterActivity.this,     ListActivity.class));
                                }
                            }
                        });
                    } else {
                        Toast.makeText(RegisterActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            signIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent I = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(I);
                }
            });
        }
    }