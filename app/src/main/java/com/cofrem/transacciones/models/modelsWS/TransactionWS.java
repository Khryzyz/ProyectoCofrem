package com.cofrem.transacciones.models.modelsWS;

public class TransactionWS {

    /**
     * Atributos de la clase
     */
    private String urlTransaction;
    private String nameSpaceTransaction;
    private String methodNameTransaction;
    private String[][] paramsTransaction;

    /**
     * Constructor de la clase TransactionWS para el modelado de las peticiones a los Web Services
     *
     * @param urlTransaction
     * @param nameSpaceTransaction
     * @param methodNameTransaction
     * @param paramsTransaction
     */
    public TransactionWS(String urlTransaction, String nameSpaceTransaction, String methodNameTransaction, String[][] paramsTransaction) {
        this.urlTransaction = urlTransaction;
        this.nameSpaceTransaction = nameSpaceTransaction;
        this.methodNameTransaction = methodNameTransaction;
        this.paramsTransaction = paramsTransaction;
    }

    /**
     * Getters y Setters
     */
    public String getUrlTransaction() {
        return urlTransaction;
    }

    public void setUrlTransaction(String urlTransaction) {
        this.urlTransaction = urlTransaction;
    }

    public String getNameSpaceTransaction() {
        return nameSpaceTransaction;
    }

    public void setNameSpaceTransaction(String nameSpaceTransaction) {
        this.nameSpaceTransaction = nameSpaceTransaction;
    }

    public String getMethodNameTransaction() {
        return methodNameTransaction;
    }

    public void setMethodNameTransaction(String methodNameTransaction) {
        this.methodNameTransaction = methodNameTransaction;
    }

    public String[][] getParamsTransaction() {
        return paramsTransaction;
    }

    public void setParamsTransaction(String[][] paramsTransaction) {
        this.paramsTransaction = paramsTransaction;
    }

}