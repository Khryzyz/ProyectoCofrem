package com.cofrem.transacciones.modules.moduleConfiguration.configurationPrinter.ui;

import com.cofrem.transacciones.models.ConfigurationPrinter;

public interface ConfigurationPrinterScreenView {

    /**
     * Metodo para manejar la verificacion exitosa
     */
    void handleVerifyConfigurationInitialPrinterSuccess(ConfigurationPrinter configuration);

    /**
     * Metodo para manejar la verificacion con error
     */
    void handleVerifyConfigurationInitialPrinterError();

    /**
     * Metodo para manejar el insert exitoso de la configuración de la impresora
     */
    void handleSaveConfigurationPrinterSuccess();

    /**
     * Metodo para manejar el insert con error de la configuración de la impresora
     */
    void handleSaveConfigurationPrinterError();
}
