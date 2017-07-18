package com.cofrem.transacciones.Modules.ModuleTransaction.CreditoScreen.events;

public class CreditoScreenEvent {

    public final static int onTransaccionWSRegisterSuccess = 0;
    public final static int onTransaccionWSRegisterError = 1;
    public final static int onTransaccionDBRegisterSuccess = 2;
    public final static int onTransaccionDBRegisterError = 3;


    // Variable que maneja los tipos de eventos
    private int eventType;

    // Variable que maneja los mensajes de error de los eventos
    private String errorMessage;

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
}
