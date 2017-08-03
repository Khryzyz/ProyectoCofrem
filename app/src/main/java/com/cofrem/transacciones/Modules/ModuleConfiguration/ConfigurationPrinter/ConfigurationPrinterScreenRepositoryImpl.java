package com.cofrem.transacciones.Modules.ModuleConfiguration.ConfigurationPrinter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.cofrem.transacciones.Modules.ModuleConfiguration.ConfigurationPrinter.events.ConfigurationPrinterScreenEvent;
import com.cofrem.transacciones.database.AppDatabase;
import com.cofrem.transacciones.lib.EventBus;
import com.cofrem.transacciones.lib.GreenRobotEventBus;
import com.cofrem.transacciones.lib.MagneticHandler;

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
     * Metodo que verifica:
     *  - La existencia de la configuración inicial
     *  - En caso de no existir mostrará la vista de configuración
     *  - En caso de existir validara el acceso
     *
     * @param context
     */
    @Override
    public void validateInitialConfig(Context context) {

        //Variable que almacena el estado de la conexión a internet
        boolean internetConnection = verifyInternetConnection(context);

        //Valida la existencia de conexion a internet
        if (internetConnection) {

            // Registra el evento de existencia de conexion a internet
            postEvent(ConfigurationPrinterScreenEvent.onInternetConnectionSuccess);

            //Consulta la existencia del registro de configuracion
            int conteoRegistrosConfiguracionInicial = AppDatabase.getInstance(context).conteoConfiguracionConexion();

            switch (conteoRegistrosConfiguracionInicial) {

                /**
                 * En caso de que no exista la configuracion
                 * - Registra el evento de no existencia de configuracion
                 * - Inserta el registro del valor de acceso al dispositivo
                 */
                case 0:

                    postEvent(ConfigurationPrinterScreenEvent.onVerifyInitialConfigNoExiste);

                    //Consulta la existencia del registro de contraseña tecnica por defecto en el dispositivo
                    int conteoRegistroConfiguracionAcceso = AppDatabase.getInstance(context).conteoConfiguracionAcceso();

                    /**
                     * En caso de que no exista el registro de configuracion de acceso intenta registrarlo
                     */
                    if (conteoRegistroConfiguracionAcceso == 0) {

                        postEvent(ConfigurationPrinterScreenEvent.onRegistroConfiguracionAccesoNoExiste);

                        //Registra el valor de acceso
                        if (AppDatabase.getInstance(context).insertRegistroInicialConfiguracionAcceso()) {

                            postEvent(ConfigurationPrinterScreenEvent.onInsertRegistroConfiguracionAccesoSuccess);

                        } else {

                            postEvent(ConfigurationPrinterScreenEvent.onInsertRegistroConfiguracionAccesoError);
                        }

                    } else {

                        postEvent(ConfigurationPrinterScreenEvent.onRegistroConfiguracionAccesoExiste);

                    }

                    break;

                case 1:

                    postEvent(ConfigurationPrinterScreenEvent.onVerifyInitialConfigExiste);

                    break;

                default:

                    postEvent(ConfigurationPrinterScreenEvent.onVerifyInitialConfigNoValida);

                    break;

            }
        } else {
            postEvent(ConfigurationPrinterScreenEvent.onInternetConnectionError);
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
    public void validateAcces(final Context context) {

        boolean deviceMagneticReader = verifyDeviceMagneticReader();
        boolean internetConnection = verifyInternetConnection(context);
        boolean devicePrinter = verifyDevicePrinter();
        boolean deviceNFC = verifyDeviceNFC();

        if ((deviceMagneticReader || deviceNFC) && devicePrinter && internetConnection) {

            if (verifyInitialRegister(context))
                postEvent(ConfigurationPrinterScreenEvent.onVerifySuccess);
            else
                postEvent(ConfigurationPrinterScreenEvent.onVerifyError);


        } else {
            if (!deviceMagneticReader) {

                postEvent(ConfigurationPrinterScreenEvent.onMagneticReaderDeviceError);

            }
            if (!deviceNFC) {

                postEvent(ConfigurationPrinterScreenEvent.onNFCDeviceError);

            }
            if (!devicePrinter) {

                postEvent(ConfigurationPrinterScreenEvent.onPrinterDeviceError);

            }
            if (!internetConnection) {

                postEvent(ConfigurationPrinterScreenEvent.onInternetConnectionError);

            }
        }


    }

    /**
     * #############################################################################################
     * Metodo propios de la clase
     * #############################################################################################
     */

    /**
     * Metodo que verifica la existencia del registro inicial
     *
     * @param context
     * @return
     */
    private boolean verifyInitialRegister(Context context) {

        boolean resultVerifyInitialRegister = false;

        AppDatabase.getInstance(context).registroInicialProductos();

        // Validacion en caso de que no existan productos registrados en el sistema
        if (AppDatabase.getInstance(context).obtenerConteoRegistroProductos() == 0) {

            if (AppDatabase.getInstance(context).registroInicialProductos()) {

                resultVerifyInitialRegister = true;

            }

        } else {

            resultVerifyInitialRegister = true;

        }

        return resultVerifyInitialRegister;

    }

    /**
     * Metodo que verifica la existencia del registro inicial
     *
     * @param context
     * @return
     */
    private boolean verifyInitialRegisterPrinter(Context context) {

        boolean resultVerifyInitialRegister = false;

        //TODO: Validacion de la configuracion inicial de la impresora esto debe ser de la FASE I

        // Validacion en caso de que no exista configuracion inicial para la impresora
        if (AppDatabase.getInstance(context).conteoConfigurationPrinter() == 0) {

            if (AppDatabase.getInstance(context).insertRegistroInicialConfiguracionPrinter()) {

                resultVerifyInitialRegister = true;

            }

        } else {

            resultVerifyInitialRegister = true;

        }

        return resultVerifyInitialRegister;

    }
    /**
     * Metodo que verifica la existencia de conexion a internet en el dispositivo
     *
     * @param context
     * @return
     */
    private boolean verifyInternetConnection(Context context) {

        boolean resultVerifyInternetConnection = false;

        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            resultVerifyInternetConnection = true;
        }

        return resultVerifyInternetConnection;

    }

    /**
     * Metodo que verifica la existencia de un lector NFC en el dispositivo
     *
     * @return
     */
    private boolean verifyDeviceNFC() {

        // TODO: Realizar proceso de comprobacion de dispositivo NFC / FASE 1
        return true;
    }

    /**
     * Metodo que verifica la existencia de un lector de banda ma en el dispositivo
     *
     * @return
     */
    private boolean verifyDeviceMagneticReader() {

        // Solicitud de prueba de dispositivo de lectura de banda magnetica
        return new MagneticHandler().testMagneticDevice();

    }

    /**
     * Metodo que verifica la existencia de una impresora en el dispositivo
     *
     * @return
     */
    private boolean verifyDevicePrinter() {

        // TODO: Realizar proceso de comprobacion de dispositivo de impresion / FASE
        return true;
    }


    /**
     * Metodo que registra los eventos
     *
     * @param type
     * @param errorMessage
     */
    private void postEvent(int type, String errorMessage) {
        ConfigurationPrinterScreenEvent configurationPrinterScreenEvent = new ConfigurationPrinterScreenEvent();
        configurationPrinterScreenEvent.setEventType(type);
        if (errorMessage != null) {
            configurationPrinterScreenEvent.setErrorMessage(errorMessage);
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

        postEvent(type, null);

    }

}
