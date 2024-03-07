package com.example.driveinn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class creditpoint_payment extends AppCompatActivity {
    EditText bn,ifsc,acno;
    TextView amount;
    Button bt;
    SharedPreferences sh;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditpoint_payment);
        bn=findViewById(R.id.editTextTextPersonName7);
        ifsc=findViewById(R.id.editTextTextPersonName8);
        acno=findViewById(R.id.editTextTextPersonName9);
        amount=findViewById(R.id.textView58);
        bt=findViewById(R.id.button12);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String amp=sh.getString("amount_credit","");
        amount.setText(amp);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String amt=sh.getString("amt", "");
                final String bname=bn.getText().toString();
                final String ifs=ifsc.getText().toString();
                final String ac=acno.getText().toString();
                sh.getString("ip", "");
//                url = sh.getString("urll", "") + "android_credit_point_payment";
                String url=sh.getString("urll","")+"/android_credit_point_payment";

                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                                try {
                                    JSONObject jsonObj = new JSONObject(response);
                                    if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
                                        Toast.makeText(creditpoint_payment.this, "payment using credit point", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(getApplicationContext(), home.class);
                                        startActivity(i);
                                    } else if (jsonObj.getString("status").equalsIgnoreCase("no")) {
                                        Toast.makeText(creditpoint_payment.this, "wrong bank details", Toast.LENGTH_SHORT).show();
                                        //                                        Intent i =new Intent(getApplicationContext(),Make_payment.class);
                                        //                                        startActivity(i);
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Insufficient balance", Toast.LENGTH_LONG).show();
                                    }
                                    //                                    }
                                    //
                                    //
                                    //                                    else {
                                    //                                        Toast.makeText(getApplicationContext(), "Inefficient balance", Toast.LENGTH_LONG).show();
                                    //                                    }

                                } catch (Exception e) {
                                    Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                                Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                ) {

                    //                value Passing android to python
                    @Override
                    protected Map<String, String> getParams() {
                        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        Map<String, String> params = new HashMap<String, String>();
                        String id=sh.getString("lid","");
                        params.put("log",id);

//                        params.put("amount", sh.getString("amount", ""));//passing to python
                        params.put("bank", bname);//passing to python
                        params.put("ifs", ifs);//passing to python
                        params.put("acn", ac);//passing to python
                        params.put("reqid", sh.getString("reqid",""));//passing to python request_id
                        params.put("cid", sh.getString("cid",""));//passing to python contractor_id
                        params.put("amount", sh.getString("amount_credit", ""));
                        params.put("credits", sh.getString("credits", ""));

                        return params;
                    }
                };


                int MY_SOCKET_TIMEOUT_MS = 100000;

                postRequest.setRetryPolicy(new DefaultRetryPolicy(
                        MY_SOCKET_TIMEOUT_MS,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                requestQueue.add(postRequest);



            }
        });
    }
}