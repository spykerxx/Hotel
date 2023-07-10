package com.example.hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CurrentReservationActivity extends AppCompatActivity {

    private TextView room;
    private Button checkout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_reservation);

        room= (TextView) findViewById(R.id.ResRoomNumber);

        room.setText(HomeActivity.currentRoomNumber);

        checkout= (Button) findViewById(R.id.buttonCheckOut);
        if (room.getText().toString().isEmpty()){
            checkout.setEnabled(false);
        }
        checkout.setOnClickListener(v -> {

            String number= room.getText().toString();

            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    //Starting Write and Read data with URL
                    //Creating array for parameters
                    String[] field = new String[2];
                    field[0] = "number";
                    field[1] = "username";
                    //Creating array for data
                    String[] data = new String[2];
                    data[0] = number;
                    data[1] = HomeActivity.username;
                    PutData putData = new PutData(MainActivity.IP+"checkout.php", "POST", field, data);
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            String result = putData.getResult();
                            if(result.equals("checkout Success")){
                                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(CurrentReservationActivity.this , HomeActivity.class);
                                startActivity(intent);
                                finish();
                                HomeActivity.clearReservation();
                            }
                            else{
                                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                    //End Write and Read data with URL
                }
            });



        });
    }
}