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
    void handleValorTransaccionNoValido();

    /**
     * Metodo para manejar la el valor valido en la transaccion
     */
    void handleValorTransaccionValido(int valorTransaccion);

}
