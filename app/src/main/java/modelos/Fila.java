package modelos;

public class Fila {
    private String nombreDoctor;
    private String tituloDoctor;
    private String horaLLegada;
    private String tiempoRestante;
    private String turnosRestantes;
    private String estado;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    private float latitudDestino;
    private float longitudDestino;

    public String getNombreDoctor() {
        return nombreDoctor;
    }

    public void setNombreDoctor(String nombreDoctor) {
        this.nombreDoctor = nombreDoctor;
    }

    public String getTituloDoctor() {
        return tituloDoctor;
    }

    public void setTituloDoctor(String tituloDoctor) {
        this.tituloDoctor = tituloDoctor;
    }

    public String getHoraLLegada() {
        return horaLLegada;
    }

    public void setHoraLLegada(String horaLLegada) {
        this.horaLLegada = horaLLegada;
    }

    public String getTiempoRestante() {
        return tiempoRestante;
    }

    public void setTiempoRestante(String tiempoRestante) {
        this.tiempoRestante = tiempoRestante;
    }

    public String getTurnosRestantes() {
        return turnosRestantes;
    }

    public void setTurnosRestantes(String turnosRestantes) {
        this.turnosRestantes = turnosRestantes;
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