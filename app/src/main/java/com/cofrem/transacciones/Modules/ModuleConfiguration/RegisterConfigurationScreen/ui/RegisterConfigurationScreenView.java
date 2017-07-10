package com.cofrem.transacciones.Modules.ModuleConfiguration.RegisterConfigurationScreen.ui;

public interface RegisterConfigurationScreenView {

    /**
     * Metodo para manejar el valor de acceso valido
     */
    void handlePasswordTecnicoValido();

    /**
     * Metodo para manejar el valor de acceso NO valido
     */
    void handlePasswordTecnicoNoValido();

    /**
     * Metodo para manejar el error en la configuracion de valor de acceso
     */
    void handlePasswordTecnicoError();

    /**
     * Metodo para manejar el registro de la configuracion correcto
     */
    void handleRegistroConfigConexionSuccess();

    /**
     * Metodo para manejar el registro de la configuracion erroneo
     */
    void handleRegistroConfigConexionError();

    /**
     * Metodo para manejar la consulta de la informacion del dispositivo
     */
    void handleInformacionDispositivoSuccess();

    /**
     * Metodo para manejar el registro de informacion del dispositivo incorrecto
     */
    void handleInformacionDispositivoErrorConexion();

    /**
     * Metodo para manejar el registro de informacion del dispositivo incorrecto
     */
    void handleInformacionDispositivoErrorInformacion();

    /**
     * Metodo para manejar el registro de la informacion del dispositivo correcto
     */
    void handleProccessInformacionEstablecimientoSuccess();

    /**
     * Metodo para manejar el registro de la informacion del dispositivo erronea
     */
    void handleProccessInformacionEstablecimientoError();

}
