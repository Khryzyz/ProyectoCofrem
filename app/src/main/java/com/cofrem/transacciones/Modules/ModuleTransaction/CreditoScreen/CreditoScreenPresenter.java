package com.cofrem.transacciones.Modules.ModuleTransaction.CreditoScreen;

import android.content.Context;

import com.cofrem.transacciones.Modules.ModuleTransaction.CreditoScreen.events.CreditoScreenEvent;
import com.cofrem.transacciones.models.Transaccion;

public interface CreditoScreenPresenter {

    /**
     * Metodo para obtener el numero de tarjeta desde el dispositivo
     *
     * @param context
     * @param transaccion
     */
    void registrarTransaccion(Context context, Transaccion transaccion);

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
