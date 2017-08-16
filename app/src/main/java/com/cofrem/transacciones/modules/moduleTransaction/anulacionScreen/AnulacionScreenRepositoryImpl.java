package com.cofrem.transacciones.modules.moduleTransaction.anulacionScreen;

import android.content.Context;
import android.util.Log;

import com.cofrem.transacciones.database.AppDatabase;
import com.cofrem.transacciones.global.InfoGlobalTransaccionSOAP;
import com.cofrem.transacciones.lib.KsoapAsync;
import com.cofrem.transacciones.lib.MD5;
import com.cofrem.transacciones.models.Transaccion;
import com.cofrem.transacciones.models.modelsWS.MessageWS;
import com.cofrem.transacciones.models.modelsWS.TransactionWS;
import com.cofrem.transacciones.models.modelsWS.modelTransaccion.InformacionTransaccion;
import com.cofrem.transacciones.models.modelsWS.modelTransaccion.ResultadoTransaccion;
import com.cofrem.transacciones.modules.moduleTransaction.anulacionScreen.events.AnulacionScreenEvent;
import com.cofrem.transacciones.lib.EventBus;
import com.cofrem.transacciones.lib.GreenRobotEventBus;

import org.ksoap2.serialization.SoapObject;

import java.util.concurrent.ExecutionException;

public class AnulacionScreenRepositoryImpl implements AnulacionScreenRepository {
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
    public AnulacionScreenRepositoryImpl() {
    }

    /**
     * #############################################################################################
     * Metodos sobrecargados de la interface
     * #############################################################################################
     */

    /**
     * Metodo quevalida la existencia de la clave de administracion
     *
     * @param context
     * @param passAdmin
     */
    @Override
    public void validarPasswordAdministrador(Context context, String passAdmin) {

        int validateExistValorAcceso = AppDatabase.getInstance(context).validarAccesoByClaveAdministracion(MD5.crypt(passAdmin));

        switch (validateExistValorAcceso) {
            case 0:
                postEvent(AnulacionScreenEvent.onClaveAdministracionNoValida);
                break;
            case 1:
                postEvent(AnulacionScreenEvent.onClaveAdministracionValida);
                break;
            default:
                postEvent(AnulacionScreenEvent.onClaveAdministracionError);
                break;
        }

    }

    /**
     * @param context
     * @param numeroCargo
     */
    @Override
    public void obtenerValorTransaccion(Context context, String numeroCargo) {

        Log.i("Repositorio cargo", numeroCargo);

        int valorTransaccion = AppDatabase.getInstance(context).obtenerValorTransaccion(numeroCargo);

        switch (valorTransaccion) {
            case AnulacionScreenEvent.VALOR_TRANSACCION_NO_VALIDO:
                postEvent(AnulacionScreenEvent.onValorTransaccionNoValido);
                break;
            default:
                postEvent(AnulacionScreenEvent.onValorTransaccionValido, valorTransaccion);
                break;
        }
    }

    /**
     * @param context
     * @param transaccion
     */
    @Override
    public void registrarTransaccion(Context context, Transaccion transaccion) {

        ResultadoTransaccion resultadoTransaccion = registrarTransaccionConsumoWS(context, transaccion);

        //Registra mediante el WS la transaccion
        if (resultadoTransaccion != null) {

            MessageWS messageWS = resultadoTransaccion.getMessageWS();

            if (messageWS.getCodigoMensaje() == MessageWS.statusTransaccionExitosa) {

                //Registro en la base de datos de la transaccion
                if (registrarTransaccionConsumoDB(context, resultadoTransaccion.getInformacionTransaccion())) {

                    postEvent(AnulacionScreenEvent.onTransaccionSuccess);

                    //Imprime el recibo
                    imprimirRecibo(context);

                } else {

                    //Error en el registro en la Base de Datos la transaccion
                    postEvent(AnulacionScreenEvent.onTransaccionDBRegisterError);

                }
            } else {
                //Error en el registro de la transaccion del web service
                postEvent(AnulacionScreenEvent.onTransaccionWSRegisterError, messageWS.getDetalleMensaje());
            }
        } else

        {
            //Error en la conexion con el Web Service
            postEvent(AnulacionScreenEvent.onTransaccionWSConexionError);
        }

    }

    /**
     * #############################################################################################
     * Metodo propios de la clase
     * #############################################################################################
     */

    /**
     * Metodo que:
     * - registra mediante el WS la transaccion
     * - Extrae el estado de la transaccion
     *
     * @param context
     * @param transaccion
     * @return
     */

    private ResultadoTransaccion registrarTransaccionConsumoWS(Context context, Transaccion transaccion) {

        //Se crea una variable de estado de la transaccion
        ResultadoTransaccion resultadoTransaccion = null;

        //Inicializacion y declaracion de parametros para la peticion web service
        String[][] params = {
                {InfoGlobalTransaccionSOAP.PARAM_NAME_ANULACION_CODIGO_TERMINAL, AppDatabase.getInstance(context).obtenerCodigoTerminal()},
                {InfoGlobalTransaccionSOAP.PARAM_NAME_ANULACION_NUMERO_APROBACION, transaccion.getNumero_cargo()},
                {InfoGlobalTransaccionSOAP.PARAM_NAME_ANULACION_CEDULA_USUARIO, transaccion.getNumero_documento()},
                {InfoGlobalTransaccionSOAP.PARAM_NAME_ANULACION_NUMERO_TARJETA, transaccion.getNumero_tarjeta()},
                {InfoGlobalTransaccionSOAP.PARAM_NAME_ANULACION_CLAVE_USUARIO, String.valueOf(transaccion.getClave())},
                {InfoGlobalTransaccionSOAP.PARAM_NAME_ANULACION_TIPO_ENCRIPTACION, String.valueOf(transaccion.getTipo_encriptacion())},
                {InfoGlobalTransaccionSOAP.PARAM_NAME_ANULACION_VALOR_APROBADO, String.valueOf(transaccion.getValor())},
        };

        //Creacion del modelo TransactionWS para ser usado dentro del webservice
        TransactionWS transactionWS = new TransactionWS(
                InfoGlobalTransaccionSOAP.HTTP + AppDatabase.getInstance(context).obtenerURLConfiguracionConexion() + InfoGlobalTransaccionSOAP.WEB_SERVICE_URI,
                InfoGlobalTransaccionSOAP.HTTP + InfoGlobalTransaccionSOAP.NAME_SPACE,
                InfoGlobalTransaccionSOAP.METHOD_NAME_ANULACION,
                params);

        //Inicializacion del objeto que sera devuelto por la transaccion del webservice
        SoapObject soapTransaction = null;

        try {

            //Transaccion solicitada al web service
            soapTransaction = new KsoapAsync(new KsoapAsync.ResponseKsoapAsync() {

                /**
                 * Metodo sobrecargado que maneja el callback de los datos
                 *
                 * @param soapResponse
                 * @return
                 */
                @Override
                public SoapObject processFinish(SoapObject soapResponse) {
                    return soapResponse;
                }

            }).execute(transactionWS).get();

        } catch (InterruptedException | ExecutionException e) {

            e.printStackTrace();

        }

        //Si la transaccion no genero resultado regresa un establecimiento vacio
        if (soapTransaction != null) {

            //Inicializacion del modelo MessageWS
            MessageWS messageWS = new MessageWS((SoapObject) soapTransaction.getProperty(1));

            switch (messageWS.getCodigoMensaje()) {

                //Transaccion exitosa
                case MessageWS.statusTransaccionExitosa:

                    InformacionTransaccion informacionTransaccion = new InformacionTransaccion((SoapObject) soapTransaction.getProperty(1));
                    resultadoTransaccion = new ResultadoTransaccion(
                            informacionTransaccion,
                            messageWS
                    );
                    break;

                case MessageWS.statusTarjetaHabienteNoExiste:
                case MessageWS.statusClaveErrada:
                case MessageWS.statusTarjetHabienteInactivo:
                case MessageWS.statusTarjetaHabienteMora:
                case MessageWS.statusTarjetaHabienteSinCupoDisponible:
                case MessageWS.statusCedulaTarjetaNoExiste:
                case MessageWS.statusTerminalNoExiste:
                case MessageWS.statusTarjetaNoPermitidaEnTerminal:
                case MessageWS.statusErrorDatabase:
                case MessageWS.statusTerminalErrorException:
                    resultadoTransaccion = new ResultadoTransaccion(
                            messageWS
                    );
                    break;
            }

        }

        //Retorno de estado de transaccion
        return resultadoTransaccion;
    }

    /**
     * Metodo que registra en la base de datos interna la transaccion
     *
     * @param context
     * @return
     */
    private boolean registrarTransaccionConsumoDB(Context context, InformacionTransaccion informacionTransaccion) {

        //Se crea una variable de estado de la transaccion
        boolean statusTransaction = false;

        //Se registra la transaccion
        if (AppDatabase.getInstance(context).insertRegistroTransaction(informacionTransaccion, Transaccion.TIPO_TRANSACCION_ANULACION)) {
            statusTransaction = true;
        }

        return statusTransaction;
    }


    /**
     * Metodo que imprime el recibo de la transaccion
     *
     * @param context
     */
    public void imprimirRecibo(Context context) {


    }

    /**
     * Metodo que registra los eventos
     *
     * @param type
     * @param errorMessage
     */
    private void postEvent(int type, String errorMessage, int valorInt) {

        AnulacionScreenEvent anulacionScreenEvent = new AnulacionScreenEvent();

        anulacionScreenEvent.setEventType(type);

        if (errorMessage != null) {
            anulacionScreenEvent.setErrorMessage(errorMessage);
        }

        if (valorInt != AnulacionScreenEvent.VALOR_TRANSACCION_NO_VALIDO) {
            anulacionScreenEvent.setValorInt(valorInt);
        }

        EventBus eventBus = GreenRobotEventBus.getInstance();

        eventBus.post(anulacionScreenEvent);
    }

    /**
     * Sobrecarga del metodo postevent
     *
     * @param type
     */
    private void postEvent(int type) {

        postEvent(type, null, AnulacionScreenEvent.VALOR_TRANSACCION_NO_VALIDO);

    }

    /**
     * Sobrecarga del metodo postevent
     *
     * @param type
     */
    private void postEvent(int type, int valorInt) {

        postEvent(type, null, valorInt);

    }

    /**
     * Sobrecarga del metodo postevent
     *
     * @param type
     */
    private void postEvent(int type, String errorMessage) {

        postEvent(type, errorMessage, AnulacionScreenEvent.VALOR_TRANSACCION_NO_VALIDO);

    }

}
