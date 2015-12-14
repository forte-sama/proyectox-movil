package modelos;

/**
 * Created by saleta on 12/14/2015.
 */
public class PeticionEdicion {
    private String usuario;
    private String nueva_contrasena;
    private String vieja_contrasena;
    private String nuevo_correo;
    private String nuevo_telefono;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNueva_contrasena() {
        return nueva_contrasena;
    }

    public void setNueva_contrasena(String nueva_contrasena) {
        this.nueva_contrasena = nueva_contrasena;
    }

    public String getVieja_contrasena() {
        return vieja_contrasena;
    }

    public void setVieja_contrasena(String vieja_contrasena) {
        this.vieja_contrasena = vieja_contrasena;
    }

    public String getNuevo_correo() {
        return nuevo_correo;
    }

    public void setNuevo_correo(String nuevo_correo) {
        this.nuevo_correo = nuevo_correo;
    }

    public String getNuevo_telefono() {
        return nuevo_telefono;
    }

    public void setNuevo_telefono(String nuevo_telefono) {
        this.nuevo_telefono = nuevo_telefono;
    }

    public PeticionEdicion(String usuario, String nueva_contrasena, String vieja_contrasena, String nuevo_correo, String nuevo_telefono) {
        this.usuario = usuario;
        this.nueva_contrasena = nueva_contrasena;
        this.vieja_contrasena = vieja_contrasena;
        this.nuevo_correo = nuevo_correo;
        this.nuevo_telefono = nuevo_telefono;
    }
}
