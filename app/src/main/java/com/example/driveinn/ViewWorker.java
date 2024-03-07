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
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ViewWorker extends AppCompatActivity {
    ListView l1 ;
    String[] id,img,name,email,contact,landmark,place,adno,latitude,longitude ;
    String url ;
    SharedPreferences sh ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_worker);
        l1=findViewById(R.id.li);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url = sh.getString("url","")+"/android_view_worker";

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
                                img = new String[js.length()];
                                email = new String[js.length()];
                                contact = new String[js.length()];
                                landmark = new String[js.length()];
                                place = new String[js.length()];
                                adno = new String[js.length()];
                                latitude = new String[js.length()];
                                longitude = new String[js.length()];


                                for (int i = 0; i < js.length(); i++) {
                                    JSONObject u = js.getJSONObject(i);

                                    id[i] = u.getString("id");
                                    name[i] = u.getString("name");
                                    img[i] = u.getString("img");
                                    email[i] = u.getString("email");
                                    contact[i] = u.getString("contact");
                                    landmark[i] = u.getString("landmark");
                                    place[i] = u.getString("place");
                                    adno[i] = u.getString("adno");
                                    latitude[i] = u.getString("latitude");
                                    longitude[i] = u.getString("longitude");


                                }
                                l1.setAdapter(new CustomViewWorker(getApplicationContext(), id,img,name,email,contact,landmark,place,adno,latitude,longitude));
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