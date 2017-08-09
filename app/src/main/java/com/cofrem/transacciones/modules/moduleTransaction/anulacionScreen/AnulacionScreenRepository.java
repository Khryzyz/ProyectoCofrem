package com.cofrem.transacciones.modules.moduleTransaction.anulacionScreen;

import android.content.Context;

public interface AnulacionScreenRepository {

    /**
     * Metodo que valida
     */
    void validarPasswordAdministrador(Context context, String pass);
}
