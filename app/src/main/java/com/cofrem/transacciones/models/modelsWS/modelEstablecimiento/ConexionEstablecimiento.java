package com.cofrem.transacciones.models.modelsWS.modelEstablecimiento;

import org.ksoap2.serialization.SoapObject;

public class ConexionEstablecimiento {


    //Modelo usado en la respuesta del WS para la respuesta terminalResult

    //Modelado rapido de llaves del terminalResulta
    public static final String CODIGO_TERMINAL_KEY = "codigoTerminal";
    public static final String UUID_KEY = "uuid";
    public static final String NOMBRE_TERMINAL_KEY = "nombreTerminal";
    public static final String IP_PRINCIPAL_KEY = "ip1";
    public static final String IP_SECUNDARIA_KEY = "ip2";
    public static final String PUERTO_KEY = "puerto";
    public static final String CLAVE_TECNICO_KEY = "claveTecnico";
    public static final String CLAVE_COMERCIO_KEY = "claveComercio";
    public static final String MAC_KEY = "mac";

    //Informacion de conexion registrada en el server y de identificacion
    private String codigoTerminal;
    private String uuid;
    private String nombreTerminal;
    private String ip1;
    private String ip2;
    private String puerto;
    private String claveTecnivo;
    private String claveComercio;
    private String mac;

    /**
     * Constructor de la clase que recibe un Objeto tipo SoapObject
     *
     * @param soap
     */
    public ConexionEstablecimiento(SoapObject soap) {

        this.codigoTerminal = soap.getPropertyAsString(CODIGO_TERMINAL_KEY).equals("anyType{}") ? "" : soap.getPropertyAsString(CODIGO_TERMINAL_KEY);

        this.uuid = soap.getPropertyAsString(UUID_KEY).equals("anyType{}") ? "" : soap.getPropertyAsString(UUID_KEY);

        this.nombreTerminal = soap.getPropertyAsString(NOMBRE_TERMINAL_KEY).equals("anyType{}") ? "" : soap.getPropertyAsString(NOMBRE_TERMINAL_KEY);

        this.ip1 = soap.getPropertyAsString(IP_PRINCIPAL_KEY).equals("anyType{}") ? "" : soap.getPropertyAsString(IP_PRINCIPAL_KEY);

        this.ip2 = soap.getPropertyAsString(IP_SECUNDARIA_KEY).equals("anyType{}") ? "" : soap.getPropertyAsString(IP_SECUNDARIA_KEY);

        this.puerto = soap.getPropertyAsString(PUERTO_KEY).equals("anyType{}") ? "" : soap.getPropertyAsString(PUERTO_KEY);

        this.claveTecnivo = soap.getPropertyAsString(CLAVE_TECNICO_KEY).equals("anyType{}") ? "" : soap.getPropertyAsString(CLAVE_TECNICO_KEY);

        this.claveComercio = soap.getPropertyAsString(CLAVE_COMERCIO_KEY).equals("anyType{}") ? "" : soap.getPropertyAsString(CLAVE_COMERCIO_KEY);

        this.mac = soap.getPropertyAsString(MAC_KEY).equals("anyType{}") ? "" : soap.getPropertyAsString(MAC_KEY);

    }

    public String getCodigoTerminal() {
        return codigoTerminal;
    }

    public void setCodigoTerminal(String codigoTerminal) {
        this.codigoTerminal = codigoTerminal;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNombreTerminal() {
        return nombreTerminal;
    }

    public void setNombreTerminal(String nombreTerminal) {
        this.nombreTerminal = nombreTerminal;
    }

    public String getIp1() {
        return ip1;
    }

    public void setIp1(String ip1) {
        this.ip1 = ip1;
    }

    public String getIp2() {
        return ip2;
    }

    public void setIp2(String ip2) {
        this.ip2 = ip2;
    }

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    public String getClaveTecnivo() {
        return claveTecnivo;
    }

    public void setClaveTecnivo(String claveTecnivo) {
        this.claveTecnivo = claveTecnivo;
    }

    public String getClaveComercio() {
        return claveComercio;
    }

    public void setClaveComercio(String claveComercio) {
        this.claveComercio = claveComercio;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
}