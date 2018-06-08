package com.ey.taxchain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ReportsBaseAdapter extends BaseAdapter {
    private static ArrayList<ReportResults> reportArrayList;

    private LayoutInflater mInflater;
    private int colCount;

    public ReportsBaseAdapter(Context context, ArrayList<ReportResults> results, int count) {
    	
        reportArrayList = results;
        mInflater = LayoutInflater.from(context);
        colCount=count;
    }
 
    public int getCount() {
        return reportArrayList.size();
    }
 
    public Object getItem(int position) {
        return reportArrayList.get(position);
    }
 
    public long getItemId(int position) {
        return position;
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.activity_reports_list_adapter, null);
            holder = new ViewHolder();
            holder.col1 = (TextView) convertView.findViewById(R.id.rep_List_col1);
            holder.col2 = (TextView) convertView
                    .findViewById(R.id.rep_List_col2);
            holder.col3 = (TextView) convertView.findViewById(R.id.rep_List_col3);
            holder.col4 = (TextView) convertView.findViewById(R.id.rep_List_col4);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
 
        holder.col1.setText(reportArrayList.get(position).getCol1());
        holder.col2.setText(reportArrayList.get(position)
                .getCol2());
        holder.col3.setText(reportArrayList.get(position).getCol3());
        holder.col4.setText(reportArrayList.get(position).getCol4());
        if(colCount==2){
            holder.col3.setVisibility(View.GONE);
            holder.col4.setVisibility(View.GONE);
        }else if(colCount==3){
            holder.col4.setVisibility(View.GONE);
        }
        return convertView;
    }
 
    static class ViewHolder {
        TextView col1;
        TextView col2;
        TextView col3;
        TextView col4;
    }
}
