package com.cofrem.transacciones.SplashScreen;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.cofrem.transacciones.SplashScreen.events.SplashScreenEvent;
import com.cofrem.transacciones.database.AppDatabase;
import com.cofrem.transacciones.lib.EventBus;
import com.cofrem.transacciones.lib.GreenRobotEventBus;
import com.cofrem.transacciones.lib.KsoapAsync;
import com.cofrem.transacciones.models.Transaccion;

public class SplashScreenRepositoryImpl implements SplashScreenRepository {

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
    public SplashScreenRepositoryImpl() {
    }

    /**
     * #############################################################################################
     * Metodos sobrecargados de la interface
     * #############################################################################################
     */

    /**
     * Metodo que verifica:
     * - La existencia de la configuración inicial
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
            postEvent(SplashScreenEvent.onInternetConnectionSuccess);

            //Consulta la existencia del registro de configuracion
            int conteoRegistrosConfiguracionInicial = AppDatabase.getInstance(context).conteoConfiguracionConexion();


            switch (conteoRegistrosConfiguracionInicial) {

                /**
                 * En caso de que no exista la configuracion
                 * - Registra el evento de no existencia de configuracion
                 * - Inserta el registro del valor de acceso al dispositivo
                 */
                case 0:

                    //Consulta la existencia del registro de valor de acceso al dispositivo
                    int conteoRegistroConfiguracionAcceso = AppDatabase.getInstance(context).conteoConfiguracionAcceso();

                    /**
                     * En caso de que no exista el registro de configuracion de acceso intenta registrarlo
                     */
                    if (conteoRegistroConfiguracionAcceso == 0) {

                        //Registra el valor de acceso
                        if (AppDatabase.getInstance(context).insertRegistroInicialConfiguracionAcceso())
                            postEvent(SplashScreenEvent.onVerifyInitialConfigExiste);

                        if (AppDatabase.getInstance(context).insertRegistroInicialConfiguracionAcceso()) {
                            postEvent(SplashScreenEvent.onVerifyInitialConfigNoExiste);

                        } else {
                            postEvent(SplashScreenEvent.onVerifyInitialConfigExiste);
                        }

                    } else {
                        postEvent(SplashScreenEvent.onVerifyInitialConfigExiste);
                    }

                    break;

                case 1:

                    postEvent(SplashScreenEvent.onVerifyInitialConfigExiste);

                    break;

                default:

                    postEvent(SplashScreenEvent.onVerifyInitialConfigNoValida);

                    break;

            }
        } else {
            postEvent(SplashScreenEvent.onInternetConnectionError);
        }
    }

    /**
     * Metodo que verifica:
     * - Existencia de datos
     * - Validez de datos
     *
     * @param context
     */
    @Override
    public void validateAcces(final Context context) {

        boolean deviceMagneticReader = verifyDeviceMagneticReader(context);
        boolean internetConnection = verifyInternetConnection(context);
        boolean devicePrinter = verifyDevicePrinter(context);
        boolean deviceNFC = verifyDeviceNFC(context);

        if ((deviceMagneticReader || deviceNFC) && devicePrinter && internetConnection) {

            if (verifyInitialRegister(context))
                postEvent(SplashScreenEvent.onVerifySuccess);
            else
                postEvent(SplashScreenEvent.onVerifyError);
        } else {
            if (!deviceMagneticReader) {

                postEvent(SplashScreenEvent.onMagneticReaderDeviceError);

            }
            if (!deviceNFC) {

                postEvent(SplashScreenEvent.onNFCDeviceError);

            }
            if (!devicePrinter) {

                postEvent(SplashScreenEvent.onPrinterDeviceError);

            }
            if (!internetConnection) {

                postEvent(SplashScreenEvent.onInternetConnectionError);

            }
        }


    }

    /**
     * #############################################################################################
     * Metodo propios de la clase
     * #############################################################################################
     */
    /**
     * @param context
     * @return
     */
    private boolean verifyInitialRegister(Context context) {
        if (AppDatabase.getInstance(context).obtenerConteoRegistroProductos() == 0) {
            if (AppDatabase.getInstance(context).insertRegistroInicialProductos()) {

                int prueba = 0;
                if (AppDatabase.getInstance(context).insertRegistroPruebaTransaction(1)) {
                    prueba++;
                }
                if (AppDatabase.getInstance(context).insertRegistroPruebaTransaction(2)) {
                    prueba++;

                }
                if (prueba==2)
                return true;

            }
        } else {
            Transaccion modelTransaccion = AppDatabase.getInstance(context).obtenerUltimaTransaccion();
            return true;
        }
        return true;
    }


    /**
     * Metodo que verifica la existencia de conexion a internet en el dispositivo
     *
     * @param context
     * @return
     */
    private boolean verifyInternetConnection(Context context) {

        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    /**
     * Metodo que verifica la existencia de un lector NFC en el dispositivo
     *
     * @param context
     * @return
     */
    private boolean verifyDeviceNFC(Context context) {
        return true;
    }

    /**
     * Metodo que verifica la existencia de un lector de banda ma en el dispositivo
     *
     * @param context
     * @return
     */
    private boolean verifyDeviceMagneticReader(Context context) {
        return true;
    }

    /**
     * Metodo que verifica la existencia de una impresora en el dispositivo
     *
     * @param context
     * @return
     */
    private boolean verifyDevicePrinter(Context context) {
        return true;
    }


    /**
     * Metodo que registra los eventos
     *
     * @param type
     * @param errorMessage
     */
    private void postEvent(int type, String errorMessage) {
        SplashScreenEvent splashScreenEvent = new SplashScreenEvent();
        splashScreenEvent.setEventType(type);
        if (errorMessage != null) {
            splashScreenEvent.setErrorMessage(errorMessage);
        }

        EventBus eventBus = GreenRobotEventBus.getInstance();

        eventBus.post(splashScreenEvent);
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
