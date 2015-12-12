package software.proyecto.proyectox;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import modelos.Fila;
import modelos.MensajeServer;
import utilidades.API;
import utilidades.CustomListAdapter;

import static com.google.android.gms.internal.zzip.runOnUiThread;

public class FilasFragment extends Fragment {
    private ProgressDialog pDialog;
    private List<Fila> filaList = new ArrayList<Fila>();
    private ListView listView;


    private ListView listaFilas;

    public static FilasFragment newInstance() {
        FilasFragment fragment = new FilasFragment();
        return fragment;
    }

    public FilasFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_filas, container, false);
        listView = (ListView) rootView.findViewById(R.id.lista_FF);



        Asincrono asinc = new Asincrono(this,filaList);
        asinc.execute("cesarito");




        return rootView;

    }
    private class Asincrono extends AsyncTask<String,Void,List<Fila>>
    {
        private FilasFragment contexto;
        ProgressDialog progressDialog;
        List<Fila> filas;
        CustomListAdapter adapter;
        public Asincrono(FilasFragment contexto,List<Fila> filas)
        {
            super();
            this.contexto = contexto;
            this.filas = filas;
        }

        @Override
        protected void onPreExecute()
        {
            progressDialog = ProgressDialog.show(contexto.getActivity(), "", "Buscando filas... ");
        }


        @Override
        protected List<Fila> doInBackground(String... params)
        {
            filaList = API.getInstance().request_filas(params[0]);
            Log.d("jesus2", filaList.get(0).getTitulo_doctor());


            return filas;

        }

        @Override
        protected void onPostExecute(List<Fila> mensajeServer)
        {
            super.onPostExecute(mensajeServer);
            adapter = new CustomListAdapter(getActivity(), filaList);
            Log.d("jesus2", filaList.get(0).getTitulo_doctor());
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            for (Fila f : filaList){
               Contador con = new Contador(Integer.parseInt(f.getTiempo_estimado())*1000,1000,f);
               con.start();

            }


            progressDialog.dismiss();


        }

        private class Contador extends CountDownTimer {


            private Fila fila;
            public Contador(long millisInFuture, long countDownInterval,Fila f) {
                super(millisInFuture, countDownInterval);
                this.fila = f;
            }

            public void onTick(long millisUntilFinished) {

            String tiempoRestante;
            if (TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)<60){
                tiempoRestante = String.format("%2d minutos",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
                );
            }
            else{
                tiempoRestante = String.format("%2d horas, %02d min",
                        TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) -
                                TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished))
                );
            }
            Log.d("jesus",fila.getTiempo_estimado());
                fila.setTiempo_estimado_display(tiempoRestante);
                fila.setTiempo_estimado(String.valueOf(millisUntilFinished/1000));
            adapter.notifyDataSetChanged();

        }

        public void onFinish() {
            fila.setTiempo_estimado_display("0 minutos");
            fila.setTiempo_estimado("0");
        }
    }
    }
}