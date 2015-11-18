package com.appmedica.loginregister.actividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.appmedica.loginregister.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button bLogout;
    EditText etNombre;
    Button bMapa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        etNombre = (EditText) findViewById(R.id.etNombre_AReg);



        bLogout = (Button) findViewById(R.id.bLogout);
        bMapa =(Button) findViewById(R.id.bMapa);

        bMapa.setOnClickListener(this);
        bLogout.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bLogout:
                startActivity(new Intent(this,Register.class));
                break;
            case R.id.bMapa:
                startActivity(new Intent(this,Login.class));
                break;
        }
    }
}
