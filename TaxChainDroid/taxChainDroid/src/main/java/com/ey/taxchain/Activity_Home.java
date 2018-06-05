package com.ey.taxchain;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
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

public class Activity_Home extends Activity {
    EditText cust_Name,cust_Id,age,referenceNo,amt;
    Spinner investmentType,category;
    TransactionUtil transactionUtil=new TransactionUtil();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taxchain_home);


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

    public void onAdvisor(View v){

        Intent intent = new Intent(Activity_Home.this, Activity_Advisor.class);
        startActivity(intent);

    }
    public void onDroid(View v){

        Intent intent = new Intent(Activity_Home.this, Activity_SubmitTransaction.class);
        startActivity(intent);
    }

}
