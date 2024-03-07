package com.example.driveinn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class CustomViewReqStatus extends BaseAdapter {

    private Context context;
    String[] id,name,date,query,status,latitude,longitude,amount ;


    public CustomViewReqStatus(Context applicationContext, String[] id, String[] name, String[] date, String[] query, String[] status, String[] latitude, String[] longitude,String[] amount) {

        this.context=applicationContext;
        this.id=id;
        this.name=name;
        this.date=date;
        this.query=query;
        this.status=status;
        this.latitude=latitude;
        this.longitude=longitude;
        this.amount=amount;




    }

    @Override
    public int getCount() {
        return amount.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(view==null)
        {
            gridView=new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView=inflator.inflate(R.layout.activity_custom_view_req_status,null);

        }
        else
        {
            gridView=(View)view;

        }

        TextView tv1=(TextView)gridView.findViewById(R.id.textView23);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView24);
//        TextView tv3=(TextView)gridView.findViewById(R.id.textView25);
        TextView tv4=(TextView)gridView.findViewById(R.id.textView26);
        TextView tv5=(TextView)gridView.findViewById(R.id.textView27);
        TextView tv6=(TextView)gridView.findViewById(R.id.textView25);
        Button b1 = (Button)gridView.findViewById(R.id.button13);

        b1.setTag(i);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int ik=(int)view.getTag();
                String url = "http://maps.google.com/?q=" + latitude[ik] + "," + longitude[ik];
                Intent i = new Intent(android.content.Intent.ACTION_VIEW,  Uri.parse(url));
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
        Button b2 = (Button)gridView.findViewById(R.id.button15);               // payment
        b2.setTag(i);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = (int) view.getTag();
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor ed = sh.edit();
                ed.putString("aid",id[i]);
                ed.putString("amount",amount[pos]);
                ed.commit();
                Intent i = new Intent(context,payment_mode.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);

            }
        });



        tv1.setTextColor(Color.BLACK);
        tv1.setText(name[i]);

        tv2.setTextColor(Color.BLACK);
        tv2.setText(date[i]);


//        tv3.setTextColor(Color.BLACK);
//        tv3.setText(time[i]);

        tv4.setTextColor(Color.BLACK);
        tv4.setText(query[i]);

        tv5.setTextColor(Color.BLACK);
        tv5.setText(status[i]);

        tv6.setTextColor(Color.BLACK);
        tv6.setText(amount[i]);








        return gridView;
    }
}
