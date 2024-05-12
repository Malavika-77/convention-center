package com.example.junkets.Hotel;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.junkets.Common.Login;
import com.example.junkets.Common.Utility;
import com.example.junkets.Customer.Customer_registration;
import com.example.junkets.R;

import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Hotel_add_facilities.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Hotel_add_facilities#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Hotel_add_facilities extends Fragment {

    EditText pname,pdesc,prate;
    Button add;
    String PNAME,PDESC,PRATE,H_ID;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Hotel_add_facilities() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Hotel_add_facilities.
     */
    // TODO: Rename and change types and number of parameters
    public static Hotel_add_facilities newInstance(String param1, String param2) {
        Hotel_add_facilities fragment = new Hotel_add_facilities();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_hotel_add_facilities, container, false);

        SharedPreferences prefs = getContext().getSharedPreferences("mysharedprefs", MODE_PRIVATE);
        H_ID= prefs.getString("reg_id", "No logid");

        pname=view.findViewById(R.id.hotel_package_name);
        pdesc=view.findViewById(R.id.hotel_package_desc);
        prate=view.findViewById(R.id.hotel_package_rate);
        add=view.findViewById(R.id.hotel_package_button);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PNAME=pname.getText().toString();
                PDESC=pdesc.getText().toString();
                PRATE=prate.getText().toString();

                if(PNAME.isEmpty()){
                    pname.requestFocus();
                    pname.setError("add package name");
                }else if(PDESC.isEmpty()){
                    pdesc.requestFocus();
                    pdesc.setError("please add description");
                }else if (PRATE.isEmpty()){
                    prate.requestFocus();
                    prate.setError("add rate");
                }else{
                    Volly_call_Hotel_add_package();
                }
            }
        });



        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void Volly_call_Hotel_add_package(){

        final ProgressDialog pd;
        pd = new ProgressDialog(getContext());
        pd.setCancelable(false);
        pd.setTitle("Registering ...");
        pd.show();

        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(Request.Method.POST, Utility.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);
                pd.dismiss();

                if (!response.trim().equals("failed")) {

                    Toast.makeText(getContext(), "Successfully Added", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getContext(), Hotel_home.class));

                }
                else {
                    Toast.makeText(getContext(), "Some error occured !", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getContext(), Hotel_add_facilities.class));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Toast.makeText(getContext(), "my Error :" + error, Toast.LENGTH_LONG).show();
                Log.i("My Error", "" + error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<String, String>();
//                SharedPreferences sp=getSharedPreferences("booking_info", Context.MODE_PRIVATE);
                map.put("key", "hotel_add_package");
                map.put("pname", PNAME);
                map.put("pdesc", PDESC);
                map.put("prate", PRATE);
                map.put("hid", H_ID);


                return map;
            }
        };
        queue.add(request);
    }
}
