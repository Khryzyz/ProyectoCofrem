package com.cofrem.transacciones.Modules.ModuleReports.ReimpresionScreenActivity;

import android.content.Context;

interface ReimpresionScreenInteractor {


    void validarExistenciaUltimoRecibo(Context context);


    /**
     * Metodo que Reimprime unm recibo por numero de cargo:
     *
     * @param context , numCargo
     *
     */
    void validarExistenciaReciboConNumCargo(Context context,String numCargo);
}
