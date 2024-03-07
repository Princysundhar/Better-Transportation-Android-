package com.example.driveinn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ViewRequestStatus extends AppCompatActivity {

    ListView l1 ;
    String[] id,name,date,query,status,latitude,longitude,amount ;
    String url ;
    SharedPreferences sh ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_request_status);
        l1=findViewById(R.id.reqsts);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url = sh.getString("url","")+"/android_view_request_status";

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        // response
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {

                                JSONArray js = jsonObj.getJSONArray("data");
                                id = new String[js.length()];
                                name = new String[js.length()];
                                date = new String[js.length()];
                                query = new String[js.length()];
                                status = new String[js.length()];
                                latitude = new String[js.length()];
                                longitude = new String[js.length()];
                                amount = new String[js.length()];



                                for (int i = 0; i < js.length(); i++) {
                                    JSONObject u = js.getJSONObject(i);

                                    id[i] = u.getString("id");
                                    name[i] = u.getString("name");
                                    date[i] = u.getString("date");
                                    query[i] = u.getString("query");
                                    status[i] = u.getString("status");
                                    latitude[i] = u.getString("latitude");
                                    longitude[i] = u.getString("longitude");
                                    amount[i] = u.getString("amount");



                                }
                                l1.setAdapter(new CustomViewReqStatus(getApplicationContext(), id,name,date,query,status,latitude,longitude,amount ));
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                            }

                        }    catch (Exception e) {
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
            @Override
            protected Map<String, String> getParams() {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                Map<String, String> params = new HashMap<String, String>();

                params.put("lid",sh.getString("lid",""));
                params.put("latitude",gpstracker.lati);
                params.put("longitude",gpstracker.longi);

                return params;
            }
        };

        int MY_SOCKET_TIMEOUT_MS=100000;

        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);
    }
}