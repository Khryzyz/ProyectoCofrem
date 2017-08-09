package com.cofrem.transacciones.modules.moduleTransaction.anulacionScreen;

import android.content.Context;

import com.cofrem.transacciones.modules.moduleTransaction.anulacionScreen.events.AnulacionScreenEvent;
import com.cofrem.transacciones.models.Transaccion;

public interface AnulacionScreenPresenter {

    /**
     * metodo presentador
     */
    void validarPasswordAdministrador(Context context, String pass);

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
     * @param anulacionScreenEvent
     */
    void onEventMainThread(AnulacionScreenEvent anulacionScreenEvent);

}
