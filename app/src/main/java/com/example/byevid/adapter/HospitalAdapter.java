package com.example.byevid.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.byevid.HospitalActivity;
import com.example.byevid.HospitalDetailActivity;
import com.example.byevid.R;
import com.example.byevid.model.Hospital;

import java.util.ArrayList;
import java.util.List;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.HospitalViewHolder>{
    private Context context;
    private List<Hospital> hospitalList;

    public HospitalAdapter(Context context, ArrayList<Hospital> hospitalList) {
        this.context = context;
        this.hospitalList = hospitalList;
    }

    public class HospitalViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_distance;
        ImageView thumbnail;
        LinearLayout ll_hospital;

        public HospitalViewHolder(@NonNull View itemView) {
            super(itemView);

            ll_hospital = itemView.findViewById(R.id.ll_hospital);
            tv_name = itemView.findViewById(R.id.tv_list_hospital_title);
            tv_distance = itemView.findViewById(R.id.tv_list_hospital_distance);
            thumbnail = itemView.findViewById(R.id.img_list_hospital_thumb);
        }
    }

    @NonNull
    @Override
    public HospitalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_hospital, parent, false);

        return new HospitalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalViewHolder holder, int position) {
        final Hospital data = hospitalList.get(position);

        holder.tv_name.setText(data.getName());
        holder.tv_distance.setText("7 km");
        Glide.with(context)
            .load(data.getImgUrl())
            .fitCenter()
            .dontAnimate()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.thumbnail);

        // Click listener
        holder.ll_hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "Klik", Toast.LENGTH_SHORT).show();
                Intent detail = new Intent(context, HospitalDetailActivity.class);
                detail.putExtra("Data", data);
                context.startActivity(detail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hospitalList.size();
    }
}
