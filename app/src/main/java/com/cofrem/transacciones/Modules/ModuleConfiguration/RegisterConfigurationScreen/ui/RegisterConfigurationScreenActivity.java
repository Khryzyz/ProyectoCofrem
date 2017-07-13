package com.cofrem.transacciones.Modules.ModuleConfiguration.RegisterConfigurationScreen.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputFilter;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.cofrem.transacciones.ConfigurationScreenActivity_;
import com.cofrem.transacciones.Modules.ModuleConfiguration.RegisterConfigurationScreen.RegisterConfigurationScreenPresenter;
import com.cofrem.transacciones.Modules.ModuleConfiguration.RegisterConfigurationScreen.RegisterConfigurationScreenPresenterImpl;
import com.cofrem.transacciones.R;
import com.cofrem.transacciones.SplashScreen.ui.SplashScreenActivity_;
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

    //Almacena el valor de la pantalla anterior
    int valorRetorno;

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
    @ViewById
    FrameLayout frlPgbHldRegisterScreen;


    //Paso configuracion_register_paso_pass_tecnico
    @ViewById
    Button btnConfiguracionRegisterPassTecnicoBotonCancelar;
    @ViewById
    EditText edtConfiguracionRegisterPassTecnicoContenidoClave;

    //Paso configuracion_register_paso_pass_host
    @ViewById
    Button btnConfiguracionRegisterHostBotonCancelar;
    @ViewById
    EditText edtConfiguracionRegisterHostContenidoValor;

    //Paso configuracion_register_paso_pass_port
    @ViewById
    Button btnConfiguracionRegisterPortBotonCancelar;
    @ViewById
    EditText edtConfiguracionRegisterPortContenidoValor;

    //Paso configuracion_register_paso_pass_dispositivo
    @ViewById
    Button btnConfiguracionRegisterDispositivoBotonCancelar;
    @ViewById
    EditText edtConfiguracionRegisterDispositivoContenidoValor;

    /**
     * Model que almacena la configuracion del dispositivo
     */
    Configurations modelConfiguration = new Configurations();

    /**
     * Pasos definidos
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
        pasoRegisterConfguration = PASO_PASS_TECNICO;

        //Primera ventana visible
        bodyContentConfigurationPassTecnico.setVisibility(View.VISIBLE);

        //Recibe los parametros del Bundle
        Bundle args = getIntent().getExtras();

        valorRetorno = args.getInt(Configurations.keyConfiguration);

        if (valorRetorno == Configurations.configuracionRegistrarConfigInicial) {
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
     * Metodo que intercepta las pulsaciones de las teclas del teclado fisico
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

                switch (pasoRegisterConfguration) {

                    case PASO_PASS_TECNICO:
                        //Metodo para validar la contraseña
                        validarPasswordTecnico();
                        break;

                    case PASO_HOST:
                        //Metodo para registrar el host de conexion
                        registrarHost();
                        break;

                    case PASO_PORT:
                        //Metodo para registrar el port de conexion
                        registrarPort();
                        break;

                    case PASO_DISPOSITIVO:
                        //Metodo para registrar el identificador del dispositivo
                        registrarDispositivo();
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
    public void handlePasswordTecnicoValido() {

        //Oculta la barra de progreso
        hideProgress();

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
    public void handlePasswordTecnicoNoValido() {

        //Oculta la barra de progreso
        hideProgress();

        //Vacia la caja de contraseña de administracion tecnica
        edtConfiguracionRegisterPassTecnicoContenidoClave.setText("");

        //Muestra el mensaje de error en la contraseña de administracion tecnica
        Toast.makeText(this, R.string.configuration_text_valor_acceso_no_valido, Toast.LENGTH_SHORT).show();

    }

    /**
     * Metodo para manejar el error en la configuracion de valor de acceso
     */
    @Override
    public void handlePasswordTecnicoError() {

        //Oculta la barra de progreso
        hideProgress();

        //Vacia la caja de contraseña de administracion tecnica
        edtConfiguracionRegisterPassTecnicoContenidoClave.setText("");

        //Muestra el mensaje de error en la configuracion de la contraseña de administracion tecnica
        Toast.makeText(this, R.string.configuration_text_valor_acceso_error, Toast.LENGTH_SHORT).show();

    }

    /**
     * Metodo para manejar el registro de configuracion de conexion correcto
     */
    @Override
    public void handleRegistroConfigConexionSuccess() {

        //Muestra el mensaje de informacion del registro de conexion correcto
        Toast.makeText(this, R.string.configuration_text_registro_conexion_success, Toast.LENGTH_SHORT).show();

    }

    /**
     * Metodo para manejar el registro de configuracion de conexion incorrecto
     */
    @Override
    public void handleRegistroConfigConexionError() {

        //Oculta la barra de progreso
        hideProgress();

        //Muestra el mensaje de error del registro de conexion incorrecto
        Toast.makeText(this, R.string.configuration_text_registro_conexion_error, Toast.LENGTH_SHORT).show();

    }

    /**
     * Metodo para manejar el registro de informacion del dispositivo correcto
     */
    @Override
    public void handleInformacionDispositivoSuccess() {

        //Muestra el mensaje de informacion del registro de informacion del dispositivo correcto
        Toast.makeText(this, R.string.configuration_text_informacion_dispositivo_success, Toast.LENGTH_SHORT).show();

    }

    /**
     * Metodo para manejar el registro de informacion del dispositivo incorrecto
     */
    @Override
    public void handleInformacionDispositivoErrorConexion() {

        //Oculta la barra de progreso
        hideProgress();

        //Muestra el mensaje de error del registro de informacion del dispositivo incorrecto
        Toast.makeText(this, R.string.configuration_text_informacion_dispositivo_error_conexion, Toast.LENGTH_SHORT).show();

        pasoRegisterConfguration = PASO_HOST;

        //Oculta las vistas
        inicializarOcultamientoVistas();

        //Muestra la vista del codigo de host
        bodyContentConfigurationHost.setVisibility(View.VISIBLE);

    }

    /**
     * Metodo para manejar el registro de informacion del dispositivo incorrecto
     */
    @Override
    public void handleInformacionDispositivoErrorInformacion() {

        //Oculta la barra de progreso
        hideProgress();

        //Muestra el mensaje de error del registro de informacion del dispositivo incorrecto
        Toast.makeText(this, R.string.configuration_text_informacion_dispositivo_error_informacion, Toast.LENGTH_SHORT).show();

        pasoRegisterConfguration = PASO_DISPOSITIVO;

        //Oculta las vistas
        inicializarOcultamientoVistas();

        //Muestra la vista del codigo de dispositivo
        bodyContentConfigurationDispositivo.setVisibility(View.VISIBLE);
    }

    /**
     * Metodo para manejar el registro de la informacion del dispositivo correcto
     */
    @Override
    public void handleProccessInformacionEstablecimientoSuccess() {

        //Oculta la barra de progreso
        hideProgress();

        //Muestra el mensaje de error del registro de informacion del dispositivo incorrecto
        Toast.makeText(this, R.string.configuration_text_procesar_informacion_dispositivo_success, Toast.LENGTH_SHORT).show();

        switch (valorRetorno) {

            case Configurations.configuracionRegistrarConfigInicial:

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent intent = new Intent(RegisterConfigurationScreenActivity.this, SplashScreenActivity_.class);

                        //Agregadas banderas para no retorno
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                                | Intent.FLAG_ACTIVITY_NEW_TASK
                                | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                        startActivity(intent);
                    }
                }, 1000);

                break;

            case Configurations.configuracionRegistrar:

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent intent = new Intent(RegisterConfigurationScreenActivity.this, ConfigurationScreenActivity_.class);

                        //Agregadas banderas para no retorno
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                                | Intent.FLAG_ACTIVITY_NEW_TASK
                                | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                        startActivity(intent);
                    }
                }, 1000);

                break;

        }
    }

    /**
     * Metodo para manejar el registro de la informacion del dispositivo erronea
     */
    @Override
    public void handleProccessInformacionEstablecimientoError() {

        //Oculta la barra de progreso
        hideProgress();

        //Muestra el mensaje de error del registro de informacion del dispositivo incorrecto
        Toast.makeText(this, R.string.configuration_text_procesar_informacion_dispositivo_error, Toast.LENGTH_SHORT).show();

        pasoRegisterConfguration = PASO_HOST;

        //Oculta las vistas
        inicializarOcultamientoVistas();

        //Muestra la vista del codigo de host
        bodyContentConfigurationHost.setVisibility(View.VISIBLE);

    }


    /**
     * #############################################################################################
     * Metodo propios de la clase
     * #############################################################################################
     */

    /**
     * Metodo para mostrar la barra de progreso
     */
    private void showProgress() {
        //TODO: VERIFICAR QUE ESTA MOSTRANDO LA BARRA DE PROGRESO
        // Muestra la barra  de progreso
        frlPgbHldRegisterScreen.setVisibility(View.VISIBLE);
    }

    /**
     * Metodo para ocultar la barra de progreso
     */
    private void hideProgress() {
        //Oculta la barra de progreso
        frlPgbHldRegisterScreen.setVisibility(View.GONE);
    }

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
            R.id.edtConfiguracionRegisterPortContenidoValor
    })
    public void hideKeyBoard() {

        //TODO:VERIFICAR QUE EL TECLADO SE ESTA OCULTANDO
        //Oculta el teclado
        KeyBoard.hide(this);

    }

    /**
     * Metodo para regresar a la ventana de configuracion
     */
    @Click({R.id.btnConfiguracionRegisterPassTecnicoBotonCancelar,
            R.id.btnConfiguracionRegisterHostBotonCancelar,
            R.id.btnConfiguracionRegisterPortBotonCancelar,
            R.id.btnConfiguracionRegisterDispositivoBotonCancelar
    })
    public void navigateToConfigurationScreen() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(RegisterConfigurationScreenActivity.this, ConfigurationScreenActivity_.class);
                //Agregadas banderas para no retorno
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                        | Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intent);
            }
        }, 1000);
    }

    /**
     * Metodo que envia la contraseña ingresada para su validacion
     */
    @Click(R.id.btnConfiguracionRegisterPassTecnicoBotonAceptar)
    public void validarPasswordTecnico() {

        // Se obtiene el texto de la contraseña
        String passwordAdmin = edtConfiguracionRegisterPassTecnicoContenidoClave.getText().toString();

        if (passwordAdmin.length() == 4) {

            //Muestra la barra de progreso
            showProgress();

            registerConfigurationScreenPresenter.validarPasswordTecnico(this, passwordAdmin);

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
    public void registrarHost() {

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
    public void registrarPort() {

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
    public void registrarDispositivo() {

        //Registra el valor del codigo de dispositivo en el modelo de la configuracion
        modelConfiguration.setCodigoDispositivo(edtConfiguracionRegisterDispositivoContenidoValor.getText().toString());

        //Muestra la barra de progreso
        showProgress();

        registerConfigurationScreenPresenter.registrarConfiguracionConexion(this, modelConfiguration);
    }
}
