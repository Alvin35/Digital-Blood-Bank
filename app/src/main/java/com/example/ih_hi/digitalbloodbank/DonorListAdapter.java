package com.example.ih_hi.digitalbloodbank;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class DonorListAdapter extends BaseAdapter {

    private Context mContext;
    private List<DonorDetails> donorList;


    public DonorListAdapter(Context mContext, List<DonorDetails> donorList) {
        this.mContext = mContext;
        this.donorList = donorList;
    }

    @Override
    public int getCount() {
        return donorList.size();
    }

    @Override
    public Object getItem(int position) {
        return donorList.get(position);
    }
    public long getItemId(int position) {
        return donorList.get(position).get_id();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(mContext, R.layout.donor_listview, null);
        TextView donorName = (TextView)v.findViewById(R.id.donorNameTV);
        TextView age = (TextView)v.findViewById(R.id.ageTV);
        TextView grp = (TextView)v.findViewById(R.id.bloodTV);
        TextView weight = (TextView)v.findViewById(R.id.weightTV);
        TextView lastDate = (TextView)v.findViewById(R.id.donateTV);
        TextView loca = (TextView)v.findViewById(R.id.locationTV);

        donorName.setText(donorList.get(position).getdName());
        age.setText(donorList.get(position).getdAge());
        grp.setText(donorList.get(position).getdBldgrp());
        weight.setText(donorList.get(position).getdWeight());
        lastDate.setText(donorList.get(position).getdLastDate());
        loca.setText(donorList.get(position).getdLoca());

        return v;
    }
}
