package com.example.driveinn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class custom_view_request_history extends BaseAdapter {
    String[]hid,requests,amount,date,payment_mode;
    private Context context;


    public custom_view_request_history(Context applicationContext, String[] hid, String[] requests, String[] amount, String[] date, String[] payment_mode) {
        this.context  =applicationContext;
        this.hid = hid;
        this.requests = requests;
        this.amount = amount;
        this.date = date;
        this.payment_mode = payment_mode;

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
            gridView=inflator.inflate(R.layout.activity_custom_view_request_history,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView35);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView49);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView51);
        TextView tv4=(TextView)gridView.findViewById(R.id.textView53);


        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
        tv4.setTextColor(Color.BLACK);


        tv1.setText(requests[i]);
        tv2.setText(amount[i]);
        tv3.setText(date[i]);
        tv4.setText(payment_mode[i]);




        return gridView;
    }
}