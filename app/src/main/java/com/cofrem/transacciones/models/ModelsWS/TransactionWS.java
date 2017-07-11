package com.cofrem.transacciones.models.ModelsWS;

public class TransactionWS {

    private String urlTransaction;
    private String nameSpaceTransaction;
    private String methodNameTransaction;
    private String[][] paramsTransaction;

    public TransactionWS() {
    }

    public TransactionWS(String urlTransaction, String nameSpaceTransaction, String methodNameTransaction, String[][] paramsTransaction) {
        this.urlTransaction = urlTransaction;
        this.nameSpaceTransaction = nameSpaceTransaction;
        this.methodNameTransaction = methodNameTransaction;
        this.paramsTransaction = paramsTransaction;
    }

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