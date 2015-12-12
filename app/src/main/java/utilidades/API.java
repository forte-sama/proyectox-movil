package utilidades;

import android.util.Log;


import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import modelos.Fila;
import modelos.MensajeLogin;
import modelos.MensajeServer;
import modelos.PeticionFila;
import modelos.UsuarioMovil;

/**
 * Created by Saleta on 11/17/2015.
 */
public class API
{
    private static API instance = null;
    private static final String API_URL = "http://testx-isc434.herokuapp.com/api/";
    protected API() {

    }
    public static API getInstance() {
        if(instance == null) {
            instance = new API();
        }
        return instance;
    }



    public static MensajeServer request_registro(String username,String password, String telefono, String email,
                                                 String nombre, String apellido, String sexo, String fecha_nacimiento,
                                                 String tipo_sangre){

        String url = API_URL+"request_registro";
        UsuarioMovil user = new UsuarioMovil(nombre,apellido,username,password,telefono,email,sexo,fecha_nacimiento,tipo_sangre);
        Gson gson = new Gson();
        String data = gson.toJson(user);
        Log.d("jesus",data);

        String result = conexion_http(url, data) ;
        Log.d("jesus",result);
        MensajeServer mensaje = gson.fromJson(result, MensajeServer.class);

        Log.d("jesus",mensaje.toString());
        return mensaje;
    }

    public static MensajeServer request_login(String username,String password){

        String url = API_URL+"request_login";
        MensajeLogin msg = new MensajeLogin(username,password);
        Gson gson = new Gson();
        String data = gson.toJson(msg);
        Log.d("jesus",data);

        String result = conexion_http(url, data);
        Log.d("jesus",result);
        MensajeServer mensaje = gson.fromJson(result, MensajeServer.class);

        Log.d("jesus",mensaje.toString());
        return mensaje;
    }

    private static String conexion_http(String url, String json_request){
        HttpURLConnection httpcon;
        String result = null;
        try{
//Connect
            httpcon = (HttpURLConnection) ((new URL (url).openConnection()));
            httpcon.setDoOutput(true);
            httpcon.setRequestProperty("Content-Type", "application/json");
            httpcon.setRequestProperty("Accept", "application/json");
            httpcon.setRequestMethod("POST");
            httpcon.connect();

//Write
            OutputStream os = httpcon.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(json_request);
            writer.close();
            os.close();

//Read
            BufferedReader br = new BufferedReader(new InputStreamReader(httpcon.getInputStream(),"UTF-8"));

            String line = null;
            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            br.close();
            result = sb.toString();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<Fila> request_filas(String username){
        String url = API_URL+"request_info_fila";
        PeticionFila pet = new PeticionFila(username);
        Gson gson = new Gson();
        String data = gson.toJson(pet);
        Log.d("jesus",data);

        String result = conexion_http(url, data) ;
        Log.d("jesus",result);
        Fila[] mensaje = gson.fromJson(result, Fila[].class);



        return Arrays.asList(mensaje);
    }





}
