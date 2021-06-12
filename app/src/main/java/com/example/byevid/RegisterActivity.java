package com.example.byevid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class RegisterActivity extends AppCompatActivity {

    private TextInputLayout tx_email_regist, tx_pass_regist;
    private Button btn_register, btn_login_with_google;
    private TextView tv_log_into_acc;
    private ImageView btn_back;
    private CheckBox cb_terms;
    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // button back
        btn_back = findViewById(R.id.btn_register_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignInActivity.class));
                finish();
            }
        });

        // registration process
        tx_email_regist = findViewById(R.id.tx_register_email);
        tx_pass_regist = findViewById(R.id.tx_register_password);
        btn_register = findViewById(R.id.btn_register);
        cb_terms = findViewById(R.id.cb_register_toc);
        fAuth = FirebaseAuth.getInstance();
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = tx_email_regist.getEditText().getText().toString().trim();
                String password = tx_pass_regist.getEditText().getText().toString().trim();

                // firebase authentication
                fAuth = FirebaseAuth.getInstance();
                if (fAuth.getCurrentUser() != null) {
                    startActivity(new Intent(getApplicationContext(), RegisterNameActivity.class));
                    finish();
                }

                // check if all fields are filled
                if (TextUtils.isEmpty(email)) {
                    tx_email_regist.setError("Email tidak boleh dikosongkan.");
                    return;
                } else {
                    tx_email_regist.setErrorEnabled(false);
                }

                if (password.length() == 0) {
                    tx_pass_regist.setError("Password tidak boleh dikosongkan");
                    return;
                } else if (password.length() < 8) {
                    tx_pass_regist.setError("Password harus terdiri dari 8 atau lebih karakter.");
                    return;
                } else {
                    tx_pass_regist.setErrorEnabled(false);
                }

                // check if the checkbox is clicked
                boolean cb_checked = cb_terms.isChecked();
                if (!cb_checked) {
                    cb_terms.setError("");
                    Toast.makeText(RegisterActivity.this, "Silahkan tandai boks untuk melanjutkan proses registrasi.", Toast.LENGTH_SHORT).show();
                } else {
                    cb_terms.setError(null);
                }

                // firebase authentication
                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(getApplicationContext(), RegisterNameActivity.class));
                        } else {
                            Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        // if account exists, log into account
        tv_log_into_acc = findViewById(R.id.tv_reg_login);
        tv_log_into_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignInActivity.class));
            }
        });

        // login with google account
        btn_login_with_google = findViewById(R.id.btn_register_google);
        btn_login_with_google.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

            }
        });
    }

}