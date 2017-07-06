package com.cofrem.transacciones.SplashScreen;

import android.content.Context;

import com.cofrem.transacciones.SplashScreen.events.SplashScreenEvent;

public interface SplashScreenPresenter {

    /**
     * Metodo para la creacion del presentador
     */
    void onCreate();

    /**
     * Metodo para la destruccion del presentador
     */
    void onDestroy();

    /**
     * Metodo para la verificacion de la existencia de la configuración inicial
     *
     * @param context
     */
    void validateInitialConfig(Context context);

    /**
     * Metodo para la verificacion de los datos
     *
     * @param context
     */
    void validateAccess(Context context);

    /**
     * Metodo para recibir los eventos generados
     *
     * @param splashScreenEvent
     */
    void onEventMainThread(SplashScreenEvent splashScreenEvent);

}
