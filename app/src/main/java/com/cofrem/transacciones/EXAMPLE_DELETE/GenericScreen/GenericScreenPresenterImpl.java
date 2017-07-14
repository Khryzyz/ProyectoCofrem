package com.cofrem.transacciones.EXAMPLE_DELETE.GenericScreen;

import com.cofrem.transacciones.EXAMPLE_DELETE.GenericScreen.events.GenericScreenEvent;
import com.cofrem.transacciones.EXAMPLE_DELETE.GenericScreen.ui.GenericScreenView;
import com.cofrem.transacciones.lib.EventBus;
import com.cofrem.transacciones.lib.GreenRobotEventBus;

public class GenericScreenPresenterImpl implements GenericScreenPresenter {


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
    //Instanciamiento de la interface genericScreenView
    private GenericScreenView genericScreenView;

    //Instanciamiento de la interface GenericScreenInteractor
    private GenericScreenInteractor genericScreenInteractor;

    /**
     * #############################################################################################
     * Constructor de la clase
     * #############################################################################################
     *
     * @param genericScreenView
     */
    public GenericScreenPresenterImpl(GenericScreenView genericScreenView) {
        this.genericScreenView = genericScreenView;
        this.genericScreenInteractor = new GenericScreenInteractorImpl();
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
        genericScreenView = null;
        eventBus.unregister(this);
    }

    /**
     * Metodo para la verificacion de los datos
     */
    @Override
    public void VerifySuccess() {
        if (genericScreenView != null) {
            genericScreenInteractor.validateAccess();
        }
    }

    /**
     * Sobrecarga del metodo onEventMainThread de la interface SaldoScreenPresenter para el manejo de eventos
     *
     * @param genericScreenEvent
     */
    @Override
    public void onEventMainThread(GenericScreenEvent genericScreenEvent) {
        switch (genericScreenEvent.getEventType()) {

            case GenericScreenEvent.onVerifySuccess:
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
        if (genericScreenView != null) {
            genericScreenView.handleVerifySuccess();
        }
    }

}
