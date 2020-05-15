package com.example.projetandroid.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetandroid.R;
import com.example.projetandroid.presentation.Singletons;
import com.example.projetandroid.presentation.controller.DetailController;
import com.example.projetandroid.presentation.model.Fact;


public class Detail extends AppCompatActivity {

    private Fact fact;
    private TextView Txt_Fact;
    private TextView Title;
    private DetailController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        controller = new DetailController(this, Singletons.getSharedPreferencesInstance(this));
        controller.onStart();
    }

    public void showFact(String symbol) {
        Title = findViewById(R.id.Title);
        Title.setText(symbol);
        Txt_Fact = findViewById(R.id.Txt_Fact);
        fact = controller.getAFact(symbol);
        Txt_Fact.setText(fact.getFact());
    }



    private void showAPIError() {
        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_LONG).show();
    }
}

