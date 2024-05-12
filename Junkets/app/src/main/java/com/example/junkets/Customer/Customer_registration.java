package com.example.junkets.Customer;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.junkets.Admin.Admin_home;
import com.example.junkets.Common.Login;
import com.example.junkets.Common.Utility;
import com.example.junkets.Hotel.Hotel_home;
import com.example.junkets.R;
import com.example.junkets.TravelAgency.Agency_home;

import java.util.HashMap;
import java.util.Map;

public class Customer_registration extends AppCompatActivity {

    EditText name,age,aadhaar,phone,address,email,password;
    Button register;
    String NAME,AGE,AADHAAR,ADDRESS,PHONE,EMAIL,PASSWORD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_customer_registration);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        name=findViewById(R.id.customer_reg_name);
        age=findViewById(R.id.customer_reg_age);
        aadhaar=findViewById(R.id.customer_reg_aadhaar);
        address=findViewById(R.id.customer_reg_aadress);
        email=findViewById(R.id.customer_reg_email);
        phone=findViewById(R.id.customer_reg_phone);
        password=findViewById(R.id.customer_reg_password);
        register=findViewById(R.id.customer_reg_button);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NAME=name.getText().toString();
                AGE=age.getText().toString();
                AADHAAR=aadhaar.getText().toString();
                ADDRESS=address.getText().toString();
                PHONE=phone.getText().toString();
                EMAIL=email.getText().toString();
                PASSWORD=password.getText().toString();

                if(NAME.isEmpty()){
                    name.requestFocus();
                    name.setError("please add name");
                }else if(AGE.isEmpty()){
                    age.requestFocus();
                    age.setError("please add age");
                }else if(AADHAAR.isEmpty()){
                    aadhaar.requestFocus();
                    aadhaar.setError("please add aadhaar no");
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
                    Volly_call_Customer_registration();
                }
            }
        });

    }
    public void Volly_call_Customer_registration(){

        final ProgressDialog pd;
        pd = new ProgressDialog(Customer_registration.this);
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
                map.put("key", "Customer_registration");
                map.put("name", NAME);
                map.put("age", AGE);
                map.put("aadhaar", AADHAAR);
                map.put("address", ADDRESS);
                map.put("phone", PHONE);
                map.put("email", EMAIL);
                map.put("password", PASSWORD);

                return map;
            }
        };
        queue.add(request);
    }

}
