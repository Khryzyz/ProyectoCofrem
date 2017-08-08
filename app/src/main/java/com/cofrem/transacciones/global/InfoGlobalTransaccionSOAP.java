package com.cofrem.transacciones.global;

public class InfoGlobalTransaccionSOAP {

    public final static String HTTP = "http://";

    public final static String WEB_SERVICE_URI = "/WsDatafono/Datafono?wsdl";

    public final static String NAME_SPACE = "datafono/";

    /**
     * Datos para el Web Service Punto
     */
    public final static String METHOD_NAME_PUNTO = "Punto";
    public final static String PARAM_NAME_CODIGO_PUNTO = "codigoPunto";


    /**
     * Datos para el Web Service Terminal
     */
    public final static String METHOD_NAME_TERMINAL = "Terminal";
    public final static String PARAM_NAME_TERMINAL_CODIGO_TERMINAL = "codigoTerminal";

    /**
     * Datos para el Web Service Transaccion
     */
    public final static String METHOD_NAME_TRANSACCION = "Transaccion";

    public final static String PARAM_NAME_TRANSACCION_CEDULA_USUARIO = "cedulaUsuario";
    public final static String PARAM_NAME_TRANSACCION_CLAVE_USUARIO = "clave";
    public final static String PARAM_NAME_TRANSACCION_CODIGO_TERMINAL = "codigoTerminal";
    public final static String PARAM_NAME_TRANSACCION_NUMERO_TARJETA = "numeroTarjeta";
    public final static String PARAM_NAME_TRANSACCION_TIPO_SERVICIO = "tipoServicio";
    public final static String PARAM_NAME_TRANSACCION_VALOR_SOLICITADO = "valorSolicitado";
    public final static String PARAM_NAME_TRANSACCION_ENTRADA_SERVICIO = "servicioInput";
    public final static String PARAM_NAME_TRANSACCION_TIPO_ENCRIPTACION = "tipoEncrip";
    public final static String PARAM_NAME_TRANSACCION_TIPO_TRANSACCION = "tipoTransaccion";


}