package utilidades;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import modelos.Fila;
import software.proyecto.proyectox.R;


public class FilasListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Fila> filaItems;
    private TextView year;
    private static final int TIEMPO_AMARILLO  = 60;
    private static final int TIEMPO_ROJO  = 10;


    public FilasListAdapter(Activity activity, List<Fila> filaItems) {
        this.activity = activity;
        this.filaItems = filaItems;
    }

    @Override
    public int getCount() {
        return filaItems.size();
    }

    @Override
    public Object getItem(int location) {
        return filaItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);


        TextView title = (TextView) convertView.findViewById(R.id.titulo_CR);
        TextView nombre = (TextView) convertView.findViewById(R.id.nombreDoc_CR);
        TextView tiempoRestante = (TextView) convertView.findViewById(R.id.tiempoDisponible_LR);
        TextView esCita = (TextView) convertView.findViewById(R.id.tvEsCita_LR);
        ImageView semaforo = (ImageView) convertView.findViewById(R.id.thumbnail);



        Fila f = filaItems.get(position);
        int minutos_restantes = Integer.parseInt(f.getTiempo_estimado())/60;
        if (minutos_restantes <= TIEMPO_AMARILLO){
            if(minutos_restantes <= TIEMPO_ROJO)
                semaforo.setImageResource(R.drawable.semaforo_rojo);
            else
                semaforo.setImageResource(R.drawable.semaforo_amarillo);

        }
        else
            semaforo.setImageResource(R.drawable.semaforo_verde);
        if(f.getEs_cita().equals("true")){
            esCita.setText("Cita");
        }
        else{
            esCita.setText("");
        }
        title.setText(f.getTitulo_doctor());
        nombre.setText(f.getNombre_doctor());
        tiempoRestante.setText(f.getTiempo_estimado_display());


        //horaLLegada.setText(m.getHoraLLegada());

        return convertView;
    }



}
