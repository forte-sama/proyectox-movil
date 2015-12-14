package software.proyecto.proyectox;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

public class FilaDetalleActivity extends AppCompatActivity  implements View.OnClickListener {


    Button time;
    EditText latitudo;
    EditText longitudo;
    EditText latitudd;
    EditText longitudd;
    TextView resultados;
    obtenerWebServices hiloconexion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fila_detalle);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



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
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            case R.id.button:
                hiloconexion = new obtenerWebServices();
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
}
