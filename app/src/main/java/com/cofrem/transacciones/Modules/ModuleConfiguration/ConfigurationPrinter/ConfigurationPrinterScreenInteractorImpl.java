package com.cofrem.transacciones.Modules.ModuleConfiguration.ConfigurationPrinter;


import android.content.Context;

import com.cofrem.transacciones.models.ConfigurationPrinter;

class ConfigurationPrinterScreenInteractorImpl implements ConfigurationPrinterScreenInteractor {
    /**
     * #############################################################################################
     * Declaracion de componentes y variables
     * #############################################################################################
     */

    /**
     * #############################################################################################
     * Instanciamientos de las clases
     * #############################################################################################
     */
    private ConfigurationPrinterScreenRepository configurationPrinterScreenRepository;

    /**
     * #############################################################################################
     * Constructor de la clase
     * #############################################################################################
     */
    public ConfigurationPrinterScreenInteractorImpl() {

        configurationPrinterScreenRepository = new ConfigurationPrinterScreenRepositoryImpl();

    }
    /**
     * #############################################################################################
     * Metodos sobrecargados de la interface
     * #############################################################################################
     */

    @Override
    public void saveConfigurationPrinter(Context context, ConfigurationPrinter configuration) {
        configurationPrinterScreenRepository.saveConfigurationPrinter(context,configuration);
    }

    /**
     *
     */
    @Override
    public void VerifyConfigurationInitialPrinter(Context context) {
        //Valida el acceso a la app
        configurationPrinterScreenRepository.VerifyConfigurationInitialPrinter(context);
    }
}
