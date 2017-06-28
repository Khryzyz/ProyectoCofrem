package com.cofrem.transacciones.Modules.ModuleConfiguration.RegisterConfigurationScreen;

import com.cofrem.transacciones.Modules.ModuleConfiguration.RegisterConfigurationScreen.events.RegisterConfigurationScreenEvent;

public interface RegisterConfigurationScreenPresenter {

    /**
     * metodo presentador
     */
    void VerifySuccess();

    /**
     * Metodo para la creacion del presentador
     */
    void onCreate();

    /**
     * Metodo para la destruccion del presentador
     */
    void onDestroy();


    /**
     * Metodo para recibir los eventos generados
     *
     * @param splashScreenEvent
     */
    void onEventMainThread(RegisterConfigurationScreenEvent splashScreenEvent);

}
