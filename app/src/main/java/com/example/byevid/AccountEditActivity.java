package com.example.byevid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputLayout;

public class AccountEditActivity extends AppCompatActivity {
    private ImageView btn_back, img_ava;
    private Button btn_save, btn_edit_img, btn_clear_img;
    private TextInputLayout tx_name, tx_email;

    private boolean isPhotoChanged;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_edit);

        img_ava = (ImageView) findViewById(R.id.img_edit_ava);

        btn_back = (ImageView) findViewById(R.id.btn_edit_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Button for add image
        btn_edit_img = (Button) findViewById(R.id.btn_account_edit_img);
        btn_edit_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent();
                gallery.setAction(Intent.ACTION_GET_CONTENT);
                gallery.setType("image/*");
                startActivityForResult(gallery, 1);
            }
        });

        // Button for reset image
        btn_clear_img = (Button) findViewById(R.id.btn_account_clear_img);
        btn_clear_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetImg();
            }
        });

        // Button for save data
        btn_save = (Button) findViewById(R.id.btn_edit_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AccountEditActivity.this, "Save", Toast.LENGTH_SHORT).show();
                // Intent and result code here
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check action from gallery
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            // Flag changes
            isPhotoChanged = true;
            btn_clear_img.setVisibility(View.VISIBLE);

            imageUri = data.getData();
            img_ava.setImageURI(imageUri);
        }
    }

    private void resetImg() {
        isPhotoChanged = false;
        imageUri = null;
        img_ava.setImageURI(null);

//        Glide.with(this)
//                .load(url)
//                .fitCenter()
//                .dontAnimate()
//                .dontTransform()
//                .into(img);

        btn_clear_img.setVisibility(View.GONE);
    }
}