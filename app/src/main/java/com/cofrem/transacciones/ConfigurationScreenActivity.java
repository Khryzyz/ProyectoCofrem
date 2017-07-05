package com.cofrem.transacciones;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;

import com.cofrem.transacciones.Modules.ModuleConfiguration.RegisterConfigurationScreen.ui.RegisterConfigurationScreenActivity_;
import com.cofrem.transacciones.Modules.ModuleConfiguration.TestCommunicationScreen.ui.TestCommunicationScreenActivity_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_configuration_screen)
public class ConfigurationScreenActivity extends Activity {

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
    void ConfigurationInit() {

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
    @Click(R.id.btnConfigurationScreenModuleRegisterConfiguration)
    public void navigateToRegisterConfiguration() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(ConfigurationScreenActivity.this, RegisterConfigurationScreenActivity_.class);
                //Agregadas banderas para no retorno
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                        | Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intent);
            }
        }, 1000);

    }

    /**
     * Metodo para navegar a la ventana principal
     */
    @Click(R.id.btnConfigurationScreenModuleTestCommunication)
    public void navigateToTestCommunication() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(ConfigurationScreenActivity.this, TestCommunicationScreenActivity_.class);
                //Agregadas banderas para no retorno
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                        | Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intent);
            }
        }, 1000);

    }

    /**
     * Metodo para navegar a la ventana principal
     */
    @Click(R.id.btnConfigurationScreenBack)
    public void navigateToMainScreen() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(ConfigurationScreenActivity.this, MainScreenActivity_.class);
                //Agregadas banderas para no retorno
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                        | Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intent);
            }
        }, 1000);

    }


}