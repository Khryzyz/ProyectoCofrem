package com.cofrem.transacciones.models.modelsWS.modelTransaccion;

import com.cofrem.transacciones.models.modelsWS.MessageWS;

public class ResultadoTransaccion {

    //Modelo usado en la respuesta del WS para la respuesta transacResult
    private InformacionTransaccion informacionTransaccion;
    private MessageWS messageWS;

    public ResultadoTransaccion(MessageWS messageWS) {
        this.messageWS = messageWS;
    }

    public ResultadoTransaccion(InformacionTransaccion informacionTransaccion,
                                MessageWS messageWS) {
        this.informacionTransaccion = informacionTransaccion;
        this.messageWS = messageWS;
    }

    public InformacionTransaccion getInformacionTransaccion() {
        return informacionTransaccion;
    }

    public void setInformacionTransaccion(InformacionTransaccion informacionTransaccion) {
        this.informacionTransaccion = informacionTransaccion;
    }

    public MessageWS getMessageWS() {
        return messageWS;
    }

    public void setMessageWS(MessageWS messageWS) {
        this.messageWS = messageWS;
    }

}