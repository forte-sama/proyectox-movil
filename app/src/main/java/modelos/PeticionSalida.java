package modelos;

/**
 * Created by saleta on 12/15/2015.
 */
public class PeticionSalida {
    private String cod_fila_turno;

    public PeticionSalida(String cod_fila_turno) {
        this.cod_fila_turno = cod_fila_turno;
    }

    public String getCod_fila_turno() {
        return cod_fila_turno;
    }

    public void setCod_fila_turno(String cod_fila_turno) {
        this.cod_fila_turno = cod_fila_turno;
    }
}
