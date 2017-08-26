package com.cofrem.transacciones.modules.moduleTransaction.anulacionScreen.ui;

public interface AnulacionScreenView {

    /**
     * Metodo para manejar la verificacion exitosa de la clave de administracion
     */
    void handleClaveAdministracionValida();

    /**
     * Metodo para manejar la verificacion erronea de la clave de administracion
     */
    void handleClaveAdministracionNoValida();

    /**
     * Metodo para manejar error en la verificacion de la clave de administracion
     */
    void handleClaveAdministracionError();

    /**
     * Metodo para manejar la el valor no valido en la transaccion
     */
    void handleNumeroCargoNoRelacionado();

    /**
     * Metodo para manejar la el valor valido en la transaccion
     */
    void handleNumeroCargoRelacionado(int valorTransaccion);

    /**
     * Metodo para manejar la el valor no valido en la transaccion
     */
    void handleTransaccionSuccess();

    /**
     * Metodo para manejar la el valor no valido en la transaccion
     */
    void handleTransaccionWSConexionError();

    /**
     * Metodo para manejar la el valor no valido en la transaccion
     *
     * @param errorMessage
     */
    void handleTransaccionWSRegisterError(String errorMessage);

    /**
     * Metodo para manejar la el valor no valido en la transaccion
     */
    void handleTransaccionDBRegisterError();

}
