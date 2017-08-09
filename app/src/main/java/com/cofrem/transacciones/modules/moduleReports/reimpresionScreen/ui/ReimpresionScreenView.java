package com.cofrem.transacciones.modules.moduleReports.reimpresionScreen.ui;

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

    /**
     * Metodo para manejar la existencia de un recibo por numero de cargo
     */
    void handleVerifyClaveAdministradorSuccess();


    /**
     * Metodo para manejar la existencia de un recibo por numero de cargo
     */
    void handleVerifyClaveAdministradorError();


    /**
     * Metodo para manejar la existencia de recibos para reporte por detalle
     */
    void handleVerifyExistenceReporteDetalleSuccess();

    /**
     * Metodo para manejar la existencia de recibos para reporte por detalle
     */
    void handleVerifyExistenceReporteDetalleError();


    /**
     * Metodo para manejar la impresion del ultimo recibo exitosa
     */
    void handleImprimirUltimoReciboSuccess();

    /**
     * Metodo para manejar la impresion del ultimo recibo Error
     */
    void handleImprimirUltimoReciboError(String error);

    /**
     * Metodo para manejar la impresion exitosa del recibo por numero de cargo
     */
    void handleImprimirReciboPorNumCargoSuccess();

    /**
     * Metodo para manejar la impresion Error del recibo por numero de cargo
     */
    void handleImprimirReciboPorNumCargoError(String error);

    /**
     * Metodo para manejar la impresion exitosa del recibo por numero de cargo
     */
    void handleImprimirReporteDetalleSuccess();

    /**
     * Metodo para manejar la impresion Error del recibo por numero de cargo
     */
    void handleImprimirReporteDetalleError(String error);

    /**
     * Metodo para manejar la impresion exitosa del recibo por numero de cargo
     */
    void handleImprimirReporteGeneralSuccess();

    /**
     * Metodo para manejar la impresion Error del recibo por numero de cargo
     */
    void handleImprimirReporteGeneralError(String error);

}
