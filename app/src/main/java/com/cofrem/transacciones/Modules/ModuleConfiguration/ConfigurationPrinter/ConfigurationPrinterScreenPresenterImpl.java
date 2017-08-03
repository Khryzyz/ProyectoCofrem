package com.cofrem.transacciones.Modules.ModuleConfiguration.ConfigurationPrinter;

import android.content.Context;

import com.cofrem.transacciones.Modules.ModuleConfiguration.ConfigurationPrinter.events.ConfigurationPrinterScreenEvent;
import com.cofrem.transacciones.Modules.ModuleConfiguration.ConfigurationPrinter.ui.SplashScreenView;
import com.cofrem.transacciones.lib.EventBus;
import com.cofrem.transacciones.lib.GreenRobotEventBus;

public class SplashScreenPresenterImpl implements ConfigurationPrinterScreenPresenter {

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
    //Instanciamiento de la interface SplashScreenView
    private SplashScreenView splashScreenView;

    //Instanciamiento de la interface ConfigurationPrinterScreenInteractor
    private ConfigurationPrinterScreenInteractor configurationPrinterScreenInteractor;

    /**
     * #############################################################################################
     * Constructor de la clase
     * #############################################################################################
     *
     * @param splashScreenView
     */
    public SplashScreenPresenterImpl(SplashScreenView splashScreenView) {
        this.splashScreenView = splashScreenView;
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
        splashScreenView = null;
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
        if (splashScreenView != null) {
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
        if (splashScreenView != null) {
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
        if (splashScreenView != null) {
            splashScreenView.handleVerifyInitialConfigExiste();
        }
    }

    /**
     * Metodo para manejar la verificacion exitosa
     */
    private void onVerifyInitialConfigNoExiste() {
        if (splashScreenView != null) {
            splashScreenView.handleVerifyInitialConfigNoExiste();
        }
    }

    /**
     * Metodo para manejar la existencia de la configuracion inicial NO valida
     */
    private void onVerifyInitialConfigNoValida() {
        if (splashScreenView != null) {
            splashScreenView.handleVerifyInitialConfigNoValida();
        }
    }

    /**
     * Metodo para manejar la existencia de la configuracion de acceso
     */
    private void onRegistroConfiguracionAccesoExiste() {
        if (splashScreenView != null) {
            splashScreenView.handleRegistroConfiguracionAccesoExiste();
        }
    }

    /**
     * Metodo para manejar la no existencia de la configuracion de acceso
     */
    private void onRegistroConfiguracionAccesoNoExiste() {
        if (splashScreenView != null) {
            splashScreenView.handleRegistroConfiguracionAccesoNoExiste();
        }
    }

    /**
     * Metodo para manejar el registro de la configuracion de acceso exitosa
     */
    private void onInsertRegistroConfiguracionAccesoSuccess() {
        if (splashScreenView != null) {
            splashScreenView.handleInsertRegistroConfiguracionAccesoSuccess();
        }
    }

    /**
     * Metodo para manejar el registro de la configuracion de acceso erronea
     */
    private void onInsertRegistroConfiguracionAccesoError() {
        if (splashScreenView != null) {
            splashScreenView.handleInsertRegistroConfiguracionAccesoError();
        }
    }

    /**
     * Metodo para manejar la verificacion exitosa
     */
    private void onVerifySuccess() {
        if (splashScreenView != null) {
            splashScreenView.handleVerifySuccess();
        }
    }

    /**
     * Metodo para manejar la verificacion erronea
     */
    private void onVerifyError() {
        if (splashScreenView != null) {
            splashScreenView.handleVerifyError();
        }
    }

    /**
     * Metodo para manejar la conexion a internet exitosa
     */
    private void onInternetConnectionSuccess() {
        if (splashScreenView != null) {
            splashScreenView.handleInternetConnectionSuccess();
        }
    }

    /**
     * Metodo para manejar la conexion a internet erronea
     */
    private void onInternetConnectionError() {
        if (splashScreenView != null) {
            splashScreenView.handleInternetConnectionError();
        }
    }

    /**
     * Metodo para manejar la conexion al dispositivo lector de banda magnetica exitosa
     */
    private void onMagneticReaderDeviceSuccess() {
        if (splashScreenView != null) {
            splashScreenView.handleMagneticReaderDeviceSuccess();
        }
    }

    /**
     * Metodo para manejar la conexion al dispositivo lector de banda magnetica erronea
     */
    private void onMagneticReaderDeviceError() {
        if (splashScreenView != null) {
            splashScreenView.handleMagneticReaderDeviceError();
        }
    }

    /**
     * Metodo para manejar la conexion al dispositivo NFC exitosa
     */
    private void onNFCDeviceSuccess() {
        if (splashScreenView != null) {
            splashScreenView.handleNFCDeviceSuccess();
        }
    }

    /**
     * Metodo para manejar la conexion al dispositivo NFC erronea
     */
    private void onNFCDeviceError() {
        if (splashScreenView != null) {
            splashScreenView.handleNFCDeviceError();
        }
    }

    /**
     * Metodo para manejar la conexion al dispositivo de impresion exitosa
     */
    private void onPrinterDeviceSuccess() {
        if (splashScreenView != null) {
            splashScreenView.handlePrinterDeviceSuccess();
        }
    }

    /**
     * Metodo para manejar la conexion al dispositivo de impresion erronea
     */
    private void onPrinterDeviceError() {
        if (splashScreenView != null) {
            splashScreenView.handlePrinterDeviceError();
        }
    }
}
