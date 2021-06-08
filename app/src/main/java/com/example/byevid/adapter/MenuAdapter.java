package com.example.byevid.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.byevid.R;

import java.util.List;

public class MenuAdapter extends ArrayAdapter<Menu> {
    private final Context context;
    List<Menu> listMenu;
    int layout;

    private static class MenuHolder {
        TextView title;
        ImageView icon;
    }

    public MenuAdapter(Context context, int layout, List<Menu> listMenu) {
        super(context, layout, listMenu);

        this.context = context;
        this.layout = layout;
        this.listMenu = listMenu;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        MenuHolder holder;

        final View result;

        if(convertView == null){
            holder = new MenuHolder();
            
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(layout, parent,false);

            holder.icon = (ImageView) convertView.findViewById(R.id.img_list_account_icon);
            holder.title = (TextView) convertView.findViewById(R.id.tv_list_account_menu);

            result = convertView;
            convertView.setTag(holder);
        }
        else {
            holder = (MenuHolder) convertView.getTag();
            result = convertView;
        }

        // Get data item from position
//        Menu menu = getItem(position);
//        holder.title.setText();
//
//        Glide.with(context)
//                .load(menu.getItem(position).getIcon())
//                .fitCenter()
//                .dontAnimate()
//                .dontTransform()
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(holder.icon);

        return convertView;
    }
}
