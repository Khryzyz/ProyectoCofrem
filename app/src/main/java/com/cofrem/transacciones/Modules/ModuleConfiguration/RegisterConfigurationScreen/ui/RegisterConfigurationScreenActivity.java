package com.cofrem.transacciones.Modules.ModuleConfiguration.RegisterConfigurationScreen.ui;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.cofrem.transacciones.Modules.ModuleConfiguration.RegisterConfigurationScreen.RegisterConfigurationScreenPresenter;
import com.cofrem.transacciones.Modules.ModuleConfiguration.RegisterConfigurationScreen.RegisterConfigurationScreenPresenterImpl;
import com.cofrem.transacciones.R;
import com.cofrem.transacciones.lib.KeyBoard;
import com.cofrem.transacciones.models.Configurations;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import static android.view.KeyEvent.KEYCODE_ENTER;

@EActivity(R.layout.activity_configuration_register_screen)
public class RegisterConfigurationScreenActivity extends Activity implements RegisterConfigurationScreenView {

    /**
     * #############################################################################################
     * Declaracion de componentes y variables
     * #############################################################################################
     */
    /**
     * Declaracion de los Contoles
     */

    // Contents del modulo
    @ViewById
    RelativeLayout bodyContentConfigurationPassTecnico;
    @ViewById
    RelativeLayout bodyContentConfigurationHost;
    @ViewById
    RelativeLayout bodyContentConfigurationPort;
    @ViewById
    RelativeLayout bodyContentConfigurationDispositivo;
    @ViewById
    RelativeLayout bodyContentConfigurationExito;

    //Paso configuracion__register_paso_pass_tecnico
    @ViewById
    Button btnConfiguracionRegisterPassTecnicoBotonCancelar;
    @ViewById
    EditText edtConfiguracionRegisterPassTecnicoContenidoClave;

    //Paso configuracion__register_paso_pass_host
    @ViewById
    Button btnConfiguracionRegisterHostBotonCancelar;
    @ViewById
    EditText edtConfiguracionRegisterHostContenidoValor;

    //Paso configuracion__register_paso_pass_port
    @ViewById
    Button btnConfiguracionRegisterPortBotonCancelar;
    @ViewById
    EditText edtConfiguracionRegisterPortContenidoValor;

    //Paso configuracion__register_paso_pass_dispositivo
    @ViewById
    Button btnConfiguracionRegisterDispositivoBotonCancelar;
    @ViewById
    EditText edtConfiguracionRegisterDispositivoContenidoValor;

    /**
     * Model que almacena la configuracion del dispositivo
     */
    Configurations modelConfiguration = new Configurations();

    /**
     * Pasos definidos del registro de configuracion
     */
    int pasoRegisterConfguration = 0; // Define el paso actual

    final static int PASO_PASS_TECNICO = 0;
    final static int PASO_HOST = 1;
    final static int PASO_PORT = 2;
    final static int PASO_DISPOSITIVO = 3;

    //Creación del filtro para el ingreso de IP
    InputFilter inputFilter = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source,
                                   int start,
                                   int end,
                                   android.text.Spanned dest,
                                   int dstart,
                                   int dend) {
            if (end > start) {
                String destTxt = dest.toString();
                String resultingTxt = destTxt.substring(0, dstart)
                        + source.subSequence(start, end)
                        + destTxt.substring(dend);
                if (!resultingTxt
                        .matches("^\\d{1,3}(\\.(\\d{1,3}(\\.(\\d{1,3}(\\.(\\d{1,3})?)?)?)?)?)?")) {
                    return "";
                } else {
                    String[] splits = resultingTxt.split("\\.");
                    for (int i = 0; i < splits.length; i++) {
                        if (Integer.valueOf(splits[i]) > 255) {
                            return "";
                        }
                    }
                }
            }
            return null;
        }

    };

    /**
     * #############################################################################################
     * Instanciamientos de las clases
     * #############################################################################################
     */
    //Instanciamiento de la interface SaldoScreenPresenter
    private RegisterConfigurationScreenPresenter registerConfigurationScreenPresenter;


    /**
     * #############################################################################################
     * Constructor  de  la clase
     * #############################################################################################
     */
    @AfterViews
    void ConfigurationInit() {

        /**
         * Instanciamiento e inicializacion del presentador
         */
        registerConfigurationScreenPresenter = new RegisterConfigurationScreenPresenterImpl(this);

        /**
         * Llamada al metodo onCreate del presentador para el registro del bus de datos
         */
        registerConfigurationScreenPresenter.onCreate();

        /**
         * Metodo que oculta por defecto los include de la vista
         */
        inicializarOcultamientoVistas();

        //Inicializa el paso del registro de la configuracion
        pasoRegisterConfguration = 0;

        //Primera ventana visible
        bodyContentConfigurationPassTecnico.setVisibility(View.VISIBLE);

        //Recibe los parametros del Bundle
        Bundle args = getIntent().getExtras();

        if (args.getInt(Configurations.keyConfiguration) == Configurations.configuracionRegistrarConfigInicial) {
            btnConfiguracionRegisterPassTecnicoBotonCancelar.setVisibility(View.GONE);
            btnConfiguracionRegisterHostBotonCancelar.setVisibility(View.GONE);
            btnConfiguracionRegisterPortBotonCancelar.setVisibility(View.GONE);
            btnConfiguracionRegisterDispositivoBotonCancelar.setVisibility(View.GONE);
        }

        //Seteando el valor del filtro en el EditText
        edtConfiguracionRegisterHostContenidoValor.setFilters(new InputFilter[]{inputFilter});

    }

    /**
     * #############################################################################################
     * Metodos sobrecargados del sistema
     * #############################################################################################
     */

    /**
     * Metodo sobrecargado de la vista para la destruccion de la activity
     */
    @Override
    public void onDestroy() {
        registerConfigurationScreenPresenter.onDestroy();
        super.onDestroy();
    }

    /**
     * Meotodo que intercepta las pulsaciones de las teclas del teclado fisico
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        /**
         * Keycodes disponibles
         *
         * 4: Back
         * 66: Enter
         * 67: Delete
         *
         */
        switch (keyCode) {

            case KEYCODE_ENTER:

                //Metodo para ocultar el teclado
                hideKeyBoard();
                switch (pasoRegisterConfguration) {

                    case PASO_PASS_TECNICO:
                        //Metodo para validar la contraseña
                        validatePasswordAdmin();
                        break;

                    case PASO_HOST:
                        //Metodo para registrar el host de conexion
                        registerHost();
                        break;

                    case PASO_PORT:
                        //Metodo para registrar el port de conexion
                        registerPort();
                        break;

                    case PASO_DISPOSITIVO:
                        //Metodo para registrar el identificador del dispositivo
                        registerDispositivo();
                        break;
                }
                break;

            default:
                Log.i("Key Pressed", String.valueOf(keyCode));
                break;

        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * Metodo que interfiere la presion del boton "Back"
     */
    @Override
    public void onBackPressed() {

        switch (pasoRegisterConfguration) {

            case PASO_PASS_TECNICO:
                //Vacia la caja de contraseña
                edtConfiguracionRegisterPassTecnicoContenidoClave.setText("");
                break;

            case PASO_HOST:
                //Vacia la caja del host de conexion
                edtConfiguracionRegisterHostContenidoValor.setText("");
                break;

            case PASO_PORT:
                //Vacia la caja del puerto de conexion
                edtConfiguracionRegisterHostContenidoValor.setText("");

                break;

            case PASO_DISPOSITIVO:
                //Vacia la caja del codigo de identificacion del dispositivo
                edtConfiguracionRegisterHostContenidoValor.setText("");

                break;

        }
    }

    /**
     * #############################################################################################
     * Metodos sobrecargados de la interface
     * #############################################################################################
     */

    /**
     * Metodo para manejar el valor de acceso valido
     */
    @Override
    public void handleValorAccesoValido() {

        //Oculta la vista de la contraseña de administracion tecnica
        bodyContentConfigurationPassTecnico.setVisibility(View.GONE);

        //Muestra la vista del Host de conexion
        bodyContentConfigurationHost.setVisibility(View.VISIBLE);

        //Actualiza el paso actual
        pasoRegisterConfguration++;
    }

    /**
     * Metodo para manejar el valor de acceso NO valido
     */
    @Override
    public void handleValorAccesoNoValido() {

        //Vacia la caja de contraseña de administracion tecnica
        edtConfiguracionRegisterPassTecnicoContenidoClave.setText("");

        //Muestra el mensaje de error en la contraseña de administracion tecnica
        Toast.makeText(this, R.string.configuration_text_valor_acceso_no_valido, Toast.LENGTH_SHORT).show();

    }

    /**
     * Metodo para manejar el error en la configuracion de valor de acceso
     */
    @Override
    public void handleValorAccesoError() {

        //Vacia la caja de contraseña de administracion tecnica
        edtConfiguracionRegisterPassTecnicoContenidoClave.setText("");

        //Muestra el mensaje de error en la configuracion de la contraseña de administracion tecnica
        Toast.makeText(this, R.string.configuration_text_valor_acceso_error, Toast.LENGTH_SHORT).show();

    }

    /**
     * Metodo para manejar el registro de conexion correcto
     */
    @Override
    public void handleRegistroConexionSuccess() {

        //Muestra el mensaje de informacion del registro de conexion correcto
        Toast.makeText(this, R.string.configuration_text_registro_conexion_success, Toast.LENGTH_SHORT).show();

    }

    /**
     * Metodo para manejar el registro de conexion incorrecto
     */
    @Override
    public void handleRegistroConexionError() {

        //Muestra el mensaje de error del registro de conexion incorrecto
        Toast.makeText(this, R.string.configuration_text_registro_conexion_error, Toast.LENGTH_SHORT).show();

    }

    /**
     * Metodo para manejar el registro de informacion del dispositivo correcto
     */
    @Override
    public void handleRegistroInformacionDispositivoSuccess() {

        //Muestra el mensaje de informacion del registro de informacion del dispositivo correcto
        Toast.makeText(this, R.string.configuration_text_informacion_dispositivo_success, Toast.LENGTH_SHORT).show();

    }

    /**
     * Metodo para manejar el registro de informacion del dispositivo incorrecto
     * por error en el registro en la base de datos
     */
    @Override
    public void handleRegistroInformacionDispositivoErrorDatabase() {

        //Muestra el mensaje de error del registro de informacion del dispositivo incorrecto
        Toast.makeText(this, R.string.configuration_text_informacion_dispositivo_error_database, Toast.LENGTH_SHORT).show();

    }

    /**
     * Metodo para manejar el registro de informacion del dispositivo incorrecto
     * por error en la conexion con el web service
     */
    @Override
    public void handleRegistroInformacionDispositivoErrorConnection() {

        //Muestra el mensaje de error del registro de informacion del dispositivo incorrecto
        Toast.makeText(this, R.string.configuration_text_informacion_dispositivo_error_connection, Toast.LENGTH_SHORT).show();

    }


    /**
     * #############################################################################################
     * Metodo propios de la clase
     * #############################################################################################
     */

    /**
     * Metodo que oculta por defecto los include de la vista
     */
    private void inicializarOcultamientoVistas() {

        bodyContentConfigurationHost.setVisibility(View.GONE);
        bodyContentConfigurationPort.setVisibility(View.GONE);
        bodyContentConfigurationDispositivo.setVisibility(View.GONE);
        bodyContentConfigurationExito.setVisibility(View.GONE);

    }

    /**
     * Metodo que oculta el teclado al presionar el EditText
     */
    @Click({R.id.edtConfiguracionRegisterPassTecnicoContenidoClave,
            R.id.edtConfiguracionRegisterHostContenidoValor,
            R.id.edtConfiguracionRegisterPortContenidoValor,
            R.id.edtConfiguracionRegisterDispositivoContenidoValor
    })
    public void hideKeyBoard() {

        //Oculta el teclado
        KeyBoard.hide(this);

    }

    /**
     * Metodo que envia la contraseña ingresada para su validacion
     */
    @Click(R.id.btnConfiguracionRegisterPassTecnicoBotonAceptar)
    public void validatePasswordAdmin() {

        // Se obtiene el texto de la contraseña
        String passwordAdmin = edtConfiguracionRegisterPassTecnicoContenidoClave.getText().toString();

        if (passwordAdmin.length() == 4) {

            registerConfigurationScreenPresenter.validateAccessAdmin(this, passwordAdmin);

        } else {

            //Vacia la caja de contraseña
            edtConfiguracionRegisterPassTecnicoContenidoClave.setText("");

            //Muestra el mensaje de error de formato de la contraseña
            Toast.makeText(this, R.string.configuration_error_format_valor_acceso, Toast.LENGTH_SHORT).show();

        }

    }


    /**
     * Metodo que registra la configuracion del host
     */
    @Click(R.id.btnConfiguracionRegisterHostBotonAceptar)
    public void registerHost() {

        //Registra el valor del host en el modelo de la configuracion
        modelConfiguration.setHost(edtConfiguracionRegisterHostContenidoValor.getText().toString());

        //Oculta la vista del Host de conexion
        bodyContentConfigurationHost.setVisibility(View.GONE);

        //Muestra la vista del Port de conexion
        bodyContentConfigurationPort.setVisibility(View.VISIBLE);

        //Actualiza el paso actual
        pasoRegisterConfguration++;
    }

    /**
     * Metodo que registra la configuracion del port
     */
    @Click(R.id.btnConfiguracionRegisterPortBotonAceptar)
    public void registerPort() {

        //Registra el valor del port en el modelo de la configuracion
        modelConfiguration.setPort(Integer.parseInt(edtConfiguracionRegisterPortContenidoValor.getText().toString()));

        //Oculta la vista del Port de conexion
        bodyContentConfigurationPort.setVisibility(View.GONE);

        //Muestra la vista del codigo de dispositivo
        bodyContentConfigurationDispositivo.setVisibility(View.VISIBLE);

        //Actualiza el paso actual
        pasoRegisterConfguration++;
    }

    /**
     * Metodo que registra la configuracion del dispositivo
     */
    @Click(R.id.btnConfiguracionRegisterDispositivoBotonAceptar)
    public void registerDispositivo() {

        //Registra el valor del codigo de dispositivo en el modelo de la configuracion
        modelConfiguration.setCodigoDispositivo(edtConfiguracionRegisterDispositivoContenidoValor.getText().toString());

        registerConfigurationScreenPresenter.registerConexion(this, modelConfiguration);

        //Actualiza el paso actual
        pasoRegisterConfguration++;
    }

}
