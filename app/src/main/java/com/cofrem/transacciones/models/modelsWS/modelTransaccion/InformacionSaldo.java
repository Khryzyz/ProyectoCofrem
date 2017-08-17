package com.cofrem.transacciones.models.modelsWS.modelTransaccion;

import org.ksoap2.serialization.SoapObject;

public class InformacionSaldo {

    //Modelo usado en la respuesta del WS para la respuesta terminalResult

    //Modelado rapido de llaves del saldoResult
    public static final String PROPERTY_SALDO_RESULT = "saldoResult";
    private static final String KEY_SALDO_CEDULA_USUARIO = "cedulaUsuario";
    private static final String KEY_SALDO_CODIGO_TERMINAL = "codigoTerminal";
    private static final String KEY_SALDO_NUMERO_TARJETA = "numeroTarjeta";
    private static final String KEY_SALDO_DETALLE_VALOR = "valorResult";
    private static final String KEY_SALDO_DETALLE_TIPO_SERVICIO = "detalleTipoServicio";
    private static final String KEY_SALDO_TIPO_SERVICIO = "tipoServicio";
    private static final String KEY_SALDO_VALOR = "valor";
    private static final String KEY_SALDO_VALOR_TOTAL_SALDO = "valorTotalSaldo";

    //Informacion de conexion registrada en el server y de identificacion
    private String cedulaUsuario;
    private String codigoTerminal;
    private String numeroTarjeta;
    private String valorTotalSaldo;
    private String detalleTipoServicio;
    private String tipoServicio;
    private String valor;

    /**
     * Constructor de la clase que recibe un Objeto tipo SoapObject
     *
     * @param soap
     */
    public InformacionSaldo(SoapObject soap) {

        this.cedulaUsuario = soap.getPropertyAsString(KEY_SALDO_CEDULA_USUARIO).equals("anyType{}") ? "" : soap.getPropertyAsString(KEY_SALDO_CEDULA_USUARIO);

        this.codigoTerminal = soap.getPropertyAsString(KEY_SALDO_CODIGO_TERMINAL).equals("anyType{}") ? "" : soap.getPropertyAsString(KEY_SALDO_CODIGO_TERMINAL);

        this.numeroTarjeta = soap.getPropertyAsString(KEY_SALDO_NUMERO_TARJETA).equals("anyType{}") ? "" : soap.getPropertyAsString(KEY_SALDO_NUMERO_TARJETA);

        this.valorTotalSaldo = soap.getPropertyAsString(KEY_SALDO_VALOR_TOTAL_SALDO).equals("anyType{}") ? "" : soap.getPropertyAsString(KEY_SALDO_VALOR_TOTAL_SALDO);

        SoapObject soapDetalleValor = (SoapObject) soap.getProperty(KEY_SALDO_DETALLE_VALOR);

        this.detalleTipoServicio = soapDetalleValor.getPropertyAsString(KEY_SALDO_DETALLE_TIPO_SERVICIO).equals("anyType{}") ? "" : soapDetalleValor.getPropertyAsString(KEY_SALDO_DETALLE_TIPO_SERVICIO);

        this.tipoServicio = soapDetalleValor.getPropertyAsString(KEY_SALDO_TIPO_SERVICIO).equals("anyType{}") ? "" : soapDetalleValor.getPropertyAsString(KEY_SALDO_TIPO_SERVICIO);

        this.valor = soapDetalleValor.getPropertyAsString(KEY_SALDO_VALOR).equals("anyType{}") ? "" : soapDetalleValor.getPropertyAsString(KEY_SALDO_VALOR);

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

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getValorTotalSaldo() {
        return valorTotalSaldo;
    }

    public void setValorTotalSaldo(String valorTotalSaldo) {
        this.valorTotalSaldo = valorTotalSaldo;
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
