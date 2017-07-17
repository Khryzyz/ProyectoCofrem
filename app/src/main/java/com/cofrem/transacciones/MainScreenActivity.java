package com.cofrem.transacciones;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;


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

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(MainScreenActivity.this, TransactionScreenActivity_.class);
                //Agregadas banderas para no retorno
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                        | Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intent);
            }
        }, 1000);
    }

    /**
     * Metodo para navegar a la ventana de reportes
     */
    @Click(R.id.btnMainScreenModuleReport)
    public void navigateToReportsView() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(MainScreenActivity.this, ReportScreenActivity_.class);
                //Agregadas banderas para no retorno
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                        | Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intent);
            }
        }, 1000);

    }

    /**
     * Metodo para navegar a la ventana de configuraciones
     */
    @Click(R.id.btnMainScreenModuleConfiguration)
    public void navigateToConfigurationView() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(MainScreenActivity.this, ConfigurationScreenActivity_.class);
                //Agregadas banderas para no retorno
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                        | Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intent);
            }
        }, 1000);

    }
}
