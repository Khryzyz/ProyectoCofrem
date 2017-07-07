package com.cofrem.transacciones.SplashScreen.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cofrem.transacciones.MainScreenActivity_;
import com.cofrem.transacciones.Modules.ModuleConfiguration.RegisterConfigurationScreen.ui.RegisterConfigurationScreenActivity_;
import com.cofrem.transacciones.R;
import com.cofrem.transacciones.SplashScreen.SplashScreenPresenter;
import com.cofrem.transacciones.SplashScreen.SplashScreenPresenterImpl;
import com.cofrem.transacciones.models.Configurations;
import com.cofrem.transacciones.models.Reports;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_splash_screen)
public class SplashScreenActivity extends Activity implements SplashScreenView {

    /**
     * #############################################################################################
     * Declaracion de componentes y variables
     * #############################################################################################
     */
    //Declaracion de los Contoles
    @ViewById
    TextView txvSplashScreenInfo;
    @ViewById
    ProgressBar pgbLoadingSplashScreen;

    /**
     * #############################################################################################
     * Instanciamientos de las clases
     * #############################################################################################
     */
    //Instanciamiento de la interface SplashScreenPresenter
    private SplashScreenPresenter splashScreenPresenter;

    /**
     * #############################################################################################
     * Constructor  de  la clase
     * #############################################################################################
     */
    @AfterViews
    void SplashInit() {

        /**
         * Instanciamiento e inicializacion del presentador
         */
        splashScreenPresenter = new SplashScreenPresenterImpl(this);

        /**
         * Llamada al metodo onCreate del presentador para el registro del bus de datos
         */
        splashScreenPresenter.onCreate();

        // Metodo para colocar la orientacion de la app
        setOrientation();

        // Metodo para validar la configuracion inicial
        validateConfig();

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
        splashScreenPresenter.onDestroy();
        super.onDestroy();
    }

    /**
     * #############################################################################################
     * Metodos sobrecargados de la interface
     * #############################################################################################
     */

    /**
     * Metodo para manejar la existencia de la configuracion inicial
     */
    @Override
    public void handleVerifyInitialConfigExiste() {

        //Agrega el texto de error del manejador de configuracion inicial encontrada
        txvSplashScreenInfo.setText(txvSplashScreenInfo.getText() +
                "\n " + getString(R.string.general_message_verify_configuration_initial_existe)
        );

        /**
         * Llamada al metodo validateAccesAdmin del presentador que valida:
         *  - Conexion a internet
         *  - Existencia datos en DB interna
         *  - Coherencia de datos con el servidor
         */
        splashScreenPresenter.validateAccess(this);
    }

    /**
     * Metodo para manejar la NO existencia de la configuracion inicial
     */
    @Override
    public void handleVerifyInitialConfigNoExiste() {

        //Agrega el texto de error del manejador de configuracion inicial no encontrada
        txvSplashScreenInfo.setText(txvSplashScreenInfo.getText() +
                "\n " + getString(R.string.general_message_verify_configuration_initial_no_existe)
        );

        //Navegando a la ventana de configuración
        navigateToConfigurationScreen();
    }

    /**
     * Metodo para manejar la existencia de la configuracion inicial NO valida
     */
    @Override
    public void handleVerifyInitialConfigNoValida() {

        //Oculta la barra de progreso
        hideProgress();

        //Agrega el texto de error del manejador de verificacion inicial no valida
        txvSplashScreenInfo.setText(txvSplashScreenInfo.getText() +
                "\n " + getString(R.string.general_message_verify_configuration_initial_no_valida)
        );

    }

    /**
     * Metodo para manejar el error al registrar el valor de acceso
     */
    @Override
    public void handleInsertRegistroValorAccesoError() {

        //Oculta la barra de progreso
        hideProgress();

        //Agrega el texto de error del manejador de verificacion inicial no valida
        txvSplashScreenInfo.setText(txvSplashScreenInfo.getText() +
                "\n " + getString(R.string.general_message_verify_register_valor_acceso)
        );
    }

    /**
     * Metodo para manejar la verificacion exitosa
     */
    @Override
    public void handleVerifySuccess() {
        txvSplashScreenInfo.setText(txvSplashScreenInfo.getText() +
                "\n" +
                getString(R.string.general_message_verify_success)
        );

        navigateToMainScreen();

    }

    /**
     * Metodo para manejar la verificacion erronea
     */
    @Override
    public void handleVerifyError() {
        txvSplashScreenInfo.setText(txvSplashScreenInfo.getText() +
                "\n" +
                getString(R.string.general_message_verify_error)
        );

        hideProgress();
    }

    /**
     * Metodo para manejar la conexion a internet exitosa
     */
    @Override
    public void handleInternetConnectionSuccess() {

        //Agrega el texto de informacion del manejador de conexion a internet existente
        txvSplashScreenInfo.setText(txvSplashScreenInfo.getText() +
                "\n" +
                getString(R.string.general_message_internet_info)
        );
    }

    /**
     * Metodo para manejar la conexion a internet erronea
     */
    @Override
    public void handleInternetConnectionError() {

        //Oculta la barra de progreso
        hideProgress();

        //Agrega el texto de error del manejador de conexion a internet no existente
        txvSplashScreenInfo.setText(txvSplashScreenInfo.getText() +
                "\n" +
                getString(R.string.general_message_internet_error)
        );
    }

    /**
     * Metodo para manejar la conexion al dispositivo lector de banda magnetica exitosa
     */
    @Override
    public void handleMagneticReaderDeviceSuccess() {
        txvSplashScreenInfo.setText(txvSplashScreenInfo.getText() +
                "\n" +
                getString(R.string.general_message_magnetic_device_info)
        );
    }

    /**
     * Metodo para manejar la conexion al dispositivo lector de banda magnetica erronea
     */
    @Override
    public void handleMagneticReaderDeviceError() {
        txvSplashScreenInfo.setText(txvSplashScreenInfo.getText() +
                "\n" +
                getString(R.string.general_message_magnetic_device_error)
        );
    }

    /**
     * Metodo para manejar la conexion al dispositivo NFC exitosa
     */
    @Override
    public void handleNFCDeviceSuccess() {
        txvSplashScreenInfo.setText(txvSplashScreenInfo.getText() +
                "\n" +
                getString(R.string.general_message_nfc_device_info)
        );
    }

    /**
     * Metodo para manejar la conexion al dispositivo NFC erronea
     */
    @Override
    public void handleNFCDeviceError() {
        txvSplashScreenInfo.setText(txvSplashScreenInfo.getText() +
                "\n" +
                getString(R.string.general_message_nfc_device_error)
        );
    }

    /**
     * Metodo para manejar la conexion al dispositivo de impresion exitosa
     */
    @Override
    public void handlePrinterDeviceSuccess() {
        txvSplashScreenInfo.setText(txvSplashScreenInfo.getText() +
                "\n" +
                getString(R.string.general_message_printer_device_info)
        );
    }

    /**
     * Metodo para manejar la conexion al dispositivo de impresion erronea
     */
    @Override
    public void handlePrinterDeviceError() {
        txvSplashScreenInfo.setText(txvSplashScreenInfo.getText() +
                "\n" +
                getString(R.string.general_message_printer_device_error)
        );
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
        // Muesra la barra  de progreso
        pgbLoadingSplashScreen.setVisibility(View.VISIBLE);
    }

    /**
     * Metodo para ocultar la barra de progreso
     */
    private void hideProgress() {
        //Oculta la barra de progreso
        pgbLoadingSplashScreen.setVisibility(View.GONE);
    }

    /**
     * Metodo que coloca la orientacion de la App de forma predeterminada
     */
    private void setOrientation() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    /**
     * Metodo para validar la configuración inicial
     */
    private void validateConfig() {

        // Muestra la barra de progreso
        showProgress();

        /**
         * Llamada al metodo validateInitialConfig del presentador que valida:
         *  - La existencia de la configuración inicial
         *  - En caso de no existir mostrará la vista de configuración
         *  - En caso de existir validara el acceso
         */
        splashScreenPresenter.validateInitialConfig(this);

    }

    /**
     * Metodo para navegar a la ventana inicial
     */
    private void navigateToMainScreen() {

        txvSplashScreenInfo.setText(txvSplashScreenInfo.getText() +
                "\n Aqui se muestra la pantalla principal"
        );

        hideProgress();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, MainScreenActivity_.class);
                //Agregadas  ventanas para no retorno
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                        | Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intent);
            }
        }, 3000);
    }

    /**
     * Metodo para navegar a la ventana de registro de configuracion
     */
    private void navigateToConfigurationScreen() {

        //Oculta la barra de progreso
        hideProgress();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //Inicializacion del Bundle de argumentos
                Bundle args = new Bundle();

                args.putInt(Configurations.keyConfiguration, Configurations.configuracionRegistrarConfigInicial);

                Intent intent = new Intent(SplashScreenActivity.this, RegisterConfigurationScreenActivity_.class);
                //Agregadas banderas para no retorno
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                        | Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                intent.putExtras(args);

                startActivity(intent);
            }
        }, 1000);
    }

}
