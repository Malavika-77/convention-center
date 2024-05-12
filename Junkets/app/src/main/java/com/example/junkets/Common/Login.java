package com.example.junkets.Common;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.junkets.Admin.Admin_home;
import com.example.junkets.Customer.Customer_home;
import com.example.junkets.Hotel.Hotel_home;
import com.example.junkets.MainActivity;
import com.example.junkets.R;
import com.example.junkets.TravelAgency.Agency_home;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    Button login;
    TextView register;
    EditText email,password;
    String EMAIL,PASSWORD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        int PERMISSION_ALL = 1;
        String[] PERMISSIONS = {
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.CAMERA,
                Manifest.permission.SEND_SMS,

        };

        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }

        login = findViewById(R.id.login_button);
        register = findViewById(R.id.login_register);
        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);




        register.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                return false;
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EMAIL = email.getText().toString();
                PASSWORD = password.getText().toString();

                if (EMAIL.isEmpty()) {
                    email.requestFocus();
                    email.setError("please enter valid email");
                } else if (PASSWORD.isEmpty()) {
                    password.requestFocus();
                    password.setError("please enter password");
                } else {
                    Volly_call_Login();

                }
            }
        });


    }

    public void Volly_call_Login(){

        final ProgressDialog pd;
        pd = new ProgressDialog(Login.this);
        pd.setCancelable(false);
        pd.setTitle("Logging in...");
        pd.show();

        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, Utility.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);
                pd.dismiss();

                if (!response.trim().equals("failed")) {

                    Toast.makeText(getApplicationContext(), "Successfully Logged In", Toast.LENGTH_LONG).show();

                    String data = response;
                    String arr[] = data.trim().split(":");
                    String reg_id = arr[0];
                    String usertype = arr[1];

                    //.......... Storing credentials to preferences................................

                    SharedPreferences.Editor editor = getSharedPreferences("mysharedprefs", MODE_PRIVATE).edit();
                    editor.putString("reg_id", reg_id);
                    editor.putString("usertype", usertype);
                    editor.apply();

                    if (usertype.equals("admin")){
                        startActivity(new Intent(getApplicationContext(), Admin_home.class));
                    }
                    else  if (usertype.equals("hotel")){
                        startActivity(new Intent(getApplicationContext(), Hotel_home.class));
                    }
                    else  if (usertype.equals("agency")){
                        startActivity(new Intent(getApplicationContext(), Agency_home.class));
                    }
                    else {
                        startActivity(new Intent(getApplicationContext(), Customer_home.class));

                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "invalid username or password !", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), Login.class));
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
                map.put("key", "login");
                map.put("email", EMAIL);
                map.put("password", PASSWORD);

                return map;
            }
        };
        queue.add(request);
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }


}
