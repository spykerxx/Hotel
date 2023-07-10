package com.example.hotel;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UserShowActivity extends AppCompatActivity {
    private static  final String BASE_URL = MainActivity.IP+"get_users.php";
    String[] username;
    String[] email;
    private List<User> items = new ArrayList<>();
    private BottomNavigationView bottomNavigationView;
    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_show);

        recycler=findViewById(R.id.user_recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        loadItems();
        //  print();


        bottomNavigationView=(BottomNavigationView)findViewById(R.id.nav_viewFood);
        bottomNavigationView.setSelectedItemId(R.id.navigation_users);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.navigation_users:
                    return true;

                case R.id.navigation_rooms:
                    startActivity(new Intent(getApplicationContext() , RoomViewActivity.class));
                    finish();
                    overridePendingTransition(0,0);
                    return true;

                 }
            return false;
        });





    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void loadItems() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {



                        try {

                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i<array.length(); i++){

                                JSONObject object = array.getJSONObject(i);
                                String id =Integer.toString(object.getInt("id"));
                                String name = object.getString("username");
                                String email = object.getString("email");
                                String roomNo= object.getString("roomNo");
                             // Toast.makeText(UserShowActivity.this, name,Toast.LENGTH_LONG).show();
                                User user = new User(id,name, email,roomNo);
                                items.add(user);
                            }
                            //text.setText(items.toString());
                        }catch (Exception e){

                        }

                        CaptionedUserAdapter adapter = new CaptionedUserAdapter(UserShowActivity.this,
                                items);
                        recycler.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                Toast.makeText(UserShowActivity.this, error.toString(),Toast.LENGTH_LONG).show();

            }
        });

        Volley.newRequestQueue(UserShowActivity.this).add(stringRequest);

    }
    private void print(){
        //   text.setText("22"+"-->"+items.get(1).toString());

    }
}