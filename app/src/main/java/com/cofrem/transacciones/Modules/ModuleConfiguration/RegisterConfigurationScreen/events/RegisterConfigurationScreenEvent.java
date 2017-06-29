package com.cofrem.transacciones.Modules.ModuleConfiguration.RegisterConfigurationScreen.events;

public class RegisterConfigurationScreenEvent {

    public final static int onValorAccesoValido = 0;
    public final static int onValorAccesoNoValido = 1;
    public final static int onValorAccesoError= 2;

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
