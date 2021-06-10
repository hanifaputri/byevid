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
        hospitalList = new ArrayList<>();
        addData();

        // Configure recycler view
        recyclerView = (RecyclerView) findViewById(R.id.rv_hospital_list);

        adapter = new HospitalAdapter(HospitalActivity.this, hospitalList);
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
        hospitalList.add(new Hospital("RS Hermina Galaxy", "0218205377", -6.2686781,106.9700773, "Ruko Grand Galaxy City Jalan Gardenia Raya Blok BA1 No.11, RT.003/RW.017, Jaka Setia, Kec. Bekasi Sel., Kota Bks, Jawa Barat 17147", "https://d1ojs48v3n42tp.cloudfront.net/provider_location_banner/752970_3-4-2020_12-48-40.jpeg"));
        hospitalList.add(new Hospital("RS Universitas Indonesia (RSUI)", "02150829292", -6.371845188303128 , 106.82994694136114, "Pondok Cina, Kecamatan Beji, Kota Depok, Jawa Barat 16424", "https://lh5.googleusercontent.com/p/AF1QipMY571DEmB8do2oJrBolITm40Bi2cFUAC-6yRtX=w408-h408-k-no"));
        hospitalList.add(new Hospital("RSUD Cempaka Putih", "0214224243",  -6.184602167686795 , 106.87242355412886, "Jl. Rawasari Sel. No.1, RT.16/RW.2, Cemp. Putih Tim., Kec. Cemp. Putih, Kota Jakarta Pusat, Daerah Khusus Ibukota Jakarta 10510", "https://lh5.googleusercontent.com/p/AF1QipPd530QOC2PCWUDvzEOgRrPyhkrFS30tUzQxGcP=w426-h240-k-no"));
        hospitalList.add(new Hospital("RSUD Sawah Besar", "02162320819", -6.148758854624023 , 106.82981285228097, "Jl. Dwiwarna Raya No.6-8, RT.15/RW.9, Karang Anyar, Kecamatan Sawah Besar, Kota Jakarta Pusat, Daerah Khusus Ibukota Jakarta 10740", "https://lh5.googleusercontent.com/p/AF1QipM7mfPLXET3TvOfSuaFmjWlC6Ec-0r4SFxvzDip=w408-h544-k-no"));
        hospitalList.add(new Hospital("RSUD Cengkareng", "02154372874",  -6.142760981971637 , 106.7348406117991, "Jl. Bumi Cengkareng Indah No.1, RT.13/RW.10, Cengkareng Tim., Kecamatan Cengkareng, Kota Jakarta Barat, Daerah Khusus Ibukota Jakarta 11730", "https://lh5.googleusercontent.com/p/AF1QipPTya3L-EYpaGvJ9AAQBlX2PN8y5tRHSukM_TMX=w408-h862-k-no"));
        hospitalList.add(new Hospital("RSUD Pasar Minggu", "02129059999", -6.29394991211612 , 106.819935123447, "Jl. TB Simatupang No.1, RT.1/RW.5, Ragunan, Kec. Ps. Minggu, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12550", "https://lh5.googleusercontent.com/p/AF1QipOytNqnIn4abYaiTZFr9AiYaihvbo0R69xnjSTq=w408-h306-k-no"));
        hospitalList.add(new Hospital("RSUD Tanah Abang", "0213150427", -6.190709937616554, 106.8144734117996, "Jl. KH. Mas Mansyur No. 30, RT. 05 / RT. 07, RT.5/RW.7, Kb. Kacang, Kecamatan Tanah Abang, Kota Jakarta Pusat, Daerah Khusus Ibukota Jakarta 10240", "https://lh5.googleusercontent.com/p/AF1QipOHgV6UmSafYDyP3nUWCHwliCnhWApPTEwxor7e=w408-h306-k-no"));
        hospitalList.add(new Hospital("RSUD Kebayoran Baru", "02122774429", -6.265284876641982 , 106.79858250995291, "Jl. Abdul Majid Raya No.28, RT.2/RW.5, Cipete Utara, Kec. Kby. Baru, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12150", "https://lh5.googleusercontent.com/p/AF1QipP2GAWxSLXABEaQsvafww13PCppdAZ-Jr-mcM6G=w408-h306-k-no"));
        hospitalList.add(new Hospital("RSUD Kebayoran Lama", "02127939067", -6.248562703030908 , 106.78548265412942, "Jl. Jatayu, RT.1/RW.12, Kby. Lama Sel., Kec. Kby. Lama, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12240", "https://lh5.googleusercontent.com/p/AF1QipOH_jtN-yBbfeexJhZh04GSEixCPNIaanMrD7eM=w427-h240-k-no"));
        hospitalList.add(new Hospital("RSUD Kalideres", "02122552766", -6.126484444389164 , 106.70404119645752, "Jl. Satu Maret No.48, RT.1/RW.4, Pegadungan, Kec. Kalideres, Kota Jakarta Barat, Daerah Khusus Ibukota Jakarta 11830", "https://lh5.googleusercontent.com/p/AF1QipPMPOefbozO9mduYdNvJyVDKrjy7h15iUJZgk0Q=w408-h271-k-no"));
        hospitalList.add(new Hospital("RSUD Jatipadang", "02122784477", -6.28528735120366 , 106.84230721180055, "Jl. Raya Ragunan No.16, RW.11, Ps. Minggu, Kec. Ps. Minggu, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12740", "https://lh5.googleusercontent.com/p/AF1QipMa0BwNaH57aP3_dyNMzk9ocauIfTXVKsbo1-Fu=w426-h240-k-no"));
    }
}