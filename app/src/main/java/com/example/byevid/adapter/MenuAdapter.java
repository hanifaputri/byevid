package com.example.byevid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.byevid.R;
import com.example.byevid.model.Settings;

import java.util.ArrayList;
import java.util.List;

public class MenuAdapter extends ArrayAdapter<Settings> {
    private final Context context;
    List<Settings> listSetting;
    int layout;

    private static class MenuHolder {
        TextView title;
        ImageView icon;
    }

    public MenuAdapter(Context context, int layout, ArrayList<Settings> listSetting) {
        super(context, layout, listSetting);

        this.context = context;
        this.layout = layout;
        this.listSetting = listSetting;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        MenuHolder holder;

        if(convertView == null){
            holder = new MenuHolder();
            
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(layout, parent,false);

            holder.icon = (ImageView) convertView.findViewById(R.id.img_list_account_icon);
            holder.title = (TextView) convertView.findViewById(R.id.tv_list_account_menu);

            convertView.setTag(holder);
        }
        else {
            holder = (MenuHolder) convertView.getTag();
        }

//      Get data item from position
        Settings menu = getItem(position);
        holder.title.setText(menu.getTitle());
        Glide.with(context)
                .load(menu.getIcon())
                .into(holder.icon);

        return convertView;
    }
}
