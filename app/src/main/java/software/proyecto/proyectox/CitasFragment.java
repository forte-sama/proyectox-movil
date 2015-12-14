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

import modelos.Cita;
import modelos.Fila;
import utilidades.API;
import utilidades.CitasListAdapter;
import utilidades.FilasListAdapter;

public class CitasFragment extends Fragment {

    private List<Cita> citaList = new ArrayList<Cita>();
    private ListView listView;
    private TextView noCitas;


    public static CitasFragment newInstance() {
        CitasFragment fragment = new CitasFragment();
        return fragment;
    }

    public CitasFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_citas, container, false);
        listView = (ListView) rootView.findViewById(R.id.lista_FF);
        noCitas = (TextView) rootView.findViewById(R.id.noCitas_CR);
        noCitas.setAlpha(0.0f);


        Asincrono asinc = new Asincrono(this,citaList);
        asinc.execute("jozu789");



        return rootView;

    }


    private class Asincrono extends AsyncTask<String,Void,List<Cita>>
    {
        private CitasFragment contexto;
        ProgressDialog progressDialog;
        List<Cita> citas;
        CitasListAdapter adapter;
        public Asincrono(CitasFragment contexto,List<Cita> citas)
        {
            super();
            this.contexto = contexto;
            this.citas = citas;
        }

        @Override
        protected void onPreExecute()
        {
            progressDialog = ProgressDialog.show(contexto.getActivity(), "", "Buscando Citas... ");
        }

        @Override
        protected List<Cita> doInBackground(String... params)
        {


            List<Cita> citasResul;
            citasResul = API.getInstance().request_citas(params[0]);
            if(citasResul.get(0).getEstado_peticion().equals("0")){
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        noCitas.setAlpha(0.0f);
                    }
                });
                citaList =citasResul;
                Log.d("jesus2", citaList.get(0).getTitulo_doctor());
            }
            else{
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        noCitas.setAlpha(1.0f);
                    }
                });
            }
            return citas;

        }

        @Override
        protected void onPostExecute(List<Cita> mensajeServer)
        {
            super.onPostExecute(mensajeServer);
            adapter = new CitasListAdapter(getActivity(), citaList);

            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
//            for (Cita c : citaList){
//                Contador con = new Contador(Integer.parseInt(f.getTiempo_estimado())*1000,1000,f);
//                con.start();
//
//            }


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