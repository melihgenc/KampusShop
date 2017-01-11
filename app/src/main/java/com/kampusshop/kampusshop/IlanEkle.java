package com.kampusshop.kampusshop;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class IlanEkle extends AppCompatActivity {

    EditText Baslik;
    EditText Fiyat;
    EditText Aciklama;
    EditText Marka;
    Button Ekle;
    Button Ilanlar;
    DB_Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilan_ekle);
        controller = new DB_Controller(this);

        setTitle("İlan Ekle");

        Baslik = (EditText) findViewById(R.id.editText);
        Fiyat = (EditText) findViewById(R.id.editText2);
        Aciklama = (EditText) findViewById(R.id.editText3);
        Marka = (EditText) findViewById(R.id.editText4);

        Ekle = (Button) findViewById(R.id.button);
        Ilanlar = (Button) findViewById(R.id.button2);
        Ilanlar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent IlanlarIntent = new Intent(IlanEkle.this, IlanListele.class);
                startActivity(IlanlarIntent);
            }
        });
        veriEkle();
    }

    public void veriEkle() {
        Ekle.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean eklendiMi = controller.IlanEkle(Baslik.getText().toString(),
                                Fiyat.getText().toString(),
                                Aciklama.getText().toString(),
                                Marka.getText().toString());
                        if (eklendiMi = true)
                            Toast.makeText(IlanEkle.this, "Ilan eklendi!", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(IlanEkle.this, "Ilan eklenemedi!", Toast.LENGTH_LONG).show();
                    }
                });
    }
}