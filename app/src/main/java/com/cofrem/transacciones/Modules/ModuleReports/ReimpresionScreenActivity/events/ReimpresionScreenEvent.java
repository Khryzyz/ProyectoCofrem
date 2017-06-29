package com.cofrem.transacciones.Modules.ModuleReports.ReimpresionScreenActivity.events;

import com.cofrem.transacciones.models.Transaccion;

public class ReimpresionScreenEvent {

    public final static int onVerifySuccess = 0;
    public final static int onVerifyExistenceReciboPorNumCargoSuccess = 1;
    public final static int onVerifyExistenceReciboPorNumCargoError = 2;


    // Variable que maneja los tipos de eventos
    private int eventType;

    // Variable que maneja los mensajes de error de los eventos
    private String errorMessage;

    // Variable que maneja los resultados de las transacciones
    private Transaccion modelTransaccion;

    //Getters y Setters de la clase

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Transaccion getModelTransaccion() {
        return modelTransaccion;
    }

    public void setModelTransaccion(Transaccion modelTransaccion) {
        this.modelTransaccion = modelTransaccion;
    }
}
