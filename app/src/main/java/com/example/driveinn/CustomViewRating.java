package com.example.driveinn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class CustomViewRating extends BaseAdapter {
    String[]rid,rating,review,date,worker_info,userinfo;
    private Context context;

    public CustomViewRating(Context applicationContext, String[] rid, String[] rating, String[] review, String[] date, String[] worker_info, String[] userinfo) {
        this.context = applicationContext;
        this.rid = rid;
        this.rating = rating;
        this.review = review;
        this.date = date;
        this.worker_info = worker_info;
        this.userinfo = userinfo;

    }


    @Override
    public int getCount() {
        return review.length;
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
            gridView=inflator.inflate(R.layout.activity_custom_view_rating,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView29);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView31);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView45);
        TextView tv4=(TextView)gridView.findViewById(R.id.textView47);
        RatingBar r1 = (RatingBar)gridView.findViewById(R.id.ratingBar2);

        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
        tv4.setTextColor(Color.BLACK);


        tv1.setText(review[i]);
        tv2.setText(date[i]);
        tv3.setText(worker_info[i]);
        tv4.setText(userinfo[i]);
        r1.setRating(Float.parseFloat(rating[i]));
        r1.setEnabled(false);




        return gridView;
    }
}