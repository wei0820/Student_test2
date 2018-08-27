package com.abc.test2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends Activity {
    private TextView mTextViewResult;
    private RequestQueue mQueue;

    public static int _id;
    public static int Zipcode;
    public static String Changetime;
    public static String Ticketinfo;
    public static String Name;
    public static String Toldescribe;
    public static String Add;
    public static int x;
    private ListView mListView;
    ArrayList<Data.Result.Records> dataArrayList = new ArrayList<>();
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout button320 = (RelativeLayout) findViewById(R.id.button_320);
        RelativeLayout button324 = (RelativeLayout) findViewById(R.id.button_324);
        RelativeLayout button325 = (RelativeLayout) findViewById(R.id.button_325);
        RelativeLayout button326 = (RelativeLayout) findViewById(R.id.button_326);
        RelativeLayout button327 = (RelativeLayout) findViewById(R.id.button_327);
        RelativeLayout button328 = (RelativeLayout) findViewById(R.id.button_328);
        RelativeLayout button330 = (RelativeLayout) findViewById(R.id.button_330);
        RelativeLayout button333 = (RelativeLayout) findViewById(R.id.button_333);
        RelativeLayout button334 = (RelativeLayout) findViewById(R.id.button_334);
        RelativeLayout button335 = (RelativeLayout) findViewById(R.id.button_335);
        RelativeLayout button336 = (RelativeLayout) findViewById(R.id.button_336);
        RelativeLayout button337 = (RelativeLayout) findViewById(R.id.button_337);
        RelativeLayout button338 = (RelativeLayout) findViewById(R.id.button_338);

        button320.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIntent("320");

            }
        });
        button324.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIntent("324");

            }
        });
        button325.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIntent("325");
            }
        });
        button326.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIntent("326");
            }
        });
        button327.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIntent("327");
            }
        });
        button328.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIntent("328");
            }
        });
        button330.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIntent("330");
            }
        });
        button333.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIntent("333");
            }
        });
        button334.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setIntent("334");
            }
        });
        button335.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setIntent("335");
            }
        });
        button336.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIntent("336");
            }
        });
        button337.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIntent("337");
            }
        });
        button338.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIntent("338");

            }
        });


    }
    private void setIntent(String s){
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("data",s);
        intent.putExtras(bundle);
        intent.setClass(this,Main2Activity.class);
        startActivity(intent);
    }

    private static final String TAG = "MainActivity";
    private ArrayList<Data.Result.Records> jsonParse(final String code) {

         progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("資料讀取中");
        progressDialog.setCancelable(false);
        progressDialog.show();

        RequestQueue mQueue = Volley.newRequestQueue(this);

        String url = "https://data.tycg.gov.tw/api/v1/rest/datastore/bd906b29-9006-40ed-8bd7-67597c2577fc?limit=300&format=json";
        StringRequest getRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                            Gson gson = new Gson();
                            Data data = gson.fromJson(s,Data.class);
                            if (data!=null&&data.result!=null&&data.result.records!=null){
                                for (Data.Result.Records record : data.result.records) {
                                    if (record.Zipcode.equals(code)){
                                        Log.d(TAG, "onResponse: "+record.Name);
                                        dataArrayList.add(record);
                                    }
                                }
                                progressDialog.dismiss();
                            }


                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });
        mQueue.add(getRequest);
        return dataArrayList;
    }





}

