package com.cofrem.transacciones;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.cofrem.transacciones.Modules.ModuleTransaction.AnulacionScreenActivity.ui.AnulacionScreenActivity_;
import com.cofrem.transacciones.Modules.ModuleTransaction.CreditoScreenActivity.ui.CreditoScreenActivity_;
import com.cofrem.transacciones.Modules.ModuleTransaction.SaldoScreenActivity.ui.SaldoScreenActivity_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_transaction_screen)
public class TransactionScreenActivity extends Activity {

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
    void TransactionInit() {

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
     * Metodo para navegar a la ventana principal
     */
    @Click(R.id.btnTransactionScreenBack)
    public void navigateToMainScreen() {
        Intent intent = new Intent(this, MainScreenActivity_.class);
        startActivity(intent);
    }

    /**
     * Metodo para navegar a la ventana Credito
     */
    @Click(R.id.btnTransactionScreenModuleCredito)
    public void navigateToCreditoScreen() {
        Intent intent = new Intent(this, CreditoScreenActivity_.class);
        startActivity(intent);
    }
    /**
     * Metodo para navegar a la ventana Saldo
     */
    @Click(R.id.btnTransactionScreenModuleSaldo)
    public void navigateToSaldoScreen() {
        Intent intent = new Intent(this, SaldoScreenActivity_.class);
        startActivity(intent);
    }
    /**
     * Metodo para navegar a la ventana Anulacion
     */
    @Click(R.id.btnTransactionScreenModuleAnulacion)
    public void navigateToAnulacionScreen() {
        Intent intent = new Intent(this, AnulacionScreenActivity_.class);
        startActivity(intent);
    }

}