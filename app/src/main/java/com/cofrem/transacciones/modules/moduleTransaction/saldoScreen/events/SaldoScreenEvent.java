package com.cofrem.transacciones.modules.moduleTransaction.saldoScreen.events;

import com.cofrem.transacciones.models.modelsWS.modelTransaccion.InformacionSaldo;

public class SaldoScreenEvent {

    public final static int onTransaccionSuccess = 0;
    public final static int onTransaccionWSRegisterError = 1;
    public final static int onTransaccionWSConexionError = 2;
    public final static int onImprecionReciboSuccess = 3;
    public final static int onImprecionReciboError = 4;

    // Variable que maneja los tipos de eventos
    private int eventType;

    // Variable que maneja los mensajes de error de los eventos
    private String errorMessage;

    private InformacionSaldo informacionSaldo;

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

    public InformacionSaldo getInformacionSaldo() {
        return informacionSaldo;
    }

    public void setInformacionSaldo(InformacionSaldo informacionSaldo) {
        this.informacionSaldo = informacionSaldo;
    }
}
