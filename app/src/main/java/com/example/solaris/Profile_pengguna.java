package com.example.solaris;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class Profile_pengguna extends Activity {
    Button keluar2, upload_pp;
    TextView nama_pengguna, email_pengguna;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    FirebaseUser firebaseUser;
    ImageView pp_user;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_ly);

        //komponen profile
        nama_pengguna = findViewById(R.id.nama_pengguna);
        email_pengguna = findViewById(R.id.email_pengguna);
        keluar2 = findViewById(R.id.keluar2);
        upload_pp = findViewById(R.id.upload_pp);
        pp_user = findViewById(R.id.pp_user);

        //buat upload ke firebase
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();
        FirebaseUser fUser = fAuth.getCurrentUser();

        // buat gigit potato
        fStore.collection("Users").document(fUser.getUid()).get().addOnSuccessListener(documentSnapshot -> {
            nama_pengguna.setText(documentSnapshot.get("fName").toString());
            email_pengguna.setText(documentSnapshot.get("email").toString());
        });

        //BUAT NAMPILIN FOTO DARI FIREBASE
        //StorageReference profilRef = FirebaseStorage.getInstance().getReferenceFromUrl("users/"+fAuth.getCurrentUser().getUid()+"/profile.jpg");
        StorageReference profilRef = storageReference.child("users/"+fAuth.getCurrentUser().getUid()+"/profile.jpg");
        profilRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(pp_user);
            }
        });

        //INI BUAT UPLOADNYA
        upload_pp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //buat buka galeri
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryIntent, 1000);
            }
        });

        keluar2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                keeluar();
            }
        });

    }//penutup oncreate

    //BUAT ACTIVITY RESULT PAS UPLOAD
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000){
            if (resultCode == Activity.RESULT_OK){
                Uri profilURI = data.getData();

                //pp_user.setImageURI(profilURI);
                uploadImageToFirebase(profilURI);
            }
        }
    }

    private void uploadImageToFirebase(Uri profilURI) {
        //MASUKIN FOTO KE FIREBASE
        StorageReference fileRef = storageReference.child("users/"+fAuth.getCurrentUser().getUid()+"/profile.jpg");
        fileRef.putFile(profilURI).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>(){
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot){
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(pp_user);
                    }
                });
                Toast.makeText(Profile_pengguna.this, "Pembaruan Berhasil", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Profile_pengguna.this, "Gagal Diperbarui", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //tombol keluar
    public void keeluar() {
        FirebaseAuth.getInstance().signOut();//logout
        startActivity(new Intent(getApplicationContext(),Splash.class));
        finish();
    }


}//penutup main activity
