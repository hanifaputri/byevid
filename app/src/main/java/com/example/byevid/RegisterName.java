package com.example.byevid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterName extends AppCompatActivity {

   // private static final String TAG = "Register Nama";
    private TextInputLayout tx_insert_name;
    private Button btn_done;
    private ImageView btn_back;
    private FirebaseUser fUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_name);

        // initialize button back
        btn_back = findViewById(R.id.btn_edit_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignInActivity.class));
            }
        });

        // input name
        tx_insert_name = findViewById(R.id.tx_register_name);
        fUser = FirebaseAuth.getInstance().getCurrentUser();
        btn_done = findViewById(R.id.btn_register_name_done);
        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(tx_insert_name.getEditText().getText().toString().trim())
                        .build();

                fUser.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                            //Log.d(TAG, "Nama berhasil ditambahkan.");
                        }
                    }
                });
            }
        });
    }
}