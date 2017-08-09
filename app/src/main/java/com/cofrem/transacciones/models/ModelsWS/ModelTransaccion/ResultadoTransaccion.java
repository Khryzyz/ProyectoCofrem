package com.cofrem.transacciones.models.ModelsWS.ModelTransaccion;

import com.cofrem.transacciones.models.ModelsWS.MessageWS;

public class Transaction {


    //Modelo usado en la respuesta del WS para la respuesta transacResult
    private ResultadoTransaccion resultadoTransaccion;
    private MessageWS messageWS;

    public Transaction(MessageWS messageWS) {
        this.messageWS = messageWS;
    }

    public Transaction(ResultadoTransaccion resultadoTransaccion,
                       MessageWS messageWS) {
        this.resultadoTransaccion = resultadoTransaccion;
        this.messageWS = messageWS;
    }

    public ResultadoTransaccion getResultadoTransaccion() {
        return resultadoTransaccion;
    }

    public void setResultadoTransaccion(ResultadoTransaccion resultadoTransaccion) {
        this.resultadoTransaccion = resultadoTransaccion;
    }

    public MessageWS getMessageWS() {
        return messageWS;
    }

    public void setMessageWS(MessageWS messageWS) {
        this.messageWS = messageWS;
    }

}