package com.cofrem.transacciones.Modules.ModuleConfiguration.RegisterConfigurationScreen;

import com.cofrem.transacciones.Modules.ModuleConfiguration.RegisterConfigurationScreen.events.RegisterConfigurationScreenEvent;
import com.cofrem.transacciones.Modules.ModuleConfiguration.RegisterConfigurationScreen.ui.RegisterConfigurationScreenView;
import com.cofrem.transacciones.lib.EventBus;
import com.cofrem.transacciones.lib.GreenRobotEventBus;

public class RegisterConfigurationScreenPresenterImpl implements RegisterConfigurationScreenPresenter {


    /**
     * #############################################################################################
     * Declaracion de componentes y variables
     * #############################################################################################
     */
    //Declaracion del bus de eventos
    EventBus eventBus;

    /**
     * #############################################################################################
     * Instanciamientos de las clases
     * #############################################################################################
     */
    //Instanciamiento de la interface registerConfigurationScreenView
    private RegisterConfigurationScreenView registerConfigurationScreenView;

    //Instanciamiento de la interface RegisterConfigurationScreenInteractor
    private RegisterConfigurationScreenInteractor registerConfigurationScreenInteractor;

    /**
     * #############################################################################################
     * Constructor de la clase
     * #############################################################################################
     *
     * @param registerConfigurationScreenView
     */
    public RegisterConfigurationScreenPresenterImpl(RegisterConfigurationScreenView registerConfigurationScreenView) {
        this.registerConfigurationScreenView = registerConfigurationScreenView;
        this.registerConfigurationScreenInteractor = new RegisterConfigurationScreenInteractorImpl();
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    /**
     * Sobrecarga del metodo onCreate de la interface SaldoScreenPresenter "crear" el registro al bus de eventos
     */
    @Override
    public void onCreate() {

        eventBus.register(this);

    }

    /**
     * Sobrecarga del metodo onDestroy de la interface SaldoScreenPresenter para "eliminar"  el registro al bus de eventos
     */
    @Override
    public void onDestroy() {
        registerConfigurationScreenView = null;
        eventBus.unregister(this);
    }

    /**
     * Metodo para la verificacion de los datos
     */
    @Override
    public void VerifySuccess() {
        if (registerConfigurationScreenView != null) {
            registerConfigurationScreenInteractor.validateAccess();
        }
    }

    /**
     * Sobrecarga del metodo onEventMainThread de la interface SaldoScreenPresenter para el manejo de eventos
     *
     * @param registerConfigurationScreenEvent
     */
    @Override
    public void onEventMainThread(RegisterConfigurationScreenEvent registerConfigurationScreenEvent) {
        switch (registerConfigurationScreenEvent.getEventType()) {

            case RegisterConfigurationScreenEvent.onVerifySuccess:
                onVerifySuccess();
                break;

        }
    }


    /**
     * #############################################################################################
     * Metodo propios de la clase
     * #############################################################################################
     */

    /**
     * Metodo para manejar la verificacion exitosa
     */
    private void onVerifySuccess() {
        if (registerConfigurationScreenView != null) {
            registerConfigurationScreenView.handleVerifySuccess();
        }
    }

}
