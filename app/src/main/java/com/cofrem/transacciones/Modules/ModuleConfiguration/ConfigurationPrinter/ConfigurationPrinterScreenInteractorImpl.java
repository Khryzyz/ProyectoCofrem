package com.cofrem.transacciones.Modules.ModuleConfiguration.ConfigurationPrinter;

import android.content.Context;

public class ConfigurationPrinterScreenInteractorImpl implements ConfigurationPrinterScreenInteractor {

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

    /**
     * Metodo que verifica:
     *  - La existencia de la configuración inicial
     *  - En caso de no existir mostrará la vista de configuración
     *  - En caso de existir validara el acceso
     *
     * @param context
     */
    @Override
    public void validateInitialConfig(Context context) {
        configurationPrinterScreenRepository.validateInitialConfig(context);
    }

    /**
     * Metodo que verifica:
     *  - Conexion a internet
     *  - Existencia datos en DB interna
     *  - Coherencia de datos con el servidor
     *
     * @param context
     */
    @Override
    public void validateAccess(Context context) {
        //Valida el acceso a la app
        configurationPrinterScreenRepository.validateAcces(context);
    }
}
