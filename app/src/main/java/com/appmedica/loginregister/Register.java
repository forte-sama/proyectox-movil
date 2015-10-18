package com.appmedica.loginregister;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity implements View.OnClickListener{

    Button bRegistrar;
    EditText etNombre, etApellido, etEdad, etUsername, etPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etNombre = (EditText) findViewById(R.id.etNombre);
        etApellido =(EditText) findViewById(R.id.etApellido);
        etEdad=(EditText) findViewById(R.id.etEdad);
        etUsername=(EditText)findViewById(R.id.etUsername);
        etPassword =(EditText) findViewById(R.id.etPassword);

        bRegistrar = (Button) findViewById(R.id.bRegistrar);

        bRegistrar.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.bRegistrar:
                break;
        }
    }
}
