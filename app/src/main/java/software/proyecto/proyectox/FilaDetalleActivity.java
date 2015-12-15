package software.proyecto.proyectox;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import modelos.MensajeServer;
import utilidades.API;

public class FilaDetalleActivity extends AppCompatActivity  implements View.OnClickListener {


    Button time;
    EditText latitudo;
    EditText longitudo;
    EditText latitudd;
    EditText longitudd;
    TextView resultados,tvDoctor,tvTitulo,tvNombreAsistente,tvHoraEntrada,tvTiempoEstimado,tvTurnosRestantes,tvMensaje;


    ImageView semaforo;
    Button btSalir;
    String codFila;

    obtenerWebServices hiloconexion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        setContentView(R.layout.activity_fila_detalle);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_turno);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);


        tvDoctor = (TextView) findViewById(R.id.tvDoctor_AFD);
        tvTitulo =(TextView)findViewById(R.id.tvPediatra_AFD);
        tvNombreAsistente=(TextView)findViewById(R.id.tvAsistente_AFD);
        tvHoraEntrada=(TextView)findViewById(R.id.tvHora_llegada_AFD);
        tvTiempoEstimado=(TextView)findViewById(R.id.tvTiempo_Estimado_AFD);
        tvTurnosRestantes=(TextView)findViewById(R.id.tvTurnos_Restantes_AFD);
        btSalir = (Button) findViewById(R.id.btSalir_AFD);
        semaforo = (ImageView) findViewById(R.id.imgSemaforo_AFD);
        tvMensaje = (TextView) findViewById(R.id.tvMsj_AFD);
        int tiempo = Integer.parseInt(bundle.getString("tiempo_estimado"))/60;
        if(tiempo < 60){
            if(tiempo<10){
                semaforo.setImageResource(R.drawable.rojo);
                tvMensaje.setText("Su turno está muy próximo.\n Se recomienda su presencia en el consultorio.");
            }
            else{
                tvMensaje.setText("Su turno está cercano.\t        ");
                semaforo.setImageResource(R.drawable.amarillo);
            }
        }
        else{
            tvMensaje.setText("Su turno no llegará por ahora. Tiene tiempo para otras diligencias.");
            semaforo.setImageResource(R.drawable.verde);
        }

        tvDoctor.setText(bundle.getString("nombre_doctor"));
        tvTitulo.setText(bundle.getString("titulo_doctor"));
        tvNombreAsistente.setText(bundle.getString("nombre_asistente"));
        tvHoraEntrada.setText(bundle.getString("hora_llegada"));
        tvTiempoEstimado.setText(bundle.getString("tiempo_estimado_display"));
        tvTurnosRestantes.setText(bundle.getString("turnos_restantes"));
        codFila = bundle.getString("cod_fila");

        btSalir.setOnClickListener(this);



        //  time = (Button)findViewById(R.id.button);
        // latitudo = (EditText)findViewById(R.id.lato);
//        longitudo =(EditText)findViewById(R.id.lono);
//        latitudd = (EditText)findViewById(R.id.latd);
//        longitudd =(EditText)findViewById(R.id.lond);
//        resultados = (TextView)findViewById(R.id.resultados);

        //time.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btSalir_AFD:
                Asincrono asinc = new Asincrono(this);
                asinc.execute(codFila);
                //hiloconexion = new obtenerWebServices();
                //   hiloconexion.execute(latitudo.getText().toString(),longitudo.getText().toString(),latitudd.getText().toString(),longitudd.getText().toString());
                break;
            default:
                break;


        }
    }




    public class obtenerWebServices extends AsyncTask<String,Integer,String> {


        @Override
        protected String doInBackground(String... params) {

            String cadena = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=";
            //https://maps.googleapis.com/maps/api/directions/json?origin=Toronto&destination=Montreal&key=AIzaSyDjCGHpzvqyUIzAG-QtBlBLTlicAR5ZWjs
            //   https://maps.googleapis.com/maps/api/distancematrix/json?origins=19.44783,-70.68325&destinations=19.45096,-70.69472
            cadena = cadena + params[0];
            cadena = cadena+ ",";
            cadena = cadena+params[1];
            cadena = cadena + "&destinations=";
            cadena = cadena+params[2];
            cadena = cadena+ ",";
            cadena = cadena+params[3];
            //Log.d("cadena",cadena);

            URL url = null;
            String devuelve="";
            try {
                url = new URL(cadena);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();


                connection.setRequestProperty("User-Agent", "Mozilla/5.0"+ "(Linux; Android 1.5; es-ES) Ejemplo HTTP");
                int resultados = connection.getResponseCode();

                StringBuilder result = new StringBuilder();

                if(resultados==HttpURLConnection.HTTP_OK)
                {
                    InputStream in = new BufferedInputStream(connection.getInputStream());
                    BufferedReader reader  = new BufferedReader(new InputStreamReader(in));

                    String line;

                    while((line=reader.readLine())!=null)
                    {
                        result.append(line);
                    }

                    JSONObject respuestaJSON =new JSONObject(result.toString());

                    JSONArray resultJSON = respuestaJSON.getJSONArray("rows");


                    int direccion = 0;

                    //String dire="";

                    if(resultJSON.length()>0)
                    {

                        direccion = resultJSON.getJSONObject(0).getJSONArray("elements").getJSONObject(0).getJSONObject("duration").getInt("value");

                    }
                    devuelve = "Direccion:"+ direccion;

                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return devuelve;
        }

        @Override
        protected void onPreExecute() {
            resultados.setText("");
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String aVoid) {
            resultados.setText(aVoid);
            //super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(String aVoid) {
            super.onCancelled(aVoid);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }
    private class Asincrono extends AsyncTask<String,Void, MensajeServer>
    {
        private FilaDetalleActivity contexto;
        ProgressDialog progressDialog;
        public Asincrono(FilaDetalleActivity contexto)
        {
            super();
            this.contexto = contexto;
        }

        @Override
        protected void onPreExecute()
        {
            progressDialog = ProgressDialog.show(contexto, "", "Procesando solicitud... ");
        }

        @Override
        protected MensajeServer doInBackground(String... params)
        {

            MensajeServer resultado= API.getInstance().request_salida(params[0]);
            return resultado;

        }

        @Override
        protected void onPostExecute(MensajeServer mensajeServer)
        {
            super.onPostExecute(mensajeServer);
            progressDialog.dismiss();
            String mensaje;
            if (mensajeServer.cod_error==0){
                mensaje = "Se ha salido de la fila exitosamente de la fila.";
                contexto.startActivity(new Intent(contexto, FilasActivity.class));
                AlertDialog.Builder builder = new AlertDialog.Builder(contexto);

                builder.setTitle("") //
                        .setMessage(mensaje) //
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // TODO
                                dialog.dismiss();
                            }
                        }) ;

                builder.show();
                contexto.finish();

            }
            else{
                mensaje = "Hubo un error saliendo de la fila.";
                contexto.startActivity(new Intent(contexto, FilasActivity.class));
                AlertDialog.Builder builder = new AlertDialog.Builder(contexto);
                builder.setTitle("") //
                        .setMessage(mensaje) //
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // TODO
                                dialog.dismiss();
                            }
                        }) ;

                builder.show();

            }
        }
    }
}
