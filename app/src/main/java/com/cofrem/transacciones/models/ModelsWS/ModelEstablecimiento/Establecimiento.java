package com.cofrem.transacciones.models.ModelsWS.ModelEstablecimiento;

import com.cofrem.transacciones.models.ModelsWS.MessageWS;

public class Establecimiento {


    //Modelo usado en la respuesta del WS para la respuesta terminalResult

    private InformacionEstablecimiento informacionEstablecimiento;
    private ConexionEstablecimiento conexionEstablecimiento;
    private MessageWS messageWS;

    public Establecimiento(InformacionEstablecimiento informacionEstablecimiento, ConexionEstablecimiento conexionEstablecimiento, MessageWS messageWS) {
        this.informacionEstablecimiento = informacionEstablecimiento;
        this.conexionEstablecimiento = conexionEstablecimiento;
        this.messageWS = messageWS;
    }

    public InformacionEstablecimiento getInformacionEstablecimiento() {
        return informacionEstablecimiento;
    }

    public void setInformacionEstablecimiento(InformacionEstablecimiento informacionEstablecimiento) {
        this.informacionEstablecimiento = informacionEstablecimiento;
    }

    public ConexionEstablecimiento getConexionEstablecimiento() {
        return conexionEstablecimiento;
    }

    public void setConexionEstablecimiento(ConexionEstablecimiento conexionEstablecimiento) {
        this.conexionEstablecimiento = conexionEstablecimiento;
    }

    public MessageWS getMessageWS() {
        return messageWS;
    }

    public void setMessageWS(MessageWS messageWS) {
        this.messageWS = messageWS;
    }
}