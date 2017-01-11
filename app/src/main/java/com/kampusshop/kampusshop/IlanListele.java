package com.kampusshop.kampusshop;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class IlanListele extends AppCompatActivity {

    ListView IlanlarListe;
    Button IlanEkleBtn;
    DB_Controller controller;
    public ArrayList<String> ilanlarArrayList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilan_listele);
        controller = new DB_Controller(this);

        setTitle("İlanlar");

        IlanlarListe = (ListView) findViewById(R.id.ListView);
        ArrayList<String> veritabaniArray = myArrayListFromDatabase();
        ListAdapter myListAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, veritabaniArray);
        IlanlarListe.setAdapter(myListAdapter);

        IlanEkleBtn = (Button)findViewById(R.id.button3);
        IlanEkleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent IlanEkleIntent = new Intent(IlanListele.this, IlanEkle.class);
                startActivity(IlanEkleIntent);
            }
        });
    }
    public ArrayList<String> myArrayListFromDatabase() {
        Cursor res = controller.IlanListele();
        res.moveToFirst();
        String veritabaniString = "";

        while (!res.isAfterLast()) {
            if (res.getString(res.getColumnIndex("Baslik")) != null) {
                veritabaniString += res.getString(res.getColumnIndex("Baslik"));
                veritabaniString += "\n";

                String baslik = res.getString(res.getColumnIndex("Baslik"));
                String fiyat = res.getString(res.getColumnIndex("Fiyat"));
                String aciklama = res.getString(res.getColumnIndex("Aciklama"));
                String marka = res.getString(res.getColumnIndex("Marka"));
                ilanlarArrayList.add(baslik + "\n" + fiyat + "\n" + aciklama + "\n" + marka);
            }
        res.moveToNext();
        }
        return ilanlarArrayList;
    }

}
