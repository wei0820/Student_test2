package com.abc.test2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;

public class Main2Activity extends Activity {
    private ListView mListView;
    private MyAdapter mAdapter;
    ArrayList<Data.Result.Records> mAllData = new ArrayList<>();
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mListView = (ListView) findViewById(R.id.listview);

        mAdapter = new MyAdapter(mAllData);

        mListView.setAdapter(mAdapter);
       String code = getIntent().getStringExtra("data");
        jsonParse(code);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent petint = new Intent(Main2Activity.this,Main3Activity.class);
                petint.putExtra("json", new Gson().toJson(mAdapter.mDatas.get(position)));
                startActivity(petint);

            }
        });
    }
    public class MyAdapter extends BaseAdapter {
        private ArrayList<Data.Result.Records> mDatas;

        public MyAdapter(ArrayList<Data.Result.Records> datas) {
            mDatas = datas;
        }
        public void updateData(ArrayList<Data.Result.Records> datas) {
            mDatas = datas;
            notifyDataSetChanged();
        }
        @Override
        public int getCount() {
            return mDatas.size();
        }

        @Override
        public Object getItem(int position) {
            return mDatas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null)
                convertView = LayoutInflater.from(Main2Activity.this).inflate(
                        R.layout.layout_item, null);
            Data.Result.Records data = mDatas.get(position);

            TextView textname = (TextView) convertView.findViewById(R.id.text);

            textname.setText(data.Name);
            return convertView;
        }

    }
    private void jsonParse(final String code) {

        progressDialog = new ProgressDialog(this);
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
                                    mAllData.add(record);
                                }
                            }
                            mAdapter.notifyDataSetChanged();
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
    }
}
