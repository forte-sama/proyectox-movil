package com.appmedica.loginregister.actividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.appmedica.loginregister.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;

public class Login extends AppCompatActivity implements View.OnClickListener{


    GoogleMap googleMap;
    MapView mapView;


    @Override
    protected void onResume()
    {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        mapView.onDestroy();

    }


    @Override

    protected void onPause()
    {
        super.onPause();
        mapView.onPause();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mapView=(MapView)findViewById(R.id.Mapa);
        mapView.onCreate(savedInstanceState);

        googleMap=mapView.getMap();
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.setMyLocationEnabled(true);




       // bLogin.setOnClickListener(this);
       // tvRegistrarseLink.setOnClickListener(this);





    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {

        }
    }
}
