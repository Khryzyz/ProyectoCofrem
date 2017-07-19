package com.cofrem.transacciones.Modules.ModuleTransaction.SaldoScreen;

import android.content.Context;

import com.cofrem.transacciones.Modules.ModuleTransaction.SaldoScreen.events.SaldoScreenEvent;
import com.cofrem.transacciones.models.Transaccion;

public interface SaldoScreenPresenter {

    //Todo: crear metodos presentador

    /**
     * metodo presentador
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
     * @param saldoScreenEvent
     */
    void onEventMainThread(SaldoScreenEvent saldoScreenEvent);

}
