package com.cofrem.transacciones.Modules.ModuleReports.ReimpresionScreenActivity.ui;

import com.cofrem.transacciones.models.Transaccion;

public interface ReimpresionScreenView {

    /**
     * Metodo para manejar la existencia un recibo por numero de cargo
     */
    void handleVerifyExistenceReciboPorNumCargoSuccess(Transaccion modelTransaccion);


    /**
     * Metodo para manejar la NO existencia un recibo por numero de cargo
     */
    void handleVerifyExistenceReciboPorNumCargoError();


}
