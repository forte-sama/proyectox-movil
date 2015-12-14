package modelos;

public class Cita {
    private String nombre_doctor;
    private String titulo_doctor;
    private String hora_programada;
    private String estado_peticion;
    private String fecha;
    private String estado_cita;

    public String getNombre_doctor() {
        return nombre_doctor;
    }

    public void setNombre_doctor(String nombre_doctor) {
        this.nombre_doctor = nombre_doctor;
    }

    public String getTitulo_doctor() {
        return titulo_doctor;
    }

    public void setTitulo_doctor(String titulo_doctor) {
        this.titulo_doctor = titulo_doctor;
    }

    public String getHora_programada() {
        return hora_programada;
    }

    public void setHora_programada(String hora_programada) {
        this.hora_programada = hora_programada;
    }

    public String getEstado_peticion() {
        return estado_peticion;
    }

    public void setEstado_peticion(String estado_peticion) {
        this.estado_peticion = estado_peticion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado_cita() {
        return estado_cita;
    }

    public void setEstado_cita(String estado_cita) {
        this.estado_cita = estado_cita;
    }

    public Cita() {
    }


}