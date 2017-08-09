package com.cofrem.transacciones.splashScreen;

import android.content.Context;

public interface SplashScreenRepository {

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
    void validateAcces(Context context);

}
