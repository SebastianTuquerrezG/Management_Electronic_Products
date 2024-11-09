package com.edu.unicauca.eproducts.eproduct_api_rest.controllers.exceptionsController.exceptionsEstructure;

public final class ErrorUtils {
    ErrorUtils(){}

    public static Error createError(final String codigoError, final String llaveMensaje, final Integer codigoHttp){
        final Error error = new Error();
        error.setCodigoError(codigoError);
        error.setMensaje(llaveMensaje);
        error.setCodigoHttp(codigoHttp);
        return error;
    }
}
