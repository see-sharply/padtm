package edu.pawm.lab3;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.MediaStore;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import edu.pawm.lab3.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    final static int REQUEST_IMAGE_CAPTURE = 1;

    private void showText(String text){
        Context context = getApplicationContext();

        Toast toast = Toast.makeText(
                context,
                text,
                Toast.LENGTH_SHORT);

        toast.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showText("Kliknięto przycisk FAB");
            }
        });
    }

    public void kliknij(View view){
        showText("Kliknięto przycisk Button");

        Intent intencja = new Intent(this, LoginActivity.class);
        startActivity(intencja);
    }

    public void imageCapture(View view){
        try {
            Intent intencja = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intencja, REQUEST_IMAGE_CAPTURE);
        }
        catch (Exception e)
        {
            showText(e.getMessage());
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bundle extras = data.getExtras();
        Bitmap imageBitmap = (Bitmap) extras.get("data");
        LinearLayout lay = (LinearLayout)(findViewById(R.id.cont));
        lay.setBackground(new BitmapDrawable(getResources(), imageBitmap));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id)
        {
            case R.id.action_settings1:
                showText(String.valueOf(R.id.action_settings1));
                break;
            case R.id.action_settings2:
                showText(String.valueOf(R.id.action_settings2));
                break;
            case R.id.action_settings3:
                showText(String.valueOf(R.id.action_settings3));
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}