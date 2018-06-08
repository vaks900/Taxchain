package com.ey.taxchain;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MyCustomBaseAdapter extends BaseAdapter {
    private static ArrayList<SearchResults> searchArrayList;
 
    private LayoutInflater mInflater;
 
    public MyCustomBaseAdapter(Context context, ArrayList<SearchResults> results) {
    	
        searchArrayList = results;
        mInflater = LayoutInflater.from(context);
        
    }
 
    public int getCount() {
        return searchArrayList.size();
    }
 
    public Object getItem(int position) {
        return searchArrayList.get(position);
    }
 
    public long getItemId(int position) {
        return position;
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.custom_row_view, null);
            holder = new ViewHolder();
            holder.txtName = (TextView) convertView.findViewById(R.id.resultcustname);
            holder.txtNumber = (TextView) convertView
                    .findViewById(R.id.resultcustno);
            holder.txtPhone = (TextView) convertView.findViewById(R.id.resultcustmobile);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
 
        holder.txtName.setText(searchArrayList.get(position).getCustName());
        holder.txtNumber.setText(searchArrayList.get(position)
                .getCustNo());
        holder.txtPhone.setText(searchArrayList.get(position).getCustMob());
        LinearLayout outer=(LinearLayout) convertView.findViewById(R.id.resultlayout);
        if(searchArrayList.get(position).getBgColor()!= Color.TRANSPARENT){
            outer.setBackgroundColor(searchArrayList.get(position).getBgColor());//(Color.rgb(188,198,204)); //188,198,204    212,175,55
        }
        else
            outer.setBackgroundColor(Color.TRANSPARENT);
        return convertView;
    }
 
    static class ViewHolder {
        TextView txtName;
        TextView txtNumber;
        TextView txtPhone;
    }
}
