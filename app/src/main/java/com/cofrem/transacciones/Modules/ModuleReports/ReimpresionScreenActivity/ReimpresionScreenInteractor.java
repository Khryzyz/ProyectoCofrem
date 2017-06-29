package com.cofrem.transacciones.Modules.ModuleReports.ReimpresionScreenActivity;

import android.content.Context;

interface ReimpresionScreenInteractor {


    void imprimirUltimoRecibo(Context context);


    /**
     * Metodo que Reimprime unm recibo por numero de cargo:
     *
     * @param context , numCargo
     *
     */
    void imprimirConNumCargo(Context context,String numCargo);
}
