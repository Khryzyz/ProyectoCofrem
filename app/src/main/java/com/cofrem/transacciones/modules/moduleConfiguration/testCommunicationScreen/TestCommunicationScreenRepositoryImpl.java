package com.cofrem.transacciones.modules.moduleConfiguration.testCommunicationScreen;

import android.content.Context;

import com.cofrem.transacciones.database.AppDatabase;
import com.cofrem.transacciones.global.InfoGlobalTransaccionSOAP;
import com.cofrem.transacciones.lib.KsoapAsync;
import com.cofrem.transacciones.models.modelsWS.MessageWS;
import com.cofrem.transacciones.models.modelsWS.TransactionWS;
import com.cofrem.transacciones.models.modelsWS.modelTransaccion.InformacionSaldo;
import com.cofrem.transacciones.models.modelsWS.modelTransaccion.ResultadoTransaccion;
import com.cofrem.transacciones.modules.moduleConfiguration.testCommunicationScreen.events.TestCommunicationScreenEvent;
import com.cofrem.transacciones.lib.EventBus;
import com.cofrem.transacciones.lib.GreenRobotEventBus;
import org.ksoap2.serialization.SoapObject;
import java.util.concurrent.ExecutionException;

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

    @Override
    public void testComunication(Context context) {

        ResultadoTransaccion resultadoTransaccion = registrarTransaccionConsumoWS(context);

        //Registra mediante el WS la transaccion
        if (resultadoTransaccion != null) {

            MessageWS messageWS = resultadoTransaccion.getMessageWS();

            if (messageWS.getCodigoMensaje() == MessageWS.statusConsultaExitosa) {

//                postEvent(SaldoScreenEvent.onTransaccionSuccess, resultadoTransaccion.getInformacionSaldo());
                postEvent(TestCommunicationScreenEvent.onTestComunicationSuccess);

                //Imprime el recibo
                //imprimirRecibo(context);

            } else {
                //Error en el registro de la transaccion del web service
                postEvent(TestCommunicationScreenEvent.onTestComunicationError, messageWS.getDetalleMensaje());
            }
        } else {
            //Error en la conexion con el Web Service
            postEvent(TestCommunicationScreenEvent.onTransaccionWSConexionError);
        }

    }


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
     * Metodo que:
     * - registra mediante el WS la transaccion
     * - Extrae el estado de la transaccion
     *
     * @param context     contexto desde la cual se realiza la transaccion
     * @return regreso del resultado de la transaccion
     */
    private ResultadoTransaccion registrarTransaccionConsumoWS(Context context) {

        //Se crea una variable de estado de la transaccion
        ResultadoTransaccion resultadoTransaccion = null;

        //Inicializacion y declaracion de parametros para la peticion web service
        String[][] params = {
                {InfoGlobalTransaccionSOAP.PARAM_NAME_SALDO_CODIGO_TERMINAL, AppDatabase.getInstance(context).obtenerCodigoTerminal()}
        };

        //Creacion del modelo TransactionWS para ser usado dentro del webservice
        TransactionWS transactionWS = new TransactionWS(
                InfoGlobalTransaccionSOAP.HTTP + AppDatabase.getInstance(context).obtenerURLConfiguracionConexion() + InfoGlobalTransaccionSOAP.WEB_SERVICE_URI,
                InfoGlobalTransaccionSOAP.HTTP + InfoGlobalTransaccionSOAP.NAME_SPACE,
                InfoGlobalTransaccionSOAP.METHOD_NAME_TEST,
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
