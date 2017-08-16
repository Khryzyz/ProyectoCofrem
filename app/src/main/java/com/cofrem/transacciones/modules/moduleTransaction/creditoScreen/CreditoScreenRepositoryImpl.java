package com.cofrem.transacciones.modules.moduleTransaction.creditoScreen;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.cofrem.transacciones.R;
import com.cofrem.transacciones.global.InfoGlobalSettingsPrint;
import com.cofrem.transacciones.lib.PrinterHandler;
import com.cofrem.transacciones.lib.StyleConfig;
import com.cofrem.transacciones.models.ConfigurationPrinter;
import com.cofrem.transacciones.models.Establishment;
import com.cofrem.transacciones.models.PrintRow;
import com.cofrem.transacciones.modules.moduleReports.reimpresionScreen.events.ReimpresionScreenEvent;
import com.cofrem.transacciones.modules.moduleTransaction.creditoScreen.events.CreditoScreenEvent;
import com.cofrem.transacciones.database.AppDatabase;
import com.cofrem.transacciones.global.InfoGlobalTransaccionSOAP;
import com.cofrem.transacciones.lib.EventBus;
import com.cofrem.transacciones.lib.GreenRobotEventBus;
import com.cofrem.transacciones.lib.KsoapAsync;
import com.cofrem.transacciones.models.modelsWS.MessageWS;
import com.cofrem.transacciones.models.modelsWS.modelTransaccion.InformacionTransaccion;
import com.cofrem.transacciones.models.modelsWS.modelTransaccion.ResultadoTransaccion;
import com.cofrem.transacciones.models.modelsWS.TransactionWS;
import com.cofrem.transacciones.models.Transaccion;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
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

        ResultadoTransaccion resultadoTransaccion = registrarTransaccionConsumoWS(context, transaccion);

        //Registra mediante el WS la transaccion
        if (resultadoTransaccion != null) {

            MessageWS messageWS = resultadoTransaccion.getMessageWS();

            if (messageWS.getCodigoMensaje() == MessageWS.statusTransaccionExitosa) {

                //Registro en la base de datos de la transaccion
                if (registrarTransaccionConsumoDB(context, resultadoTransaccion.getInformacionTransaccion())) {

                    postEvent(CreditoScreenEvent.onTransaccionSuccess);

                    //Imprime el recibo
                    imprimirRecibo(context);

                } else {

                    //Error en el registro en la Base de Datos la transaccion
                    postEvent(CreditoScreenEvent.onTransaccionDBRegisterError);

                }
            } else {
                //Error en el registro de la transaccion del web service
                postEvent(CreditoScreenEvent.onTransaccionWSRegisterError, messageWS.getDetalleMensaje());
            }
        } else {
            //Error en la conexion con el Web Service
            postEvent(CreditoScreenEvent.onTransaccionWSConexionError);
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
        if (AppDatabase.getInstance(context).insertRegistroTransaction(informacionTransaccion, Transaccion.CODIGO_TIPO_TRANSACCION_CONSUMO)) {
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

        ConfigurationPrinter configurationPrinter = AppDatabase.getInstance(context).getConfigurationPrinter();

        int gray = configurationPrinter.getGray_level();

        Transaccion modelTransaccion = AppDatabase.getInstance(context).obtenerUltimaTransaccion();

        // creamos el ArrayList se que encarga de almacenar los rows del recibo
        ArrayList<PrintRow> printRows = new ArrayList<PrintRow>();

        //Se agrega el logo al primer renglon del recibo y se coloca en el centro
        printRows.add(PrintRow.printLogo(context, gray));

        //se siguen agregando cado auno de los String a los renglones (Rows) del recibo para imprimir
        PrintRow.printEstablecimiento(context, printRows ,gray);

        printRows.add(new PrintRow(modelTransaccion.getRegistro(), new StyleConfig(StyleConfig.Align.CENTER,gray, 20)));

        printRows.add(new PrintRow(context.getResources().getString(
                R.string.recibo_numero_transaccion), modelTransaccion.getNumero_cargo(), new StyleConfig(StyleConfig.Align.LEFT, gray)));
        printRows.add(new PrintRow(context.getResources().getString(
                R.string.recibo_valor), String.valueOf(modelTransaccion.getValor()), new StyleConfig(StyleConfig.Align.LEFT, gray)));
        printRows.add(new PrintRow(context.getResources().getString(
                R.string.recibo_numero_tarjeta), PrinterHandler.getFormatNumTarjeta(modelTransaccion.getNumero_tarjeta()), new StyleConfig(StyleConfig.Align.LEFT,gray, 20)));

        PrintRow.printFirma(context, printRows, gray);

        printRows.add(new PrintRow(".", new StyleConfig(StyleConfig.Align.LEFT, 1)));

        //retornamos el estado de la impresora tras enviar los rows para imprimir

        int status = new PrinterHandler().imprimerTexto(printRows);

        if (status == InfoGlobalSettingsPrint.PRINTER_OK) {
            postEvent(CreditoScreenEvent.onImprecionReciboSuccess);
        } else {
            postEvent(CreditoScreenEvent.onImprecionReciboError, PrinterHandler.stringErrorPrinter(status, context));
        }

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


