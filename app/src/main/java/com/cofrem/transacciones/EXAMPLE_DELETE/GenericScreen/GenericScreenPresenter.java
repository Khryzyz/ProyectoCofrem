package com.cofrem.transacciones.EXAMPLE_DELETE.GenericScreen;

import com.cofrem.transacciones.EXAMPLE_DELETE.GenericScreen.events.GenericScreenEvent;

public interface GenericScreenPresenter {

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
    void onEventMainThread(GenericScreenEvent splashScreenEvent);

}
