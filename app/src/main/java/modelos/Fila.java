package modelos;

public class Fila {
    private String nombre_doctor;
    private String titulo_doctor;
    private String hora_llegada;
    private String tiempo_estimado;
    private String tiempo_estimado_display;
    private String estado_peticion;
    private String turnos_restantes;
    private String nombre_asistente;
    private String estado;
    private String es_cita;
    private String cod_turno;

    public String getCod_turno() {
        return cod_turno;
    }

    public void setCod_turno(String cod_turno) {
        this.cod_turno = cod_turno;
    }

    public String getHora_llegada() {
        return hora_llegada;
    }

    public void setHora_llegada(String hora_llegada) {
        this.hora_llegada = hora_llegada;
    }

    public String getEs_cita() {
        return es_cita;
    }

    public void setEs_cita(String es_cita) {
        this.es_cita = es_cita;
    }

    public String getEstado_peticion() {
        return estado_peticion;
    }

    public void setEstado_peticion(String estado_peticion) {
        this.estado_peticion = estado_peticion;
    }

    public String getTiempo_estimado_display() {
        return tiempo_estimado_display;
    }

    public void setTiempo_estimado_display(String tiempo_estimado_display) {
        this.tiempo_estimado_display = tiempo_estimado_display;
    }

    public String getNombre_asistente() {
        return nombre_asistente;
    }

    public void setNombre_asistente(String nombre_asistente) {
        this.nombre_asistente = nombre_asistente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    private float latitudDestino;
    private float longitudDestino;

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

    public String getHoraLLegada() {
        return hora_llegada;
    }

    public void setHoraLLegada(String horaLLegada) {
        this.hora_llegada = horaLLegada;
    }

    public String getTiempo_estimado() {
        return tiempo_estimado;
    }

    public void setTiempo_estimado(String tiempo_estimado) {
        this.tiempo_estimado = tiempo_estimado;
    }

    public String getTurnos_restantes() {
        return turnos_restantes;
    }

    public void setTurnos_restantes(String turnos_restantes) {
        this.turnos_restantes = turnos_restantes;
    }

    public float getLatitudDestino() {
        return latitudDestino;
    }

    public void setLatitudDestino(float latitudDestino) {
        this.latitudDestino = latitudDestino;
    }

    public float getLongitudDestino() {
        return longitudDestino;
    }

    public void setLongitudDestino(float longitudDestino) {
        this.longitudDestino = longitudDestino;
    }

    public Fila() {
    }


}