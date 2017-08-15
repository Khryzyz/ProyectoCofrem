package com.cofrem.transacciones.modules.moduleTransaction.anulacionScreen;

import android.content.Context;

import com.cofrem.transacciones.models.Transaccion;

interface AnulacionScreenInteractor {

    /**
     * @param context
     * @param passAdmin
     */
    void validarPasswordAdministrador(Context context, String passAdmin);

    /**
     * @param context
     * @param numeroCargo
     */
    void obtenerValorTransaccion(Context context, String numeroCargo);

    /**
     * @param context
     * @param transaccion
     */
    void registrarTransaccion(Context context, Transaccion transaccion);


}
