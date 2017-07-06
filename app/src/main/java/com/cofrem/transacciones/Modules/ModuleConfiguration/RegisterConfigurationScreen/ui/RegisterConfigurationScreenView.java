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

}
