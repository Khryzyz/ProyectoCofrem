package com.cofrem.transacciones.SplashScreen;

import android.content.Context;

public interface SplashScreenInteractor {

    /**
     * Metodo que verifica:
     * - La existencia de la configuración inicial
     *
     * @param context
     */
    void validateInitialConfig(Context context);

    /**
     * Metodo que verifica:
     * - Existencia de datos
     * - Validez de datos
     *
     * @param context
     */
    void validateAccess(Context context);

}
