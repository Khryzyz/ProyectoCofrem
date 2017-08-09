package com.cofrem.transacciones.Modules.ModuleConfiguration.ConfigurationPrinter;


import android.content.Context;

import com.cofrem.transacciones.Modules.ModuleConfiguration.ConfigurationPrinter.events.ConfigurationPrinterScreenEvent;
import com.cofrem.transacciones.models.ConfigurationPrinter;

public interface ConfigurationPrinterScreenPresenter {

    //Todo: crear metodos presentador

    /**
     * metodo que se encarga de verificar la existencia de la configuracion de la impresora
     */
    void VerifyConfigurationInitialPrinter(Context context);


    /**
     * metodo que se encarga guardar la configuracion de la impresora
     */
    void saveConfigurationPrinter(Context context, ConfigurationPrinter configuration);

    /**
     * Metodo para la creacion del presentador
     */
    void onCreate();

    /**
     * Metodo para la destruccion del presentador
     */
    void onDestroy();


    /**
     * Metodo para recibir los eventos generados
     *
     * @param splashScreenEvent
     */
    void onEventMainThread(ConfigurationPrinterScreenEvent splashScreenEvent);

}
