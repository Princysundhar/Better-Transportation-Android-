package com.example.driveinn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText e1;
Button b1;
SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        e1=findViewById(R.id.editTextTextPersonName);
        e1.setText(sh.getString("ip",""));
        b1=findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ipaddress =e1.getText().toString();
                int flag=0;
                if(ipaddress.equalsIgnoreCase("")){
                    Toast.makeText(MainActivity.this, "Enter Valid Ip", Toast.LENGTH_SHORT).show();
                    flag++;
                }
                if(flag==0) {
                    String url1 = "http://" + ipaddress + ":5000";
                    SharedPreferences.Editor ed = sh.edit();
                    ed.putString("ip", ipaddress);
                    ed.putString("url", url1);
                    ed.commit();
                    Intent i = new Intent(getApplicationContext(), Login.class);
                    startActivity(i);
                }


            }
        });
    }
}