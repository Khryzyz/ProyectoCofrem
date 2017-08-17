package com.cofrem.transacciones.models.modelsWS.modelTransaccion;

import com.cofrem.transacciones.models.modelsWS.MessageWS;

public class ResultadoTransaccion {

    //Modelo usado en la respuesta del WS para la respuesta transacResult
    private InformacionTransaccion informacionTransaccion;
    private InformacionSaldo informacionSaldo;
    private MessageWS messageWS;

    public ResultadoTransaccion(MessageWS messageWS) {
        this.messageWS = messageWS;
    }

    public ResultadoTransaccion(InformacionTransaccion informacionTransaccion,
                                MessageWS messageWS) {
        this.informacionTransaccion = informacionTransaccion;
        this.messageWS = messageWS;
    }

    public ResultadoTransaccion(InformacionSaldo informacionSaldo,
                                MessageWS messageWS) {
        this.informacionSaldo = informacionSaldo;
        this.messageWS = messageWS;
    }

    public InformacionTransaccion getInformacionTransaccion() {
        return informacionTransaccion;
    }

    public void setInformacionTransaccion(InformacionTransaccion informacionTransaccion) {
        this.informacionTransaccion = informacionTransaccion;
    }

    public InformacionSaldo getInformacionSaldo() {
        return informacionSaldo;
    }

    public void setInformacionSaldo(InformacionSaldo informacionSaldo) {
        this.informacionSaldo = informacionSaldo;
    }

    public MessageWS getMessageWS() {
        return messageWS;
    }

    public void setMessageWS(MessageWS messageWS) {
        this.messageWS = messageWS;
    }

}