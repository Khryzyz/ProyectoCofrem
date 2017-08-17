package com.cofrem.transacciones.modules.moduleTransaction.saldoScreen;

import android.content.Context;

import com.cofrem.transacciones.database.AppDatabase;
import com.cofrem.transacciones.global.InfoGlobalTransaccionSOAP;
import com.cofrem.transacciones.lib.KsoapAsync;
import com.cofrem.transacciones.models.Transaccion;
import com.cofrem.transacciones.models.modelsWS.MessageWS;
import com.cofrem.transacciones.models.modelsWS.TransactionWS;
import com.cofrem.transacciones.models.modelsWS.modelTransaccion.InformacionSaldo;
import com.cofrem.transacciones.models.modelsWS.modelTransaccion.InformacionTransaccion;
import com.cofrem.transacciones.models.modelsWS.modelTransaccion.ResultadoTransaccion;
import com.cofrem.transacciones.modules.moduleTransaction.saldoScreen.events.SaldoScreenEvent;
import com.cofrem.transacciones.lib.EventBus;
import com.cofrem.transacciones.lib.GreenRobotEventBus;

import org.ksoap2.serialization.SoapObject;

import java.util.concurrent.ExecutionException;

public class SaldoScreenRepositoryImpl implements SaldoScreenRepository {
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
    public SaldoScreenRepositoryImpl() {
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
    public void registrarTransaccion(Context context, Transaccion transaccion) {

        ResultadoTransaccion resultadoTransaccion = registrarTransaccionConsumoWS(context, transaccion);

        //Registra mediante el WS la transaccion
        if (resultadoTransaccion != null) {

            MessageWS messageWS = resultadoTransaccion.getMessageWS();

            if (messageWS.getCodigoMensaje() == MessageWS.statusTransaccionExitosa) {

                postEvent(SaldoScreenEvent.onTransaccionSuccess, resultadoTransaccion.getInformacionSaldo());

                //Imprime el recibo
                imprimirRecibo(context);

            } else {
                //Error en el registro de la transaccion del web service
                postEvent(SaldoScreenEvent.onTransaccionWSRegisterError, messageWS.getDetalleMensaje());
            }
        } else {
            //Error en la conexion con el Web Service
            postEvent(SaldoScreenEvent.onTransaccionWSConexionError);
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
     * @param context     contexto desde la cual se realiza la transaccion
     * @param transaccion datos de la transaccion
     * @return regreso del resultado de la transaccion
     */
    private ResultadoTransaccion registrarTransaccionConsumoWS(Context context, Transaccion transaccion) {

        //Se crea una variable de estado de la transaccion
        ResultadoTransaccion resultadoTransaccion = null;

        //Inicializacion y declaracion de parametros para la peticion web service
        String[][] params = {
                {InfoGlobalTransaccionSOAP.PARAM_NAME_SALDO_CODIGO_TERMINAL, AppDatabase.getInstance(context).obtenerCodigoTerminal()},
                {InfoGlobalTransaccionSOAP.PARAM_NAME_SALDO_CEDULA_USUARIO, transaccion.getNumero_documento()},
                {InfoGlobalTransaccionSOAP.PARAM_NAME_SALDO_NUMERO_TARJETA, transaccion.getNumero_tarjeta()},
                {InfoGlobalTransaccionSOAP.PARAM_NAME_SALDO_CLAVE_USUARIO, String.valueOf(transaccion.getClave())},
                {InfoGlobalTransaccionSOAP.PARAM_NAME_SALDO_TIPO_ENCRIPTACION, String.valueOf(transaccion.getTipo_encriptacion())}
        };

        //Creacion del modelo TransactionWS para ser usado dentro del webservice
        TransactionWS transactionWS = new TransactionWS(
                InfoGlobalTransaccionSOAP.HTTP + AppDatabase.getInstance(context).obtenerURLConfiguracionConexion() + InfoGlobalTransaccionSOAP.WEB_SERVICE_URI,
                InfoGlobalTransaccionSOAP.HTTP + InfoGlobalTransaccionSOAP.NAME_SPACE,
                InfoGlobalTransaccionSOAP.METHOD_NAME_SALDO,
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
            MessageWS messageWS = new MessageWS(
                    (SoapObject) soapTransaction.getProperty(MessageWS.PROPERTY_MESSAGE)
            );

            switch (messageWS.getCodigoMensaje()) {

                //Transaccion exitosa
                case MessageWS.statusTransaccionExitosa:

                    InformacionSaldo informacionSaldo = new InformacionSaldo(
                            (SoapObject) soapTransaction.getProperty(InformacionSaldo.PROPERTY_SALDO_RESULT)
                    );

                    resultadoTransaccion = new ResultadoTransaccion(
                            informacionSaldo,
                            messageWS
                    );
                    break;

                default:
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
    private void postEvent(int type, String errorMessage, InformacionSaldo informacionSaldo) {
        SaldoScreenEvent saldoScreenEvent = new SaldoScreenEvent();
        saldoScreenEvent.setEventType(type);
        if (errorMessage != null) {
            saldoScreenEvent.setErrorMessage(errorMessage);
        }

        if (informacionSaldo != null) {
            saldoScreenEvent.setInformacionSaldo(informacionSaldo);
        }

        EventBus eventBus = GreenRobotEventBus.getInstance();

        eventBus.post(saldoScreenEvent);
    }

    /**
     * Sobrecarga del metodo postevent
     *
     * @param type
     */
    private void postEvent(int type) {

        postEvent(type, null, null);

    }

    /**
     * Sobrecarga del metodo postevent
     *
     * @param type
     */
    private void postEvent(int type, String errorMessage) {

        postEvent(type, errorMessage, null);

    }

    /**
     * Sobrecarga del metodo postevent
     *
     * @param type
     */
    private void postEvent(int type, InformacionSaldo informacionSaldo) {

        postEvent(type, null, informacionSaldo);

    }
}
