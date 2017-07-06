package com.cofrem.transacciones;

import android.app.Activity;
import android.content.Intent;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_main_screen)
public class MainScreenActivity extends Activity {

    /**
     * #############################################################################################
     * Definición de variables
     * #############################################################################################
     */


    /**
     * #############################################################################################
     * Instanciamientos de las clases
     * #############################################################################################
     */

    /**
     * #############################################################################################
     * Constructor  de  la clase
     * #############################################################################################
     */
    @AfterViews
    void MainInit() {

    }

    /**
     * Metodo para navegar a la ventana de transacciones
     */
    @Click(R.id.btnMainScreenModuleTransaction)
    public void navigateTransactionView() {
        Intent intent = new Intent(MainScreenActivity.this, TransactionScreenActivity_.class);

        startActivity(intent);
    }

    /**
     * Metodo para navegar a la ventana de reportes
     */
    @Click(R.id.btnMainScreenModuleReport)
    public void navigateToReportsView() {
        Intent intent = new Intent(MainScreenActivity.this, ReportScreenActivity_.class);

        startActivity(intent);
    }

    /**
     * Metodo para navegar a la ventana de configuraciones
     */
    @Click(R.id.btnMainScreenModuleConfiguration)
    public void navigateToConfigurationView() {
        Intent intent = new Intent(MainScreenActivity.this, ConfigurationScreenActivity_.class);

        startActivity(intent);
    }
}
