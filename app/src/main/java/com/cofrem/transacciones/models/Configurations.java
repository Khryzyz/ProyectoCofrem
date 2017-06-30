package com.cofrem.transacciones.models;

public class Configurations {

    /**
     * Llave del Bundle para la selección de configuracion
     */
    public final static String keyConfiguration = "configuracion";

    /**
     * Configuraciones disponibles
     */
    public final static int configuracionRegistrarConfigInicial = 1;

    public final static int configuracionRegistrar = 2;

    public final static int configuracionProbar = 3;

    /**
     * Modelo para el registro de la configuracion del dispositivo
     */
    private String host;
    private int port;
    private String local;
    private String establecimiento;

    //Constructor vacio
    public Configurations() {
    }

    //Constructo con parametros
    public Configurations(String host, int port, String local, String establecimiento) {
        this.host = host;
        this.port = port;
        this.local = local;
        this.establecimiento = establecimiento;
    }

    //Getters y Setters de la clase
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(String establecimiento) {
        this.establecimiento = establecimiento;
    }
}