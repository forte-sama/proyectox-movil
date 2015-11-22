package Modelos;

/**
 * Created by Saleta on 11/21/2015.
 */
public class MensajeServer

{
    public int cod_error;
    public String mensaje;

    public String toString(){
        return "Cod Error: "+this.cod_error+" Mensaje: "+this.mensaje;
    }
}
