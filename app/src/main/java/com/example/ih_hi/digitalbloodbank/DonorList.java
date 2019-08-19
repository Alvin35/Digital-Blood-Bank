package com.example.ih_hi.digitalbloodbank;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class DonorList extends Activity {


    private ListView donorList;
    private DonorListAdapter adapter;
    private List<DonorDetails> donorDetailList;
    private DatabaseHelper dbHelper;
    private Button search;
    private EditText searchLoc,searchBlood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_list);
        getActionBar().setTitle("Donors List");

        donorList = (ListView)findViewById(R.id.donorListLV);
        search = (Button)findViewById(R.id.searchBTN);
        searchLoc = (EditText)findViewById(R.id.locationSearchET);
        searchBlood = (EditText)findViewById(R.id.groupSearchET);
        dbHelper = new DatabaseHelper(this);


        donorDetailList = dbHelper.getDonorList();
        //Init adapter
        adapter = new DonorListAdapter(this,donorDetailList);
        //Set adapter for listview
        donorList.setAdapter(adapter);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bldGrp = searchBlood.getText().toString();
                String location = searchLoc.getText().toString();
                if(bldGrp.isEmpty() || location.isEmpty())
                    Toast.makeText(DonorList.this,"Fill all field!!!",Toast.LENGTH_SHORT).show();

                else
                {
                    donorDetailList = dbHelper.getSearchedDonorList(bldGrp,location);
                    adapter = new DonorListAdapter(DonorList.this, donorDetailList);
                    donorList.setAdapter(adapter);
                }
            }
        });

        registerForContextMenu(donorList);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.donorListLV) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_list, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();


        switch (item.getItemId()) {
            case R.id.call:
                String number = donorDetailList.get(info.position).getdMobile();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + number));

                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
                {
                    return true;
                }
                startActivity(callIntent);
                return true;

            case R.id.sms:
                String number1 = donorDetailList.get(info.position).getdMobile();
                /*Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                smsIntent.setType("vnd.android-dir/mms-sms");
                smsIntent.putExtra("address", number1);
                startActivity(smsIntent);*/
                Intent i = new Intent(DonorList.this,SmsOptions.class);
                Bundle data = new Bundle();
                data.putCharSequence("DONOR_NUM",number1);
                i.putExtra("DATA",data);
                startActivity(i);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

}

