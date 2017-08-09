package com.cofrem.transacciones.Modules.ModuleConfiguration.ConfigurationPrinter;

import android.content.Context;

import com.cofrem.transacciones.models.ConfigurationPrinter;

public interface ConfigurationPrinterScreenRepository {

    void VerifyConfigurationInitialPrinter(Context context);

    /**
     * metodo que se encarga guardar la configuracion de la impresora
     */
    void saveConfigurationPrinter(Context context, ConfigurationPrinter configuration);
}
