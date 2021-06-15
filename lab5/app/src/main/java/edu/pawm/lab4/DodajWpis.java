package edu.pawm.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class DodajWpis extends AppCompatActivity {

    private int modyfi_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_wpis);

        ArrayAdapter gatunki = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                new String[] {"Pies", "Kot", "Rybki"});
        Spinner gatunek = (Spinner) findViewById(R.id.gatunek);
        gatunek.setAdapter(gatunki);

        Bundle extras = getIntent().getExtras();
        try {
            if(extras.getSerializable("element") !=null)
            {
                Animal zwierz = (Animal)extras.getSerializable("element");
                EditText kolor = (EditText)findViewById(R.id.kolor);
                EditText wielkosc = (EditText)findViewById(R.id.wielkosc);
                EditText opis = (EditText)findViewById(R.id.opis);
                kolor.setText(zwierz.getKolor());
                wielkosc.setText(Float.toString(zwierz.getWielkosc()));
                opis.setText(zwierz.getOpis());
                this.modyfi_id=zwierz.getId();
            }
        }catch(Exception ex) {
            this.modyfi_id=0;
        }

    }

    public void wyslij(View view){
        String wielkosc = ((EditText)findViewById(R.id.wielkosc)).getText().toString();
        String kolor = ((EditText)findViewById(R.id.kolor)).getText().toString();
        String opis = ((EditText)findViewById(R.id.opis)).getText().toString();
        String gatunek = (((Spinner) findViewById(R.id.gatunek)).getSelectedItem().toString());

        Animal zwierze = new Animal(gatunek, kolor, Float.valueOf(wielkosc), opis);

        zwierze.setId(this.modyfi_id);

        Intent intencja = new Intent();
        intencja.putExtra("nowy", zwierze);
        setResult(RESULT_OK, intencja);
        finish();
    }
}