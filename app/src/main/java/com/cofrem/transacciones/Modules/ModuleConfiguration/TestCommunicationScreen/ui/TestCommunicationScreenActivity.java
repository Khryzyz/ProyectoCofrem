package com.cofrem.transacciones.Modules.ModuleConfiguration.TestCommunicationScreen.ui;

import android.app.Activity;

import com.cofrem.transacciones.Modules.ModuleConfiguration.TestCommunicationScreen.TestCommunicationScreenPresenter;
import com.cofrem.transacciones.Modules.ModuleConfiguration.TestCommunicationScreen.TestCommunicationScreenPresenterImpl;
import com.cofrem.transacciones.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_main_screen)
public class TestCommunicationScreenActivity extends Activity implements TestCommunicationScreenView {

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

        /**
         * metodo verificar acceso
         */
        //TODO: crear metodos
        testCommunicationScreenPresenter.VerifySuccess();

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
}
