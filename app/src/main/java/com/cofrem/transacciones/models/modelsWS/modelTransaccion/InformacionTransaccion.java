package com.cofrem.transacciones.models.modelsWS.modelTransaccion;

import org.ksoap2.serialization.SoapObject;

public class InformacionTransaccion {

    //Modelo usado en la respuesta del WS para la respuesta terminalResult

    //Modelado rapido de llaves del transacResult
    public static final String PROPERTY_TRANSAC_RESULT = "transacResult";
    private static final String KEY_TRANSAC_CEDULA_USUARIO = "cedulaUsuario";
    private static final String KEY_TRANSAC_CODIGO_TERMINAL = "codigoTerminal";
    private static final String KEY_TRANSAC_DETALLE_ESTADO = "detalleEstado";
    private static final String KEY_TRANSAC_ESTADO = "estado";
    private static final String KEY_TRANSAC_FECHA = "fecha";
    private static final String KEY_TRANSAC_HORA = "hora";
    private static final String KEY_TRANSAC_NOMBRE_AFILIADO = "nombreAfiliado";
    private static final String KEY_TRANSAC_NUMERO_APROBACION = "numeroAprobacion";
    private static final String KEY_TRANSAC_NUMERO_TARJETA = "numeroTarjeta";
    private static final String KEY_TRANSAC_TIPO_TRANSACCION = "tipoTransaccion";
    private static final String KEY_TRANSAC_DETALLE_VALOR = "valorResult";
    private static final String KEY_TRANSAC_DETALLE_TIPO_SERVICIO = "detalleTipoServicio";
    private static final String KEY_TRANSAC_TIPO_SERVICIO = "tipoServicio";
    private static final String KEY_TRANSAC_VALOR = "valor";

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
     * Constructor de la clase que recibe un Objeto tipo SoapObject
     *
     * @param soap
     */
    public InformacionTransaccion(SoapObject soap) {

        this.cedulaUsuario = soap.getPropertyAsString(KEY_TRANSAC_CEDULA_USUARIO).equals("anyType{}") ? "" : soap.getPropertyAsString(KEY_TRANSAC_CEDULA_USUARIO);

        this.codigoTerminal = soap.getPropertyAsString(KEY_TRANSAC_CODIGO_TERMINAL).equals("anyType{}") ? "" : soap.getPropertyAsString(KEY_TRANSAC_CODIGO_TERMINAL);

        this.detalleEstado = soap.getPropertyAsString(KEY_TRANSAC_DETALLE_ESTADO).equals("anyType{}") ? "" : soap.getPropertyAsString(KEY_TRANSAC_DETALLE_ESTADO);

        this.estado = soap.getPropertyAsString(KEY_TRANSAC_ESTADO).equals("anyType{}") ? "" : soap.getPropertyAsString(KEY_TRANSAC_ESTADO);

        this.fecha = soap.getPropertyAsString(KEY_TRANSAC_FECHA).equals("anyType{}") ? "" : soap.getPropertyAsString(KEY_TRANSAC_FECHA);

        this.hora = soap.getPropertyAsString(KEY_TRANSAC_HORA).equals("anyType{}") ? "" : soap.getPropertyAsString(KEY_TRANSAC_HORA);

        this.nombreAfiliado = soap.getPropertyAsString(KEY_TRANSAC_NOMBRE_AFILIADO).equals("anyType{}") ? "" : soap.getPropertyAsString(KEY_TRANSAC_NOMBRE_AFILIADO);

        this.numeroAprobacion = soap.getPropertyAsString(KEY_TRANSAC_NUMERO_APROBACION).equals("anyType{}") ? "" : soap.getPropertyAsString(KEY_TRANSAC_NUMERO_APROBACION);

        this.numeroTarjeta = soap.getPropertyAsString(KEY_TRANSAC_NUMERO_TARJETA).equals("anyType{}") ? "" : soap.getPropertyAsString(KEY_TRANSAC_NUMERO_TARJETA);

        this.tipoTransaccion = soap.getPropertyAsString(KEY_TRANSAC_TIPO_TRANSACCION).equals("anyType{}") ? "" : soap.getPropertyAsString(KEY_TRANSAC_TIPO_TRANSACCION);

        SoapObject soapDetalleValor = (SoapObject) soap.getProperty(KEY_TRANSAC_DETALLE_VALOR);

        this.detalleTipoServicio = soapDetalleValor.getPropertyAsString(KEY_TRANSAC_DETALLE_TIPO_SERVICIO).equals("anyType{}") ? "" : soapDetalleValor.getPropertyAsString(KEY_TRANSAC_DETALLE_TIPO_SERVICIO);

        this.tipoServicio = soapDetalleValor.getPropertyAsString(KEY_TRANSAC_TIPO_SERVICIO).equals("anyType{}") ? "" : soapDetalleValor.getPropertyAsString(KEY_TRANSAC_TIPO_SERVICIO);

        this.valor = soapDetalleValor.getPropertyAsString(KEY_TRANSAC_VALOR).equals("anyType{}") ? "" : soapDetalleValor.getPropertyAsString(KEY_TRANSAC_VALOR);

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
