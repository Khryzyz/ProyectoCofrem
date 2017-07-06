package com.cofrem.transacciones.global;

public class InfoTransaccion {

    // Llaves resultado de las solicitudes a DATA_TRANSACCION
    public final static String TRANS_KEY_STATUS = "STATUS";
    public final static String TRANS_KEY_MODULE = "MODULE";
    public final static String TRANS_KEY_DATA = "DATA";
    public final static String TRANS_KEY_MESSAGE = "MESSAGE";

    // Values resultado de las solicitudes a DATA_TRANSACCION
    public final static String TRANS_RESULT_STATUS_SUCCESS = "SUCCESS";
    public final static String TRANS_RESULT_STATUS_ERROR = "ERROR";

    // Values resultado de las solicitudes a DATA_MODULE
    public final static String TRANS_RESULT_MODULE_INSERT = "INSERTAR_SOLICITUD";
    public final static String TRANS_RESULT_MODULE_SELECT = "CONSULTAR_SOLICITUD";
    public final static String TRANS_RESULT_MODULE_STATUS = "ESTADO_SOLICITUD";
    public final static String TRANS_RESULT_MODULE_TRANSACTION = "TRANSACCION";

    public final static String TRANS_RESULT_MODULE_STATUS_ACCEPTED = "ACCEPT";
    public final static String TRANS_RESULT_MODULE_STATUS_REJECTED = "REJECT";
    public final static String TRANS_RESULT_MODULE_STATUS_STANDBY = "STANDBY";
    public final static String TRANS_RESULT_MODULE_STATUS_ERROR = "ERROR";


}
