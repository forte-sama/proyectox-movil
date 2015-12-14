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

import modelos.Cita;
import software.proyecto.proyectox.R;


public class CitasListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Cita> citaItems;
    private TextView year;


    public CitasListAdapter(Activity activity, List<Cita> citaItems) {
        this.activity = activity;
        this.citaItems = citaItems;
    }

    @Override
    public int getCount() {
        return citaItems.size();
    }

    @Override
    public Object getItem(int location) {
        return citaItems.get(location);
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
            convertView = inflater.inflate(R.layout.cita_row, null);


        TextView title = (TextView) convertView.findViewById(R.id.titulo_CR);
        TextView nombre = (TextView) convertView.findViewById(R.id.nombreDoc_CR);
        TextView fechaEstado = (TextView) convertView.findViewById(R.id.fechaEstado_CR);
        TextView hora = (TextView) convertView.findViewById(R.id.hora_CR);
        ImageView imagen = (ImageView) convertView.findViewById(R.id.thumbnail_CR);


        Cita c = citaItems.get(position);

        title.setText(c.getTitulo_doctor());
        nombre.setText(c.getNombre_doctor());
        hora.setText(c.getHora_programada());
        fechaEstado.setText(c.getFecha());
        if(c.getEstado_cita().equals("1")){
            imagen.setImageResource(R.drawable.luegof);
        }
        else{
            imagen.setImageResource(R.drawable.hoyf);
        }
        return convertView;
    }



}
