package com.example.byevid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

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
        btn_log_in = findViewById(R.id.btn_login);
        btn_log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}