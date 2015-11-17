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

    Button btnIngresar;
    EditText etUsuario,etContra;
    TextView tvRegistrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        etUsuario = (EditText) findViewById(R.id.etUsuario_AMain);
        etContra = (EditText)findViewById(R.id.etContra_AMain);
        tvRegistrar = (TextView) findViewById(R.id.tvRegistro_AMain);

        btnIngresar = (Button) findViewById(R.id.bLogin_AMain);

        btnIngresar.setOnClickListener(this);
        tvRegistrar.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bLogin_AMain:
               // startActivity(new Intent(this,Register.class));
                break;
            case R.id.tvRegistro_AMain:
                startActivity(new Intent(this,Register.class));
                break;
        }
    }
}
