package com.ey.taxchain;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Activity_SubmitTransaction extends Activity {
    EditText cust_Name,cust_Id,age,referenceNo,amt;
    Spinner investmentType,category;
    TransactionUtil transactionUtil=new TransactionUtil();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taxchain_registertransaction);
        cust_Id = ((EditText) findViewById(R.id.rt_cust_id));
        cust_Name = ((EditText) findViewById(R.id.rt_cust_name));
        age = ((EditText) findViewById(R.id.rt_age));
        referenceNo = ((EditText) findViewById(R.id.rt_reference_no));
        amt = ((EditText) findViewById(R.id.rt_amount));
        investmentType = ((Spinner) findViewById(R.id.rt_invst_type));
        category = ((Spinner) findViewById(R.id.rt_category));

    }

    public void onPay(View v){
        AsyncHttpClient client = new AsyncHttpClient();
        JSONObject jsonParams = new JSONObject();
        StringEntity entity=null;
        System.out.println("Started");
        try{
            String url=transactionUtil.getConnectionUrl(investmentType.getSelectedItem().toString());
            jsonParams.put("customerName", cust_Name.getText().toString());
            jsonParams.put("custId", cust_Id.getText().toString());
            jsonParams.put("age", age.getText().toString());
            jsonParams.put("category", category.getSelectedItem().toString());
            jsonParams.put("investmentType", investmentType.getSelectedItem().toString());
            jsonParams.put("referenceNumber", referenceNo.getText().toString());
            jsonParams.put("totalAmount", amt.getText().toString());
            entity = new StringEntity(jsonParams.toString());
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            client.put(this,url, entity,"application/json", new AsyncHttpResponseHandler() {
            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onSuccess(String response) {
                System.out.println("response for get Data:"+response);
                HashMap<String, ArrayList> mapTable = new HashMap<String, ArrayList>();


            }

            @Override
            public void onFailure(int statusCode, Throwable error,
                                  String content) {
                System.out.println(" Exception in get Data: Status code is"+statusCode);
                System.out.println(" Exception content in get Data. Content is :"+content);
                System.out.println(" Exception content in get Data:"+error.getMessage());
                error.printStackTrace();

            }
        });
            Toast.makeText(this,  "Ledger Transaction completed", Toast.LENGTH_LONG).show();
            clear();
        }catch (Exception e){
            System.out.println("Started"+e.getStackTrace().toString());
            e.printStackTrace();
            Toast.makeText(this,  "Error Occured. Please retry or contact admin", Toast.LENGTH_LONG).show();
        }

    }

    public void clear(){
        cust_Id.invalidate();
        cust_Id.setText("");
        cust_Name.invalidate();
        cust_Name.setText("");
        age.invalidate();
        age.setText("");
        referenceNo.invalidate();
        referenceNo.setText("");
        amt.invalidate();
        amt.setText("");
        investmentType.invalidate();
        investmentType.setSelection(0);
        category.invalidate();
        category.setSelection(0);
    }

    public void onClear(View v){
        clear();
    }
}
