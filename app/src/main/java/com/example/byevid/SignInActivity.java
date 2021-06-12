package com.example.byevid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class SignInActivity extends AppCompatActivity {

    private TextInputLayout tx_email_login, tx_pass_login;
    private TextView tv_forgot_pass, tv_register_acc;
    private Button btn_log_in, btn_login_with_google;
    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        tx_email_login = findViewById(R.id.tx_login_email);
        tx_pass_login = findViewById(R.id.tx_login_password);
        fAuth = FirebaseAuth.getInstance();
        btn_log_in = findViewById(R.id.btn_login);
        btn_log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_login = tx_email_login.getEditText().getText().toString().trim();
                String pass_login = tx_pass_login.getEditText().getText().toString().trim();

                if (TextUtils.isEmpty(email_login)) {
                    tx_email_login.setError("Silahkan masukkan email Anda.");
                    return;
                } else {
                    tx_email_login.setErrorEnabled(false);
                }

                if (TextUtils.isEmpty(pass_login)) {
                    tx_pass_login.setError("Silahkan masukkan password Anda.");
                    return;
                } else {
                    tx_pass_login.setErrorEnabled(false);
                }

                fAuth.signInWithEmailAndPassword(email_login, pass_login).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        } else {
                            Toast.makeText(SignInActivity.this, "Email atau Password yang Anda masukkan salah. Silahkan masukkan email Anda kembali.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        tv_forgot_pass = findViewById(R.id.btn_login_reset);
        tv_forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ForgotPassActivity.class));
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

}