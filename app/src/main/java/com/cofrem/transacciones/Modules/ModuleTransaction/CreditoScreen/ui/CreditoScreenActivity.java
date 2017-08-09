package com.cofrem.transacciones.Modules.ModuleTransaction.CreditoScreen.ui;

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
import com.cofrem.transacciones.Modules.ModuleTransaction.CreditoScreen.CreditoScreenPresenter;
import com.cofrem.transacciones.Modules.ModuleTransaction.CreditoScreen.CreditoScreenPresenterImpl;
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

@EActivity(R.layout.activity_transaction_credito_screen)
public class CreditoScreenActivity extends Activity implements CreditoScreenView {

    /**
     * #############################################################################################
     * Declaracion de componentes y variables
     * #############################################################################################
     */

    /**
     * Declaracion de los Contoles
     */

    // Contents del modulo
    // Contents del modulo
    @ViewById
    RelativeLayout bodyContentTransactionValorCompra;
    @ViewById
    RelativeLayout bodyContentTransactionNumeroDocumento;
    @ViewById
    RelativeLayout bodyContentTransactionVerificacionValor;
    @ViewById
    RelativeLayout bodyContentTransactionDesliceTarjeta;
    @ViewById
    RelativeLayout bodyContentTransactionLecturaIncorrecta;
    @ViewById
    RelativeLayout bodyContentTransactionPassUsuario;
    @ViewById
    RelativeLayout bodyContentTransactionTransaccionExitosa;
    @ViewById
    RelativeLayout bodyContentTransactionTransaccionErronea;
    @ViewById
    FrameLayout frlPgbHldTransactionCredito;

    //Paso transaction_credito_paso_valor_compra
    @ViewById
    Button btnCreditoTransactionValorCompraBotonCancelar;
    @ViewById
    EditText edtCreditoTransactionValorCompraValor;

    //Paso transaction_credito_paso_numero_documento
    @ViewById
    Button btnCreditoTransactionNumeroDocumentoBotonCancelar;
    @ViewById
    EditText edtCreditoTransactionNumeroDocumentoValor;

    //Paso transaction_credito_paso_verificacion_valor
    @ViewById
    Button btnCreditoTransactionVerificacionDatosBotonCancelar;
    @ViewById
    TextView txvCreditoTransactionVerificacionDatosValorCantidad;
    @ViewById
    TextView txvCreditoTransactionVerificacionDatosNumeroDocumento;

    //Paso transaction_credito_paso_clave_usuario
    @ViewById
    Button btnCreditoTransactionClaveUsuarioBotonCancelar;
    @ViewById
    EditText edtCreditoTransactionClaveUsuarioContenidoClave;


    /**
     * Model que almacena la transaccion actual
     */
    Transaccion modelTransaccion = new Transaccion();


    /**
     * Pasos definidos
     */
    int pasoCreditoTransaction = 0; // Define el paso actual

    final static int PASO_VALOR_COMPRA = 0;
    final static int PASO_NUMERO_DOCUMENTO = 1;
    final static int PASO_VERIFICACION_VALOR = 2;
    final static int PASO_DESLICE_TARJETA = 3;
    final static int PASO_CLAVE_USUARIO = 4;
    final static int PASO_TRANSACCION_EXITOSA = 5;

    /**
     * #############################################################################################
     * Instanciamientos de las clases
     * #############################################################################################
     */
    //Instanciamiento de la interface SaldoScreenPresenter
    private CreditoScreenPresenter creditoScreenPresenter;


    /**
     * #############################################################################################
     * Constructor  de  la clase
     * #############################################################################################
     */
    @AfterViews
    void MainInit() {

        //Instanciamiento e inicializacion del presentador
        creditoScreenPresenter = new CreditoScreenPresenterImpl(this);

        //Llamada al metodo onCreate del presentador para el registro del bus de datos
        creditoScreenPresenter.onCreate();

        // Metodo para colocar la orientacion de la app
        setOrientation();

        // Metodo que oculta por defecto los include de la vista
        inicializarOcultamientoVistas();

        //Inicializa el paso del registro de la transaccion
        pasoCreditoTransaction = PASO_VALOR_COMPRA;

        //Se registra consumo como el tipo de transaccion
        modelTransaccion.setTipo_transaccion(Transaccion.CODIGO_TIPO_TRANSACCION_CONSUMO);

        //Primera ventana visible
        bodyContentTransactionValorCompra.setVisibility(View.VISIBLE);
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
        creditoScreenPresenter.onDestroy();
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

                    case PASO_VALOR_COMPRA:
                        //Metodo para registrar el valor del consumo
                        registrarValorCompra();
                        break;

                    case PASO_NUMERO_DOCUMENTO:
                        //Metodo para registrar el numero de documento
                        registrarNumeroDocumento();
                        break;

                    case PASO_VERIFICACION_VALOR:
                        //Metodo para mostrar la información registrada
                        verificarValor();
                        break;

                    case PASO_DESLICE_TARJETA:
                        //Metodo para mostrar la orden de deslizar la tarjeta
                        deslizarTarjeta();
                        break;

                    case PASO_CLAVE_USUARIO:
                        //Metodo para registrar la contraseña del usuario
                        registrarClaveUsuario();
                        break;

                    case PASO_TRANSACCION_EXITOSA:

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

            case PASO_VALOR_COMPRA:
                //Vacia la caja del valor del consumo
                edtCreditoTransactionValorCompraValor.setText("");
                break;

            case PASO_NUMERO_DOCUMENTO:
                //Vacia la caja del valor del consumo
                edtCreditoTransactionNumeroDocumentoValor.setText("");
                break;

            case PASO_VERIFICACION_VALOR:

                break;

            case PASO_DESLICE_TARJETA:

                break;

            case PASO_CLAVE_USUARIO:
                //Vacia la caja del valor de la clave de usuario
                edtCreditoTransactionClaveUsuarioContenidoClave.setText("");
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
     * Metodo para manejar la transaccion exitosa
     */
    public void handleTransaccionSuccess() {

        //Oculta la barra de progreso
        hideProgress();

        //Oculta la vista del Host de conexion
        bodyContentTransactionPassUsuario.setVisibility(View.GONE);

        //Muestra la vista del Port de conexion
        bodyContentTransactionTransaccionExitosa.setVisibility(View.VISIBLE);

        //Actualiza el paso actual
        pasoCreditoTransaction++;

    }

    /**
     * Metodo para manejar la conexion del Web Service Erronea
     */
    @Override
    public void handleTransaccionWSConexionError() {

        //Oculta la barra de progreso
        hideProgress();

        //Oculta la vista del Host de conexion
        bodyContentTransactionPassUsuario.setVisibility(View.GONE);

        //Muestra la vista del Port de conexion
        bodyContentTransactionTransaccionExitosa.setVisibility(View.VISIBLE);

        //Actualiza el paso actual
        pasoCreditoTransaction++;

    }

    /**
     * Metodo para manejar la transaccion erronea desde el Web Service
     *
     * @param errorMessage
     */
    @Override
    public void handleTransaccionWSRegisterError(String errorMessage) {

        //Oculta la barra de progreso
        hideProgress();

        //Muestra el mensaje de error del registro de informacion del dispositivo incorrecto
        Toast.makeText(this, R.string.transaction_info_status_error_general, Toast.LENGTH_SHORT).show();

        //Regresa a la vista de transacciones
        navigateToTransactionScreen();
    }

    /**
     * Metodo para manejar la transaccion erronea desde la base de datos
     */
    @Override
    public void handleTransaccionDBRegisterError() {

        //Oculta la barra de progreso
        hideProgress();

        //Oculta la vista del Host de conexion
        bodyContentTransactionPassUsuario.setVisibility(View.GONE);

        //Muestra la vista del Port de conexion
        bodyContentTransactionTransaccionErronea.setVisibility(View.VISIBLE);

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
        frlPgbHldTransactionCredito.setVisibility(View.VISIBLE);
        frlPgbHldTransactionCredito.bringToFront();
        frlPgbHldTransactionCredito.invalidate();
    }

    /**
     * Metodo para ocultar la barra de progreso
     */
    private void hideProgress() {
        //Oculta la barra de progreso
        frlPgbHldTransactionCredito.setVisibility(View.GONE);
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

        bodyContentTransactionValorCompra.setVisibility(View.GONE);
        bodyContentTransactionNumeroDocumento.setVisibility(View.GONE);
        bodyContentTransactionVerificacionValor.setVisibility(View.GONE);
        bodyContentTransactionDesliceTarjeta.setVisibility(View.GONE);
        bodyContentTransactionPassUsuario.setVisibility(View.GONE);
        bodyContentTransactionTransaccionExitosa.setVisibility(View.GONE);

    }

    /**
     * Metodo que oculta el teclado al presionar el EditText
     */
    @Click({R.id.edtCreditoTransactionValorCompraValor,
            R.id.edtCreditoTransactionNumeroDocumentoValor,
            R.id.edtCreditoTransactionClaveUsuarioContenidoClave
    })
    public void hideKeyBoard() {

        //TODO:VERIFICAR QUE EL TECLADO SE ESTA OCULTANDO
        //Oculta el teclado
        KeyBoard.hide(this);

    }

    /**
     * Metodo para regresar a la ventana de transaccion
     */
    @Click({R.id.btnCreditoTransactionValorCompraBotonCancelar,
            R.id.btnCreditoTransactionNumeroDocumentoBotonCancelar,
            R.id.btnCreditoTransactionVerificacionDatosBotonCancelar,
            R.id.btnCreditoTransactionClaveUsuarioBotonCancelar,
            R.id.btnCreditoTransactionExitosaBotonSalir,
            R.id.btnCreditoTransactionErrorBotonSalir
    })
    public void navigateToTransactionScreen() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(CreditoScreenActivity.this, TransactionScreenActivity_.class);
                //Agregadas banderas para no retorno
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                        | Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intent);
            }
        }, 1000);

    }

    /**
     * Metodo para registrar el valor del consumo
     */
    @Click(R.id.btnCreditoTransactionValorCompraBotonAceptar)
    public void registrarValorCompra() {

        // Se obtiene el texto de la contraseña
        String valorCompra = edtCreditoTransactionValorCompraValor.getText().toString();

        if (valorCompra.length() > 0 && Integer.parseInt(valorCompra) >= 1 && Integer.parseInt(valorCompra) <= 3000000) {

            //Registra el valor de compra en el modelo de la transaccion
            modelTransaccion.setValor(Integer.parseInt(valorCompra));

            //Oculta la vista del Valor de compra
            bodyContentTransactionValorCompra.setVisibility(View.GONE);

            //Muestra la vista del Numero de documento
            bodyContentTransactionNumeroDocumento.setVisibility(View.VISIBLE);

            //Actualiza el paso actual
            pasoCreditoTransaction++;

        } else if (valorCompra.length() == 0) {

            //Vacia la caja de contraseña
            edtCreditoTransactionValorCompraValor.setText("");

            //Muestra el mensaje de error de formato de la contraseña
            Toast.makeText(this, R.string.transaction_error_valor, Toast.LENGTH_SHORT).show();

        } else if (Integer.parseInt(valorCompra) < 1) {

            //Vacia la caja de contraseña
            edtCreditoTransactionValorCompraValor.setText("");

            //Muestra el mensaje de error de formato de la contraseña
            Toast.makeText(this, R.string.transaction_error_valor_monto_minimo, Toast.LENGTH_SHORT).show();

        } else if (Integer.parseInt(valorCompra) > 3000000) {

            //Vacia la caja de contraseña
            edtCreditoTransactionValorCompraValor.setText("");

            //Muestra el mensaje de error de formato de la contraseña
            Toast.makeText(this, R.string.transaction_error_valor_monto_maximo, Toast.LENGTH_SHORT).show();

        }

    }

    /**
     * Metodo para registrar el numero de documento
     */
    @Click(R.id.btnCreditoTransactionNumeroDocumentoBotonAceptar)
    public void registrarNumeroDocumento() {


        // Se obtiene el texto de la contraseña
        String numeroDocumento = edtCreditoTransactionNumeroDocumentoValor.getText().toString();

        if (numeroDocumento.length() > 0) {

            //Registra el valor del numero de documento en el modelo de transaccion
            modelTransaccion.setNumero_documento(numeroDocumento);

            txvCreditoTransactionVerificacionDatosValorCantidad.setText(
                    String.valueOf(modelTransaccion.getValor())
            );

            txvCreditoTransactionVerificacionDatosNumeroDocumento.setText(
                    String.valueOf(modelTransaccion.getNumero_documento())
            );

            //Oculta la vista del numero de documento
            bodyContentTransactionNumeroDocumento.setVisibility(View.GONE);

            //Muestra la vista de verificacion del valor
            bodyContentTransactionVerificacionValor.setVisibility(View.VISIBLE);

            //Actualiza el paso actual
            pasoCreditoTransaction++;

        } else {

            //Vacia la caja de contraseña
            edtCreditoTransactionValorCompraValor.setText("");

            //Muestra el mensaje de error de formato de la contraseña
            Toast.makeText(this, R.string.transaction_error_numero_documento, Toast.LENGTH_SHORT).show();

        }

    }

    /**
     * Metodo para mostrar la información registrada
     */
    @Click(R.id.btnCreditoTransactionVerificacionDatosBotonAceptar)
    public void verificarValor() {

        //Oculta la vista de verificacion de valor
        bodyContentTransactionVerificacionValor.setVisibility(View.GONE);

        //Muestra la vista de deslizar tarjeta
        bodyContentTransactionDesliceTarjeta.setVisibility(View.VISIBLE);

        //Actualiza el paso actual
        pasoCreditoTransaction++;

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

        if (magneticHandler[1] != null) {


            String clave = magneticHandler[1]
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

            //Registra el valor del numero de tarjeta en el modelo de la transaccion
            modelTransaccion.setNumero_tarjeta(clave);

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
    public void lecturaTarjetaCorrecta() {

        //Oculta la vista de deslizar tarjeta
        bodyContentTransactionDesliceTarjeta.setVisibility(View.GONE);

        //Muestra la vista de contraseña de usuario
        bodyContentTransactionPassUsuario.setVisibility(View.VISIBLE);

        //Actualiza el paso actual
        pasoCreditoTransaction++;
    }

    /**
     * Metodo para mostrar la lectura erronea de tarjeta
     */
    public void lecturaTarjetaErronea() {


        //Oculta la vista de deslizar tarjeta
        bodyContentTransactionDesliceTarjeta.setVisibility(View.GONE);

        //Muestra la vista de contraseña de usuario
        bodyContentTransactionLecturaIncorrecta.setVisibility(View.VISIBLE);

    }

    /**
     * Metodo para registrar la contraseña del usuario
     */
    @Click(R.id.btnCreditoTransactionClaveUsuarioBotonAceptar)
    public void registrarClaveUsuario() {

        // Se obtiene el texto de la contraseña
        String passwordUser = edtCreditoTransactionClaveUsuarioContenidoClave.getText().toString();

        if (passwordUser.length() == 4) {

            //Mostrar la barra de progreso
            showProgress();

            //Se registra la contraseña en el modelo
            modelTransaccion.setClave(Integer.valueOf(passwordUser));

            //TODO: agregar las diferentes encriptaciones
            //Se registra el tipo de encriptacion en el modelo
            modelTransaccion.setTipo_encriptacion(Transaccion.CODIGO_ENCR_NO_ENCRIPTADO);

            //TODO: agregar los diferentes tipos de productos
            //Se registra el tipo de producto en el modelo
            modelTransaccion.setTipo_servicio(Transaccion.CODIGO_PRODUCTO_CUPO_ROTATIVO);

            //Actualiza el paso actual
            pasoCreditoTransaction++;

            //Registra la transaccion
            creditoScreenPresenter.registrarTransaccion(this, modelTransaccion);


        } else {

            //Vacia la caja de contraseña
            edtCreditoTransactionClaveUsuarioContenidoClave.setText("");

            //Muestra el mensaje de error de formato de la contraseña
            Toast.makeText(this, R.string.transaction_error_format_clave_usuario, Toast.LENGTH_SHORT).show();

        }

    }

}
