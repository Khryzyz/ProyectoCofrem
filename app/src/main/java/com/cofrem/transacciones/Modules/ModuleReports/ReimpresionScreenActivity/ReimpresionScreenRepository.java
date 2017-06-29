package com.cofrem.transacciones.Modules.ModuleReports.ReimpresionScreenActivity;

import android.content.Context;

public interface ReimpresionScreenRepository {

    void validateAcces();

    void imprimirUltimoRecibo(Context context);
    
    /**
     * Metodo que Reimprime unm recibo por numero de cargo:
     *
     * @param context , numCargo
     *
     */
    void imprimirConNumCargo(Context context,String numCargo);

    /**
     * Metodo que Reimprime unm recibo por numero de cargo:
     *
     * @param context , numCargo
     *
     */
    void validadExistenciaReciboConNumCargo(Context context,String numCargo);

}
