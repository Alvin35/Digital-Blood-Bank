package com.example.ih_hi.digitalbloodbank;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import java.util.Calendar;

import android.graphics.Color;
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

public class Registration extends Activity implements AdapterView.OnItemSelectedListener{

    DatabaseHelper helper = new DatabaseHelper(this);
    EditText dName,dMobile,dEmail,dLoca,dPass,dAge,dLastDate,dWeight,conPass;
    Spinner dBldgrp;
    Button reg;
    TextView selectDate,never;
    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;
    private EditText fixedDate;
    private String bloodgrp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getActionBar().setTitle("Registration Info.");

        reg = (Button)findViewById(R.id.signUpBTN);
        selectDate = (TextView) findViewById(R.id.selectDateBTN);
        dName = (EditText)findViewById(R.id.donorNameET);
        dMobile = (EditText)findViewById(R.id.donorMobileET);
        dEmail = (EditText)findViewById(R.id.donorEmailET);
        dLoca = (EditText)findViewById(R.id.donorLocationET);
        dPass = (EditText)findViewById(R.id.donorPassET);
        dBldgrp = (Spinner) findViewById(R.id.donorBloodSP);
        dAge = (EditText)findViewById(R.id.donorAgeET);
        dLastDate = (EditText)findViewById(R.id.lastDateET);
        dWeight = (EditText)findViewById(R.id.donorWeightET);
        conPass = (EditText)findViewById(R.id.confirmPassET);
        never = (TextView)findViewById(R.id.neverBTN);

        //for blood group
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.bloodGroup, android.R.layout.simple_spinner_item);
        dBldgrp.setAdapter(adapter);
        dBldgrp.setOnItemSelectedListener(this);
        //for calender
        fixedDate = (EditText)findViewById(R.id.lastDateET);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);
        dLastDate.setText("");

        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(999);
            }
        });

        never.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dLastDate.setText("Never");
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = dName.getText().toString();
                String mobile = dMobile.getText().toString();
                String email = dEmail.getText().toString();
                String location = dLoca.getText().toString();
                String password = dPass.getText().toString();
                String conPassword = conPass.getText().toString();
                //String bloodgrp = dBldgrp.getText().toString();
                String age = dAge.getText().toString();
                String lastdate = dLastDate.getText().toString();
                String weight = dWeight.getText().toString();

                if(!password.equals(conPassword))
                {
                    //popup message if password don't match
                    Toast msg = Toast.makeText(Registration.this,"Password Don't Match !",Toast.LENGTH_SHORT);
                    msg.show();
                }
                else if(name.isEmpty()||mobile.isEmpty()||email.isEmpty()||location.isEmpty()||password.isEmpty()||
                        conPassword.isEmpty()||bloodgrp.equals("Select Blood Group")||age.isEmpty()||lastdate.isEmpty()
                        ||weight.isEmpty())
                {
                    Toast msg = Toast.makeText(Registration.this,"Please, Fill All The Field !",Toast.LENGTH_SHORT);
                    msg.show();
                }
                else
                {
                    //Insert Donor Info in Database
                    Contact c = new Contact();
                    //Set values name,email,mobile etc.
                    c.setdName(name);
                    c.setdMobile(mobile);
                    c.setdEmail(email);
                    c.setdLoca(location);
                    c.setdBldgrp(bloodgrp);
                    c.setdAge(age);
                    c.setdLastDate(lastdate);
                    c.setdWeight(weight);
                    c.setdPass(password);
                    Toast msg = Toast.makeText(Registration.this,"Regestration Completed !",Toast.LENGTH_SHORT);
                    msg.show();
                    helper.insertContact(c);
                    finish();
                }
            }
        });
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
        fixedDate.setText(day+"-"+month+"-"+year);
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
            //Toast msg = Toast.makeText(Registration.this,"BLD "+text.getText(),Toast.LENGTH_SHORT);
            //msg.show();
            bloodgrp = text.getText().toString();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
