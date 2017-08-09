package com.cofrem.transacciones.models.ModelsWS.ModelTransaccion;

import org.ksoap2.serialization.SoapObject;

public class ResultadoTransaccion {


    //Modelo usado en la respuesta del WS para la respuesta terminalResult

    //Modelado rapido de llaves del terminalResulta
    public static final String CEDULA_USUARIO = "cedulaUsuario";
    public static final String CODIGO_TERMINAL = "codigoTerminal";
    public static final String DETALLE_ESTADO = "detalleEstado";
    public static final String ESTADO = "estado";
    public static final String FECHA = "fecha";
    public static final String HORA = "hora";
    public static final String NOMBRE_AFILIADO = "nombreAfiliado";
    public static final String NUMERO_APROBACION = "numeroAprobacion";
    public static final String NUMERO_TARJETA = "numeroTarjeta";
    public static final String TIPO_TRANSACCION = "tipoTransaccion";
    public static final String DETALLE_TIPO_SERVICIO = "detalleTipoServicio";
    public static final String TIPO_SERVICIO = "tipoServicio";
    public static final String VALOR = "valor";

    //Informacion de conexion registrada en el server y de identificacion
    private String cedulaUsuario;
    private String codigoTerminal;
    private String detalleEstado;
    private String estado;
    private String fecha;
    private String hora;
    private String nombreAfiliado;
    private String numeroAprobacion;
    private String numeroTarjeta;
    private String tipoTransaccion;
    private String detalleTipoServicio;
    private String tipoServicio;
    private String valor;

    /**
     * Constructor vacio de la clase
     */
    public ResultadoTransaccion() {
    }

    /**
     * Constructor de la clase que recibe un Objeto tipo SoapObject
     *
     * @param soap
     */
    public ResultadoTransaccion(SoapObject soap) {

        this.cedulaUsuario = soap.getPropertyAsString(CEDULA_USUARIO).equals("anyType{}") ? "" : soap.getPropertyAsString(CEDULA_USUARIO);

        this.codigoTerminal = soap.getPropertyAsString(CODIGO_TERMINAL).equals("anyType{}") ? "" : soap.getPropertyAsString(CODIGO_TERMINAL);

        this.detalleEstado = soap.getPropertyAsString(DETALLE_ESTADO).equals("anyType{}") ? "" : soap.getPropertyAsString(DETALLE_ESTADO);

        this.estado = soap.getPropertyAsString(ESTADO).equals("anyType{}") ? "" : soap.getPropertyAsString(ESTADO);

        this.fecha = soap.getPropertyAsString(FECHA).equals("anyType{}") ? "" : soap.getPropertyAsString(FECHA);

        this.hora = soap.getPropertyAsString(HORA).equals("anyType{}") ? "" : soap.getPropertyAsString(HORA);

        this.nombreAfiliado = soap.getPropertyAsString(NOMBRE_AFILIADO).equals("anyType{}") ? "" : soap.getPropertyAsString(NOMBRE_AFILIADO);

        this.numeroAprobacion = soap.getPropertyAsString(NUMERO_APROBACION).equals("anyType{}") ? "" : soap.getPropertyAsString(NUMERO_APROBACION);

        this.numeroTarjeta = soap.getPropertyAsString(NUMERO_TARJETA).equals("anyType{}") ? "" : soap.getPropertyAsString(NUMERO_TARJETA);

        this.tipoTransaccion = soap.getPropertyAsString(TIPO_TRANSACCION).equals("anyType{}") ? "" : soap.getPropertyAsString(TIPO_TRANSACCION);

        this.detalleTipoServicio = soap.getPropertyAsString(DETALLE_TIPO_SERVICIO).equals("anyType{}") ? "" : soap.getPropertyAsString(DETALLE_TIPO_SERVICIO);

        this.tipoServicio = soap.getPropertyAsString(TIPO_SERVICIO).equals("anyType{}") ? "" : soap.getPropertyAsString(TIPO_SERVICIO);

        this.valor = soap.getPropertyAsString(VALOR).equals("anyType{}") ? "" : soap.getPropertyAsString(VALOR);

    }

    public String getCedulaUsuario() {
        return cedulaUsuario;
    }

    public void setCedulaUsuario(String cedulaUsuario) {
        this.cedulaUsuario = cedulaUsuario;
    }

    public String getCodigoTerminal() {
        return codigoTerminal;
    }

    public void setCodigoTerminal(String codigoTerminal) {
        this.codigoTerminal = codigoTerminal;
    }

    public String getDetalleEstado() {
        return detalleEstado;
    }

    public void setDetalleEstado(String detalleEstado) {
        this.detalleEstado = detalleEstado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getNombreAfiliado() {
        return nombreAfiliado;
    }

    public void setNombreAfiliado(String nombreAfiliado) {
        this.nombreAfiliado = nombreAfiliado;
    }

    public String getNumeroAprobacion() {
        return numeroAprobacion;
    }

    public void setNumeroAprobacion(String numeroAprobacion) {
        this.numeroAprobacion = numeroAprobacion;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public String getDetalleTipoServicio() {
        return detalleTipoServicio;
    }

    public void setDetalleTipoServicio(String detalleTipoServicio) {
        this.detalleTipoServicio = detalleTipoServicio;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
