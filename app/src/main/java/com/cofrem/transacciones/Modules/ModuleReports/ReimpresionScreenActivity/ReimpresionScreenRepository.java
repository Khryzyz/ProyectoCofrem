package com.cofrem.transacciones.Modules.ModuleReports.ReimpresionScreenActivity;

import android.content.Context;

public interface ReimpresionScreenRepository {

    void validateAcces();


    /**
     * Metodo que validara la existencua de un ultimo recibo
     *  - Si existe un ultimo recibo enviara un modelo de la transaccion
     *  - si NO exite un recibo solo notifica que no existe
     *
     * @param context , numCargo
     */
    void validarExistenciaUltimoRecibo(Context context);

    /**
     * Metodo que validara la existencua de recibo especificando el numero de cargo
     *  - Si existe un recibo con el numero de cargo enviara un modelo de la transaccion
     *  - si NO exite un recibo solo notifica que no existe
     *
     * @param context , numCargo
     */
    void validarExistenciaReciboConNumCargo(Context context,String numCargo);

    /**
     * Metodo que validara la clave del administrador para dar paso a la reimpresion de recibos:
     *
     * @param context , numCargo
     */
    void validarClaveAdministrador(Context context,String clave);

    /**
     * Metodo que validara la existencua de recibos
     *  - Si existen recibos enviara una lista de modelo de la transaccion
     *  - si NO exite un recibo solo notifica que no existen
     *
     * @param context , numCargo
     */
    void validarExistenciaDetalleRecibos(Context context);




}
