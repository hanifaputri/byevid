package com.example.byevid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AccountEditActivity extends AppCompatActivity {
    private static final String TAG = "Edit Account";

    private ImageView btn_back, img_ava;
    private Button btn_save, btn_edit_img, btn_clear_img;
    private TextInputLayout tx_name, tx_email;

    // Firebase instance
    private FirebaseUser fUser;
    private StorageReference storage = FirebaseStorage.getInstance().getReference();

    // Profile
    private String name, email;

    private boolean isPhotoChanged;
    private Uri ava, imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_edit);

        // Get firebase instance
        fUser = FirebaseAuth.getInstance().getCurrentUser();
        tx_name = (TextInputLayout) findViewById(R.id.tx_account_edit_name);
        tx_email = (TextInputLayout) findViewById(R.id.tx_account_edit_email);

        if (fUser != null) {
            for (UserInfo profile : fUser.getProviderData()) {
                // Name, email address, and profile photo Url
                name = profile.getDisplayName();
                email = profile.getEmail();
                ava = profile.getPhotoUrl();

                tx_name.getEditText().setText(profile.getDisplayName());
                tx_email.getEditText().setText(profile.getEmail());
            }
        }

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
        btn_save = (Button) findViewById(R.id.btn_account_edit_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(AccountEditActivity.this, "Save", Toast.LENGTH_SHORT).show();
                updateData();
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


    private void updateData() {
//
//        setResult(RESULT_OK);
//        finish();
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
            .setDisplayName(tx_name.getEditText().getText().toString().trim())
            .build();

        fUser.updateProfile(profileUpdates)
            .addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        setResult(RESULT_OK);
                        finish();

                        Log.d(TAG, "User profile updated. Name : " + profileUpdates.getDisplayName());
                    } else {
                        Log.d(TAG, "Failed to update data: " + task.getException().getMessage());
                    }
                }
            });
    }

    private void resetImg() {
        // Change flag
        isPhotoChanged = false;
        imageUri = null;

//        Glide.with(this)
//            .load(ava)
//            .fitCenter()
//            .dontAnimate()
//            .dontTransform()
//            .into(img_ava);

        // Remove button
        btn_clear_img.setVisibility(View.GONE);
    }
    /*
    Update:

    Check image change ?
        Yes -> Push Image
        No  ->

    Push Data
        img(
     */

//    private void uploadImage() {
//        StorageReference imgRef = storage.child(System.currentTimeMillis() + "." + getFileExtension(imageUri));
//        imgRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                imgRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                    @Override
//                    public void onSuccess(Uri uri) {
//                        String imgUrl = uri.toString();
//
//                        // Set image Uri
//                        imageUri = uri;
//
//                        // Delete previous image
////                        deletePrevImage();
//
//                        // Update imageurl data
//
////                        finish();
//                    }
//                });
//            }
//        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
////                btnUpdate.setText("Loading ...");
////                btnDelete.setEnabled(false);
////                btnUpdate.setEnabled(false);
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Log.d(TAG, e.getMessage());
////                btnDelete.setEnabled(true);
////                btnUpdate.setEnabled(true);
////                btnUpdate.setText("Update");
////                Toast.makeText(UpdateActivity.this, "Update failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    private String getFileExtension(Uri mUri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));
    }

//    private void deletePrevImage() {
//        // Remove previous image if image not the same as placeholder
//        Log.i(TAG, "URL: " + url + " \nDef URL:" + data.getDefaultImageUrl() + "\nEqual? :" + (url.equals(data.getDefaultImageUrl())));
//        if (!url.equals(data.getDefaultImageUrl())) {
//            StorageReference prevRef = FirebaseStorage.getInstance().getReferenceFromUrl(url);
//            prevRef.delete();
//
//            Log.i(TAG, "Image is not placeholder. Previous image deleted");
//        } else {
//            Log.i(TAG, "Image placeholder. Previous image not deleted.");
//        }
//    }

}