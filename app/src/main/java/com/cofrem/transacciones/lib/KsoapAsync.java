package com.cofrem.transacciones.lib;

import android.os.AsyncTask;

import com.cofrem.transacciones.global.InfoGlobalTransaccionSOAP;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

public class KsoapAsync extends AsyncTask<String, Object, SoapObject> {
    @Override
    protected SoapObject doInBackground(String... params) {
        //se crea un nuevo Soap Request
        SoapObject soapRequest = new SoapObject(InfoGlobalTransaccionSOAP.NAME_SPACE, InfoGlobalTransaccionSOAP.METHOD_NAME_PUNTO);

        PropertyInfo propertyInfo = new PropertyInfo();
        propertyInfo.setName(InfoGlobalTransaccionSOAP.PARAM_NAME_CODIGO_PUNTO);
        propertyInfo.setValue(params[0].toString().trim());
        propertyInfo.setType(String.class);

        //Se agrega propiedad
        soapRequest.addProperty(propertyInfo);

        //request to server and get Soap Primitive response
        SoapObject soapResponse = KsoapTransaction.getData(InfoGlobalTransaccionSOAP.URL, InfoGlobalTransaccionSOAP.ACTION_NAME_SOLICITUD_PUNTO, soapRequest);

        return soapResponse;
    }

    @Override
    protected void onPostExecute(SoapObject soapResponse) {

    }
}
