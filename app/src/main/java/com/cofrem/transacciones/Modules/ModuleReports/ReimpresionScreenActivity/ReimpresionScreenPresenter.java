package com.cofrem.transacciones.Modules.ModuleReports.ReimpresionScreenActivity;

import com.cofrem.transacciones.Modules.ModuleReports.ReimpresionScreenActivity.events.ReimpresionScreenEvent;

public interface ReimpresionScreenPresenter {

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
    void onEventMainThread(ReimpresionScreenEvent splashScreenEvent);

}
