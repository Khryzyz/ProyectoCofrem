package com.cofrem.transacciones.Modules.ModuleConfiguration.RegisterConfigurationScreen;

import android.content.Context;

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
     * Valida el acceso a la configuracion del dispositivo mediante la contraseña de administrador
     *
     * @param context
     * @param passAdmin
     */
    @Override
    public void validateAccessAdmin(Context context, int passAdmin) {
        if (registerConfigurationScreenView != null) {
            registerConfigurationScreenInteractor.validateAccessAdmin(context, passAdmin);
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

            case RegisterConfigurationScreenEvent.onValorAccesoValido:
                onValorAccesoValido();
                break;
            case RegisterConfigurationScreenEvent.onValorAccesoNoValido:
                onValorAccesoNoValido();
                break;
            case RegisterConfigurationScreenEvent.onValorAccesoError:
                onValorAccesoError();
                break;

        }
    }


    /**
     * #############################################################################################
     * Metodo propios de la clase
     * #############################################################################################
     */

    /**
     * Metodo para manejar el valor de acceso valido
     */
    private void onValorAccesoValido() {
        if (registerConfigurationScreenView != null) {
            registerConfigurationScreenView.handleValorAccesoValido();
        }
    }
    /**
     * Metodo para manejar el valor de acceso NO valido
     */
    private void onValorAccesoNoValido() {
        if (registerConfigurationScreenView != null) {
            registerConfigurationScreenView.handleValorAccesoNoValido();
        }
    }
    /**
     * Metodo para manejar el error en la configuracion de valor de acceso
     */
    private void onValorAccesoError() {
        if (registerConfigurationScreenView != null) {
            registerConfigurationScreenView.handleValorAccesoError();
        }
    }

}
