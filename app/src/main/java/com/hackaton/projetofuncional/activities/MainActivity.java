package com.hackaton.projetofuncional.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hackaton.projetofuncional.R;
import com.hackaton.projetofuncional.actionbar.ActionBarManager;
import com.hackaton.projetofuncional.entities.Ocorrencia;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private ActionBarManager actionBar;
    public static GoogleMap mMap;
    public static Location location;


    private String TAG = "MATEUS";
    public static GoogleApiClient mGoogleApiClient;
    public static ArrayList<Ocorrencia> arrDados = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        actionBar = new ActionBarManager(this, toolbar);


        callConnection();
        arrDados = criarDadosFalsos();

        ImageView im1 = (ImageView) findViewById(R.id.iv_denuncia);
        ImageView im2 = (ImageView) findViewById(R.id.iv_projatv);
        ImageView im3 = (ImageView) findViewById(R.id.iv_projfut);
        ImageView im4 = (ImageView) findViewById(R.id.iv_voce);

        im1.setBackground(new ColorDrawable(0xfff44336));
        im2.setBackground(new ColorDrawable(0xffffeb3b));
        im3.setBackground(new ColorDrawable(0xff64dd17));
        im4.setBackground(new ColorDrawable(0xff4527a0));


    }

    public ArrayList<Ocorrencia> criarDadosFalsos() {
        ArrayList<Ocorrencia> arr = new ArrayList<>();
        int i = 1, d = 1;
        arr.add(new Ocorrencia("Joao Antonio" + (++i), "04/06/2017 01:25", "Realmente, isso tá causando um transtorno pra sociedade" + (++d), 150, 2, -8.877379, -36.4678918, 0, 0));
        arr.add(new Ocorrencia("Joao Antonio" + (++i), "04/06/2017 01:30", "Realmente, isso tá causando um transtorno pra sociedade" + (++d), 150, 2, -8.877014, -36.4664424, 0, 1));
        arr.add(new Ocorrencia("Joao Antonio" + (++i), "04/06/2017 01:35", "Realmente, isso tá causando um transtorno pra sociedade" + (++d), 150, 2, -8.8855002, -36.4689299, 1, 10));
        arr.add(new Ocorrencia("Joao Antonio" + (++i), "04/06/2017 01:45", "Realmente, isso tá causando um transtorno pra sociedade" + (++d), 150, 2, -8.9129116, -36.4974487, 1, 10));
        arr.add(new Ocorrencia("Joao Antonio" + (++i), "04/06/2017 01:50", "Realmente, isso tá causando um transtorno pra sociedade" + (++d), 150, 2, -8.8890677, -36.4926003, 2, 11));
        arr.add(new Ocorrencia("Joao Antonio" + (++i), "04/06/2017 01:55", "Realmente, isso tá causando um transtorno pra sociedade" + (++d), 150, 2, -8.8819978, -36.499972, 2, 11));
        arr.add(new Ocorrencia("Joao Antonio" + (++i), "04/06/2017 01:56", "Realmente, isso tá causando um transtorno pra sociedade" + (++d), 150, 2, -8.8837826, -36.4936495, 0, 2));
        arr.add(new Ocorrencia("Joao Antonio" + (++i), "04/06/2017 01:56", "Realmente, isso tá causando um transtorno pra sociedade" + (++d), 150, 2, -8.8814267, -36.4880738, 0, 3));
        arr.add(new Ocorrencia("Joao Antonio" + (++i), "04/06/2017 01:56", "Realmente, isso tá causando um transtorno pra sociedade" + (++d), 150, 2, -8.8831282, -36.482498, 0, 2));
        arr.add(new Ocorrencia("Joao Antonio" + (++i), "04/06/2017 01:56", "Realmente, isso tá causando um transtorno pra sociedade" + (++d), 150, 2, -8.8843042, -36.4740047, 0, 1));
        arr.add(new Ocorrencia("Joao Antonio" + (++i), "04/06/2017 01:56", "Realmente, isso tá causando um transtorno pra sociedade" + (++d), 150, 2, -8.8836144, -36.4761215, 1, 10));
        arr.add(new Ocorrencia("Joao Antonio" + (++i), "04/06/2017 01:56", "Realmente, isso tá causando um transtorno pra sociedade" + (++d), 150, 2, -8.882844, -36.4752202, 1, 10));
        arr.add(new Ocorrencia("Joao Antonio" + (++i), "04/06/2017 01:56", "Realmente, isso tá causando um transtorno pra sociedade" + (++d), 150, 2, -8.8835343, -36.479524, 2, 11));
        arr.add(new Ocorrencia("Joao Antonio" + (++i), "04/06/2017 01:56", "Realmente, isso tá causando um transtorno pra sociedade" + (++d), 150, 2, -8.882944, -36.4786734, 2, 11));
        arr.add(new Ocorrencia("Joao Antonio" + (++i), "04/06/2017 01:56", "Realmente, isso tá causando um transtorno pra sociedade" + (++d), 150, 2, -8.881033, -36.4779645, 0, 2));
        arr.add(new Ocorrencia("Joao Antonio" + (++i), "04/06/2017 01:56", "Realmente, isso tá causando um transtorno pra sociedade" + (++d), 150, 2, -8.8830741, -36.4762633, 0, 3));


        return arr;
    }

    public void repopulate(int tipo) {
        /*if (drawerItem.getIdentifier() == 1) {//todos
        } else if (drawerItem.getIdentifier() == 2) {//denuncias
        } else if (drawerItem.getIdentifier() == 3) {//projetos futuros
        } else if (drawerItem.getIdentifier() == 4) {//projetos ativos
        }*/
        mMap.clear();


        marcarLugar(location.getLatitude(), location.getLongitude(), false);

        for (Ocorrencia in : arrDados) {
            if (tipo == 1) {//todos
                LatLng sydney = new LatLng(in.lat, in.longe);
                if (in.tipoVisualizacao == 0) {
                    mMap.addMarker(new MarkerOptions().position(sydney).title(in.descricao)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                } else if (in.tipoVisualizacao == 1) {
                    mMap.addMarker(new MarkerOptions().position(sydney).title(in.descricao)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
                } else if (in.tipoVisualizacao == 2) {
                    mMap.addMarker(new MarkerOptions().position(sydney).title(in.descricao)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                }
            } else if (tipo == 2 && in.tipoVisualizacao == 0) {//denuncias
                LatLng sydney = new LatLng(in.lat, in.longe);
                mMap.addMarker(new MarkerOptions().position(sydney).title(in.descricao)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            } else if (tipo == 3 && in.tipoVisualizacao == 1) {//projetos futuros
                LatLng sydney = new LatLng(in.lat, in.longe);
                mMap.addMarker(new MarkerOptions().position(sydney).title(in.descricao)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
            } else if (tipo == 4 && in.tipoVisualizacao == 2) {//projetos ativos
                LatLng sydney = new LatLng(in.lat, in.longe);
                mMap.addMarker(new MarkerOptions().position(sydney).title(in.descricao)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
            }


        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.clear();


        for (Ocorrencia in : arrDados) {
            LatLng sydney = new LatLng(in.lat, in.longe);
            if (in.tipoVisualizacao == 0) {
                mMap.addMarker(new MarkerOptions().position(sydney).title(in.descricao)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
            } else if (in.tipoVisualizacao == 1) {
                mMap.addMarker(new MarkerOptions().position(sydney).title(in.descricao)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
            } else if (in.tipoVisualizacao == 2) {
                mMap.addMarker(new MarkerOptions().position(sydney).title(in.descricao)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
            }

        }

        GoogleMap.OnMarkerClickListener aux = new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                for (int i = 0; i < arrDados.size(); i++) {
                    if (marker.getPosition().latitude == location.getLatitude() &&
                            marker.getPosition().longitude == location.getLongitude()) {
                        Toast.makeText(MainActivity.this, "Você!!", Toast.LENGTH_SHORT).show();
                        continue;
                    }
                    if (arrDados.get(i).lat == marker.getPosition().latitude
                            && arrDados.get(i).longe == marker.getPosition().longitude) {
                        OcorrenciaActivity.ocorrenciaId = i;


                        Intent intent = new Intent(MainActivity.this, OcorrenciaActivity.class);
                        startActivity(intent);

                        break;
                    }

                }


                return true;
            }
        };

        googleMap.setOnMarkerClickListener(aux);

    }

    private void marcarLugar(double a, double b, boolean c) {
        LatLng sydney = new LatLng(a, b);

        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        if (c) {
            mMap.addMarker(new MarkerOptions().position(sydney)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(a, b), 16.0f));
        } else {
            mMap.addMarker(new MarkerOptions().position(sydney)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
            //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(a, b), 16.0f));
        }

    }

    private synchronized void callConnection() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.i(TAG, "onConnected(" + bundle + ")");

        boolean flag1 = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;

        if (!flag1) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 200);
        }
        boolean flag2 = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;

        if (!flag2) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, 201);
        }

        //if (flag1 && flag2) {
        location = LocationServices
                .FusedLocationApi
                .getLastLocation(mGoogleApiClient);

        Log.i(TAG, "chegou");

        if (location != null) {
            Log.i(TAG, "latitude: " + location.getLatitude());
            Log.i(TAG, "longitude: " + location.getLongitude());
            //printar(location.getLatitude(), location.getLongitude());

            marcarLugar(location.getLatitude(), location.getLongitude(), true);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        /*if (grantResults != null)
            switch (requestCode) {
                case 200: {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        // {Some Code}
                    }
                    break;
                }
                case 201: {
                    Log.i(TAG, "-> TAMANHO: " + grantResults.length);
                    if (grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                        // {Some Code}
                    }
                    break;
                }
            }
            */
    }

    public void printar(double i, double x) {
        Toast.makeText(this, "Latitude: " + i + "\nLongitude: " + x, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "onConnectionSuspended(" + i + ")");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i(TAG, "onConnectionFailed(" + connectionResult + ")");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //Toast.makeText(this, "AAAAAAAEEEEEEE", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, AddOcorrenciaActivity.class);
            startActivity(intent);
            //this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
