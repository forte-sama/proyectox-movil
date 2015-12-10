package software.proyecto.proyectox;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

import utilidades.API;
import utilidades.Validacion;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Looper;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;



import java.util.Calendar;

import modelos.MensajeServer;
import utilidades.API;
import utilidades.Validacion;

public class Register extends AppCompatActivity implements View.OnClickListener{

    EditText etNombre, etApellido, etFecha, etTelefono, etUsuario, etContras, etCorreo;
    RadioGroup rgGrupo;
    Spinner spinner;
    Button btnRegistrar;
    String[] tiposSangre = {"O+", "O-", "A+","A-","B+","B-","AB+","AB-"};
    API api = API.getInstance();
    Validacion validacion = Validacion.getInstance();
    Toolbar toolbar;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etNombre = (EditText) findViewById(R.id.etNombre_AReg);
        etApellido =(EditText) findViewById(R.id.etApellido_AReg);
        etFecha = (EditText) findViewById(R.id.etFecha_AReg);
        etTelefono = (EditText) findViewById(R.id.etTelefono_AReg);
        etFecha.setOnClickListener(this);
        etUsuario = (EditText) findViewById(R.id.etNombreUsuario_AReg);
        etContras = (EditText) findViewById(R.id.etContra_AReg);
        etCorreo = (EditText) findViewById(R.id.etCorreo_AReg);
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitle("Registro");
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

        etUsuario.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {

            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (!hasFocus)
                    validacion.esUsuarioValido(etUsuario, true);
            }
        });

        etContras.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {

            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (!hasFocus)
                    validacion.esPassValido(etContras);
            }
        });

        etNombre.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {

            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (!hasFocus)
                    validacion.esNombreValido(etNombre, true);
            }
        });

        etApellido.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {

            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (!hasFocus)
                    validacion.esNombreValido(etApellido, true);
            }
        });

        etTelefono.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {

            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (!hasFocus)
                    validacion.esTelefonoValido(etTelefono, true);
            }
        });

        etCorreo.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (!hasFocus)
                    validacion.esCorreoValido(etCorreo,true);
            }
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
            final String sexo;
            if (radioButton.getText().toString().equals("Masculino"))
                sexo = "M";
            else
                sexo = "F";
            Asincrono asinc = new Asincrono(this);
            asinc.execute(etUsuario.getText().toString(), etContras.getText().toString(),
                    etTelefono.getText().toString(), etCorreo.getText().toString(),
                    etNombre.getText().toString(), etApellido.getText().toString(),
                    sexo, etFecha.getText().toString(),
                    spinner.getSelectedItem().toString());

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
        if (!validacion.tieneTexto(etFecha))
        {
            toast.show();
            ret = false;
        }
        if (!validacion.tieneTexto(etFecha))
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

    private class Asincrono extends AsyncTask<String,Void,MensajeServer>
    {
        private Register contexto;
        ProgressDialog progressDialog;
        public Asincrono(Register contexto)
        {
            super();
            this.contexto = contexto;
        }

        @Override
        protected void onPreExecute()
        {
            progressDialog = ProgressDialog.show(contexto, "", "Registrando... ");
        }

        @Override
        protected MensajeServer doInBackground(String... params)
        {

            MensajeServer resultado=API.getInstance().request_registro(params[0], params[1],params[2],params[3],
                    params[4], params[5],params[6],params[7],
                    params[8]);
            return resultado;

        }

        @Override
        protected void onPostExecute(MensajeServer mensajeServer)
        {
            super.onPostExecute(mensajeServer);
            progressDialog.dismiss();

            switch(mensajeServer.cod_error)
            {
                case 0:
                    contexto.startActivity(new Intent(contexto, FilasActivity.class));
                    finish();
                    break;
                case 2:
                    contexto.etUsuario.setError("Este usuario ya existe.");
                    break;
                case 3:
                    contexto.etCorreo.setError("Alguien se ha registrado utilizando este correo.");
                    break;
                case 4:
                    contexto.etCorreo.setError("Alguien se ha registrado utilizando este correo.");
                    contexto.etUsuario.setError("Este usuario ya existe.");
                    break;
            }
        }
    }




}


