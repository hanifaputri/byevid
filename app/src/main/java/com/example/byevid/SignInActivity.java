package com.example.byevid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.byevid.utils.CustomProgressDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class SignInActivity extends AppCompatActivity {
    private static final String TAG = "Sign In";

    private TextInputLayout tx_email_login, tx_pass_login;
    private TextView tv_forgot_pass, tv_register_acc;
    private Button btn_log_in, btn_login_with_google;

    private CustomProgressDialog dialog_loading;

    // Firebase
    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // Get firebase instance
        fAuth = FirebaseAuth.getInstance();
        // Redirect if user authenticated
        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        }

        tx_email_login = findViewById(R.id.tx_login_email);
        tx_pass_login = findViewById(R.id.tx_login_password);

        btn_log_in = findViewById(R.id.btn_login);
        btn_log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authenticateUser();
            }
        });

        tv_forgot_pass = findViewById(R.id.btn_login_reset);
        tv_forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reset = new Intent(SignInActivity.this, ForgotPassActivity.class);
                startActivityForResult(reset, 88);
            }
        });

        tv_register_acc = findViewById(R.id.btn_login_register);
        tv_register_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });

        btn_login_with_google = findViewById(R.id.btn_login_google);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 88 && resultCode == RESULT_OK) {
            String resEmail = data.getStringExtra("email");
            Toast.makeText(this, "Instruksi email telah dikirim ke " + resEmail, Toast.LENGTH_LONG).show();
        }
    }

    private void authenticateUser() {
        String email_login = tx_email_login.getEditText().getText().toString().trim();
        String pass_login = tx_pass_login.getEditText().getText().toString().trim();

        if (validateData()) {
            // Show dialog
            dialog_loading = new CustomProgressDialog(this, R.layout.dialog_loading);
            dialog_loading.show();

            fAuth.signInWithEmailAndPassword(email_login, pass_login).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    dialog_loading.dismiss();
                    if (task.isSuccessful()) {
                        Log.d(TAG, "Login successful.");
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    } else {
                        Toast.makeText(SignInActivity.this, "Email atau Password yang Anda masukkan salah. Silahkan masukkan email Anda kembali.", Toast.LENGTH_LONG).show();
                        Log.d(TAG, task.getException().getMessage());
                    }
                }
            });
        }
    }

    private boolean validateData() {
        String email = tx_email_login.getEditText().getText().toString().trim();
        String password = tx_pass_login.getEditText().getText().toString().trim();

        // check if all fields are filled
        if (!email.isEmpty()) {
            if (!validateEmail(email)){
                tx_email_login.setError("Email tidak valid");
            } else {
                tx_email_login.setErrorEnabled(false);
            }
        } else {
            tx_email_login.setError("Email tidak boleh dikosongkan.");
            return false;
        }

        if (password.length() == 0) {
            tx_pass_login.setError("Password tidak boleh dikosongkan");
            return false;
        } else {
            tx_pass_login.setErrorEnabled(false);
        }
        return true;
    }


    public boolean validateEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}