package com.cofrem.transacciones.models.modelsWS.modelEstablecimiento;

import org.ksoap2.serialization.SoapObject;

public class InformacionEstablecimiento {

    //Modelo usado en la respuesta del WS para la respuesta terminalResult

    //Modelado rapido de llaves del terminalResulta
    public static final String CODIGO_PUNTO_KEY = "codigoPunto";
    public static final String NOMBRE_PUNTO_KEY = "nombrePunto";
    public static final String DIRECCION_PUNTO_KEY = "direccionPunto";
    public static final String CIUDAD_PUNTO_KEY = "ciudadPunto";
    public static final String NIT_COMERCIO_KEY = "nitComercio";
    public static final String RAZON_SOCIAL_COMERCIO_KEY = "razonSocialComercio";

    //Informacion del Establecimiento

    private String codigoPunto;
    private String nombrePunto;
    private String direccionPunto;
    private String ciudadPunto;
    private String nitComercio;
    private String razonSocialComercio;

    /**
     * Constructor de la clase que recibe un Objeto tipo SoapObject
     *
     * @param soap
     */
    public InformacionEstablecimiento(SoapObject soap) {

        this.codigoPunto = soap.getPropertyAsString(CODIGO_PUNTO_KEY).equals("anyType{}") ? "" : soap.getPropertyAsString(CODIGO_PUNTO_KEY);

        this.nombrePunto = soap.getPropertyAsString(NOMBRE_PUNTO_KEY).equals("anyType{}") ? "" : soap.getPropertyAsString(NOMBRE_PUNTO_KEY);

        this.direccionPunto = soap.getPropertyAsString(DIRECCION_PUNTO_KEY).equals("anyType{}") ? "" : soap.getPropertyAsString(DIRECCION_PUNTO_KEY);

        this.ciudadPunto = soap.getPropertyAsString(CIUDAD_PUNTO_KEY).equals("anyType{}") ? "" : soap.getPropertyAsString(CIUDAD_PUNTO_KEY);

        this.nitComercio = soap.getPropertyAsString(NIT_COMERCIO_KEY).equals("anyType{}") ? "" : soap.getPropertyAsString(NIT_COMERCIO_KEY);

        this.razonSocialComercio = soap.getPropertyAsString(RAZON_SOCIAL_COMERCIO_KEY).equals("anyType{}") ? "" : soap.getPropertyAsString(RAZON_SOCIAL_COMERCIO_KEY);
    }

    public String getCodigoPunto() {
        return codigoPunto;
    }

    public void setCodigoPunto(String codigoPunto) {
        this.codigoPunto = codigoPunto;
    }

    public String getNombrePunto() {
        return nombrePunto;
    }

    public void setNombrePunto(String nombrePunto) {
        this.nombrePunto = nombrePunto;
    }

    public String getDireccionPunto() {
        return direccionPunto;
    }

    public void setDireccionPunto(String direccionPunto) {
        this.direccionPunto = direccionPunto;
    }

    public String getCiudadPunto() {
        return ciudadPunto;
    }

    public void setCiudadPunto(String ciudadPunto) {
        this.ciudadPunto = ciudadPunto;
    }

    public String getNitComercio() {
        return nitComercio;
    }

    public void setNitComercio(String nitComercio) {
        this.nitComercio = nitComercio;
    }

    public String getRazonSocialComercio() {
        return razonSocialComercio;
    }

    public void setRazonSocialComercio(String razonSocialComercio) {
        this.razonSocialComercio = razonSocialComercio;
    }
}