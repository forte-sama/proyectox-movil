package software.proyecto.proyectox;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import modelos.Fila;
import utilidades.API;
import utilidades.FilasListAdapter;

public class FilasFragment extends Fragment {
    private ProgressDialog pDialog;
    private List<Fila> filaList = new ArrayList<Fila>();
    private ListView listView;
    private TextView noFilas;


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
        noFilas = (TextView) rootView.findViewById(R.id.noFilas_FF);
        noFilas.setAlpha(0.0f);


        Asincrono asinc = new Asincrono(this,filaList);
        asinc.execute("jozu789");




        return rootView;

    }


private class Asincrono extends AsyncTask<String,Void,List<Fila>>
    {
        private FilasFragment contexto;
        ProgressDialog progressDialog;
        List<Fila> filas;
        FilasListAdapter adapter;
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


            List<Fila> filasResul;
            filasResul = API.getInstance().request_filas(params[0]);
            if(filasResul.get(0).getEstado_peticion().equals("0")){
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        noFilas.setAlpha(0.0f);
                    }
                });
                filaList=filasResul;
                Log.d("jesus2", filaList.get(0).getTitulo_doctor());
            }
            else{
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        noFilas.setAlpha(1.0f);
                    }
                });
            }

            return filas;

        }

        @Override
        protected void onPostExecute(List<Fila> mensajeServer)
        {
            super.onPostExecute(mensajeServer);
            adapter = new FilasListAdapter(getActivity(), filaList);

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