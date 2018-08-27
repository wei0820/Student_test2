package com.abc.test2;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

public class Main3Activity extends Activity {
    private TextView textView,textView2,textView3,textView4,
    textView5,textView6,textView7,textView8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        textView = (TextView) findViewById(R.id.text);
        textView2 = (TextView) findViewById(R.id.text2);
        textView3 = (TextView) findViewById(R.id.text3);
        textView4 = (TextView) findViewById(R.id.text4);
        textView5= (TextView) findViewById(R.id.text5);
        textView6 = (TextView) findViewById(R.id.text6);
        textView7 = (TextView) findViewById(R.id.text7);
        textView8 = (TextView) findViewById(R.id.text8);

        String json = getIntent().getStringExtra("json");
        Data.Result.Records records = new Gson().fromJson(json,   Data.Result.Records.class);
        if(records!=null){
            textView.setText("名稱："+records.Name);
            textView2.setText("電話："+records.Tel);
            textView3.setText("地址："+records.Add);
            textView4.setText("開放時間："+records.Opentime);
            textView5.setText("門票："+records.Ticketinfo);
            textView6.setText("停車資訊："+records.Parkinginfo);
            textView7.setText("備註："+records.Remarks);
            textView8.setText("簡述："+records.Toldescribe);
        }



    }

}
