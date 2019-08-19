package com.example.ih_hi.digitalbloodbank;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class SmsOptions extends Activity implements AdapterView.OnItemSelectedListener {

    private EditText cName,cMobNo,cLoc,cHospital;
    private Spinner cBldGrp;
    private Button sendSms;
    private String bloodgrp,donorNum;
    private String msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_options);
        getActionBar().setTitle("Request For Blood");

        cName = (EditText)findViewById(R.id.contactNameET);
        cMobNo = (EditText)findViewById(R.id.contactNumberET);
        cLoc = (EditText)findViewById(R.id.paitentLocationET);
        cHospital = (EditText)findViewById(R.id.hospitalNameET);
        cBldGrp = (Spinner)findViewById(R.id.patientBloodGrpSP);
        sendSms = (Button)findViewById(R.id.sendBTN);

        //Recieve Data
        Intent i = getIntent();
        Bundle b = i.getBundleExtra("DATA");
        donorNum = b.getString("DONOR_NUM");

        //For Blood Group Spinner
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.bloodGroup, android.R.layout.simple_spinner_item);
        cBldGrp.setAdapter(adapter);
        cBldGrp.setOnItemSelectedListener(this);

        sendSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                msg ="I badly need 1 bag blood. Please help me if you can ." +
                        " Here is my contact address:"+
                        "\nContact Name: "+cName.getText().toString()+
                        "\nContact Number: "+cMobNo.getText().toString()+
                        "\nBlood Group: "+bloodgrp+
                        "\nHospital Name: "+cHospital.getText().toString()+
                        "\nLocation: "+cLoc.getText().toString();

                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                smsIntent.setType("vnd.android-dir/mms-sms");
                smsIntent.putExtra("address", donorNum);
                smsIntent.putExtra("sms_body",msg);
                finish();
                startActivity(smsIntent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        TextView text = (TextView) view;
        if(i==0)
        {
            text.setTextColor(Color.GRAY);
        }
        else
        {
            bloodgrp = text.getText().toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
