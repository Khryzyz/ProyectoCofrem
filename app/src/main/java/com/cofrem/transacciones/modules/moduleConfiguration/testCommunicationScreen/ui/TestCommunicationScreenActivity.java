package com.cofrem.transacciones.modules.moduleConfiguration.testCommunicationScreen.ui;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.cofrem.transacciones.ConfigurationScreenActivity_;
import com.cofrem.transacciones.global.InfoGlobalSettingsBlockButtons;
import com.cofrem.transacciones.models.InfoHeaderApp;
import com.cofrem.transacciones.modules.moduleConfiguration.configurationPrinter.ui.ConfigurationPrinterScreenActivity;
import com.cofrem.transacciones.modules.moduleConfiguration.testCommunicationScreen.TestCommunicationScreenPresenter;
import com.cofrem.transacciones.modules.moduleConfiguration.testCommunicationScreen.TestCommunicationScreenPresenterImpl;
import com.cofrem.transacciones.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_configuration_test_screen)
public class TestCommunicationScreenActivity extends Activity implements TestCommunicationScreenView {

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
    @ViewById
    TextView txvConfiguracionConexionPruebaNumCargo;
    @ViewById
    TextView txvConfiguracionConexionPruebaResultado;
    @ViewById
    FrameLayout frlPgbHldTestComunication;

    /**
     * #############################################################################################
     * Instanciamientos de las clases
     * #############################################################################################
     */
    //Instanciamiento de la interface SaldoScreenPresenter
    private TestCommunicationScreenPresenter testCommunicationScreenPresenter;


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
        testCommunicationScreenPresenter = new TestCommunicationScreenPresenterImpl(this);

        /**
         * Llamada al metodo onCreate del presentador para el registro del bus de datos
         */
        testCommunicationScreenPresenter.onCreate();

        // Metodo que oculta por defecto los include de la vista
        inicializarOcultamientoVistas();

        // Metodo para colocar la orientacion de la app
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Coloca la informacion del encabezado
        setInfoHeader();

        testCommunicationScreenPresenter.testComunication(this);

        showProgress();
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
        testCommunicationScreenPresenter.onDestroy();
        super.onDestroy();
    }

    /**
     * Metodo que interfiere en la presion del boton Task
     */
    @Override
    protected void onPause() {
        super.onPause();

        ActivityManager activityManager = (ActivityManager) getApplicationContext()
                .getSystemService(Context.ACTIVITY_SERVICE);

        activityManager.moveTaskToFront(getTaskId(), 0);
    }

    /**
     * Metodo sobrecargado de la vista para la presion de las teclas de volumen
     *
     * @param event evento de la presion de una tecla
     * @return regresa el rechazo de la presion
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (InfoGlobalSettingsBlockButtons.blockedKeys.contains(event.getKeyCode())) {
            return true;
        } else {
            return super.dispatchKeyEvent(event);
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

    @Override
    public void handleTestComunicationSuccess() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                txvConfiguracionConexionPruebaResultado.setText(R.string.configuration_text_exitosa);
                hideProgress();
            }
        }, 2000);

    }

    @Override
    public void handleTestComunicationError(final String error) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                txvConfiguracionConexionPruebaResultado.setText(error);
                hideProgress();
            }
        }, 500);
    }

    @Override
    public void handleTransaccionWSConexionError() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                txvConfiguracionConexionPruebaResultado.setText(R.string.configuration_text_informacion_dispositivo_error_conexion);
                hideProgress();
            }
        }, 500);
    }

    /**
     * Metodo para navegar a la ventana de transacciones
     */
    @Override
    public void navigateTransactionView() {

    }

    /**
     * Metodo para navegar a la ventana de reportes
     */
    @Override
    public void navigateToReportsView() {

    }

    /**
     * Metodo para navegar a la ventana de configuraciones
     */
    @Override
    public void navigateToConfigurationView() {

    }

    @Click(R.id.btnConfiguracionConexionPruebaBotonSalir)
    void cancelConfigurationPrinter() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(TestCommunicationScreenActivity.this, ConfigurationScreenActivity_.class);
                //Agregadas banderas para no retorno
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                        | Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intent);
            }
        }, 500);
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
        frlPgbHldTestComunication.setVisibility(View.VISIBLE);
        frlPgbHldTestComunication.bringToFront();
        frlPgbHldTestComunication.invalidate();
    }

    /**
     * Metodo para ocultar la barra de progreso
     */
    private void hideProgress() {
        frlPgbHldTestComunication.setVisibility(View.GONE);
    }

    /**
     * Metodo que oculta por defecto los include de la vista
     */
    private void inicializarOcultamientoVistas() {


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
}
