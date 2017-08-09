package com.cofrem.transacciones.modules.moduleConfiguration.configurationPrinter.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cofrem.transacciones.ConfigurationScreenActivity_;
import com.cofrem.transacciones.models.InfoHeaderApp;
import com.cofrem.transacciones.modules.moduleConfiguration.configurationPrinter.ConfigurationPrinterScreenPresenter;
import com.cofrem.transacciones.modules.moduleConfiguration.configurationPrinter.ConfigurationPrinterScreenPresenterImpl;
import com.cofrem.transacciones.R;
import com.cofrem.transacciones.models.ConfigurationPrinter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.SeekBarProgressChange;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_configuration_printer_screen)
public class ConfigurationPrinterScreenActivity extends Activity implements ConfigurationPrinterScreenView {


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
    SeekBar seekBarGrayLevel;
    @ViewById
    SeekBar seekBarFontSize;
    @ViewById
    TextView txvConfigurationPrinterValorGrayLevel;
    @ViewById
    TextView txvConfigurationPrinterValorFontSize;
    @ViewById
    Button btnConfigurationPrinterBotonAceptar;
    @ViewById
    Button btnConfigurationPrinterBotonCancelar;

    /**
     * #############################################################################################
     * Instanciamientos de las clases
     * #############################################################################################
     */
    //Instanciamiento de la interface SaldoScreenPresenter
    private ConfigurationPrinterScreenPresenter configurationPrinterScreenPresenter;

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
        configurationPrinterScreenPresenter = new ConfigurationPrinterScreenPresenterImpl(this);

        /**
         * Llamada al metodo onCreate del presentador para el registro del bus de datos
         */
        configurationPrinterScreenPresenter.onCreate();

        /**
         * metodo verificar acceso
         */
        //TODO: crear metodos
        configurationPrinterScreenPresenter.VerifyConfigurationInitialPrinter(this);

        // Metodo para colocar la orientacion de la app
        setOrientation();

        // Metodo que llena el header de la App
        setInfoHeader();


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
        configurationPrinterScreenPresenter.onDestroy();
        super.onDestroy();
    }

    /**
     * #############################################################################################
     * Metodos sobrecargados de la interface
     * #############################################################################################
     */


    @SeekBarProgressChange(R.id.seekBarGrayLevel)
    void onProgressChangeOnSeekBarGrayLevel(SeekBar seekBar, int progress) {
        txvConfigurationPrinterValorGrayLevel.setText(this.getString(R.string.configuration_text_gray_level, progress + 1));
    }

    @SeekBarProgressChange(R.id.seekBarFontSize)
    void onProgressChangeOnSeekBarFontSize(SeekBar seekBar, int progress) {
        txvConfigurationPrinterValorFontSize.setText(this.getString(R.string.configuration_text_font_size, progress + 1));
    }


    /**
     * Metodo para manejar la verificacion de la configuracion inicial exitosa de la impresora
     */
    @Override
    public void handleVerifyConfigurationInitialPrinterSuccess(ConfigurationPrinter configuration) {
        seekBarFontSize.setProgress(configuration.getFont_size() - 1);
        seekBarGrayLevel.setProgress(configuration.getGray_level() - 1);
        txvConfigurationPrinterValorGrayLevel.setText(this.getString(R.string.configuration_text_gray_level, seekBarGrayLevel.getProgress() + 1));
        txvConfigurationPrinterValorFontSize.setText(this.getString(R.string.configuration_text_font_size, seekBarFontSize.getProgress() + 1));

    }

    @Override
    public void handleVerifyConfigurationInitialPrinterError() {

    }

    @Override
    public void handleSaveConfigurationPrinterSuccess() {
        Toast.makeText(this, this.getString(R.string.configuration_text_registro_printer_success), Toast.LENGTH_LONG).show();
        cancelConfigurationPrinter();
    }

    @Override
    public void handleSaveConfigurationPrinterError() {
        Toast.makeText(this, this.getString(R.string.configuration_text_registro_printer_error), Toast.LENGTH_LONG).show();
        cancelConfigurationPrinter();
    }

    @Click(R.id.btnConfigurationPrinterBotonAceptar)
    void saveConfigurationPrinter() {
        ConfigurationPrinter configuration = new ConfigurationPrinter();
        configuration.setFont_size(seekBarFontSize.getProgress() + 1);
        configuration.setGray_level(seekBarGrayLevel.getProgress() + 1);

        configurationPrinterScreenPresenter.saveConfigurationPrinter(this, configuration);
    }

    @Click(R.id.btnConfigurationPrinterBotonCancelar)
    void cancelConfigurationPrinter() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(ConfigurationPrinterScreenActivity.this, ConfigurationScreenActivity_.class);
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
     * Metodo que coloca la orientacion de la App de forma predeterminada
     */
    private void setOrientation() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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
