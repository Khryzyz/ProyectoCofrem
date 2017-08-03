package com.cofrem.transacciones.Modules.ModuleConfiguration.ConfigurationPrinter;

import android.content.Context;

import com.cofrem.transacciones.Modules.ModuleConfiguration.ConfigurationPrinter.events.ConfigurationPrinterScreenEvent;
import com.cofrem.transacciones.Modules.ModuleConfiguration.ConfigurationPrinter.ui.ConfigurationPrinterScreenView;
import com.cofrem.transacciones.lib.EventBus;
import com.cofrem.transacciones.lib.GreenRobotEventBus;

public class ConfigurationPrinterScreenPresenterImpl implements ConfigurationPrinterScreenPresenter {

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
    //Instanciamiento de la interface ConfigurationPrinterScreenView
    private ConfigurationPrinterScreenView configurationPrinterScreenView;

    //Instanciamiento de la interface ConfigurationPrinterScreenInteractor
    private ConfigurationPrinterScreenInteractor configurationPrinterScreenInteractor;

    /**
     * #############################################################################################
     * Constructor de la clase
     * #############################################################################################
     *
     * @param configurationPrinterScreenView
     */
    public ConfigurationPrinterScreenPresenterImpl(ConfigurationPrinterScreenView configurationPrinterScreenView) {
        this.configurationPrinterScreenView = configurationPrinterScreenView;
        this.configurationPrinterScreenInteractor = new ConfigurationPrinterScreenInteractorImpl();
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    /**
     * #############################################################################################
     * Metodos sobrecargados de la interface
     * #############################################################################################
     */

    /**
     * Sobrecarga del metodo onCreate de la interface ConfigurationPrinterScreenPresenter "crear" el registro al bus de eventos
     */
    @Override
    public void onCreate() {

        eventBus.register(this);

    }

    /**
     * Sobrecarga del metodo onDestroy de la interface ConfigurationPrinterScreenPresenter para "eliminar"  el registro al bus de eventos
     */
    @Override
    public void onDestroy() {
        configurationPrinterScreenView = null;
        eventBus.unregister(this);
    }

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
        if (configurationPrinterScreenView != null) {
            configurationPrinterScreenInteractor.validateInitialConfig(context);
        }
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
        if (configurationPrinterScreenView != null) {
            configurationPrinterScreenInteractor.validateAccess(context);
        }
    }

    /**
     * Sobrecarga del metodo onEventMainThread de la interface ConfigurationPrinterScreenPresenter para el manejo de eventos
     *
     * @param configurationPrinterScreenEvent
     */
    @Override
    public void onEventMainThread(ConfigurationPrinterScreenEvent configurationPrinterScreenEvent) {
        switch (configurationPrinterScreenEvent.getEventType()) {

            case ConfigurationPrinterScreenEvent.onVerifyInitialConfigExiste:
                onVerifyInitialConfigExiste();
                break;

            case ConfigurationPrinterScreenEvent.onVerifyInitialConfigNoExiste:
                onVerifyInitialConfigNoExiste();
                break;

            case ConfigurationPrinterScreenEvent.onVerifyInitialConfigNoValida:
                onVerifyInitialConfigNoValida();
                break;

            case ConfigurationPrinterScreenEvent.onRegistroConfiguracionAccesoExiste:
                onRegistroConfiguracionAccesoExiste();
                break;

            case ConfigurationPrinterScreenEvent.onRegistroConfiguracionAccesoNoExiste:
                onRegistroConfiguracionAccesoNoExiste();
                break;

            case ConfigurationPrinterScreenEvent.onInsertRegistroConfiguracionAccesoSuccess:
                onInsertRegistroConfiguracionAccesoSuccess();
                break;

            case ConfigurationPrinterScreenEvent.onInsertRegistroConfiguracionAccesoError:
                onInsertRegistroConfiguracionAccesoError();
                break;

            case ConfigurationPrinterScreenEvent.onVerifySuccess:
                onVerifySuccess();
                break;

            case ConfigurationPrinterScreenEvent.onVerifyError:
                onVerifyError();
                break;

            case ConfigurationPrinterScreenEvent.onInternetConnectionSuccess:
                onInternetConnectionSuccess();
                break;

            case ConfigurationPrinterScreenEvent.onInternetConnectionError:
                onInternetConnectionError();
                break;

            case ConfigurationPrinterScreenEvent.onMagneticReaderDeviceSuccess:
                onMagneticReaderDeviceSuccess();
                break;

            case ConfigurationPrinterScreenEvent.onMagneticReaderDeviceError:
                onMagneticReaderDeviceError();
                break;

            case ConfigurationPrinterScreenEvent.onNFCDeviceSuccess:
                onNFCDeviceSuccess();
                break;

            case ConfigurationPrinterScreenEvent.onNFCDeviceError:
                onNFCDeviceError();
                break;

            case ConfigurationPrinterScreenEvent.onPrinterDeviceSuccess:
                onPrinterDeviceSuccess();
                break;

            case ConfigurationPrinterScreenEvent.onPrinterDeviceError:
                onPrinterDeviceError();
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
    private void onVerifyInitialConfigExiste() {
        if (configurationPrinterScreenView != null) {
            configurationPrinterScreenView.handleVerifyInitialConfigExiste();
        }
    }

    /**
     * Metodo para manejar la verificacion exitosa
     */
    private void onVerifyInitialConfigNoExiste() {
        if (configurationPrinterScreenView != null) {
            configurationPrinterScreenView.handleVerifyInitialConfigNoExiste();
        }
    }

    /**
     * Metodo para manejar la existencia de la configuracion inicial NO valida
     */
    private void onVerifyInitialConfigNoValida() {
        if (configurationPrinterScreenView != null) {
            configurationPrinterScreenView.handleVerifyInitialConfigNoValida();
        }
    }

    /**
     * Metodo para manejar la existencia de la configuracion de acceso
     */
    private void onRegistroConfiguracionAccesoExiste() {
        if (configurationPrinterScreenView != null) {
            configurationPrinterScreenView.handleRegistroConfiguracionAccesoExiste();
        }
    }

    /**
     * Metodo para manejar la no existencia de la configuracion de acceso
     */
    private void onRegistroConfiguracionAccesoNoExiste() {
        if (configurationPrinterScreenView != null) {
            configurationPrinterScreenView.handleRegistroConfiguracionAccesoNoExiste();
        }
    }

    /**
     * Metodo para manejar el registro de la configuracion de acceso exitosa
     */
    private void onInsertRegistroConfiguracionAccesoSuccess() {
        if (configurationPrinterScreenView != null) {
            configurationPrinterScreenView.handleInsertRegistroConfiguracionAccesoSuccess();
        }
    }

    /**
     * Metodo para manejar el registro de la configuracion de acceso erronea
     */
    private void onInsertRegistroConfiguracionAccesoError() {
        if (configurationPrinterScreenView != null) {
            configurationPrinterScreenView.handleInsertRegistroConfiguracionAccesoError();
        }
    }

    /**
     * Metodo para manejar la verificacion exitosa
     */
    private void onVerifySuccess() {
        if (configurationPrinterScreenView != null) {
            configurationPrinterScreenView.handleVerifySuccess();
        }
    }

    /**
     * Metodo para manejar la verificacion erronea
     */
    private void onVerifyError() {
        if (configurationPrinterScreenView != null) {
            configurationPrinterScreenView.handleVerifyError();
        }
    }

    /**
     * Metodo para manejar la conexion a internet exitosa
     */
    private void onInternetConnectionSuccess() {
        if (configurationPrinterScreenView != null) {
            configurationPrinterScreenView.handleInternetConnectionSuccess();
        }
    }

    /**
     * Metodo para manejar la conexion a internet erronea
     */
    private void onInternetConnectionError() {
        if (configurationPrinterScreenView != null) {
            configurationPrinterScreenView.handleInternetConnectionError();
        }
    }

    /**
     * Metodo para manejar la conexion al dispositivo lector de banda magnetica exitosa
     */
    private void onMagneticReaderDeviceSuccess() {
        if (configurationPrinterScreenView != null) {
            configurationPrinterScreenView.handleMagneticReaderDeviceSuccess();
        }
    }

    /**
     * Metodo para manejar la conexion al dispositivo lector de banda magnetica erronea
     */
    private void onMagneticReaderDeviceError() {
        if (configurationPrinterScreenView != null) {
            configurationPrinterScreenView.handleMagneticReaderDeviceError();
        }
    }

    /**
     * Metodo para manejar la conexion al dispositivo NFC exitosa
     */
    private void onNFCDeviceSuccess() {
        if (configurationPrinterScreenView != null) {
            configurationPrinterScreenView.handleNFCDeviceSuccess();
        }
    }

    /**
     * Metodo para manejar la conexion al dispositivo NFC erronea
     */
    private void onNFCDeviceError() {
        if (configurationPrinterScreenView != null) {
            configurationPrinterScreenView.handleNFCDeviceError();
        }
    }

    /**
     * Metodo para manejar la conexion al dispositivo de impresion exitosa
     */
    private void onPrinterDeviceSuccess() {
        if (configurationPrinterScreenView != null) {
            configurationPrinterScreenView.handlePrinterDeviceSuccess();
        }
    }

    /**
     * Metodo para manejar la conexion al dispositivo de impresion erronea
     */
    private void onPrinterDeviceError() {
        if (configurationPrinterScreenView != null) {
            configurationPrinterScreenView.handlePrinterDeviceError();
        }
    }
}
