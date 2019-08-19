package com.example.ih_hi.digitalbloodbank;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class EditProfile extends Activity implements AdapterView.OnItemSelectedListener{

    DatabaseHelper helper = new DatabaseHelper(this);
    EditText curName,curMobNo,curEmail,curLocation,curAge,curWeight,curLastDate;
    Button saveUpdateInfo,selectDate;
    Spinner curBldGrp;
    private int userID;
    private String updateBloodGrp;
    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;
    private EditText fixedDate;
    private ArrayList<String> currentInfo = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        getActionBar().setTitle("Edit Profile.");

        //Receive Data
        currentInfo = (ArrayList<String>)getIntent().getSerializableExtra("CURRENT_INFO");
        userID = getIntent().getIntExtra("USER_ID",0);

        curName = (EditText)findViewById(R.id.donorNameET);
        curMobNo = (EditText)findViewById(R.id.donorMobileET);
        curEmail = (EditText)findViewById(R.id.donorEmailET);
        curLocation = (EditText)findViewById(R.id.donorLocationET);
        curBldGrp = (Spinner)findViewById(R.id.donorBloodSP);
        curAge = (EditText)findViewById(R.id.donorAgeET);
        curWeight = (EditText)findViewById(R.id.donorWeightET);
        curLastDate = (EditText)findViewById(R.id.lastDateET);
        saveUpdateInfo = (Button)findViewById(R.id.saveBTN);
        selectDate = (Button)findViewById(R.id.selectDateBTN);

        setCurrentInfo();

        //For Blood Group
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.bloodGroup, android.R.layout.simple_spinner_item);
        curBldGrp.setAdapter(adapter);
        curBldGrp.setOnItemSelectedListener(this);

        //for calender
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(999);
            }
        });

        saveUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String updateName = curName.getText().toString();
                String updateMobNo = curMobNo.getText().toString();
                String updateEmail = curEmail.getText().toString();
                String updateLocation = curLocation.getText().toString();
                String updateAge = curAge.getText().toString();
                String updateLastDate = curLastDate.getText().toString();
                String updateWeight = curWeight.getText().toString();

                if(updateName.isEmpty()||updateMobNo.isEmpty()||updateEmail.isEmpty()||updateLocation.isEmpty()||
                        updateBloodGrp.equals("Select Blood Group")||updateAge.isEmpty()||updateLastDate.isEmpty()
                        ||updateWeight.isEmpty())
                {
                    Toast msg = Toast.makeText(EditProfile.this,"Please, Fill All The Field !",Toast.LENGTH_SHORT);
                    msg.show();
                }
                else
                {
                    //Insert Donor Info in Database
                    Contact c = new Contact();
                    //Set values name,email,mobile etc.
                    c.setdName(updateName);
                    c.setdMobile(updateMobNo);
                    c.setdEmail(updateEmail);
                    c.setdLoca(updateLocation);
                    c.setdBldgrp(updateBloodGrp);
                    c.setdAge(updateAge);
                    c.setdLastDate(updateLastDate);
                    c.setdWeight(updateWeight);
                    Toast msg = Toast.makeText(EditProfile.this,"Update Completed !",Toast.LENGTH_SHORT);
                    msg.show();
                    helper.updateContact(c,userID);
                    Intent i = new Intent(EditProfile.this,DonorProfile.class);
                    Bundle data = new Bundle();
                    data.putInt("USER_ID",userID);
                    i.putExtra("USER_DATA",data);
                    finish();
                    startActivity(i);
                }
            }
        });

    }
    public void setCurrentInfo()
    {
        curName.setText(currentInfo.get(0));
        curMobNo.setText(currentInfo.get(1));
        curEmail.setText(currentInfo.get(2));
        curLocation.setText(currentInfo.get(3));
        //curBldGrp.setText(currentInfo.get(4));
        curAge.setText(currentInfo.get(5));
        curWeight.setText(currentInfo.get(6));
        curLastDate.setText(currentInfo.get(7));
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        if(id==999){
            return new DatePickerDialog(this,myDateListener,year,month,day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3)
                {
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        curLastDate.setText(day+"-"+month+"-"+year);
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        TextView text = (TextView) view;
        if(i==0)
        {
            text.setText(currentInfo.get(4));
            updateBloodGrp = currentInfo.get(4);
        }
        else
        {
            //Toast msg = Toast.makeText(Registration.this,"BLD "+text.getText(),Toast.LENGTH_SHORT);
            //msg.show();
            updateBloodGrp = text.getText().toString();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
