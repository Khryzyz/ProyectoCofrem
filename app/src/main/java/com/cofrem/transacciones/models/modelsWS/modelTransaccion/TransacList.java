package com.cofrem.transacciones.models.modelsWS.modelTransaccion;

/**
 * Created by luispineda on 21/09/17.
 */

public class TransacList {

    public static final String ESTADO_ACTIVO_SIN_CIERRE = "A";
    public static final String ESTADO_DEVOLUCION_SIN_CIERRE = "D";


    private String codigoTerminal;
    private String numeroAprobacion;
    private String cedulaUsuario;
    private String valorAprobado;
    private String estado;

    public TransacList() {
    }

    /**
     *
     * @param codigoTerminal
     * @param numeroAprobacion
     * @param cedulaUsuario
     * @param valorAprobado
     * @param estado
     */
    public TransacList(String codigoTerminal, String numeroAprobacion, String cedulaUsuario, String valorAprobado, String estado) {
        this.codigoTerminal = codigoTerminal;
        this.numeroAprobacion = numeroAprobacion;
        this.cedulaUsuario = cedulaUsuario;
        this.valorAprobado = valorAprobado;
        this.estado = estado;
    }

    public String getCodigoTerminal() {
        return codigoTerminal;
    }

    public void setCodigoTerminal(String codigoTerminal) {
        this.codigoTerminal = codigoTerminal;
    }

    public String getNumeroAprobacion() {
        return numeroAprobacion;
    }

    public void setNumeroAprobacion(String numeroAprobacion) {
        this.numeroAprobacion = numeroAprobacion;
    }

    public String getCedulaUsuario() {
        return cedulaUsuario;
    }

    public void setCedulaUsuario(String cedulaUsuario) {
        this.cedulaUsuario = cedulaUsuario;
    }

    public String getValorAprobado() {
        return valorAprobado;
    }

    public void setValorAprobado(String valorAprobado) {
        this.valorAprobado = valorAprobado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
