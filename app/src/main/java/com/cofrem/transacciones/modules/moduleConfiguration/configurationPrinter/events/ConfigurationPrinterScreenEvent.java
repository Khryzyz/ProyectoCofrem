package com.cofrem.transacciones.modules.moduleConfiguration.configurationPrinter.events;

import com.cofrem.transacciones.models.ConfigurationPrinter;

public class ConfigurationPrinterScreenEvent {

    public final static int onVerifyConfigurationInitialSuccess = 0;
    public final static int onVerifyConfigurationInitialError = 1;
    public final static int onSaveConfigurationPrinterSuccess = 2;
    public final static int onSaveConfigurationPrinterError = 3;

    // Variable que maneja los tipos de eventos
    private int eventType;

    // Variable que maneja los mensajes de error de los eventos
    private String errorMessage;

    // Variable que maneja el modelo de configuracion de la impresora
    private ConfigurationPrinter configurationPrinter;

    //Getters y Setters de la clase

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ConfigurationPrinter getConfigurationPrinter() {
        return configurationPrinter;
    }

    public void setConfigurationPrinter(ConfigurationPrinter configurationPrinter) {
        this.configurationPrinter = configurationPrinter;
    }
}
