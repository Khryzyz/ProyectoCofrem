package com.cofrem.transacciones.Modules.ModuleConfiguration.ConfigurationPrinter;

import android.content.Context;

import com.cofrem.transacciones.Modules.ModuleConfiguration.ConfigurationPrinter.events.ConfigurationPrinterScreenEvent;

public interface ConfigurationPrinterScreenPresenter {

    /**
     * Metodo para la creacion del presentador
     */
    void onCreate();

    /**
     * Metodo para la destruccion del presentador
     */
    void onDestroy();

    /**
     * Metodo que verifica:
     *  - La existencia de la configuración inicial
     *  - En caso de no existir mostrará la vista de configuración
     *  - En caso de existir validara el acceso
     *
     * @param context
     */
    void validateInitialConfig(Context context);

    /**
     * Metodo que verifica:
     *  - Conexion a internet
     *  - Existencia datos en DB interna
     *  - Coherencia de datos con el servidor
     *
     * @param context
     */
    void validateAccess(Context context);

    /**
     * Metodo para recibir los eventos generados
     *
     * @param configurationPrinterScreenEvent
     */
    void onEventMainThread(ConfigurationPrinterScreenEvent configurationPrinterScreenEvent);

}
