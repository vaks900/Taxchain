package com.ey.taxchain;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;


public class Activity_Advisor extends Activity {
    EditText cust_Name,age;
    TextView result;
    Spinner category;
    final int DEFAULT_TIMEOUT = 30 * 1000;
    TransactionUtil transactionUtil=new TransactionUtil();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taxchain_advisorhome);
        cust_Name = ((EditText) findViewById(R.id.rt_cust_name));
        age = ((EditText) findViewById(R.id.rt_age));
        category = ((Spinner) findViewById(R.id.rt_category));
        result=((TextView) findViewById(R.id.rt_result));

    }

    public void onPay(View v){
        AsyncHttpClient client = new AsyncHttpClient();
        JSONObject jsonParams = new JSONObject();
        StringEntity entity=null;
        System.out.println("Started");
        try{
            String url="http://10.168.3.251:5000/invt/"+age.getText().toString()+"/"+category.getSelectedItem().toString().toLowerCase().replace(" ","");
            client.setTimeout(DEFAULT_TIMEOUT);
            client.get(url, new AsyncHttpResponseHandler() {
            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onSuccess(String response) {
                System.out.println("response for get Data:"+response);
                try {
                    JSONObject obj = new JSONObject(TransactionUtil.convertStandardJSONString(response));
                    System.out.println("Started"+obj.get("INVESTMENTTYPE").toString());
                    System.out.println("Started"+((JSONObject)obj.get("INVESTMENTTYPE")).getString("0"));
                    result.setText("Hi "+cust_Name.getText().toString()+", you can invest in "+ ((JSONObject)obj.get("INVESTMENTTYPE")).getString("0"));
                } catch (Exception e) {
                    e.printStackTrace();
                }




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
            Toast.makeText(this,  "Please Wait", Toast.LENGTH_LONG).show();

        }catch (Exception e){
            System.out.println("Started"+e.getStackTrace().toString());
            e.printStackTrace();
            Toast.makeText(this,  "Error Occured. Please retry or contact admin", Toast.LENGTH_LONG).show();
        }

    }


}
