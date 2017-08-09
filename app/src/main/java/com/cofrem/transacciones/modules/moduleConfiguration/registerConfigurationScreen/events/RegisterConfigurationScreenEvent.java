package com.cofrem.transacciones.modules.moduleConfiguration.registerConfigurationScreen.events;

public class RegisterConfigurationScreenEvent {

    public final static int onValorAccesoValido = 0;
    public final static int onValorAccesoNoValido = 1;
    public final static int onValorAccesoError = 2;

    public final static int onRegistroConfigConexionSuccess = 3;
    public final static int onRegistroConfigConexionError = 4;

    public final static int onInformacionDispositivoSuccess = 5;
    public final static int onInformacionDispositivoErrorConexion = 6;
    public final static int onInformacionDispositivoErrorInformacion = 7;

    public final static int onProccessInformacionEstablecimientoSuccess = 8;
    public final static int onProccessInformacionEstablecimientoError = 9;


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
