package com.cofrem.transacciones.Modules.ModuleTransaction.AnulacionScreen;

import com.cofrem.transacciones.Modules.ModuleTransaction.AnulacionScreen.events.AnulacionScreenEvent;

public interface AnulacionScreenPresenter {

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
     * @param anulacionScreenEvent
     */
    void onEventMainThread(AnulacionScreenEvent anulacionScreenEvent);

}
