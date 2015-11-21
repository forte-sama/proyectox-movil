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
import java.util.LinkedHashMap;
import java.util.Map;

import Modelos.UsuarioMovil;

/**
 * Created by Saleta on 11/17/2015.
 */
public class API
{
    private static API instance = null;
    protected API() {

    }
    public static API getInstance() {
        if(instance == null) {
            instance = new API();
        }
        return instance;
    }



    public static void request_registro(String username,String password, String telefono, String email,
                                        String nombre, String apellido, String sexo, String fecha_nacimiento,
                                        String tipo_sangre){
        HttpURLConnection httpcon;
        String url = "http://testx-isc434.herokuapp.com/api/request_registro";
        UsuarioMovil lol = new UsuarioMovil(nombre,apellido,username,password,telefono,email,sexo,fecha_nacimiento,tipo_sangre);
        Gson gson = new Gson();
        String data = gson.toJson(lol);
        Log.d("jesus",data);
       // String data =" {\"username\":\"sadddddddsulo\",\"password\":\"asdfasdf\",\"telefono\":\"564-445-4555\",\"email\":\"email@mmg.com\",\"nombre\":\"nombre\",\"apellido\":\"apellido\",\"sexo\":\"M\",\"fecha_nacimiento\":\"11-10-2003\",\"cod_usuario_movil\":null,\"tipo_sangre\":\"O+\"}";
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
            writer.write(data);
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
            Log.d("jesus2",result);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void test(){
        UsuarioMovil lol = new UsuarioMovil("Manuel","Saleta","jozu789","1234","809-583-8274","manus@gmail.com","M","12-12-2003","O+");
        Gson gson = new Gson();
        String json = gson.toJson(lol);
        Log.d("jesus",json);
    }

}
