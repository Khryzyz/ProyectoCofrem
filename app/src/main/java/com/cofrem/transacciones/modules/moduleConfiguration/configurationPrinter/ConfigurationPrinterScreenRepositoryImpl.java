package com.cofrem.transacciones.modules.moduleConfiguration.configurationPrinter;

import android.content.Context;

import com.cofrem.transacciones.modules.moduleConfiguration.configurationPrinter.events.ConfigurationPrinterScreenEvent;
import com.cofrem.transacciones.database.AppDatabase;
import com.cofrem.transacciones.lib.EventBus;
import com.cofrem.transacciones.lib.GreenRobotEventBus;
import com.cofrem.transacciones.models.ConfigurationPrinter;

public class ConfigurationPrinterScreenRepositoryImpl implements ConfigurationPrinterScreenRepository {
    /**
     * #############################################################################################
     * Declaracion de componentes y variables
     * #############################################################################################
     */


    /**
     * #############################################################################################
     * Constructor de la clase
     * #############################################################################################
     */
    public ConfigurationPrinterScreenRepositoryImpl() {
    }

    /**
     * #############################################################################################
     * Metodos sobrecargados de la interface
     * #############################################################################################
     */

    /**
     *
     */
    @Override
    public void VerifyConfigurationInitialPrinter(Context context) {

        ConfigurationPrinter configuracion = AppDatabase.getInstance(context).getConfigurationPrinter();

        if(configuracion!=null){
            postEvent(ConfigurationPrinterScreenEvent.onVerifyConfigurationInitialSuccess,configuracion);
        }else{
            postEvent(ConfigurationPrinterScreenEvent.onVerifyConfigurationInitialError);
        }

    }

    @Override
    public void saveConfigurationPrinter(Context context, ConfigurationPrinter configuration) {
        if(AppDatabase.getInstance(context).insertConfigurationPrinter(configuration)){
            postEvent(ConfigurationPrinterScreenEvent.onSaveConfigurationPrinterSuccess);
        }else{
            postEvent(ConfigurationPrinterScreenEvent.onSaveConfigurationPrinterError);
        }
    }
/**
     * #############################################################################################
     * Metodo propios de la clase
     * #############################################################################################
     */

    /**
     * Metodo que registra los eventos
     *
     * @param type
     * @param errorMessage
     */
    private void postEvent(int type, ConfigurationPrinter configuracion, String errorMessage) {
        ConfigurationPrinterScreenEvent configurationPrinterScreenEvent = new ConfigurationPrinterScreenEvent();
        configurationPrinterScreenEvent.setEventType(type);
        if (errorMessage != null) {
            configurationPrinterScreenEvent.setErrorMessage(errorMessage);
        }
        if (configuracion != null) {
            configurationPrinterScreenEvent.setConfigurationPrinter(configuracion);
        }

        EventBus eventBus = GreenRobotEventBus.getInstance();

        eventBus.post(configurationPrinterScreenEvent);
    }

    /**
     * Sobrecarga del metodo postevent
     *
     * @param type
     */
    private void postEvent(int type) {

        postEvent(type, null, null);

    }

    /**
     * Sobrecarga del metodo postevent
     *
     * @param type
     */
    private void postEvent(int type, ConfigurationPrinter configuration) {

        postEvent(type, configuration, null);

    }
}
