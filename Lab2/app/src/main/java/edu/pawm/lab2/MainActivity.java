package edu.pawm.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private void showMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showMessage("Metoda OnCreate");
    }

    //przywrócone po wstrzymaniu
    @Override
    protected void onResume() {
        super.onResume();
        showMessage("Metoda OnResume");
    }

    //wstrzymanie
    @Override
    protected void onPause() {
        super.onPause();
        showMessage("Metoda OnPause");
    }

    @Override
    protected void onStart() {
        super.onStart();
        showMessage("Metoda onStart");
    }

    //zamkniete
    @Override
    protected void onDestroy() {
        super.onDestroy();
        showMessage("metoda OnDestory");
    }
}