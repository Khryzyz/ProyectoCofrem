package com.cofrem.transacciones.SplashScreen.events;

/**
 * Clase que maneja los eventos de la Pantalla de Inicio
 */
public class SplashScreenEvent {

    /**
     * Eventos asociados a la Pantalla de Inicio
     */
    // Eventos de verificacion de disponibilidad del sistema
    public final static int onVerifyInitialConfigExiste = 0;
    public final static int onVerifyInitialConfigNoExiste = 1;
    public final static int onVerifyInitialConfigNoValida = 2;
    public final static int onVerifySuccess = 3;
    public final static int onVerifyError = 4;
    public final static int onInternetConnectionSuccess = 5;
    public final static int onInternetConnectionError = 6;
    public final static int onMagneticReaderDeviceSuccess = 7;
    public final static int onMagneticReaderDeviceError = 8;
    public final static int onNFCDeviceSuccess = 9;
    public final static int onNFCDeviceError = 10;
    public final static int onPrinterDeviceSuccess = 11;
    public final static int onPrinterDeviceError = 12;


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
