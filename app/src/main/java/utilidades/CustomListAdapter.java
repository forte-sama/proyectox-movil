package utilidades;



        import customlistviewvolley.androidhive.info.customlistviewvolley.model.Fila;

        import customlistviewvolley.androidhive.info.customlistviewvolley.R;

        import java.util.List;

        import android.app.Activity;
        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.TextView;

        import com.android.volley.toolbox.ImageLoader;
        import com.android.volley.toolbox.NetworkImageView;

public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Fila> movieItems;
    private TextView year;


    public CustomListAdapter(Activity activity, List<Fila> filaItems) {
        this.activity = activity;
        this.movieItems = filaItems;



    }

    @Override
    public int getCount() {
        return movieItems.size();
    }

    @Override
    public Object getItem(int location) {
        return movieItems.get(location);
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


        TextView title = (TextView) convertView.findViewById(R.id.titulo_LR);
        TextView nombre = (TextView) convertView.findViewById(R.id.nombreDoc_LR);
        TextView tiempoRestante = (TextView) convertView.findViewById(R.id.tiempoDisponible_LR);
        //TextView horaLLegada = (TextView) convertView.findViewById(R.id.horaLLegada);

        // getting movie data for the row
        Fila m = movieItems.get(position);

        title.setText(m.getTituloDoctor());
        nombre.setText(m.getNombreDoctor());
        tiempoRestante.setText(m.getTiempoRestante());
        //horaLLegada.setText(m.getHoraLLegada());

        return convertView;
    }



}
