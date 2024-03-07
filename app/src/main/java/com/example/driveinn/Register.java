package com.example.driveinn;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    EditText e1 , e2 , e3 , e4 ,e5 ;
    Button b1;
    ImageView im1;
    SharedPreferences sh;
    Bitmap bitmap = null;
    String url;
    ProgressDialog pd;
    String MobilePattern = "[6-9][0-9]{9}";
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    //    String password_pattern="[A-Z][a-z][0-9]{3,8}";
    String password_pattern="[A-Za-z0-9]{3,8}";



    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        sh.getString("ip", "");
        url = sh.getString("url", "") + "/android_register";

        e1 = findViewById(R.id.editTextTextPersonName3);
        e2 = findViewById(R.id.editTextTextPersonName4);
        e3 = findViewById(R.id.editTextTextPersonName5);
        e4 = findViewById(R.id.editTextTextPassword2);
        e5 = findViewById(R.id.editTextTextPassword3);
        im1 = findViewById(R.id.imageView3);

        b1 = findViewById(R.id.button4);
        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 100);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=e1.getText().toString();
                String email=e2.getText().toString();
                String contact=e3.getText().toString();
                String passwd=e4.getText().toString();
                String cpasswd=e5.getText().toString();
                int flag = 0;
                if (name.equalsIgnoreCase("")) {
                    e1.setError("Null");
                    flag++;
                }

                if (!email.matches(emailPattern)) {
                    e2.setError("Null");
                    flag++;
                }
                if (!contact.matches(MobilePattern)) {
                    e3.setError("Null");
                    flag++;
                }
                if (!passwd.matches(password_pattern)) {
                    e4.setError("Null");
                    flag++;
                }
                if(!passwd.matches(cpasswd)){
                    e5.setError("match with password");
                    flag++;
                }
                if (bitmap == null) {
                    Toast.makeText(Register.this, "choose image", Toast.LENGTH_SHORT).show();
                }
                if(flag==0) {

                    uploadBitmap(name, email, contact, passwd, cpasswd);
                }
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {

            Uri imageUri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);

                im1.setImageBitmap(bitmap);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //converting to bitarray
    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
    private void uploadBitmap(final String name, final String email, final String contact, final String passwd, final String cpasswd) {


        pd = new ProgressDialog(Register.this);
        pd.setMessage("Uploading....");
        pd.show();
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, url,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            pd.dismiss();


                            JSONObject obj = new JSONObject(new String(response.data));

                            if (obj.getString("status").equals("ok")) {
                                Toast.makeText(getApplicationContext(), "Registration success", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(), Login.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(getApplicationContext(), "Registration failed", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                SharedPreferences o = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                params.put("name", name);//passing to python
                params.put("email", email);//passing to python
                params.put("contact",contact);
                params.put("password", passwd);
//                params.put("cpasswd", cpasswd);
                return params;
            }


            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                params.put("pic", new DataPart(imagename + ".png", getFileDataFromDrawable(bitmap)));
                return params;
            }
        };
        int MY_SOCKET_TIMEOUT_MS=100000;

        volleyMultipartRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(this).add(volleyMultipartRequest);

//        Volley.newRequestQueue(this).add(volleyMultipartRequest);
    }





    }
