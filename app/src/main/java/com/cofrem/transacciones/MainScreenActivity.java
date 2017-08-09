package com.cofrem.transacciones;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.widget.TextView;


import com.cofrem.transacciones.models.InfoHeaderApp;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main_screen)
public class MainScreenActivity extends Activity {

    /**
     * #############################################################################################
     * Definición de variables
     * #############################################################################################
     */
    @ViewById
    TextView txvHeaderIdDispositivo;
    @ViewById
    TextView txvHeaderIdPunto;
    @ViewById
    TextView txvHeaderEstablecimiento;
    @ViewById
    TextView txvHeaderPunto;

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
        setInfoHeader();
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
