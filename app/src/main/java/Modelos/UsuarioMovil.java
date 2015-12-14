package modelos;

/**
 * Created by Saleta on 11/20/2015.
 */
public class UsuarioMovil
{
    private String nombre;
    private String apellido;
    private String username;
    private String password;
    private String telefono;
    private String email;
    private String sexo;
    private String fecha_nacimiento;
    private String tipo_sangre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getTipo_sangre() {
        return tipo_sangre;
    }

    public void setTipo_sangre(String tipo_sangre) {
        this.tipo_sangre = tipo_sangre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    private String cedula;

    public UsuarioMovil(String nombre, String apellido, String username, String password, String telefono, String email, String sexo, String fecha_nacimiento, String tipo_sangre, String cedula) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.username = username;
        this.password = password;
        this.telefono = telefono;
        this.email = email;
        this.sexo = sexo;
        this.fecha_nacimiento = fecha_nacimiento;
        this.tipo_sangre = tipo_sangre;
        this.cedula = cedula;
    }

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
