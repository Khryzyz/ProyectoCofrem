package com.cofrem.transacciones.Modules.ModuleConfiguration.RegisterConfigurationScreen;

import com.cofrem.transacciones.Modules.ModuleConfiguration.RegisterConfigurationScreen.events.RegisterConfigurationScreenEvent;
import com.cofrem.transacciones.lib.EventBus;
import com.cofrem.transacciones.lib.GreenRobotEventBus;

public class RegisterConfigurationScreenRepositoryImpl implements RegisterConfigurationScreenRepository {
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
    public RegisterConfigurationScreenRepositoryImpl() {
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
    public void validateAcces() {

        postEvent(RegisterConfigurationScreenEvent.onVerifySuccess);

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
    private void postEvent(int type, String errorMessage) {
        RegisterConfigurationScreenEvent registerConfigurationScreenEvent = new RegisterConfigurationScreenEvent();
        registerConfigurationScreenEvent.setEventType(type);
        if (errorMessage != null) {
            registerConfigurationScreenEvent.setErrorMessage(errorMessage);
        }

        EventBus eventBus = GreenRobotEventBus.getInstance();

        eventBus.post(registerConfigurationScreenEvent);
    }

    /**
     * Sobrecarga del metodo postevent
     *
     * @param type
     */
    private void postEvent(int type) {

        postEvent(type, null);

    }
}
