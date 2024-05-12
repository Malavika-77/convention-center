package com.example.junkets.Customer;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.junkets.Common.GPSTracker;
import com.example.junkets.Common.Utility;
import com.example.junkets.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Customer_view_Center extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    GPSTracker gps;
    Double latitude,longitude;
    String lat[],longi[],data[],a_id[],CENTER,A_ID;
    ArrayList id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view__center);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        gps = new GPSTracker(getApplicationContext());
        latitude = gps.getLatitude();
        longitude = gps.getLongitude();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        mMap.addMarker(new MarkerOptions().position( new LatLng(latitude, longitude)).title("me"));

        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(latitude, longitude)).zoom(12).build();


        mMap.addMarker(new MarkerOptions().position(new LatLng(10.1108977, 76.352235)).title("JUNKETS").icon(BitmapDescriptorFactory.fromResource(R.drawable.museum)));
//        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(latitude, longitude)).zoom(18).build();
//        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        volly_call_UserViewGym();
    }

    public void volly_call_UserViewGym() {


        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, Utility.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);

                if (!response.trim().equals("failed")) {

                    String ar[]=response.trim().split("&");
                    lat=ar[0].split("#");
                    longi=ar[1].split("#");
                    data=ar[2].split("#");
                    a_id=ar[3].split("#");


                    for(int i=0;i<=lat.length-1;i++) {

                        mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(lat[i]), Double.parseDouble(longi[i]))).title(data[i]).icon(BitmapDescriptorFactory.fromResource(R.drawable.travel)));
                        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(latitude, longitude)).zoom(18).build();
                        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

//                        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
//                            @Override
//                            public boolean onMarkerClick(Marker marker) {
//
//                                A_ID = a_id[i];
//                                Toast.makeText(getApplicationContext(), "id is "+A_ID, Toast.LENGTH_SHORT).show();
//
//
//                                return false;
//
//
//                            }
//                        });




                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Sorry !", Toast.LENGTH_LONG).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                pd.dismiss();
                Toast.makeText(getApplicationContext(), "my Error :" + error, Toast.LENGTH_LONG).show();
                Log.i("My Error", "" + error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<String, String>();
                map.put("key", "User_view_map");

                return map;
            }
        };
        queue.add(request);
    }
}
