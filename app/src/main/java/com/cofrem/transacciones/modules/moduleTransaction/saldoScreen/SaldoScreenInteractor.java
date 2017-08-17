package com.cofrem.transacciones.modules.moduleTransaction.saldoScreen;

import android.content.Context;
import com.cofrem.transacciones.models.Transaccion;

interface SaldoScreenInteractor {

    void registrarTransaccion(Context context, Transaccion transaccion);

}
