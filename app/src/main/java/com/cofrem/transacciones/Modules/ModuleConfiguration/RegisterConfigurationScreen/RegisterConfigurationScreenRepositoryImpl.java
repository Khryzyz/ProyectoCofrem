package com.cofrem.transacciones.Modules.ModuleConfiguration.RegisterConfigurationScreen;

import android.content.Context;

import com.cofrem.transacciones.Modules.ModuleConfiguration.RegisterConfigurationScreen.events.RegisterConfigurationScreenEvent;
import com.cofrem.transacciones.database.AppDatabase;
import com.cofrem.transacciones.global.InfoGlobalTransaccionSOAP;
import com.cofrem.transacciones.lib.EventBus;
import com.cofrem.transacciones.lib.GreenRobotEventBus;
import com.cofrem.transacciones.lib.KsoapAsync;
import com.cofrem.transacciones.models.Configurations;
import com.cofrem.transacciones.models.ModelsWS.MessageWS;
import com.cofrem.transacciones.models.ModelsWS.ModelEstablecimiento.ConexionEstablecimiento;
import com.cofrem.transacciones.models.ModelsWS.ModelEstablecimiento.Establecimiento;
import com.cofrem.transacciones.models.ModelsWS.ModelEstablecimiento.InformacionEstablecimiento;
import com.cofrem.transacciones.models.ModelsWS.TransactionWS;

import org.ksoap2.serialization.SoapObject;

import java.util.concurrent.ExecutionException;

public class RegisterConfigurationScreenRepositoryImpl implements RegisterConfigurationScreenRepository {
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
    public RegisterConfigurationScreenRepositoryImpl() {
    }

    /**
     * #############################################################################################
     * Metodos sobrecargados de la interface
     * #############################################################################################
     */

    /**
     * Valida el acceso a la configuracion del dispositivo mediante la contraseña de administrador
     *
     * @param context
     * @param passAdmin
     */
    @Override
    public void validateAccessAdmin(Context context, String passAdmin) {

        int validateExistValorAcceso = AppDatabase.getInstance(context).conteoConfiguracionAccesoByClaveTecnica(passAdmin);

        switch (validateExistValorAcceso) {
            case 0:
                postEvent(RegisterConfigurationScreenEvent.onValorAccesoNoValido);
                break;
            case 1:
                postEvent(RegisterConfigurationScreenEvent.onValorAccesoValido);
                break;
            default:
                postEvent(RegisterConfigurationScreenEvent.onValorAccesoError);
                break;
        }

    }

    /**
     * Registra los parametros de conexion del dispositivo
     *
     * @param context
     * @param configurations
     */
    @Override
    public void registerConexion(Context context, Configurations configurations) {

        //Se registra la configuracion de la conexion en el dispositivo
        if (AppDatabase.getInstance(context).insertConfiguracionConexion(configurations)) {

            //Evento Correcto de registro de configuracion de la conexion en el establecimiento
            postEvent(RegisterConfigurationScreenEvent.onRegistroConfiguracionSuccess);

            //Se crea una nueva instancia del model establecimiento
            Establecimiento establecimiento;

            //Valida por medio del WS si la informacion del establecimiento es correcta
            establecimiento = validateInfoDispositivo(context, configurations.getCodigoDispositivo());

            if (establecimiento != null) {

                //Evento Correcto de recepcion de informacion del establecimiento desde el WS
                postEvent(RegisterConfigurationScreenEvent.onInformacionDispositivoSuccess);

                //Se registra la configuracion de la conexion en el establecimiento
                if (AppDatabase.getInstance(context).processInfoEstablecimiento(establecimiento)) {

                    //Evento Correcto de registro de informacion del establecimiento desde el WS
                    // y actualizacion de accesos
                    postEvent(RegisterConfigurationScreenEvent.onProccessInformacionEstablecimientoSuccess);

                } else {

                    //Evento Erroneo de registro de informacion del establecimiento desde el WS
                    // y actualizacion de accesos
                    postEvent(RegisterConfigurationScreenEvent.onProccessInformacionEstablecimientoError);

                }

            } else {

                //Evento Erroneo de recepcion de informacion del establecimiento desde el WS
                postEvent(RegisterConfigurationScreenEvent.onInformacionDispositivoError);

            }

        } else {

            //Evento Erroneo de registro de configuracion de la conexion en el establecimiento
            postEvent(RegisterConfigurationScreenEvent.onRegistroConfiguracionError);
        }

    }


    /**
     * #############################################################################################
     * Metodo propios de la clase
     * #############################################################################################     *
     */

    /**
     * Metodo que valida mediantre el WS la existencia del dispositivo
     *
     * @param codigoDispositivo
     */
    private Establecimiento validateInfoDispositivo(Context context, String codigoDispositivo) {

        Establecimiento establecimiento = null;

        String[] params = new String[]{InfoGlobalTransaccionSOAP.PARAM_NAME_CODIGO_TERMINAL, codigoDispositivo};

        TransactionWS transactionWS = new TransactionWS(
                AppDatabase.getInstance(context).obtenerURLConfiguracionConexion(),
                InfoGlobalTransaccionSOAP.NAME_SPACE,
                InfoGlobalTransaccionSOAP.METHOD_NAME_TERMINAL,
                new String[][]{params});

        SoapObject soapTransaction = null;

        try {

            soapTransaction = new KsoapAsync(new KsoapAsync.ResponseKsoapAsync() {
                @Override
                public SoapObject processFinish(SoapObject soapResponse) {
                    return soapResponse;
                }

            }).execute(transactionWS).get();

        } catch (InterruptedException e) {

            e.printStackTrace();

        } catch (ExecutionException e) {

            e.printStackTrace();

        }

        if (soapTransaction != null)

        {

            MessageWS messageWS = new MessageWS((SoapObject) soapTransaction.getProperty(0));


            if (messageWS.getCodigoMensaje() == MessageWS.statusTerminalTransactionSuccess) {

                InformacionEstablecimiento informacionEstablecimiento = new InformacionEstablecimiento((SoapObject) soapTransaction.getProperty(1));

                ConexionEstablecimiento conexionEstablecimiento = new ConexionEstablecimiento((SoapObject) soapTransaction.getProperty(1));

                establecimiento = new Establecimiento(
                        informacionEstablecimiento,
                        conexionEstablecimiento,
                        messageWS
                );

            }

        }


        return establecimiento;
    }

    /**
     * Metodo que registra los eventos
     *
     * @param type
     * @param errorMessage
     */
    private void postEvent(int type, String errorMessage) {
        RegisterConfigurationScreenEvent registerConfigurationScreenEvent = new RegisterConfigurationScreenEvent();
        registerConfigurationScreenEvent.setEventType(type);
        if (errorMessage != null) {
            registerConfigurationScreenEvent.setErrorMessage(errorMessage);
        }

        EventBus eventBus = GreenRobotEventBus.getInstance();

        eventBus.post(registerConfigurationScreenEvent);
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
