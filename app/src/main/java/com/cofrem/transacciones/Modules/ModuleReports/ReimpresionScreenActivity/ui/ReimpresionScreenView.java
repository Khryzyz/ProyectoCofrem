package com.cofrem.transacciones.Modules.ModuleReports.ReimpresionScreenActivity.ui;

import com.cofrem.transacciones.models.Transaccion;

public interface ReimpresionScreenView {

    /**
     * Metodo para manejar la existencia de un Ultimo recibo para reimprimir
     */
    void handleVerifyExistenceUltimoReciboSuccess(Transaccion modelTransaccion);


    /**
     * Metodo para manejar la NO existencia de un Ultimo recibo para reimprimir
     */
    void handleVerifyExistenceUltimoReciboError();

    /**
     * Metodo para manejar la existencia de un recibo por numero de cargo
     */
    void handleVerifyExistenceReciboPorNumCargoSuccess(Transaccion modelTransaccion);


    /**
     * Metodo para manejar la NO existencia de un recibo por numero de cargo
     */
    void handleVerifyExistenceReciboPorNumCargoError();


}
