package com.ey.taxchain;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class CustomerListViewActivity extends Activity {
	String custName;
	String custNo;
	String custMob;
	String custId;
    String area;
	final Context context = this;
    ArrayList<ReportResults> searchResults1=new ArrayList<ReportResults>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custmgmt_searchresult);

                viewDetails();
        final ListView lv = (ListView) findViewById(R.id.srListView);
        lv.setAdapter(new ReportsBaseAdapter(this, viewDetails(),4));
         
         

    }


    public ArrayList<ReportResults> viewDetails(){
        final ArrayList<ReportResults> searchResults=new ArrayList<ReportResults>();
        AsyncHttpClient client = new AsyncHttpClient();
        String url="http://10.168.3.251:10009/api/taxchain/pacts";
        client.get(url, new AsyncHttpResponseHandler() {
            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onSuccess(String response) {
                System.out.println("response for get Data:"+response);

                try {
                    JSONArray obj = new JSONArray(TransactionUtil.convertStandardJSONString(response));
                    System.out.println("Array -->"+obj.toString());

                    for(int i = 0; i < obj.length(); i++) {
                        JSONObject objs = obj.getJSONObject(i);
                        ReportResults result=new ReportResults();
                        JSONObject state = objs.getJSONObject("state");
                        JSONObject order=state.getJSONObject("data").getJSONObject("order");
                        System.out.println("Order -->"+order.toString());
                        result.setCol1(order.getString("custId"));
                        result.setCol2(order.getString("customerName"));
                        result.setCol3(order.getString("investmentType"));
                        result.setCol4(order.getString("referenceNumber"));
                        searchResults.add(result);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }




            }

            @Override
            public void onFailure(int statusCode, Throwable error,
                                  String content) {
                System.out.println(" Exception in View Data: Status code is"+statusCode);
                System.out.println(" Exception content in View Data. Content is :"+content);
                System.out.println(" Exception content in view Data:"+error.getMessage());
                error.printStackTrace();

            }
        });
        return searchResults;
    }

}