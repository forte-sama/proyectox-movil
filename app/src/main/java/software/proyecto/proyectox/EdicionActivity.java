package software.proyecto.proyectox;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EdicionActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etTelefono, etUsuario, etContrasActual, etCorreo, etContrasNueva,  etContrasActual2;
    Button btnRegistrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        etTelefono= (EditText)findViewById(R.id.etTelefono_AReg);
        etUsuario=(EditText)findViewById(R.id.etNombreUsuario_AReg);
        etContrasActual=(EditText)findViewById(R.id.etContra_old);
        etContrasNueva=(EditText)findViewById(R.id.etContra_AReg);
        etContrasActual2=(EditText)findViewById(R.id.etContra_new);
        etCorreo=(EditText)findViewById(R.id.etCorreo_AReg);
        btnRegistrar = (Button)findViewById(R.id.btnRegistrar_AReg);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {

            case R.id.btnRegistrar_AReg:

                break;
            default:


        }

    }
}
