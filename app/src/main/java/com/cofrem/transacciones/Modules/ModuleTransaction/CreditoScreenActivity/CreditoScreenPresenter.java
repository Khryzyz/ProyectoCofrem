package com.cofrem.transacciones.Modules.ModuleTransaction.CreditoScreenActivity;

import android.content.Context;

import com.cofrem.transacciones.Modules.ModuleTransaction.CreditoScreenActivity.events.CreditoScreenEvent;

public interface CreditoScreenPresenter {

    //Todo: crear metodos presentador

    /**
     * metodo presentador
     */
    void VerifySuccess(Context context);

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
     * @param creditoScreenEvent
     */
    void onEventMainThread(CreditoScreenEvent creditoScreenEvent);

}
