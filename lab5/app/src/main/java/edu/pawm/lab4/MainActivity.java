package edu.pawm.lab4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterViewAnimator;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> target;
    private SimpleCursorAdapter adapter;
    private MySQLite db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] values = new String[]{
                "Pies", "Kot", "Gołąb", "Kruk", "Dzik", "Karp", "Osioł", "Chomik", "Mysz", "Jeż", "Karaluch"
        };

        this.target = new ArrayList<String>();
        this.target.addAll(Arrays.asList(values));

        Context context = this;
        this.db = new MySQLite(context);
        Cursor cursor = db.lista();
        int layout = android.R.layout.simple_list_item_2;
        String[] from = new String[] {"_id", "gatunek"};
        int[] to = new int[] {android.R.id.text1, android.R.id.text2};
        int flags = SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE;

        this.adapter = new SimpleCursorAdapter(context, layout, cursor, from, to, flags);

        ListView listview = (ListView)findViewById(R.id.listView);
        listview.setAdapter(this.adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView name = (TextView)
                view.findViewById(android.R.id.text1);

                Animal zwierz = db.pobierz(Integer.parseInt(name.getText().toString()));

                Intent intencja = new Intent(getApplicationContext(), DodajWpis.class);
                intencja.putExtra("element", zwierz);
                startActivityForResult(intencja, 2);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public void nowyWpis(MenuItem mi){
        Intent intencja = new Intent(this, DodajWpis.class);
        startActivityForResult(intencja, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==1 && resultCode == RESULT_OK)
        {
            Bundle extras = data.getExtras();
            Animal nowy = (Animal)
            extras.getSerializable("nowy");

            this.db.dodaj(nowy);

            adapter.changeCursor(db.lista());
            adapter.notifyDataSetChanged();

            return;
        }

        if (requestCode==2 && resultCode == RESULT_OK)
        {
            Bundle extras = data.getExtras();
            Animal nowy = (Animal)
                    extras.getSerializable("nowy");

            this.db.aktualizuj(nowy);

            adapter.changeCursor(db.lista());
            adapter.notifyDataSetChanged();

            return;
        }
    }
}