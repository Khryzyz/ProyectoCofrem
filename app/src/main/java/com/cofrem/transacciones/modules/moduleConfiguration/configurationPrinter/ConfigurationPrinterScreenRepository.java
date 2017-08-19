package com.cofrem.transacciones.modules.moduleConfiguration.configurationPrinter;

import android.content.Context;

import com.cofrem.transacciones.models.ConfigurationPrinter;

public interface ConfigurationPrinterScreenRepository {

    void VerifyConfigurationInitialPrinter(Context context);

    /**
     * metodo que se encarga guardar la configuracion de la impresora
     */
    void saveConfigurationPrinter(Context context, ConfigurationPrinter configuration);


    /**
     * metodo que se encarga de realizar una impresion de prueba
     */
    void imprimirPrueba(Context context, int gray);
}
