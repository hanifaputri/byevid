package com.example.byevid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ForgotPassActivity extends AppCompatActivity {

    private TextInputLayout tx_insert_email;
    private Button btn_send_email;
    private ImageView btn_back;
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

        // send email
        tx_insert_email = findViewById(R.id.tx_input_email);
        btn_send_email = findViewById(R.id.btn_send);
        fUser = FirebaseAuth.getInstance().getCurrentUser();
        btn_send_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInput()) {
                    fUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                startActivity(new Intent(getApplicationContext(), ForgotPassActivity.class));
                                Toast.makeText(ForgotPassActivity.this, "Permintaan reset password telah dikirimkan ke email Anda. Silahkan periksa email Anda.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ForgotPassActivity.this, "Email yang Anda masukkan tidak ditemukan. Silahkan masukkan email Anda kembali.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    // check email validity
    protected boolean validateInput() {
        // check field is filled
        if (tx_insert_email.getEditText().getText().toString().trim().isEmpty()) {
            tx_insert_email.setError("Silahkan masukkan email Anda.");
            return false;
        }

        // check proper email
        if (!isEmailValid(tx_insert_email.getEditText().getText().toString().trim())) {
            tx_insert_email.setError("Email yang Anda masukkan tidak ditemukan.");
            return false;
        }
        return true;
    }

    protected boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}