package com.example.byevid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.byevid.utils.CustomProgressDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ForgotPassActivity extends AppCompatActivity {
    private static final String TAG = "Forgot Password";
    
    private TextInputLayout tx_insert_email;
    private Button btn_send_email;
    private ImageView btn_back;
    private CustomProgressDialog dialog_loading;

    private FirebaseAuth fAuth;
    private FirebaseUser fUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        // initialize button back
        btn_back = findViewById(R.id.btn_edit_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignInActivity.class));
            }
        });

        // Firebase instance
        fAuth = FirebaseAuth.getInstance();
        fUser = fAuth.getCurrentUser();
        
        // send email
        tx_insert_email = findViewById(R.id.tx_input_email);
        btn_send_email = findViewById(R.id.btn_send);
        btn_send_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendResetEmail();
            }
        });
    }
    
    private void sendResetEmail() {
        String email = tx_insert_email.getEditText().getText().toString().trim();
        // Validate email
        if (validateInput()) {
            // Show dialog
            dialog_loading = new CustomProgressDialog(this, R.layout.dialog_loading);
            dialog_loading.show();

            fAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    dialog_loading.dismiss();

                    if (task.isSuccessful()) {
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("email", email);
                        setResult(Activity.RESULT_OK, resultIntent);
                        finish();
                    } else {
                        Toast.makeText(ForgotPassActivity.this, "Email gagal dikirim: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        Log.w(TAG, task.getException().getMessage());
                    }
                }
            });
        }
    }
    
    // check email validity
    private boolean validateInput() {
        // check field is filled
        if (tx_insert_email.getEditText().getText().toString().trim().isEmpty()) {
            tx_insert_email.setError("Silahkan masukkan email Anda.");
            return false;
        }

        // check proper email
        if (!isEmailValid(tx_insert_email.getEditText().getText().toString().trim())) {
            tx_insert_email.setError("Masukkan email yang valid");
            return false;
        }
        return true;
    }

    private boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}