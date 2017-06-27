package com.cofrem.transacciones.SplashScreen.ui;

/**
 * Interface usada en la actividad de la Pantalla principal
 */
public interface SplashScreenView {

    /**
     * Metodo para mostrar la barra de progreso
     */
    void showProgress();

    /**
     * Metodo para ocultar la barra de progreso
     */
    void hideProgress();

    /**
     * Metodo para manejar la existencia de la configuracion inicial
     */
    void handleVerifyInitialConfigSuccess();

    /**
     * Metodo para manejar la NO existencia de la configuracion inicial
     */
    void handleVerifyInitialConfigError();

    /**
     * Metodo para manejar la verificacion exitosa
     */
    void handleVerifySuccess();

    /**
     * Metodo para manejar la verificacion erronea
     */
    void handleVerifyError();

    /**
     * Metodo para manejar la conexion a internet exitosa
     */
    void handleInternetConnectionSuccess();

    /**
     * Metodo para manejar la conexion a internet erronea
     */
    void handleInternetConnectionError();

    /**
     * Metodo para manejar la conexion al dispositivo lector de banda magnetica exitosa
     */
    void handleMagneticReaderDeviceSuccess();

    /**
     * Metodo para manejar la conexion al dispositivo lector de banda magnetica erronea
     */
    void handleMagneticReaderDeviceError();

    /**
     * Metodo para manejar la conexion al dispositivo NFC exitosa
     */
    void handleNFCDeviceSuccess();

    /**
     * Metodo para manejar la conexion al dispositivo NFC erronea
     */
    void handleNFCDeviceError();

    /**
     * Metodo para manejar la conexion al dispositivo de impresion exitosa
     */
    void handlePrinterDeviceSuccess();

    /**
     * Metodo para manejar la conexion al dispositivo de impresion erronea
     */
    void handlePrinterDeviceError();


}
