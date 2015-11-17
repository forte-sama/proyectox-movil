package com.appmedica.loginregister.actividades;

import android.app.DatePickerDialog;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.appmedica.loginregister.R;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

public class Register extends AppCompatActivity implements View.OnClickListener{

    Button bFecha;
    EditText etNombre, etApellido, etFecha, etTelefono, etUsuario, etContras, etCorreo;
    RadioGroup rgGrupo;
    Spinner spinner;
    Button btnRegistrar;
    String[] tiposSangre = {"O+", "O-", "A+","A-","B+","B-","AB+","AB-"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("Registro de Usuario");
        etNombre = (EditText) findViewById(R.id.etNombre_AReg);
        etApellido =(EditText) findViewById(R.id.etApellido_AReg);
        etFecha = (EditText) findViewById(R.id.etFecha_AReg);
        etTelefono = (EditText) findViewById(R.id.etTelefono_AReg);
        etFecha.setOnClickListener(this);
        etUsuario = (EditText) findViewById(R.id.etNombreUsuario_AReg);
        etContras = (EditText) findViewById(R.id.etContra_AReg);
        etCorreo = (EditText) findViewById(R.id.etCorreo_AReg);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar_AReg);
        btnRegistrar.setOnClickListener(this);
        RadioButton rb = (RadioButton)findViewById(R.id.rdbMasculino_AReg);
        rb.setChecked(true);
        rgGrupo = (RadioGroup) findViewById(R.id.radioGroup_AReg);

        ArrayAdapter<String> stringArrayAdapter= new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_dropdown_item,
                        tiposSangre);
        spinner = (Spinner) findViewById(R.id.spinner_AReg);
        spinner.setAdapter(stringArrayAdapter);

        etCorreo = (EditText) findViewById(R.id.etCorreo_AReg);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.etFecha_AReg:
                btnFechaHandler();
                break;
            case R.id.btnRegistrar_AReg:
                int radioButtonID = rgGrupo.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton)rgGrupo.findViewById(radioButtonID);
                String sexo;
                if (radioButton.getText().toString().equals("Masculino"))
                    sexo="M";
                else
                    sexo="F";
                try
                {
                    request(etUsuario.getText().toString(),etContras.getText().toString(),
                            etTelefono.getText().toString(),etCorreo.getText().toString(),
                            etNombre.getText().toString(),etApellido.getText().toString(),
                            sexo,etFecha.getText().toString(),
                            spinner.getSelectedItem().toString()
                            );

                } catch (IOException e)
                {
                    e.printStackTrace();
                }
                break;


        }


    }

    public static void request(String username,String password, String telefono, String email,
                               String nombre, String apellido, String sexo, String fecha_nacimiento,
                               String tipo_sangre) throws IOException{

        Map<String,Object> params = new LinkedHashMap<>();
        params.put("username", username);
        params.put("password", password);
        params.put("telefono", telefono);
        params.put("email", email);
        params.put("nombre", nombre);
        params.put("apellido", apellido);
        params.put("sexo", sexo);
        params.put("fecha_nacimiento",fecha_nacimiento);
        params.put("tipo_sangre", tipo_sangre);
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String,Object> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        Log.d("Test1",postData.toString());
        String urlParameters  = postData.toString();
        byte[] postData2       = urlParameters.getBytes();
        int    postDataLength = postData2.length;
        String request        = "http://testx-isc434.herokuapp.com/api/request_registro";
        System.out.println("shieeet");
        URL    url            = new URL( request );
        HttpURLConnection conn= (HttpURLConnection) url.openConnection();
        conn.setDoOutput( true );
        conn.setInstanceFollowRedirects(false);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty( "charset", "utf-8");
        conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        conn.setUseCaches(false);
         DataOutputStream wr = new DataOutputStream( conn.getOutputStream());
            wr.write(postData2);
            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            for ( int c = in.read(); c != -1; c = in.read() )
                Log.d("hey!",String.valueOf((char)c));




    }


    private void btnFechaHandler(){
        final Calendar c = Calendar.getInstance();
        int anio = c.get(Calendar.YEAR) - 20;
        int mes= c.get(Calendar.MONTH);
        int dia = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // Setear valor en editText
                        etFecha.setText((monthOfYear + 1)+ "-"
                                + dayOfMonth  + "-" + year);
                    }
                }, anio, mes, dia);
        dpd.show();

    }
}


