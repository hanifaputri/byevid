package com.example.byevid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "Register";

    private TextInputLayout tx_email_regist, tx_pass_regist;
    private Button btn_register, btn_login_with_google;
    private TextView tv_log_into_acc;
    private ImageView btn_back;
    private CheckBox cb_terms;
    private CustomProgressDialog dialog_loading;

    // Firebase
    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Get firebase instance
        fAuth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Log.i(TAG, "User already signed in.");
            Intent name = new Intent(RegisterActivity.this, RegisterName.class);
            startActivity(name);
        }

        // registration process
        tx_email_regist = findViewById(R.id.tx_register_email);
        tx_pass_regist = findViewById(R.id.tx_register_password);
        btn_register = findViewById(R.id.btn_register);
        cb_terms = findViewById(R.id.cb_register_toc);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });

        // if account exists, log into account
        tv_log_into_acc = findViewById(R.id.tv_reg_login);
        tv_log_into_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });

        // login with google account
        btn_login_with_google = findViewById(R.id.btn_register_google);

        // button back
        btn_back = findViewById(R.id.btn_register_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignInActivity.class));
                finish();
            }
        });
    }

    private void createUser() {
        String email = tx_email_regist.getEditText().getText().toString().trim();
        String password = tx_pass_regist.getEditText().getText().toString().trim();

        // Show dialog
        dialog_loading = new CustomProgressDialog(this, R.layout.dialog_loading);

        if (validateForm()){
            dialog_loading.show();
            // firebase authentication
            fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    dialog_loading.dismiss();
                    if (task.isSuccessful()) {
                        Log.i(TAG, "Register succeed");

                        // Intent to register name
                        startActivity(new Intent(getApplicationContext(), RegisterName.class));
                    } else {
                        Log.d(TAG, task.getException().getMessage());
                        Toast.makeText(RegisterActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private boolean validateForm() {
        String email = tx_email_regist.getEditText().getText().toString();
        String password = tx_pass_regist.getEditText().getText().toString();

        // check if all fields are filled
        if (!email.isEmpty()) {
            if (!validateEmail(email)){
                tx_email_regist.setError("Email tidak valid");
            } else {
                tx_email_regist.setErrorEnabled(false);
            }
        } else {
            tx_email_regist.setError("Email tidak boleh dikosongkan.");
            return false;
        }

        if (password.length() == 0) {
            tx_pass_regist.setError("Password tidak boleh dikosongkan");
            return false;
        } else if (password.length() < 8) {
            tx_pass_regist.setError("Password harus terdiri dari 8 atau lebih karakter.");
            return false;
        } else {
            tx_pass_regist.setErrorEnabled(false);
        }

        // check if the checkbox is clicked
        boolean cb_checked = cb_terms.isChecked();
        if (!cb_checked) {
            cb_terms.setError("");
            Toast.makeText(RegisterActivity.this, "Centang untuk melanjutkan proses registrasi.", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            cb_terms.setError(null);
        }
        return true;
    }

    public boolean validateEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}