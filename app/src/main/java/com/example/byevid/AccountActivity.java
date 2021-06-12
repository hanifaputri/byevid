package com.example.byevid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.byevid.adapter.MenuAdapter;
import com.example.byevid.model.Settings;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

import java.util.ArrayList;

public class AccountActivity extends AppCompatActivity {
    private static final String TAG = "Account Activity";

    private ListView listView;
    private ArrayList<Settings> listSetting;
    private MenuAdapter adapter;

    private Button btn_logout;
    private TextView tv_name, tv_email;

    // Firebase instance
    private FirebaseUser fUser;
    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        // Navigation bar
        BottomNavigationView bottomNav = (BottomNavigationView) findViewById(R.id.bottom_navbar);
        bottomNav.setSelectedItemId(R.id.navbar_account);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navbar_home:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                        break;
                    case R.id.navbar_hospital:
                        startActivity(new Intent(getApplicationContext(), HospitalActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                        break;
                    case R.id.navbar_article:
                        startActivity(new Intent(getApplicationContext(), ArticleActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                        break;
                    case R.id.navbar_account:
                        break;
                }
                return true;
            }
        });

        // Get firebase instance
        fAuth = FirebaseAuth.getInstance();
        fUser = fAuth.getCurrentUser();

        // Set name & emaial
        tv_name = (TextView) findViewById(R.id.tv_account_name);
        tv_email = (TextView) findViewById(R.id.tv_account_email);

        if (fUser != null) {
            for (UserInfo profile : fUser.getProviderData()) {
                // Name, email address, and profile photo Url
                String name = profile.getDisplayName();
                String email = profile.getEmail();
                // If name not empty
                if (!name.isEmpty()) {
                    tv_name.setText(profile.getDisplayName());
                } else {
                    tv_name.setText("Hi, User!");
                }

                if (!email.isEmpty()) {
                    tv_email.setText(profile.getEmail());
                } else {
                    tv_email.setText("Email belum diset");
                }
            }
        }

        listView = (ListView) findViewById(R.id.lv_account_list);

        btn_logout = (Button) findViewById(R.id.btn_account_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut();
            }
        });

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
                    if (data.getTitle().equals("Ubah Profil")) {
                        showActivityForResult(data.getTo(), 99);
                    } else {
                        showActivity(data.getTo());
                    }
                } else {
                    Toast.makeText(AccountActivity.this, "Tes: " + data.getTitle() , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 99 && resultCode == RESULT_OK) {
            recreate();
            Log.d(TAG, "Request code 99");
            Toast.makeText(this, "Profil berhasil diupdate", Toast.LENGTH_LONG).show();
        } else {
            Log.d(TAG, "Request code not retrieved");
        }
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

    private void logOut(){
        // Show confirmation dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Keluar?");
        builder.setMessage("Apakah kamu yakin ingin keluar?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                fAuth.signOut();
//                startActivity(new Intent(AccountActivity.this, SignInActivity.class));
                finish();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}