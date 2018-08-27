package com.abc.test2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

public class Main3Activity extends Activity  implements  View.OnClickListener{
    private TextView textView,textView2,textView3,textView4,
    textView5,textView6,textView7,textView8;
    Data.Result.Records records;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
//        textView = (TextView) findViewById(R.id.text);
        textView2 = (TextView) findViewById(R.id.text2);
        textView3 = (TextView) findViewById(R.id.text3);
        textView4 = (TextView) findViewById(R.id.text4);
        textView5= (TextView) findViewById(R.id.text5);
        textView6 = (TextView) findViewById(R.id.text6);
        textView7 = (TextView) findViewById(R.id.text7);
        textView8 = (TextView) findViewById(R.id.text8);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
        textView4.setOnClickListener(this);
        textView5.setOnClickListener(this);
        textView6.setOnClickListener(this);
        textView7.setOnClickListener(this);
        textView8.setOnClickListener(this);
        String json = getIntent().getStringExtra("json");
        records = new Gson().fromJson(json,   Data.Result.Records.class);
        if(records!=null){
            textView2.setText("電話");
            textView3.setText("地址");
            textView4.setText("開放時間");
            textView5.setText("門票");
            textView6.setText("停車資訊");
            textView7.setText("備註");
            textView8.setText("簡述");
        }



    }
    private void setBundle(String s){
        Intent i = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("key",s);
        i.putExtras(bundle);
        i.setClass(Main3Activity.this,Main4Activity.class);
        startActivity(i);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text2:
                setBundle(records.Tel);
                break;
            case R.id.text3:
                setBundle(records.Add);
                break;

            case R.id.text4:
                setBundle(records.Opentime);
                break;
            case R.id.text5:
                setBundle(records.Ticketinfo);
                break;

            case R.id.text6:
                setBundle(records.Parkinginfo);
                break;

            case R.id.text7:
                setBundle(records.Remarks);
                break;
            case R.id.text8:
                setBundle(records.Toldescribe);
                break;



        }
    }
}
