package com.cofrem.transacciones.Modules.ModuleConfiguration.RegisterConfigurationScreen.ui;

public interface RegisterConfigurationScreenView {

    /**
     * Metodo para manejar el valor de acceso valido
     */
    void handleValorAccesoValido();

    /**
     * Metodo para manejar el valor de acceso NO valido
     */
    void handleValorAccesoNoValido();

    /**
     * Metodo para manejar el error en la configuracion de valor de acceso
     */
    void handleValorAccesoError();

    /**
     * Metodo para manejar el registro de conexion correcto
     */
    void handleRegistroConexionSuccess();

    /**
     * Metodo para manejar el registro de conexion incorrecto
     */
    void handleRegistroConexionError();

    /**
     * Metodo para manejar el registro de informacion del dispositivo correcto
     */
    void handleRegistroInformacionDispositivoSuccess();

    /**
     * Metodo para manejar el registro de informacion del dispositivo incorrecto
     * por error en el registro en la base de datos
     */
    void handleRegistroInformacionDispositivoErrorDatabase();

    /**
     * Metodo para manejar el registro de informacion del dispositivo incorrecto
     * por error en la conexion con el web service
     */
    void handleRegistroInformacionDispositivoErrorConnection();

}
