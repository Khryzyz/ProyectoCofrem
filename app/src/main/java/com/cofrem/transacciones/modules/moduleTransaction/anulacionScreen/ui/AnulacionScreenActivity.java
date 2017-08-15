package com.cofrem.transacciones.modules.moduleTransaction.anulacionScreen.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cofrem.transacciones.MainScreenActivity_;
import com.cofrem.transacciones.models.InfoHeaderApp;
import com.cofrem.transacciones.modules.moduleTransaction.anulacionScreen.AnulacionScreenPresenter;
import com.cofrem.transacciones.modules.moduleTransaction.anulacionScreen.AnulacionScreenPresenterImpl;
import com.cofrem.transacciones.R;
import com.cofrem.transacciones.TransactionScreenActivity_;
import com.cofrem.transacciones.lib.KeyBoard;
import com.cofrem.transacciones.lib.MagneticHandler;
import com.cofrem.transacciones.models.Transaccion;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import static android.view.KeyEvent.KEYCODE_ENTER;

@EActivity(R.layout.activity_transaction_anulacion_screen)
public class AnulacionScreenActivity extends Activity implements AnulacionScreenView {

    /**
     * #############################################################################################
     * Declaracion de componentes y variables
     * #############################################################################################
     */

    /**
     * Declaracion de los Contoles
     */

    // Controles del header

    @ViewById
    TextView txvHeaderIdDispositivo;
    @ViewById
    TextView txvHeaderIdPunto;
    @ViewById
    TextView txvHeaderEstablecimiento;
    @ViewById
    TextView txvHeaderPunto;

    // Contents del modulo
    @ViewById
    RelativeLayout bodyContentAnulacionClaveAdministrador;
    @ViewById
    RelativeLayout bodyContentAnulacionNumeroCargo;
    @ViewById
    RelativeLayout bodyContentAnulacionVerificacionValor;
    @ViewById
    RelativeLayout bodyContentAnulacionDesliceTarjeta;
    @ViewById
    RelativeLayout bodyContentAnulacionLecturaIncorrecta;
    @ViewById
    RelativeLayout bodyContentAnulacionClaveUsuario;
    @ViewById
    RelativeLayout bodyContentAnulacionTransaccionExitosa;
    @ViewById
    RelativeLayout bodyContentAnulacionTransaccionErronea;
    @ViewById
    FrameLayout frlPgbHldTransactionAnulacion;


    //Paso transaction_anulacion_paso_clave_administrador
    @ViewById
    Button btnAnulacionTransactionClaveAdministradorBotonCancelar;
    @ViewById
    EditText edtAnulacionTransactionClaveAdministradorContenidoClave;

    //Paso transaction_anulacion_paso_numero_cargo
    @ViewById
    Button btnAnulacionTransactionNumeroCargoBotonCancelar;
    @ViewById
    EditText edtAnulacionTransactionNumeroCargoContenidoValor;

    //Paso transaction_anulacion_paso_verificacion_valor
    @ViewById
    Button btnAnulacionTransactionVerificacionDatosBotonCancelar;

    @ViewById
    TextView txvAnulacionTransactionVerificacionDatosNumeroCargo;

    @ViewById
    TextView txvAnulacionTransactionDesliceTarjetaDatosNumeroCargo;

    //Paso transaction_anulacion_paso_deslice_tarjeta
    @ViewById
    TextView txvAnulacionTransactionVerificacionDatosValorCantidad;
    @ViewById
    TextView txvAnulacionTransactionDesliceTarjetaDatosValorCantidad;

    //Paso content_transaction_anulacion_paso_clave_usuario
    @ViewById
    Button btnAnulacionTransactionClaveUsuarioBotonCancelar;
    @ViewById
    EditText edtAnulacionTransactionClaveUsuarioContenidoClave;

    /**
     * Model que almacena la transaccion actual
     */
    Transaccion modelTransaccion = new Transaccion();


    /**
     * Pasos definidos
     */
    int pasoAnulacionTransaction = 0; // Define el paso actual

    final static int PASO_CLAVE_ADMINISTRADOR = 0;
    final static int PASO_NUMERO_CARGO = 1;
    final static int PASO_VERIFICACION_VALOR = 2;
    final static int PASO_DESLIZAR_TARJETA = 3;
    final static int PASO_CLAVE_USUARIO = 4;
    final static int PASO_TRANSACCION_EXITOSA = 5;

    /**
     * #############################################################################################
     * Instanciamientos de las clases
     * #############################################################################################
     */
    //Instanciamiento de la interface AnulacionScreenPresenter
    private AnulacionScreenPresenter anulacionScreenPresenter;

    /**
     * #############################################################################################
     * Constructor  de  la clase
     * #############################################################################################
     */
    @AfterViews
    void MainInit() {

        /**
         * Instanciamiento e inicializacion del presentador
         */
        anulacionScreenPresenter = new AnulacionScreenPresenterImpl(this);

        /**
         * Llamada al metodo onCreate del presentador para el registro del bus de datos
         */
        anulacionScreenPresenter.onCreate();

        // Metodo para colocar la orientacion de la app
        setOrientation();

        // Metodo que oculta por defecto los include de la vista
        inicializarOcultamientoVistas();

        // Metodo que llena el header de la App
        setInfoHeader();

        //Inicializa el paso del registro de la configuracion
        pasoAnulacionTransaction = PASO_CLAVE_ADMINISTRADOR;

        //Primera ventana visible
        bodyContentAnulacionClaveAdministrador.setVisibility(View.VISIBLE);

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
        anulacionScreenPresenter.onDestroy();
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

                // Ocula el soft keyboard al presionar la tecla enter
                hideKeyBoard();

                switch (pasoAnulacionTransaction) {

                    case PASO_CLAVE_ADMINISTRADOR:
                        //Metodo para registrar el valor del consumo
                        validarPasswordAdministracion();
                        break;

                    case PASO_NUMERO_CARGO:
                        //Metodo para registrar el numero de documento
                        registrarNumeroCargo();
                        break;

                    case PASO_VERIFICACION_VALOR:
                        //Metodo para mostrar la información registrada
                        verificarValor();
                        break;

                    case PASO_DESLIZAR_TARJETA:
                        //Metodo para mostrar la orden de deslizar la tarjeta
                        deslizarTarjeta();
                        break;

                    case PASO_CLAVE_USUARIO:
                        //Metodo para registrar la contraseña del usuario
                        registrarClaveUsuario();
                        break;

                    case PASO_TRANSACCION_EXITOSA:
                        //Metodo para finalizar la transaccion
                        finalizarTransaccion();
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

        switch (pasoAnulacionTransaction) {

            case PASO_CLAVE_ADMINISTRADOR:
                //Vacia la caja de la contraseña de administracion
                edtAnulacionTransactionClaveAdministradorContenidoClave.setText("");
                break;

            case PASO_NUMERO_CARGO:
                //Vacia la caja del numero de cargo
                edtAnulacionTransactionNumeroCargoContenidoValor.setText("");
                break;

            case PASO_VERIFICACION_VALOR:

                break;

            case PASO_DESLIZAR_TARJETA:

                break;

            case PASO_CLAVE_USUARIO:
                //Vacia la caja del valor de la clave de usuario
                edtAnulacionTransactionClaveUsuarioContenidoClave.setText("");
                break;

            case PASO_TRANSACCION_EXITOSA:

                break;

        }
    }

    /**
     * #############################################################################################
     * Metodos sobrecargados de la interface
     * #############################################################################################
     */

    /**
     * Metodo para manejar la verificacion exitosa de la clave de administracion
     */
    public void handleClaveAdministracionValida() {

        //Oculta la barra de progreso
        hideProgress();

        //Oculta la vista de la contraseña de administracion tecnica
        bodyContentAnulacionClaveAdministrador.setVisibility(View.GONE);

        //Muestra la vista del Host de conexion
        bodyContentAnulacionNumeroCargo.setVisibility(View.VISIBLE);

        //Actualiza el paso actual
        pasoAnulacionTransaction++;
    }

    /**
     * Metodo para manejar la verificacion erronea de la clave de administracion
     */
    public void handleClaveAdministracionNoValida() {

        //Oculta la barra de progreso
        hideProgress();

        //Vacia la caja de contraseña de administracion tecnica
        edtAnulacionTransactionClaveAdministradorContenidoClave.setText("");

        //Muestra el mensaje de error en la contraseña de administracion tecnica
        Toast.makeText(this, R.string.configuration_text_clave_no_valido, Toast.LENGTH_SHORT).show();
    }

    /**
     * Metodo para manejar error en la verificacion de la clave de administracion
     */
    public void handleClaveAdministracionError() {
        //Oculta la barra de progreso
        hideProgress();

        //Vacia la caja de contraseña de administracion tecnica
        edtAnulacionTransactionClaveAdministradorContenidoClave.setText("");

        //Muestra el mensaje de error en la configuracion de la contraseña de administracion tecnica
        Toast.makeText(this, R.string.configuration_text_clave_error, Toast.LENGTH_SHORT).show();
    }

    /**
     * Metodo para manejar el valor no valido en la transaccion
     */
    @Override
    public void handleValorTransaccionNoValido() {

    }

    /**
     * Metodo para manejar el valor valido en la transaccion
     */
    @Override
    public void handleValorTransaccionValido(int valorTransaccion) {

        //Se oculta la barra de progreso
        hideProgress();

        Log.i("Activity valor", String.valueOf(valorTransaccion));

        modelTransaccion.setValor(valorTransaccion);

        txvAnulacionTransactionVerificacionDatosNumeroCargo.setText(
                modelTransaccion.getNumero_cargo()
        );

        txvAnulacionTransactionDesliceTarjetaDatosNumeroCargo.setText(
                modelTransaccion.getNumero_cargo()
        );

        txvAnulacionTransactionVerificacionDatosValorCantidad.setText(
                String.valueOf(modelTransaccion.getValor())
        );

        txvAnulacionTransactionDesliceTarjetaDatosValorCantidad.setText(
                String.valueOf(modelTransaccion.getValor())
        );

        //Oculta la vista del numero de cargo
        bodyContentAnulacionNumeroCargo.setVisibility(View.GONE);

        //Muestra la vista de verificacion del valor
        bodyContentAnulacionVerificacionValor.setVisibility(View.VISIBLE);

        //Actualiza el paso actual
        pasoAnulacionTransaction++;

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
        frlPgbHldTransactionAnulacion.setVisibility(View.VISIBLE);
        frlPgbHldTransactionAnulacion.bringToFront();
        frlPgbHldTransactionAnulacion.invalidate();
    }

    /**
     * Metodo para ocultar la barra de progreso
     */
    private void hideProgress() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Oculta la barra de progreso
                frlPgbHldTransactionAnulacion.setVisibility(View.GONE);
            }
        }, 1000);
    }

    /**
     * Metodo que coloca la orientacion de la App de forma predeterminada
     */
    private void setOrientation() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    /**
     * Metodo que oculta por defecto los include de la vista
     */
    private void inicializarOcultamientoVistas() {

        bodyContentAnulacionClaveAdministrador.setVisibility(View.GONE);
        bodyContentAnulacionNumeroCargo.setVisibility(View.GONE);
        bodyContentAnulacionVerificacionValor.setVisibility(View.GONE);
        bodyContentAnulacionDesliceTarjeta.setVisibility(View.GONE);
        bodyContentAnulacionClaveUsuario.setVisibility(View.GONE);
        bodyContentAnulacionTransaccionExitosa.setVisibility(View.GONE);

    }

    /**
     * Metodo que llena el header de la App
     */
    private void setInfoHeader() {

        txvHeaderIdDispositivo.setText(
                String.format(
                        getString(R.string.header_text_id_dispositivo_registrado)
                        , InfoHeaderApp.getInstance().getIdDispositivo()
                )
        );

        txvHeaderIdPunto.setText(
                String.format(
                        getString(R.string.header_text_id_punto_registrado)
                        , InfoHeaderApp.getInstance().getIdPunto()
                )
        );

        txvHeaderEstablecimiento.setText(
                String.format(
                        getString(R.string.header_text_nombre_establecimiento_registrado)
                        , InfoHeaderApp.getInstance().getNombreEstablecimiento()
                )
        );

        txvHeaderPunto.setText(
                String.format(
                        getString(R.string.header_text_nombre_punto_registrado)
                        , InfoHeaderApp.getInstance().getNombrePunto()
                )
        );

    }

    /**
     * Metodo que oculta el teclado al presionar el EditText
     */
    @Click({R.id.edtAnulacionTransactionClaveAdministradorContenidoClave,
            R.id.edtAnulacionTransactionNumeroCargoContenidoValor,
            R.id.edtAnulacionTransactionClaveUsuarioContenidoClave
    })
    public void hideKeyBoard() {

        //TODO:VERIFICAR QUE EL TECLADO SE ESTA OCULTANDO
        //Oculta el teclado
        KeyBoard.hide(this);

    }

    /**
     * Metodo para regresar a la ventana de transaccion
     */
    @Click({R.id.btnAnulacionTransactionClaveAdministradorBotonCancelar,
            R.id.btnAnulacionTransactionNumeroCargoBotonCancelar,
            R.id.btnAnulacionTransactionVerificacionDatosBotonCancelar,
            R.id.btnAnulacionTransactionClaveUsuarioBotonCancelar
    })
    public void navigateToTransactionScreen() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(AnulacionScreenActivity.this, TransactionScreenActivity_.class);
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
    @Click(R.id.btnAnulacionTransactionClaveAdministradorBotonAceptar)
    public void validarPasswordAdministracion() {

        // Se obtiene el texto de la contraseña
        String passwordAdmin = edtAnulacionTransactionClaveAdministradorContenidoClave.getText().toString();

        if (passwordAdmin.length() == 4) {

            //Muestra la barra de progreso
            showProgress();

            anulacionScreenPresenter.validarPasswordAdministrador(this, passwordAdmin);

        } else {

            //Vacia la caja de contraseña
            edtAnulacionTransactionClaveAdministradorContenidoClave.setText("");

            //Muestra el mensaje de error de formato de la contraseña
            Toast.makeText(this, R.string.transaction_error_format_clave_administrador, Toast.LENGTH_SHORT).show();

        }
    }

    /**
     * Metodo para registrar el numero de documento
     */
    @Click(R.id.btnAnulacionTransactionNumeroCargoBotonAceptar)
    public void registrarNumeroCargo() {

        String numeroCargo = edtAnulacionTransactionNumeroCargoContenidoValor.getText().toString();

        if (numeroCargo.length() > 0 && !numeroCargo.equals("0")) {

            //Muestra la barra de progreso
            showProgress();

            //Registra el valor del host en el modelo de la configuracion
            modelTransaccion.setNumero_cargo(numeroCargo);

            anulacionScreenPresenter.obtenerValorTransaccion(this, numeroCargo);

        } else {

        }
    }

    /**
     * Metodo para mostrar la información registrada
     */
    @Click(R.id.btnCreditoTransactionVerificacionDatosBotonAceptar)
    public void verificarValor() {

        //Oculta la vista de verificacion de valor
        bodyContentAnulacionVerificacionValor.setVisibility(View.GONE);

        //Muestra la vista de deslizar la tarjeta
        bodyContentAnulacionDesliceTarjeta.setVisibility(View.VISIBLE);

        //Actualiza el paso actual
        pasoAnulacionTransaction++;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                deslizarTarjeta();
            }
        }, 1000);


    }

    /**
     * Metodo para mostrar la orden de deslizar la tarjeta
     */
    public void deslizarTarjeta() {

        String[] magneticHandler = new MagneticHandler().readMagnetic();
        if (true) {

            String numeroTarjeta = "033502";

            /*if (magneticHandler != null) {

            String numeroTarjeta = magneticHandler[1]
                    .replace(";", "")
                    .replace("!", "")
                    .replace("#", "")
                    .replace("$", "")
                    .replace("&", "")
                    .replace("/", "")
                    .replace("|", "")
                    .replace("(", "")
                    .replace(")", "")
                    .replace("=", "")
                    .replace("?", "")
                    .replace("¿", "")
                    .replace("¿", "")
                    .replace("¡", "")
                    .replace("*", "")
                    .replace("{", "")
                    .replace("}", "")
                    .replace("[", "")
                    .replace("]", "")
                    .replace(",", "")
                    .replace(".", "")
                    .replace("-", "")
                    .replace("_", "")
                    .replace("%", "");

*/

            //Registra el valor del numero de tarjeta en el modelo de la transaccion
            modelTransaccion.setNumero_tarjeta(numeroTarjeta);

            //En caso de la lectura correcta se continua el proceso
            lecturaTarjetaCorrecta();


        } else {
            //En caso de la lectura erronea se muestra la pantalla de error
            lecturaTarjetaErronea();
        }

    }

    /**
     * Metodo para mostrar la lectura correcta de tarjeta
     */
    private void lecturaTarjetaCorrecta() {


        //Oculta la vista de deslizar la tarjeta
        bodyContentAnulacionDesliceTarjeta.setVisibility(View.GONE);

        //Muestra la vista de clave de usuario
        bodyContentAnulacionClaveUsuario.setVisibility(View.VISIBLE);

        //Actualiza el paso actual
        pasoAnulacionTransaction++;
    }

    /**
     * Metodo para mostrar la lectura erronea de tarjeta
     */
    private void lecturaTarjetaErronea() {


        //Oculta la vista de deslizar tarjeta
        bodyContentAnulacionDesliceTarjeta.setVisibility(View.GONE);

        //Muestra la vista de contraseña de usuario
        bodyContentAnulacionLecturaIncorrecta.setVisibility(View.VISIBLE);

    }


    /**
     * Metodo para registrar la contraseña del usuario
     */
    @Click(R.id.btnCreditoTransactionClaveUsuarioBotonAceptar)
    public void registrarClaveUsuario() {

        // Se obtiene el texto de la contraseña
        String passwordUser = edtAnulacionTransactionClaveUsuarioContenidoClave.getText().toString();

        if (passwordUser.length() == 4) {

            //Mostrar la barra de progreso
            showProgress();

            //Registra la transaccion
            anulacionScreenPresenter.registrarTransaccion(this, modelTransaccion);


        } else {

            //Vacia la caja de contraseña
            edtAnulacionTransactionClaveUsuarioContenidoClave.setText("");

            //Muestra el mensaje de error de formato de la contraseña
            Toast.makeText(this, R.string.transaction_error_format_clave_usuario, Toast.LENGTH_SHORT).show();

        }

    }

    /**
     * Metodo para finalizar la transaccion
     */
    public void finalizarTransaccion() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(AnulacionScreenActivity.this, MainScreenActivity_.class);

                //Agregadas banderas para no retorno
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                        | Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intent);
            }
        }, 1000);

    }

}
