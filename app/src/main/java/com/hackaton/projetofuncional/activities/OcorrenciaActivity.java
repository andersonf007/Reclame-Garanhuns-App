package com.hackaton.projetofuncional.activities;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hackaton.projetofuncional.R;
import com.hackaton.projetofuncional.entities.CustomAdapter;
import com.hackaton.projetofuncional.entities.DataModel;
import com.hackaton.projetofuncional.entities.Ocorrencia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class OcorrenciaActivity extends AppCompatActivity implements OnMapReadyCallback {

    public ArrayList<DataModel> dataModels;
    public ListView listView;
    public CustomAdapter adapter;

    public static GoogleMap mMap;

    public static int ocorrenciaId = 0;

    private String TAG = "MATEUS";
    public static GoogleApiClient mGoogleApiClient;

    TextView tvP, tvN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocorrencia);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        listView = (ListView) findViewById(R.id.list);

        dataModels = new ArrayList<>();

        dataModels.add(new DataModel("Apple Pie", "@ 30/01/2017", "Realmente! Esse é um buraco que afeta o trânsito dos veículos"));
        dataModels.add(new DataModel("Banana Bread", "@ 30/01/2017", "Fala sério, isso já deveria estar consertado!!!!!"));
        dataModels.add(new DataModel("Cupcake", "@ 30/01/2017", "Uma vergonha um problema na infraestrutura da cidade a tanto tempo ao léu!!!!"));
        dataModels.add(new DataModel("Donut", "@ 25/03/2017", "Prefeitura, hello!!!! Consertem isso!!!!"));
        dataModels.add(new DataModel("Eclair", "@ 30/03/2017", "5 meses e contando...."));
        dataModels.add(new DataModel("Froyo", "@ 30/04/2017", "Realmente! Esse é um buraco que afeta o trânsito dos veículos"));
        dataModels.add(new DataModel("Gingerbread", "@ 30/05/2017", "Fala sério, isso já deveria estar consertado!!!!!"));
        dataModels.add(new DataModel("Honeycomb", "@ 30/06/2017", "Prefeitura, hello!!!! Consertem isso!!!!"));
        dataModels.add(new DataModel("Ice Cream Sandwich", "@ 30/07/2017", "kkkkkkkkkkkkk essa prefeitura..."));
        dataModels.add(new DataModel("Jelly Bean", "@ 30/08/2017", "Que aplicativo top!!!!"));
        dataModels.add(new DataModel("Kitkat", "@ 30/09/2017", "Realmente! Esse é um buraco que afeta o trânsito dos veículos"));
        dataModels.add(new DataModel("Lollipop", "@ 30/10/2017", "Fala sério, isso já deveria estar consertado!!!!!"));
        dataModels.add(new DataModel("Marshmallow", "@ 30/11/2017", "Prefeitura, hello!!!! Consertem isso!!!!"));


        adapter = new CustomAdapter(dataModels, getApplicationContext());

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                DataModel dataModel = dataModels.get(position);

               /* Snackbar.make(view, dataModel.getName()+"\n"+dataModel.getType()+" API: "+dataModel.getVersion_number(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();*/
            }
        });

        TextView tv1 = (TextView) findViewById(R.id.tv_ov_username);
        tv1.setText(MainActivity.arrDados.get(ocorrenciaId).criador);
        TextView tv2 = (TextView) findViewById(R.id.tv_ov_data);
        tv2.setText(MainActivity.arrDados.get(ocorrenciaId).data);
        TextView tv3 = (TextView) findViewById(R.id.tv_ov_descricao);
        tv3.setText(MainActivity.arrDados.get(ocorrenciaId).descricao);


        tvP = (TextView) findViewById(R.id.tv_positivo);
        tvP.setText("" + MainActivity.arrDados.get(ocorrenciaId).positivos);

        tvN = (TextView) findViewById(R.id.tv_negativo);
        tvN.setText("" + MainActivity.arrDados.get(ocorrenciaId).negativos);


        this.setTitle(MainActivity.arrDados.get(ocorrenciaId).getTextoTipo());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ocorrencia, menu);
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.clear();

        mMap.getUiSettings().setAllGesturesEnabled(false);

        marcarLugar();
    }

    private void marcarLugar() {
        LatLng sydney = new LatLng(MainActivity.location.getLatitude(),
                MainActivity.location.getLongitude());
        mMap.addMarker(new MarkerOptions().position(sydney));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 16));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        /*mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(MainActivity.location.getLatitude()
                , MainActivity.location.getLongitude()), 14.0f));*/
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_reportar) {
            Toast.makeText(this, "Reportou como falso! Obrigado", Toast.LENGTH_SHORT).show();

        }

        return super.onOptionsItemSelected(item);
    }


    public void avaliarPositivo(View view) {
        Toast.makeText(this, "Avaliou Positivamente! Obrigado", Toast.LENGTH_SHORT).show();
        MainActivity.arrDados.get(ocorrenciaId).positivos++;

        tvP.setText("" + MainActivity.arrDados.get(ocorrenciaId).positivos);


    }

    public void avaliarNegativo(View view) {
        Toast.makeText(this, "Avaliou Negativamente! Obrigado", Toast.LENGTH_SHORT).show();

        MainActivity.arrDados.get(ocorrenciaId).negativos++;
        tvN.setText("" + MainActivity.arrDados.get(ocorrenciaId).negativos);
    }
}
