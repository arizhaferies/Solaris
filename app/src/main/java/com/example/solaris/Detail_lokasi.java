package com.example.solaris;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class Detail_lokasi extends AppCompatActivity {
    TextView detailNama,deskripsi_tempat;
    ImageView gambar_lokasi;
    CheckBox solo_adven, grupPrifat, grupPublik;
    Button pesan_paket;
    RatingBar ratingBar;

    private void initializeWidgets(){
        detailNama= findViewById(R.id.detailNama);
        deskripsi_tempat= findViewById(R.id.deskripsi);
        gambar_lokasi=findViewById(R.id.articleimg2);
        ratingBar=findViewById(R.id.ratingBar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.places_ly);
        solo_adven=(CheckBox) findViewById(R.id.solo);
        grupPrifat=(CheckBox) findViewById(R.id.grupPrifat);
        grupPublik=(CheckBox) findViewById(R.id.grupPublik);
        pesan_paket=(Button) findViewById(R.id.pesan_paket);

        initializeWidgets();
        //BUAT NGAMBIL GAMBAR DARI FIREBASE
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            detailNama.setText(""+bundle.getString("articleName"));
            deskripsi_tempat.setText(""+bundle.getString("description"));
            Picasso.get()
                    .load(bundle.getString("imageUrl"))
                    .placeholder(R.drawable.default_img)
                    .fit()
                    .centerCrop()
                    .into(gambar_lokasi);
        }

    }

    //PILIHAN CHECKBOX
    public void onClick(View view){
        if(solo_adven.isChecked()==true && grupPrifat.isChecked()==true && grupPublik.isChecked()==true){
            Toast.makeText(Detail_lokasi.this, "Pilih Satu Paket Saja", Toast.LENGTH_SHORT).show();
        } else if (solo_adven.isChecked()==true && grupPrifat.isChecked()==true){
            Toast.makeText(Detail_lokasi.this, "Pilih Satu Paket Saja", Toast.LENGTH_SHORT).show();
        } else if(grupPrifat.isChecked()==true && grupPublik.isChecked()==true){
            Toast.makeText(Detail_lokasi.this, "Pilih Satu Paket Saja", Toast.LENGTH_SHORT).show();
        } else if(grupPublik.isChecked()==true && solo_adven.isChecked()==true){
            Toast.makeText(Detail_lokasi.this, "Pilih Satu Paket Saja", Toast.LENGTH_SHORT).show();
        }else if(solo_adven.isChecked()==true){
            Toast.makeText(Detail_lokasi.this, "Kamu Berhasil Memesan Paket Solo", Toast.LENGTH_LONG).show();
        } else if(grupPublik.isChecked()==true){
            Toast.makeText(Detail_lokasi.this, "Kamu Berhasil Memesan Paket Grup Publik", Toast.LENGTH_LONG).show();
        }else if(grupPrifat.isChecked()==true){
            Toast.makeText(Detail_lokasi.this, "Kamu Berhasil Memesan Paket Grup Privat", Toast.LENGTH_LONG).show();
        }
    }


}
