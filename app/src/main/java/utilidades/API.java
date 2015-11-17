package utilidades;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

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
                               String tipo_sangre) throws IOException
    {

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
        Log.d("Test1", postData.toString());
        String urlParameters  = postData.toString();
        byte[] postData2       = urlParameters.getBytes();
        int    postDataLength = postData2.length;
        String request        = "http://testx-isc434.herokuapp.com/api/request_registro";
        System.out.println("shieeet");
        URL url            = new URL( request );
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

}
