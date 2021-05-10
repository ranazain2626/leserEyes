package com.example.lasereyes.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.lasereyes.Adapters.ColorsAdapter;
import com.example.lasereyes.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.ArrayList;

import static android.content.Intent.ACTION_PICK;

public class ImageActivity extends AppCompatActivity {
    ImageView selected_image;
    private static final int IMAGE_PICK_CODE=11000;
    private static final int PERMISSION_CODE=1000;
    LinearLayout add_image;
    ImageButton btnEdit;
    Button btnadd;
    Button btn_remove;
    LinearLayout allButton;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_add);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });


        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.
                mInterstitialAd = interstitialAd;
                Log.i( "TAG", "onAdLoaded");
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error
                Log.i("TAG", loadAdError.getMessage());
                mInterstitialAd = null;
            }
        });


         add_image= findViewById(R.id.add_image_linear);
         selected_image=findViewById(R.id.show_imag);
        btnEdit= findViewById(R.id.edit);
        btnadd= findViewById(R.id.add_Eyes);
        btn_remove= findViewById(R.id.remove_watermark);
        allButton = findViewById(R.id.all_button);

        add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                        String[] permissions ={Manifest.permission.READ_EXTERNAL_STORAGE} ;
                        requestPermissions(permissions, PERMISSION_CODE);
                    }
                    else{
                        pickImageFromGallery();
                    }
                }else{
                    pickImageFromGallery();

                }

            }

        });




        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show1Dialog(ImageActivity.this);
            }



        });


        btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mInterstitialAd != null){
                    mInterstitialAd.show(ImageActivity.this);

                }
            }



        });


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                        String[] permissions ={Manifest.permission.READ_EXTERNAL_STORAGE} ;
                        requestPermissions(permissions, PERMISSION_CODE);
                    }
                    else{
                        pickImageFromGallery();
                    }
                }else{
                    pickImageFromGallery();

                }

            }

        });

    }

    public void show1Dialog(Context ctx){
        final Dialog dialog = new Dialog(ctx);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_colors);

        RecyclerView rv= (RecyclerView) dialog.findViewById(R.id.color);
        ArrayList titles = new ArrayList<>();
        titles.add("#636161");
        titles.add("#ff0000");
        titles.add("#cc3399");
        titles.add("#0066ff");
        titles.add("#ffcc00");
        titles.add("#3399ff");
        titles.add("#00ffcc");
        titles.add("#00ff00");
        titles.add("#ffcc00");
        titles.add("#3399ff");
        titles.add("#00ffcc");
        titles.add("#ff0000");

        ColorsAdapter adapter = new ColorsAdapter(this,titles);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(ctx,3,GridLayoutManager.VERTICAL,false);
        rv.setLayoutManager(gridLayoutManager);
        rv.setAdapter(adapter);

        dialog.show();

    }


    private void pickImageFromGallery() {
    Intent Intent = new Intent(ACTION_PICK);
    Intent.setType("image/*");
    startActivityForResult(Intent, IMAGE_PICK_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_CODE:{
                if (grantResults.length>0&& grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    pickImageFromGallery();
                }
                else {
                    Toast.makeText(this,"Permission denied",Toast.LENGTH_SHORT).show();
                }
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("mubi",requestCode+"");
        if (resultCode== RESULT_OK && requestCode == IMAGE_PICK_CODE){
            allButton.setVisibility(View.VISIBLE);
            btnEdit.setVisibility(View.VISIBLE);
            selected_image.setVisibility(View.VISIBLE);
            add_image.setVisibility(View.GONE);

            selected_image.setImageURI(data.getData());
        }
    }
}