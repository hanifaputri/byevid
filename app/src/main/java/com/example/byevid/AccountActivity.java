package com.example.byevid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.byevid.adapter.MenuAdapter;
import com.example.byevid.model.Settings;

import java.util.ArrayList;

public class AccountActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<Settings> listSetting;
    private MenuAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        listView = (ListView) findViewById(R.id.lv_account_list);

        // Initialize menu
        listSetting = new ArrayList<>();
        addData();

        // Setup adapter
        adapter = new MenuAdapter(this, R.layout.list_menu, listSetting);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Settings data = listSetting.get(position);
                if (data.getTo() != null) {
                    Intent newActivity = new Intent(getApplicationContext(), data.getTo());
                    startActivity(newActivity);
                } else  {
                    Toast.makeText(AccountActivity.this, "Tes: " + data.getTitle() , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void addData() {
        listSetting.add(new Settings("Ubah Profil", getResources().getDrawable(R.drawable.ic_baseline_edit_profile_24), AccountEditActivity.class));
        listSetting.add(new Settings("Pengaturan", getResources().getDrawable(R.drawable.ic_baseline_settings_24), null));
        listSetting.add(new Settings("Tentang", getResources().getDrawable(R.drawable.ic_baseline_info_24), AccountAboutActivity.class));
        listSetting.add(new Settings("Bantuan", getResources().getDrawable(R.drawable.ic_baseline_help_24), null));
    }

    public void showActivityForResult(Class<?> to, int requestCode) {
        Intent intent = new Intent(this, to);
        startActivityForResult(intent,  requestCode);
    }

    public void showActivity(Class<?> to) {
        Intent intent = new Intent(this, to);
        startActivity(intent);
    }
}