package com.cofrem.transacciones.Modules.ModuleConfiguration.TestCommunicationScreen;

import com.cofrem.transacciones.Modules.ModuleConfiguration.TestCommunicationScreen.events.TestCommunicationScreenEvent;
import com.cofrem.transacciones.lib.EventBus;
import com.cofrem.transacciones.lib.GreenRobotEventBus;

public class TestCommunicationScreenRepositoryImpl implements TestCommunicationScreenRepository {
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
    public TestCommunicationScreenRepositoryImpl() {
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

        postEvent(TestCommunicationScreenEvent.onVerifySuccess);

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
        TestCommunicationScreenEvent testCommunicationScreenEvent = new TestCommunicationScreenEvent();
        testCommunicationScreenEvent.setEventType(type);
        if (errorMessage != null) {
            testCommunicationScreenEvent.setErrorMessage(errorMessage);
        }

        EventBus eventBus = GreenRobotEventBus.getInstance();

        eventBus.post(testCommunicationScreenEvent);
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
