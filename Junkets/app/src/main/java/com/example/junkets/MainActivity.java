package com.example.junkets;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.junkets.Common.About;
import com.example.junkets.Customer.Customer_registration;
import com.example.junkets.Hotel.Hotel_registration;
import com.example.junkets.TravelAgency.Agency_registration;

public class MainActivity extends AppCompatActivity {

    ImageView user,hotel,agency,about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        user=findViewById(R.id.user_img);
        hotel=findViewById(R.id.hotel_img);
        agency=findViewById(R.id.travel_img);
        about=findViewById(R.id.about_img);

        user.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                startActivity(new Intent(getApplicationContext(), Customer_registration.class));
                return false;
            }
        });

        hotel.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                startActivity(new Intent(getApplicationContext(), Hotel_registration.class));
                return false;
            }
        });
        agency.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                startActivity(new Intent(getApplicationContext(), Agency_registration.class));
                return false;
            }
        });
        about.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                startActivity(new Intent(getApplicationContext(), About.class));
                return false;
            }
        });

    }
}
