package com.example.hotel;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vishnusivadas.advanced_httpurlconnection.PutData;


public class SignupActivity extends AppCompatActivity {
    private ImageView alreadyHaveAccount;
    private EditText editTextUsername, editTextEmail, editTextPassword;
    private Button buttonSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        alreadyHaveAccount= (ImageView) findViewById(R.id.imageViewAlreadyHave);
        editTextUsername= (EditText) findViewById(R.id.editTextTextPersonName);
        editTextEmail= (EditText) findViewById(R.id.loginUser);
        editTextPassword= (EditText) findViewById(R.id.loginPassword);
        buttonSignup= (Button) findViewById(R.id.buttonSignUp);

        buttonSignup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String username= String.valueOf(editTextUsername.getText());
                String email= String.valueOf(editTextEmail.getText());
                String password= String.valueOf(editTextPassword.getText());

                if(username.isEmpty() || email.isEmpty() || password.isEmpty() ){
                    Toast.makeText(getApplicationContext(), "Please make sure all fields are not empty!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //Starting Write and Read data with URL
                        //Creating array for parameters
                        String[] field = new String[3];
                        field[0] = "username";
                        field[1] = "email";
                        field[2] = "password";
                        //Creating array for data
                        String[] data = new String[3];
                        data[0] = username;
                        data[1] = email;
                        data[2] = password;
                        PutData putData = new PutData(MainActivity.IP+"signup.php", "POST", field, data);
                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                String result = putData.getResult();
                              if(result.equals("Sign Up Success")){
                                  Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                  Intent intent=new Intent(SignupActivity.this , LoginActivity.class);
                                  startActivity(intent);
                                  finish();
                              }
                              else{
                                  Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                              }

                            }
                        }
                        //End Write and Read data with URL
                    }
                });

            }
        });

        alreadyHaveAccount.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignupActivity.this , LoginActivity.class);
                startActivity(intent);
                finish();

            }
        });






    }
}