package com.cofrem.transacciones.Modules.ModuleReports.ReimpresionScreenActivity.ui;

public interface ReimpresionScreenView {

    /**
     * Metodo para manejar la existencia un recibo por numero de cargo
     */
    void handleVerifyExistenceReciboPorNumCargoSuccess();


    /**
     * Metodo para manejar la NO existencia un recibo por numero de cargo
     */
    void handleVerifyExistenceReciboPorNumCargoError();


}
