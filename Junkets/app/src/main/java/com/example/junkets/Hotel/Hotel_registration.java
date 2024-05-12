package com.example.junkets.Hotel;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.junkets.Common.GPSTracker;
import com.example.junkets.Common.Login;
import com.example.junkets.Common.Utility;
import com.example.junkets.Customer.Customer_registration;
import com.example.junkets.R;

import java.util.HashMap;
import java.util.Map;

public class Hotel_registration extends AppCompatActivity {

    EditText name,phone,address,email,password;
    RatingBar ratingBar;
    Button register;
    String NAME,RATING,ADDRESS,PHONE,EMAIL,PASSWORD;
    Double lattitude=0.0,longitude=0.0;
    GPSTracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_hotel_registration);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        name=findViewById(R.id.hotel_reg_name);
        ratingBar=findViewById(R.id.hotel_rating);
        address=findViewById(R.id.hotel_reg_place);
        email=findViewById(R.id.hotel_reg_email);
        phone=findViewById(R.id.hotel_reg_phone);
        password=findViewById(R.id.hotel_reg_password);
        register=findViewById(R.id.hotel_reg_button);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

                RATING= String.valueOf(ratingBar.getRating());

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NAME=name.getText().toString();
                ADDRESS=address.getText().toString();
                PHONE=phone.getText().toString();
                EMAIL=email.getText().toString();
                PASSWORD=password.getText().toString();
                gps=new GPSTracker(getApplicationContext());
                lattitude=gps.getLatitude();
                longitude=gps.getLongitude();

                if(NAME.isEmpty()){
                    name.requestFocus();
                    name.setError("please add name");
                }else if(PHONE.isEmpty()){
                    phone.requestFocus();
                    phone.setError("please add contact no");
                }else if(EMAIL.isEmpty()){
                    email.requestFocus();
                    email.setError("please add email");
                }else if(PASSWORD.isEmpty()){
                    password.requestFocus();
                    password.setError("please add password");
                }else if(ADDRESS.isEmpty()){
                    address.requestFocus();
                    address.setError("please add address");
                }else{
                    Volly_call_Hotel_registration();
                }
            }
        });
    }



    public void Volly_call_Hotel_registration(){

        final ProgressDialog pd;
        pd = new ProgressDialog(Hotel_registration.this);
        pd.setCancelable(false);
        pd.setTitle("Registering ...");
        pd.show();

        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, Utility.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);
                pd.dismiss();

                if (!response.trim().equals("failed")) {

                    Toast.makeText(getApplicationContext(), "Successfully Registered", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), Login.class));

                }
                else {
                    Toast.makeText(getApplicationContext(), "Some error occured !", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), Customer_registration.class));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Toast.makeText(getApplicationContext(), "my Error :" + error, Toast.LENGTH_LONG).show();
                Log.i("My Error", "" + error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<String, String>();
//                SharedPreferences sp=getSharedPreferences("booking_info", Context.MODE_PRIVATE);
                map.put("key", "hotel_registration");
                map.put("name", NAME);
                map.put("rating", RATING);
                map.put("address", ADDRESS);
                map.put("phone", PHONE);
                map.put("email", EMAIL);
                map.put("password", PASSWORD);
                map.put("lattitude",""+ lattitude);
                map.put("longitude",""+ longitude);

                return map;
            }
        };
        queue.add(request);
    }

}
