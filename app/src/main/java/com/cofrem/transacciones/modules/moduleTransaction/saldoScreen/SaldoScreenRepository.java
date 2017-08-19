package com.cofrem.transacciones.modules.moduleTransaction.saldoScreen;

import android.content.Context;

import com.cofrem.transacciones.models.Transaccion;

public interface SaldoScreenRepository {

    void registrarTransaccion(Context context, Transaccion transaccion);

    /**
     * Metodo que imprime el recibo de la consulta de saldo
     *
     * @param context
     */
    void imprimirRecibo(Context context);
}
