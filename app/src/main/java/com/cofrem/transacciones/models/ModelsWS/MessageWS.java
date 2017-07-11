package com.cofrem.transacciones.models.ModelsWS;

import org.ksoap2.serialization.SoapObject;

public class MessageWS {


    //Modelo usado en la respuesta del WS para la respuestas

    //Modelado rapido de llaves del terminalResulta
    public static final String CODIGO_MESSAGE_KEY = "codigoMensaje";
    public static final String DETALLE_MESSAGE_KEY = "detalleMensaje";

    /**
     * STATUS METHOD TERMINAL
     */
    public final static int statusTerminalTransactionNoResult = 0;
    public final static int statusTerminalTransactionSuccess = 11;
    public final static int statusTerminalNotExist = 12;
    public final static int statusTerminalExist = 13;
    public final static int statusTerminalErrorException = 99;

    //Informacion del detalleMensaje
    private int codigoMensaje;
    private String detalleMensaje;

    /**
     * Constructor de la clase que recibe un Objeto tipo SoapObject
     *
     * @param soap
     */
    public MessageWS(SoapObject soap) {

        this.codigoMensaje = soap.getPropertyAsString(CODIGO_MESSAGE_KEY).equals("anyType{}") ? statusTerminalTransactionNoResult : Integer.parseInt(soap.getPropertyAsString(CODIGO_MESSAGE_KEY));

        this.detalleMensaje = soap.getPropertyAsString(DETALLE_MESSAGE_KEY).equals("anyType{}") ? "" : soap.getPropertyAsString(DETALLE_MESSAGE_KEY);

    }

    public int getCodigoMensaje() {
        return codigoMensaje;
    }

    public void setCodigoMensaje(int codigoMensaje) {
        this.codigoMensaje = codigoMensaje;
    }

    public String getDetalleMensaje() {
        return detalleMensaje;
    }

    public void setDetalleMensaje(String detalleMensaje) {
        this.detalleMensaje = detalleMensaje;
    }
}