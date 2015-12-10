package modelos;

/**
 * Created by Saleta on 11/20/2015.
 */
public class UsuarioMovil
{
    public String nombre;
    public String apellido;
    public String username;
    public String password;
    public String telefono;
    public String email;
    public String sexo;
    public String fecha_nacimiento;
    public String tipo_sangre;

    public UsuarioMovil(String nombre, String apellido, String username, String password, String telefono, String email, String sexo, String fecha_nacimiento, String tipo_sangre)
    {
        this.nombre = nombre;
        this.apellido = apellido;
        this.username = username;
        this.password = password;
        this.telefono = telefono;
        this.email = email;
        this.sexo = sexo;
        this.fecha_nacimiento = fecha_nacimiento;
        this.tipo_sangre = tipo_sangre;
    }
}
