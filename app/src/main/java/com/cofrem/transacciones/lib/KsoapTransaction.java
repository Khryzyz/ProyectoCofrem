package com.cofrem.transacciones.lib;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class KsoapTransaction {


    /**
     * Metodo que hace la Transaccion de datos a un Servicio SOAP
     *
     * @param urlSOAP
     * @param actionSOAP
     * @param soapObject
     * @return
     */
    public static SoapObject getData(String urlSOAP,
                                     String actionSOAP,
                                     SoapObject soapObject) {

        try {

            // Se extiende de SoapEnvelope con funcionalidades de serializacion
            SoapSerializationEnvelope soapSerializationEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

            // Asigna el objeto SoapObject al soapSerializationEnvelope
            soapSerializationEnvelope.setOutputSoapObject(soapObject);

            //capa de transporte http basada en J2SE
            //crea nueva instancia -> PARAM_URL: destino de datos SOAP POST
            HttpTransportSE httpTransportSE = new HttpTransportSE(urlSOAP);

            // Establece cabecera para la accion
            // actionSOAP: accion a ejecutar
            // soapSerializationEnvelope: contiene informacion para realizar la llamada
            httpTransportSE.call(actionSOAP, soapSerializationEnvelope);

            // Obtiene la respuesta de la peticion
            SoapObject soapSerializationEnvelopeResponse = (SoapObject) soapSerializationEnvelope.getResponse();

            // Regresa el objeto
            return soapSerializationEnvelopeResponse;

        } catch (HttpResponseException e1) {

            e1.printStackTrace();

            return null;

        } catch (SoapFault soapFault) {

            soapFault.printStackTrace();

            return null;

        } catch (XmlPullParserException e1) {

            e1.printStackTrace();

            return null;

        } catch (IOException e1) {

            e1.printStackTrace();

            return null;

        } catch (Exception e) {

            e.printStackTrace();

            return null;
        }
    }
}