package com.example.byevid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.example.byevid.adapter.HospitalAdapter;
import com.example.byevid.model.Hospital;

import java.util.ArrayList;

public class HospitalActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private HospitalAdapter adapter;
    private ArrayList<Hospital> hospitalList;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);

        // Initialize data
        addData();

        // Configure recycler view
        recyclerView = (RecyclerView) findViewById(R.id.rv_hospital_list);

        adapter = new HospitalAdapter(this, hospitalList);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Divider
//        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));

        DividerItemDecoration itemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(new ColorDrawable(R.color.gray));
        recyclerView.addItemDecoration(itemDecoration);
    }

    private void addData() {
        hospitalList = new ArrayList<>();

        hospitalList.add(new Hospital("Hermina Galaxy", "0218205377", 109209389, 1203904059, "Jalan Perkutut", "https://d1ojs48v3n42tp.cloudfront.net/provider_location_banner/752970_3-4-2020_12-48-40.jpeg"));
        hospitalList.add(new Hospital("Hermina Galaxy", "0218205377", 109209389, 1203904059, "Jalan Perkutut", "https://d1ojs48v3n42tp.cloudfront.net/provider_location_banner/752970_3-4-2020_12-48-40.jpeg"));
        hospitalList.add(new Hospital("Hermina Galaxy", "0218205377", 109209389, 1203904059, "Jalan Perkutut", "https://d1ojs48v3n42tp.cloudfront.net/provider_location_banner/752970_3-4-2020_12-48-40.jpeg"));
        hospitalList.add(new Hospital("Hermina Galaxy", "0218205377", 109209389, 1203904059, "Jalan Perkutut", "https://d1ojs48v3n42tp.cloudfront.net/provider_location_banner/752970_3-4-2020_12-48-40.jpeg"));
        hospitalList.add(new Hospital("Hermina Galaxy", "0218205377", 109209389, 1203904059, "Jalan Perkutut", "https://d1ojs48v3n42tp.cloudfront.net/provider_location_banner/752970_3-4-2020_12-48-40.jpeg"));
        hospitalList.add(new Hospital("Hermina Galaxy", "0218205377", 109209389, 1203904059, "Jalan Perkutut", "https://d1ojs48v3n42tp.cloudfront.net/provider_location_banner/752970_3-4-2020_12-48-40.jpeg"));
        hospitalList.add(new Hospital("Hermina Galaxy", "0218205377", 109209389, 1203904059, "Jalan Perkutut", "https://d1ojs48v3n42tp.cloudfront.net/provider_location_banner/752970_3-4-2020_12-48-40.jpeg"));
        hospitalList.add(new Hospital("Hermina Galaxy", "0218205377", 109209389, 1203904059, "Jalan Perkutut", "https://d1ojs48v3n42tp.cloudfront.net/provider_location_banner/752970_3-4-2020_12-48-40.jpeg"));
        hospitalList.add(new Hospital("Hermina Galaxy", "0218205377", 109209389, 1203904059, "Jalan Perkutut", "https://d1ojs48v3n42tp.cloudfront.net/provider_location_banner/752970_3-4-2020_12-48-40.jpeg"));
        hospitalList.add(new Hospital("Hermina Galaxy", "0218205377", 109209389, 1203904059, "Jalan Perkutut", "https://d1ojs48v3n42tp.cloudfront.net/provider_location_banner/752970_3-4-2020_12-48-40.jpeg"));
        hospitalList.add(new Hospital("Hermina Galaxy", "0218205377", 109209389, 1203904059, "Jalan Perkutut", "https://d1ojs48v3n42tp.cloudfront.net/provider_location_banner/752970_3-4-2020_12-48-40.jpeg"));
        hospitalList.add(new Hospital("Hermina Galaxy", "0218205377", 109209389, 1203904059, "Jalan Perkutut", "https://d1ojs48v3n42tp.cloudfront.net/provider_location_banner/752970_3-4-2020_12-48-40.jpeg"));

    }
}