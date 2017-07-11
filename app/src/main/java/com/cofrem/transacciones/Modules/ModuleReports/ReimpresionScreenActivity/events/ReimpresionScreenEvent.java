package com.cofrem.transacciones.Modules.ModuleReports.ReimpresionScreenActivity.events;

import com.cofrem.transacciones.models.Transaccion;

import java.util.ArrayList;

public class ReimpresionScreenEvent {

    public final static int onVerifySuccess = 0;
    public final static int onVerifyExistenceUltimoReciboSuccess = 1;
    public final static int onVerifyExistenceUltimoReciboError = 2;
    public final static int onVerifyExistenceReciboPorNumCargoSuccess = 3;
    public final static int onVerifyExistenceReciboPorNumCargoError = 4;
    public final static int onVerifyExistenceReporteDetalleSuccess = 5;
    public final static int onVerifyExistenceReporteDetalleError = 6;
    public final static int onVerifyExistenceReporteGeneralSuccess = 7;
    public final static int onVerifyExistenceReporteGeneralError = 8;
    public final static int onVerifyClaveAdministradorSuccess = 9;
    public final static int onVerifyClaveAdministradorError = 10;


    // Variable que maneja los tipos de eventos
    private int eventType;

    // Variable que maneja los mensajes de error de los eventos
    private String errorMessage;

    // Variable que maneja los resultados de las transacciones
    private Transaccion modelTransaccion;

    // Variable que maneja los resultados de las transacciones
    private ArrayList<Transaccion> listaTransacciones;

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

    public ArrayList<Transaccion> getListaTransacciones() {
        return listaTransacciones;
    }

    public void setListaTransacciones(ArrayList<Transaccion> listaTransacciones) {
        this.listaTransacciones = listaTransacciones;
    }
}
