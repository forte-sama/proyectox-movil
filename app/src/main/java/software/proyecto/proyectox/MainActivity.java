
package software.proyecto.proyectox;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import java.util.concurrent.CountDownLatch;

import modelos.MensajeServer;
import utilidades.API;
import utilidades.Validacion;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String PREFERENCIAS = "session" ;
    public static final String USUARIO = "username";
    SharedPreferences sharedpreferences;
    Button btnIngresar;
    EditText etUsuario,etContra;
    TextView tvRegistrar;
    Button bMapa;
    ProgressDialog progressDialog;
    Validacion validacion = Validacion.getInstance();
    API api = API.getInstance();
    CountDownLatch latch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedpreferences = getSharedPreferences(PREFERENCIAS, Context.MODE_PRIVATE);
        if (!sharedpreferences.getString(USUARIO,"").equals(""))
        {
            startActivity(new Intent(this, FilasActivity.class));
            finish();
        }

        Log.d("jesus","No preferences");
        Log.d("jesus",sharedpreferences.getString(USUARIO,""));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUsuario = (EditText) findViewById(R.id.etUsuario_AMain);
        etContra = (EditText)findViewById(R.id.etContra_AMain);
        tvRegistrar = (TextView) findViewById(R.id.tvRegistro_AMain);

        btnIngresar = (Button) findViewById(R.id.bLogin_AMain);
        bMapa = (Button) findViewById(R.id.bMapa);
        bMapa.setOnClickListener(this);
        btnIngresar.setOnClickListener(this);
        tvRegistrar.setOnClickListener(this);



    }

    private  void btnRegistrarHandler(){

        if (!camposEstanValidos())
            return;
        else
        {
            Asincrono asinc = new Asincrono(this);
            asinc.execute(etUsuario.getText().toString(), etContra.getText().toString());
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bLogin_AMain:
                btnRegistrarHandler();
                break;
            case R.id.tvRegistro_AMain:
                startActivity(new Intent(this,Register.class));
                break;
            case R.id.bMapa:
                startActivity(new Intent(this,Maps.class));
                break;
        }
    }

    private boolean camposEstanValidos(){

        boolean ret = true;

        if (!validacion.esPassValido(this.etContra))
            ret = false;
        if (!validacion.tieneTexto(this.etUsuario))
            ret = false;

        return ret;
    }
    private class Asincrono extends AsyncTask<String,Void,MensajeServer>
    {
        private MainActivity contexto;
        ProgressDialog progressDialog;
        public Asincrono(MainActivity contexto)
        {
            super();
            this.contexto = contexto;
        }

        @Override
        protected void onPreExecute()
        {
            progressDialog = ProgressDialog.show(contexto, "", "Iniciando sesion... ");
        }

        @Override
        protected MensajeServer doInBackground(String... params)
        {

            MensajeServer resultado=API.getInstance().request_login(params[0], params[1]);

            return resultado;

        }

        @Override
        protected void onPostExecute(MensajeServer mensajeServer)
        {
            super.onPostExecute(mensajeServer);
            progressDialog.dismiss();

            if (mensajeServer.cod_error==0){
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(USUARIO, contexto.etUsuario.getText().toString());
                editor.commit();
                contexto.startActivity(new Intent(contexto, PrincipalActivity.class));

            }
            else{
                Toast.makeText(contexto, mensajeServer.mensaje, Toast.LENGTH_SHORT).show();
            }
        }
    }
}


