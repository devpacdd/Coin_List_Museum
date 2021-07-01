package com.example.androidapi_5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class CoinActivity extends AppCompatActivity {
    //Esta es la clase o "Activity" de la moneda, se cargará con una informacion especifica de cada moneda.

    String id;
    String number;
    String mint;
    String image_obverse;
    String image_reverse;
    String date_in;
    String date_out;
    String material;
    String denomination;

    TextView tv_id;
    TextView tv_mint;
    TextView tv_number;
    TextView tv_date_in;
    TextView tv_date_out;
    TextView tv_material;
    TextView tv_denomination;
    ImageView iv_image_obverse;
    ImageView iv_image_reverse;

    private Button btnGoBack;
    private String offeset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin);

        tv_id = findViewById(R.id.textView_ID2);
        tv_mint = findViewById(R.id.textView_Mint2);
        tv_number = findViewById(R.id.textView_Number2);
        tv_date_in = findViewById(R.id.textView_Date_in2);
        tv_date_out = findViewById(R.id.textView_Date_out2);
        tv_material = findViewById(R.id.textView_Material2);
        tv_denomination = findViewById(R.id.textView_Denomination2);
        iv_image_obverse = findViewById(R.id.imageView_Obverse2);
        iv_image_reverse = findViewById(R.id.imageView_Reverse2);
        btnGoBack = findViewById(R.id.btnVolver);

        chargeDetails();

        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });



    }


    private void chargeDetails() {
        id = getIntent().getStringExtra("id");
        number = getIntent().getStringExtra("number");
        mint = getIntent().getStringExtra("mint");
        image_obverse = getIntent().getStringExtra("image_obverse");
        image_reverse = getIntent().getStringExtra("image_reverse");
        date_in = getIntent().getStringExtra("dete_in");
        date_out = getIntent().getStringExtra("date_out");
        material = getIntent().getStringExtra("material");
        denomination = getIntent().getStringExtra("denomination");


        tv_id.setText(id);
        tv_mint.setText(mint);
        tv_number.setText(number);
        tv_date_in.setText(date_in);
        tv_date_out.setText(date_out);
        tv_material.setText(material);
        tv_denomination.setText(denomination);

        Glide.with(this).load(image_obverse).into(iv_image_obverse);
        Glide.with(this).load(image_reverse).into(iv_image_reverse);

        offeset = getIntent().getStringExtra("offset");
    }

    private void goBack(){
        //Intent intent = new Intent(this, MainActivity.class);
        //intent.putExtra("offset",offeset);
        //startActivity(intent);


        super.onBackPressed();
    }

}