package com.appmedica.loginregister.actividades;

import android.app.DatePickerDialog;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.appmedica.loginregister.R;

import java.io.IOException;
import java.util.Calendar;

import utilidades.API;
import utilidades.Validacion;

public class Register extends AppCompatActivity implements View.OnClickListener{

    Button bFecha;
    EditText etNombre, etApellido, etFecha, etTelefono, etUsuario, etContras, etCorreo;
    RadioGroup rgGrupo;
    Spinner spinner;
    Button btnRegistrar;
    String[] tiposSangre = {"O+", "O-", "A+","A-","B+","B-","AB+","AB-"};
    API api = API.getInstance();
    Validacion validacion = Validacion.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("Registro de Usuario");
        etNombre = (EditText) findViewById(R.id.etNombre_AReg);
        etApellido =(EditText) findViewById(R.id.etApellido_AReg);
        etFecha = (EditText) findViewById(R.id.etFecha_AReg);
        etTelefono = (EditText) findViewById(R.id.etTelefono_AReg);
        etFecha.setOnClickListener(this);
        etUsuario = (EditText) findViewById(R.id.etNombreUsuario_AReg);
        etContras = (EditText) findViewById(R.id.etContra_AReg);
        etCorreo = (EditText) findViewById(R.id.etCorreo_AReg);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar_AReg);
        btnRegistrar.setOnClickListener(this);
        RadioButton rb = (RadioButton)findViewById(R.id.rdbMasculino_AReg);
        rb.setChecked(true);
        rgGrupo = (RadioGroup) findViewById(R.id.radioGroup_AReg);

        ArrayAdapter<String> stringArrayAdapter= new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_dropdown_item,
                        tiposSangre);
        spinner = (Spinner) findViewById(R.id.spinner_AReg);
        spinner.setAdapter(stringArrayAdapter);

        etCorreo = (EditText) findViewById(R.id.etCorreo_AReg);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        etUsuario.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                validacion.esUsuarioValido(etUsuario,true);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        etContras.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                validacion.esPassValido(etContras);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        etNombre.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                validacion.esNombreValido(etNombre,true);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        etApellido.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                validacion.esNombreValido(etApellido, true);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        etTelefono.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                validacion.esTelefonoValido(etTelefono,true);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        etCorreo.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                validacion.esCorreoValido(etCorreo, true);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.etFecha_AReg:
                btnFechaHandler();
                break;
            case R.id.btnRegistrar_AReg:
                btnRegistrarHandler();
                break;


        }


    }


    private  void btnRegistrarHandler(){
        if (!camposEstanValidos())
        {
            return;
        }
        else
        {
            int radioButtonID = rgGrupo.getCheckedRadioButtonId();
            RadioButton radioButton = (RadioButton) rgGrupo.findViewById(radioButtonID);
            String sexo;
            if (radioButton.getText().toString().equals("Masculino"))
                sexo = "M";
            else
                sexo = "F";
            try
            {
                api.request_registro(etUsuario.getText().toString(), etContras.getText().toString(),
                        etTelefono.getText().toString(), etCorreo.getText().toString(),
                        etNombre.getText().toString(), etApellido.getText().toString(),
                        sexo, etFecha.getText().toString(),
                        spinner.getSelectedItem().toString()
                );

            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private boolean camposEstanValidos(){
        int duracion = Toast.LENGTH_SHORT;
        boolean ret = true;
        Toast toast = Toast.makeText(getApplicationContext(), "Revise los campos invalidos o llene los que estan vacios.", duracion);
        if (!validacion.esCorreoValido(etCorreo, true))
        {

            toast.show();
            ret = false;
        }
        if (!validacion.esNombreValido(etNombre, true))
        {
            toast.show();
            ret = false;
        }
        if (!validacion.esNombreValido(etApellido,true))
        {
            toast.show();
            ret = false;
        }
        if (!validacion.esTelefonoValido(etTelefono, true))
        {
            toast.show();
            ret = false;
        }
        if (!validacion.esUsuarioValido(etUsuario, true))
        {
            toast.show();
            ret = false;
        }
        if (!validacion.esFechaValida(etFecha))
        {
            toast.show();
            ret = false;
        }
        if (!validacion.esFechaValida(etFecha))
        {
            toast.show();
            ret = false;
        }
        if (!validacion.esPassValido(etContras))
        {
            toast.show();
            ret = false;
        }


        return ret;
    }

    private void btnFechaHandler(){

        final Calendar c = Calendar.getInstance();
        int anio = c.get(Calendar.YEAR) - 20;
        int mes= c.get(Calendar.MONTH);
        int dia = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // Setear valor en editText
                        etFecha.setText((monthOfYear + 1)+ "-"
                                + dayOfMonth  + "-" + year);
                        etFecha.setError(null);
                    }
                }, anio, mes, dia);
        dpd.show();



    }




}


