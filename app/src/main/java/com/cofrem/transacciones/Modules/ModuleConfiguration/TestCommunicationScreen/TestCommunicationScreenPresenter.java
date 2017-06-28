package com.cofrem.transacciones.Modules.ModuleConfiguration.TestCommunicationScreen;

import com.cofrem.transacciones.Modules.ModuleConfiguration.TestCommunicationScreen.events.TestCommunicationScreenEvent;

public interface TestCommunicationScreenPresenter {

    //Todo: crear metodos presentador

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
    void onEventMainThread(TestCommunicationScreenEvent splashScreenEvent);

}
