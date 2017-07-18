package com.cofrem.transacciones.Modules.ModuleTransaction.CreditoScreen.ui;

public interface CreditoScreenView {

    /**
     * Metodo para manejar la transaccion del Web Service Correcta
     */
    void handleTransaccionWSRegisterSuccess();

    /**
     * Metodo para manejar la transaccion del Web Service Erronea
     */
    void handleTransaccionWSRegisterError();

    /**
     * Metodo para manejar la transaccion de la Base de datos Correcta
     */
    void handleTransaccionDBRegisterSuccess();

    /**
     * Metodo para manejar la transaccion de la Base de datos Erronea
     */
    void handleTransaccionDBRegisterError();
}
