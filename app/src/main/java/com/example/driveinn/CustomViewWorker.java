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

public class CustomViewWorker extends BaseAdapter {
    private Context context;
    String[] id,img,name,email,contact,landmark,place,adno,latitude,longitude ;


    public CustomViewWorker(Context applicationContext, String[] id, String[] img, String[] name, String[] email, String[] contact, String[] landmark, String[] place, String[] adno,String[] latitude,String[]longitude) {

        this.context=applicationContext;
        this.id=id;
        this.img=img;
        this.name=name;
        this.email=email;
        this.contact=contact;
        this.landmark=landmark;
        this.place=place;
        this.adno=adno;
        this.latitude=latitude;
        this.longitude=longitude;



    }

    @Override
    public int getCount() {
        return email.length;
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
            gridView=inflator.inflate(R.layout.activity_custom_view_worker,null);

        }
        else
        {
            gridView=(View)view;

        }
        ImageView im=(ImageView) gridView.findViewById(R.id.imageView2);
        TextView tv1=(TextView)gridView.findViewById(R.id.textView12);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView13);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView14);
        TextView tv4=(TextView)gridView.findViewById(R.id.textView15);
        TextView tv5=(TextView)gridView.findViewById(R.id.textView16);
        TextView tv6=(TextView)gridView.findViewById(R.id.textView17);
        Button b1 = (Button)gridView.findViewById(R.id.button7);
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
        Button b2 = (Button)gridView.findViewById(R.id.button11);
        b2.setTag(i);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                int pos = (int) view.getTag();
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor ed = sh.edit();
                ed.putString("rid",id[i]);
                ed.commit();
                Intent i = new Intent(context,ViewRatings.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);

            }
        });
        Button b3 = (Button)gridView.findViewById(R.id.button8);
        b3.setTag(i);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                int pos = (int) view.getTag();
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor ed = sh.edit();
                ed.putString("rid",id[i]);
                ed.commit();
                Intent i = new Intent(context,SendRequest.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);

            }
        });
        Button b4 = (Button)gridView.findViewById(R.id.button3);
        b4.setTag(i);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                int pos = (int) view.getTag();
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor ed = sh.edit();
                ed.putString("aid",id[i]);
                ed.commit();
                Intent i = new Intent(context,Chat.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);

            }
        });


        tv1.setTextColor(Color.BLACK);
        tv1.setText(name[i]);

        tv2.setTextColor(Color.BLACK);
        tv2.setText(email[i]);

        tv3.setTextColor(Color.BLACK);
        tv3.setText(contact[i]);

        tv4.setTextColor(Color.BLACK);
        tv4.setText(landmark[i]);

        tv5.setTextColor(Color.BLACK);
        tv5.setText(place[i]);

        tv6.setTextColor(Color.BLACK);
        tv6.setText(adno[i]);




        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
        String ip=sh.getString("ip","");

        String url="http://" + ip + ":5000"+img[i];


        Picasso.with(context).load(url).transform(new CircleTransform()). into(im);

        return gridView;
    }
}
