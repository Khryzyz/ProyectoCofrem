package com.cofrem.transacciones.modules.moduleConfiguration.testCommunicationScreen.ui;

public interface TestCommunicationScreenView {

    /**
     * Metodo para manejar la verificacion exitosa
     */
    void handleVerifySuccess();

    /**
     * Metodo para manejar el test de comunicacion exitoso
     */
    void handleTestComunicationSuccess();

    /**
     * Metodo para manejar el test de comunicacion con error
     */
    void handleTestComunicationError(String error);

    /**
     * Metodo para manejar la el error en la comunicacion con el WS
     */
    void handleTransaccionWSConexionError();


    /**
     * Metodo para navegar a la ventana de transacciones
     */
    void navigateTransactionView();

    /**
     * Metodo para navegar a la ventana de transacciones
     */
    void navigateToReportsView();

    /**
     * Metodo para navegar a la ventana de transacciones
     */
    void navigateToConfigurationView();

}
