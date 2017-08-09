package com.cofrem.transacciones.modules.moduleTransaction.anulacionScreen.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.cofrem.transacciones.MainScreenActivity_;
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

    // Contents del modulo
    @ViewById
    RelativeLayout bodyContentClaveAdministrador;
    @ViewById
    RelativeLayout bodyContentNumeroCargo;
    @ViewById
    RelativeLayout bodyContentVerificacionValor;
    @ViewById
    RelativeLayout bodyContentDeslizarTarjeta;
    @ViewById
    RelativeLayout bodyContentClaveUsuario;
    @ViewById
    RelativeLayout bodyContentTransaccionExitosa;
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
    int pasoCreditoTransaction = 0; // Define el paso actual

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

        /**
         * Metodo que oculta por defecto los include de la vista
         */
        inicializarOcultamientoVistas();

        //Inicializa el paso del registro de la configuracion
        pasoCreditoTransaction = PASO_CLAVE_ADMINISTRADOR;

        //Primera ventana visible
        bodyContentClaveAdministrador.setVisibility(View.VISIBLE);

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

                switch (pasoCreditoTransaction) {

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

        switch (pasoCreditoTransaction) {

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
     * Metodo para manejar la verificacion exitosa
     */
    public void handleVerifySuccess() {
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
        //Oculta la barra de progreso
        frlPgbHldTransactionAnulacion.setVisibility(View.GONE);
    }

    /**
     * Metodo que oculta por defecto los include de la vista
     */
    private void inicializarOcultamientoVistas() {

        bodyContentClaveAdministrador.setVisibility(View.GONE);
        bodyContentNumeroCargo.setVisibility(View.GONE);
        bodyContentVerificacionValor.setVisibility(View.GONE);
        bodyContentDeslizarTarjeta.setVisibility(View.GONE);
        bodyContentClaveUsuario.setVisibility(View.GONE);
        bodyContentTransaccionExitosa.setVisibility(View.GONE);

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

        //Registra el valor del host en el modelo de la configuracion
        modelTransaccion.setNumero_cargo(Integer.parseInt(edtAnulacionTransactionNumeroCargoContenidoValor.getText().toString()));

        //Oculta la vista del numero de cargo
        bodyContentNumeroCargo.setVisibility(View.GONE);

        //Muestra la vista de verificacion del valor
        bodyContentVerificacionValor.setVisibility(View.VISIBLE);

        //Actualiza el paso actual
        pasoCreditoTransaction++;
    }

    /**
     * Metodo para mostrar la información registrada
     */
    @Click(R.id.btnCreditoTransactionVerificacionDatosBotonAceptar)
    public void verificarValor() {

        //Oculta la vista de verificacion de valor
        bodyContentVerificacionValor.setVisibility(View.GONE);

        //Muestra la vista de deslizar la tarjeta
        bodyContentDeslizarTarjeta.setVisibility(View.VISIBLE);

        //Actualiza el paso actual
        pasoCreditoTransaction++;

    }

    /**
     * Metodo para mostrar la orden de deslizar la tarjeta
     */
    public void deslizarTarjeta() {

        String[] magneticHandler = new MagneticHandler().readMagnetic();

        //Registra el valor del host en el modelo de la configuracion
        modelTransaccion.setNumero_tarjeta(magneticHandler[1]);

        //Oculta la vista de deslizar la tarjeta
        bodyContentDeslizarTarjeta.setVisibility(View.GONE);

        //Muestra la vista de clave de usuario
        bodyContentClaveUsuario.setVisibility(View.VISIBLE);

        //Actualiza el paso actual
        pasoCreditoTransaction++;

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
