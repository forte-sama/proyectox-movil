package software.proyecto.proyectox;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import modelos.MensajeServer;
import utilidades.API;
import utilidades.Validacion;

public class EdicionActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etTelefono, etContrasActual, etCorreo, etContrasNueva,  etContraRep;
    Button btnRegistrar;
    SharedPreferences sharedpreferences;
    String nombreUsuario;
    public static final String PREFERENCIAS = "session" ;
    public static final String USER = "username";
    Validacion validacion = Validacion.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedpreferences = getSharedPreferences(PREFERENCIAS, Context.MODE_PRIVATE);
        setContentView(R.layout.activity_edicion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_edicion);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        nombreUsuario = sharedpreferences.getString(USER, "");
        Log.d("jesus",nombreUsuario);
        etTelefono= (EditText)findViewById(R.id.etTelefono_AEdi);

        etContrasActual=(EditText)findViewById(R.id.etContraActual_AEdi);
        etContrasNueva=(EditText)findViewById(R.id.etContraNueva_AEdi);
        etContraRep =(EditText)findViewById(R.id.etContraRep_AEdi);
        etCorreo=(EditText)findViewById(R.id.etCorreo_AEdi);
        btnRegistrar = (Button)findViewById(R.id.btnGuardar_AEdi);
        btnRegistrar.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {

            case R.id.btnGuardar_AEdi:
                if(camposEstanValidos()){
                    Asincrono asinc = new Asincrono(this);
                    asinc.execute(nombreUsuario, etContrasActual.getText().toString(),
                            etTelefono.getText().toString(), etCorreo.getText().toString(),
                            etContrasNueva.getText().toString());
                }
                break;
            default:
        }

    }
    private class Asincrono extends AsyncTask<String,Void,MensajeServer>
    {
        private EdicionActivity contexto;
        ProgressDialog progressDialog;
        public Asincrono(EdicionActivity contexto) {
            super();
            this.contexto = contexto;
        }

        @Override
        protected void onPreExecute()
        {
            progressDialog = ProgressDialog.show(contexto, "", "Efectuando cambios... ");
        }

        @Override
        protected MensajeServer doInBackground(String... params)
        {

            MensajeServer resultado = API.getInstance().request_edicion(params[0], params[1],params[2],params[3],
                    params[4]);
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
                    finish();
                    break;
                case 3:
                    contexto.etCorreo.setError("Alguien se ha registrado utilizando este correo.");
                    break;
                case 1:
                    contexto.etContrasActual.setError("Contraseña incorrecta.");
                    break;

                case 6:
                    contexto.etTelefono.setError("Alguien se ha registrado utilizando este teléfono.");
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

        if (!validacion.esTelefonoValido(etTelefono, true))
        {
            toast.show();
            ret = false;
        }
        if(!etContrasNueva.getText().toString().equals(etContraRep.getText().toString())){
            toast.show();
            ret = false;
            etContraRep.setError("Las contraseñas deben de coincidir.");
        }
        if (!validacion.esPassValido(etContrasNueva))
        {
            toast.show();
            ret = false;
        }


        return ret;
    }

}
