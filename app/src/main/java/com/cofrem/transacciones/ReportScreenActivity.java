package com.cofrem.transacciones;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.cofrem.transacciones.Modules.ModuleReports.ReimpresionScreenActivity.ui.ReimpresionScreenActivity;
import com.cofrem.transacciones.Modules.ModuleReports.ReimpresionScreenActivity.ui.ReimpresionScreenActivity_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_report_screen)
public class ReportScreenActivity extends Activity {

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
    void ReportInit() {

    }

    /**
     * #############################################################################################
     * Metodos sobrecargados del sistema
     * #############################################################################################
     */

    /**
     * Metodo que interfiere la presion del boton "Back"
     */
    @Override
    public void onBackPressed() {
        String mensajeRegresar = getString(R.string.general_message_press_back) + getString(R.string.general_text_button_regresar);
        Toast.makeText(this, mensajeRegresar, Toast.LENGTH_SHORT).show();
    }

    /**
     * #############################################################################################
     * Metodo propios de la clase
     * #############################################################################################
     */

    /**
     * Metodo para navegar a la ventana de reportes
     */
    @Click(R.id.btnReportScreenModuleReimpresion)
    public void navigateToReportsReimpresion() {
        Intent intent = new Intent(this, ReimpresionScreenActivity_.class);
        startActivity(intent);
    }

    /**
     * Metodo para navegar a la ventana principal
     */
    @Click(R.id.btnReportScreenBack)
    public void navigateToMainScreen() {
        Intent intent = new Intent(this, MainScreenActivity_.class);
        startActivity(intent);
    }

}