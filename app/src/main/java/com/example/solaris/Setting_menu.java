package com.example.solaris;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Setting_menu extends Activity {
    Switch blutut, wipi, gipis, kamera1, kontak_orang;

    static int PreqCode = 1;
    static int REQUESTCODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_ly);
        //switch
        blutut = (Switch) findViewById(R.id.blue);
        wipi = (Switch) findViewById(R.id.waifi);
        gipis = (Switch) findViewById(R.id.jipies);
        kamera1 = (Switch) findViewById(R.id.kamemera);
        kontak_orang = (Switch) findViewById(R.id.buku_kontak);

        //slide
        //BLUETOOTH (udah bisa)
        blutut.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
                if (isChecked){
                    if (Build.VERSION.SDK_INT>=18){
                        CheckPermission();
                    } else {
                        nyalain();
                    }

                } else {
                    nyalain();

                }
            }

            private void CheckPermission(){
                BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
                if (ContextCompat.checkSelfPermission(Setting_menu.this, Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(Setting_menu.this, Manifest.permission.ACCESS_WIFI_STATE)){
                        Toast.makeText(Setting_menu.this, "Mohon izinkan aplikasi mengakses fitur ini.", Toast.LENGTH_LONG).show();
                    } else {
                        ActivityCompat.requestPermissions(Setting_menu.this, new String[]{Manifest
                                .permission.ACCESS_WIFI_STATE}, PreqCode);
                    }
                } else {
                    nyalain();
                }
            }

            private void nyalain (){
                BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
                if (adapter == null) {
                    Toast.makeText(getApplicationContext(), "Android Tidak Support Fitur Ini", Toast.LENGTH_LONG).show();
                } else if (adapter.isEnabled()){
                    adapter.disable();
                } else {
                    adapter.enable();
                }
            }

        });

      //WIFI (belom dicoba)
        wipi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (Build.VERSION.SDK_INT>=10){
                        CheckPermission();
                    } else {
                        buka_wifi();
                    }

                } else {
                    buka_wifi();
                }
            }

            private void buka_wifi(){
                WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                if (wifiManager == null) {
                    Toast.makeText(getApplicationContext(), "Android Tidak Support Fitur Ini", Toast.LENGTH_LONG).show();
                } else if (wifiManager.isWifiEnabled()){
                    wifiManager.setWifiEnabled(true);
                } else {
                    wifiManager.setWifiEnabled(false);
                }

            }

            private void CheckPermission(){
                if (ContextCompat.checkSelfPermission(Setting_menu.this, Manifest.permission.ACCESS_WIFI_STATE) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(Setting_menu.this, Manifest.permission.ACCESS_WIFI_STATE)){
                        Toast.makeText(Setting_menu.this, "Please Accept for Required Permission", Toast.LENGTH_SHORT).show();
                    } else {
                        ActivityCompat.requestPermissions(Setting_menu.this, new String[]{Manifest
                                .permission.ACCESS_WIFI_STATE}, PreqCode);
                    }
                } else {
                    buka_wifi();
                }
            }
        });

        //GPS (belom dicoba)
        gipis.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    CheckPermission();
                } else {
                    buka_gps();
                }
            }

            private void buka_gps(){
                Intent intent1 = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent1);
            }

            private void CheckPermission(){
                if (ContextCompat.checkSelfPermission(Setting_menu.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                    if (ActivityCompat.shouldShowRequestPermissionRationale(Setting_menu.this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                        Toast.makeText(Setting_menu.this, "Please Accept for Required Permission", Toast.LENGTH_SHORT).show();
                    } else {
                        ActivityCompat.requestPermissions(Setting_menu.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, PreqCode);
                    }
                }else {
                    buka_gps();
                }
            }
        });

        //KAMERA ( it's done omg )
        kamera1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    if (Build.VERSION.SDK_INT >= 18){
                        CheckPermission();
                    } else {
                        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                        startActivity(intent);
                    }
                } else {
                    String kameraOff;
                    kameraOff = kamera1.getTextOff().toString();
                    Toast.makeText(getApplicationContext(), kameraOff, Toast.LENGTH_LONG).show();
                }
            }

            private void CheckPermission() {
                if (ContextCompat.checkSelfPermission(Setting_menu.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(Setting_menu.this, Manifest.permission.CAMERA)) {
                        Toast.makeText(Setting_menu.this, "Please Accept for Required Permission", Toast.LENGTH_SHORT).show();

                    } else {
                        ActivityCompat.requestPermissions(Setting_menu.this, new String[]{Manifest.permission.CAMERA}, PreqCode);
                    }
                } else {
                    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                    startActivity(intent);
                }
            }
        });

        //KONTAK (akses dan buka kontak )
        kontak_orang.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    if(Build.VERSION.SDK_INT >= 18){
                        CheckPermission();
                    } else {
                        buka_kontak();
                    }
                } else {
                    String kontakOff;
                    kontakOff = kontak_orang.getTextOff().toString();
                    Toast.makeText(getApplicationContext(), kontakOff, Toast.LENGTH_LONG).show();
                }

            }

                private void buka_kontak(){
                Intent contactIntent = new Intent(Intent.ACTION_GET_CONTENT);
                contactIntent.setType("contact/*");
                startActivityForResult(contactIntent, REQUESTCODE);
            }

            private void CheckPermission(){
                if (ContextCompat.checkSelfPermission(Setting_menu.this,Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
                    if (ActivityCompat.shouldShowRequestPermissionRationale(Setting_menu.this, Manifest.permission.READ_CONTACTS)){
                        Toast.makeText(Setting_menu.this, "Please Accept for Required Permission", Toast.LENGTH_SHORT).show();
                    } else {
                        ActivityCompat.requestPermissions(Setting_menu.this, new String[]{Manifest.permission.READ_CONTACTS}, PreqCode);
                    }
                } else {
                    buka_kontak();
                }
            }


        });
    }

}
