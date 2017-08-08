package com.cofrem.transacciones.Modules.ModuleTransaction.CreditoScreen;

import android.content.Context;

import com.cofrem.transacciones.Modules.ModuleTransaction.CreditoScreen.events.CreditoScreenEvent;
import com.cofrem.transacciones.database.AppDatabase;
import com.cofrem.transacciones.global.InfoGlobalTransaccionSOAP;
import com.cofrem.transacciones.lib.EventBus;
import com.cofrem.transacciones.lib.GreenRobotEventBus;
import com.cofrem.transacciones.lib.KsoapAsync;
import com.cofrem.transacciones.models.ModelsWS.MessageWS;
import com.cofrem.transacciones.models.ModelsWS.TransactionWS;
import com.cofrem.transacciones.models.Transaccion;

import org.ksoap2.serialization.SoapObject;

import java.util.concurrent.ExecutionException;

public class CreditoScreenRepositoryImpl implements CreditoScreenRepository {
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
    public CreditoScreenRepositoryImpl() {
    }

    /**
     * #############################################################################################
     * Metodos sobrecargados de la interface
     * #############################################################################################
     */

    /**
     * Metodo para obtener el numero de tarjeta desde el dispositivo
     *
     * @param context
     * @param transaccion
     */
    @Override
    public void registrarTransaccion(Context context, Transaccion transaccion) {

        //Registra mediante el WS la transaccion
        if (registrarTransaccionConsumoWS(context, transaccion)) {

            postEvent(CreditoScreenEvent.onTransaccionWSRegisterSuccess);

            //Registro en la base de datos de la transaccion
            if (registrarTransaccionConsumoDB(context, transaccion)) {

                postEvent(CreditoScreenEvent.onTransaccionDBRegisterSuccess);

                //Imprime el recibo
                imprimirRecibo(context);

            } else {

                //Error en el registro en la Base de Datos la transaccion
                postEvent(CreditoScreenEvent.onTransaccionDBRegisterError);

            }

        } else {

            //Error en el registro mediante el WS la transaccion
            postEvent(CreditoScreenEvent.onTransaccionWSRegisterError);

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

    private boolean registrarTransaccionConsumoWS(Context context, Transaccion transaccion) {

        //Se crea una variable de estado de la transaccion
        boolean statusTransaction = true;

        //Inicializacion y declaracion de parametros para la peticion web service
        String[][] params = {
                {InfoGlobalTransaccionSOAP.PARAM_NAME_TRANSACCION_CODIGO_TERMINAL, AppDatabase.getInstance(context).obtenerCodigoTerminal()},
                {InfoGlobalTransaccionSOAP.PARAM_NAME_TRANSACCION_TIPO_TRANSACCION, String.valueOf(transaccion.getTipo_transaccion())},
                {InfoGlobalTransaccionSOAP.PARAM_NAME_TRANSACCION_CEDULA_USUARIO, transaccion.getNumero_documento()},
                {InfoGlobalTransaccionSOAP.PARAM_NAME_TRANSACCION_NUMERO_TARJETA, transaccion.getNumero_tarjeta()},
                {InfoGlobalTransaccionSOAP.PARAM_NAME_TRANSACCION_CLAVE_USUARIO, String.valueOf(transaccion.getClave())},
                {InfoGlobalTransaccionSOAP.PARAM_NAME_TRANSACCION_TIPO_ENCRIPTACION, String.valueOf(transaccion.getTipo_encriptacion())},
                {InfoGlobalTransaccionSOAP.PARAM_NAME_TRANSACCION_TIPO_SERVICIO, String.valueOf(transaccion.getTipo_servicio())},
                {InfoGlobalTransaccionSOAP.PARAM_NAME_TRANSACCION_VALOR_SOLICITADO, String.valueOf(transaccion.getValor())},
        };

        //Creacion del modelo TransactionWS para ser usado dentro del webservice
        TransactionWS transactionWS = new TransactionWS(
                InfoGlobalTransaccionSOAP.HTTP + AppDatabase.getInstance(context).obtenerURLConfiguracionConexion() + InfoGlobalTransaccionSOAP.WEB_SERVICE_URI,
                InfoGlobalTransaccionSOAP.HTTP + InfoGlobalTransaccionSOAP.NAME_SPACE,
                InfoGlobalTransaccionSOAP.METHOD_NAME_TRANSACCION,
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
            MessageWS messageWS = new MessageWS((SoapObject) soapTransaction.getProperty(0));

            switch (messageWS.getCodigoMensaje()) {

                //Transaccion exitosa
                case MessageWS.statusTransaccionExitosa:

                    break;

                //Tarjeta habiente no existe
                case MessageWS.statusTarjetaHabienteNoExiste:

                    break;

                //Clave errada
                case MessageWS.statusClaveErrada:

                    break;

                //Tarjeta habiente inactivo
                case MessageWS.statusTarjetHabienteInactivo:

                    break;

                //Tarjeta habiente en mora
                case MessageWS.statusTarjetaHabienteMora:

                    break;

                //Tarjeta habiente sin cupo disponible
                case MessageWS.statusTarjetaHabienteSinCupoDisponible:

                    break;

                //Tarjeta cedula no existe
                case MessageWS.statusCedulaTarjetaNoExiste:

                    break;

                //Tarjeta terminal no existe
                case MessageWS.statusTerminalNoExiste:

                    break;

                //Transaccion no permitida en terminal
                case MessageWS.statusTarjetaNoPermitidaEnTerminal:

                    break;

                //Error en base de datos
                case MessageWS.statusErrorDatabase:

                    break;

                //Error general en la transaccion
                case MessageWS.statusTerminalErrorException:

                    break;
            }

        }

        //Retorno de estado de transaccion
        return statusTransaction;
    }

    /**
     * Metodo que registra en la base de datos interna la transaccion
     *
     * @param context
     * @param transaccion
     * @return
     */
    private boolean registrarTransaccionConsumoDB(Context context, Transaccion transaccion) {

        //Se crea una variable de estado de la transaccion
        boolean statusTransaction = true;


        //TODO: IMPLEMENTAR WEB SERVICE DE REGISTRO DE TRANSACCION

        return statusTransaction;
    }

    /**
     * Metodo que imprime el recibo de la transaccion
     *
     * @param context
     */
    private void imprimirRecibo(Context context) {


        //TODO: Implementar la imprecion del recibo
    }

    /**
     * Metodo que registra los eventos
     *
     * @param type
     * @param errorMessage
     */
    private void postEvent(int type, String errorMessage) {
        CreditoScreenEvent creditoScreenEvent = new CreditoScreenEvent();
        creditoScreenEvent.setEventType(type);
        if (errorMessage != null) {
            creditoScreenEvent.setErrorMessage(errorMessage);
        }

        EventBus eventBus = GreenRobotEventBus.getInstance();

        eventBus.post(creditoScreenEvent);
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


