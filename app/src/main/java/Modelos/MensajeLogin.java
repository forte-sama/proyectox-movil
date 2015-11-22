package Modelos;

/**
 * Created by Saleta on 11/21/2015.
 */
public class MensajeLogin
{
    public String username;
    public String password;

    public MensajeLogin(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public String toString(){
        return "Username: "+this.username+" Contrasena: "+this.password;
    }
}
