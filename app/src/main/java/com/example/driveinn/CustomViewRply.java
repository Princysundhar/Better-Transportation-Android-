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

public class CustomViewRply extends BaseAdapter {
    String[]cid,complaint,date,reply,reply_date,ctype;
    private Context context;


    public CustomViewRply(Context applicationContext, String[] cid, String[] complaint, String[] date, String[] reply, String[] reply_date, String[] ctype) {
        this.context =applicationContext;
        this.cid = cid;
        this.complaint = complaint;
        this.date = date;
        this.reply = reply;
        this.reply_date = reply_date;
        this.ctype = ctype;

    }

    @Override
    public int getCount() {
        return complaint.length;
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
            gridView=inflator.inflate(R.layout.activity_custom_view_rply,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView40);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView42);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView44);
        TextView tv4=(TextView)gridView.findViewById(R.id.textView46);
        TextView tv5=(TextView)gridView.findViewById(R.id.textView32);


        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
        tv4.setTextColor(Color.BLACK);
        tv5.setTextColor(Color.BLACK);


        tv1.setText(complaint[i]);
        tv2.setText(date[i]);
        tv3.setText(reply[i]);
        tv4.setText(reply_date[i]);
        tv5.setText(ctype[i]);



        return gridView;
    }
}