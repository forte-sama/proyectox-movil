package software.proyecto.proyectox;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
    SharedPreferences sharedpreferences;
    public static final String PREFERENCIAS = "session" ;
    public static final String USUARIO = "username";

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
        sharedpreferences = getActivity().getSharedPreferences(PREFERENCIAS, Context.MODE_PRIVATE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Fila fila =filaList.get(position);
                Intent intent = new Intent(getContext(), FilaDetalleActivity.class);
                Bundle bun = new Bundle();
                bun.putString("nombre_doctor",fila.getNombre_doctor());
                bun.putString("titulo_doctor",fila.getTitulo_doctor());
                bun.putString("hora_llegada",fila.getHoraLLegada());
                bun.putString("tiempo_estimado",fila.getTiempo_estimado());
                bun.putString("nombre_asistente",fila.getNombre_asistente());
                bun.putString("estado",fila.getEstado());
                bun.putString("tiempo_estimado_display",fila.getTiempo_estimado_display());
                bun.putString("turnos_restantes",fila.getTurnos_restantes());
                bun.putString("cod_fila",fila.getCod_turno());
                intent.putExtras(bun);
                startActivity(intent);

            }
        });


        Asincrono asinc = new Asincrono(this,filaList);
        asinc.execute(sharedpreferences.getString(USUARIO,""));

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