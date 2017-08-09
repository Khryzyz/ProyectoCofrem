package com.cofrem.transacciones.Modules.ModuleConfiguration.ConfigurationPrinter;

import android.content.Context;

import com.cofrem.transacciones.models.ConfigurationPrinter;

interface ConfigurationPrinterScreenInteractor {

    void VerifyConfigurationInitialPrinter(Context context);

    /**
     * metodo que se encarga guardar la configuracion de la impresora
     */
    void saveConfigurationPrinter(Context context, ConfigurationPrinter configuration);
}
