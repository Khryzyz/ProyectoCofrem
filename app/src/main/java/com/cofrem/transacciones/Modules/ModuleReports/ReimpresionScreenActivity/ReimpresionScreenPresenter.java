package com.cofrem.transacciones.Modules.ModuleReports.ReimpresionScreenActivity;

import android.content.Context;

import com.cofrem.transacciones.Modules.ModuleReports.ReimpresionScreenActivity.events.ReimpresionScreenEvent;

public interface ReimpresionScreenPresenter {

    //Todo: crear metodos presentador

    /**
     * metodo presentador
     */
    void VerifySuccess();

    /**
     * Metodo para la creacion del presentador
     */
    void onCreate();

    /**
     * Metodo para la destruccion del presentador
     */
    void onDestroy();

    /**
     * Metodo que Reimprime el ultimo recibo:
     *
     * @param context
     */
    void imprimirUltimoRecibo(Context context);

    /**
     * Metodo que Reimprime unm recibo por numero de cargo:
     *
     * @param context , numCargo
     *
     */
    void imprimirConNumCargo(Context context,String numCargo);


    /**
     * Metodo para recibir los eventos generados
     *
     * @param splashScreenEvent
     */
    void onEventMainThread(ReimpresionScreenEvent splashScreenEvent);
}
