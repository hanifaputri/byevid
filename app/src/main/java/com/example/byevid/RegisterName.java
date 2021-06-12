package com.example.byevid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterName extends AppCompatActivity {
    private static final String TAG = "Register Nama";

    private TextInputLayout tx_insert_name;
    private Button btn_done;
    private ImageView btn_back;

    // Firebase instance
    private FirebaseUser fUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_name);

        // Get firebase instance
        fUser = FirebaseAuth.getInstance().getCurrentUser();

        if (fUser != null) {
            Log.i(TAG, "User authenticated");
            for (UserInfo profile : fUser.getProviderData()) {
                // Name, email address, and profile photo Url
                String name = profile.getDisplayName();
//                // If name not empty
//                if (!name.isEmpty()) {
//                    Log.i(TAG, "Nama tidak kosong. Nama: " + name);
//                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
//                    finish();
////                    Toast.makeText(this, "Nama;" + name, Toast.LENGTH_SHORT).show();
//                }
            }
        }
//        else {
////            Log.i(TAG, "User already sign");
//        }

        // input name
        tx_insert_name = findViewById(R.id.tx_register_name);

        btn_done = findViewById(R.id.btn_register_name_done);
        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateName()) {
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(tx_insert_name.getEditText().getText().toString().trim())
                        .build();

                    fUser.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.i(TAG, "Name update successfull");
                                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                                finish();
                            } else {
                                Log.d(TAG, task.getException().getMessage());
                            }
                        }
                    });
                }
            }
        });

        // initialize button back
        btn_back = findViewById(R.id.btn_register_name_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backDialog();
            }
        });
    }

    private boolean validateName() {
        String name = tx_insert_name.getEditText().getText().toString();

        if (name.isEmpty()){
            tx_insert_name.setError("Nama tidak boleh kosong");
            return false;
        }
        tx_insert_name.setErrorEnabled(false);
        return true;
    }

    private void backDialog(){
        // Show confirmation dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Apakah Anda yakin ingin kembali?");
        builder.setMessage("Anda akan kembali ke dan data nama Anda tidak akan tersimpan, apakah kamu yakin?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(RegisterName.this, "User yakin dan klik back", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(RegisterName.this, SignInActivity.class));
//                finish();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}