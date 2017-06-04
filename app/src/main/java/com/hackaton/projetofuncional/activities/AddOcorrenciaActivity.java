package com.hackaton.projetofuncional.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.hackaton.projetofuncional.R;

public class AddOcorrenciaActivity extends AppCompatActivity {
    private String[] tipos = new String[]{"Aluminação", "Elétrico", "Encanação", "Deteriorização de vias"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ocorrencia);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        ArrayAdapter<String> gameKindArray = new ArrayAdapter<String>(AddOcorrenciaActivity.this,
                android.R.layout.simple_spinner_item, tipos);
        gameKindArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = (Spinner) findViewById(R.id.spinnerTipo);
        spinner.setAdapter(gameKindArray);
    }

    public void fecharTelaAddOcorrencia(View view) {
        this.finish();
    }
}
