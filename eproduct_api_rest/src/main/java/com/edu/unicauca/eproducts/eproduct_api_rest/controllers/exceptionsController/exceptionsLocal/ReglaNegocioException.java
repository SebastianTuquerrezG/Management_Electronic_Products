package com.edu.unicauca.eproducts.eproduct_api_rest.controllers.exceptionsController.exceptionsLocal;

import com.edu.unicauca.eproducts.eproduct_api_rest.controllers.exceptionsController.exceptionsEstructure.ErrorCode;

public class ReglaNegocioException extends GestionProductsRuntimeException {
    private static final String FORMATO_EXCEPCION = "%s - Violación a regla de negocio: %s";

    private final String reglaNegocio;

    public ReglaNegocioException(final String message) {
        super(ErrorCode.BUSINESS_RULE_VALIDATION);
        this.reglaNegocio = message;
    }

    @Override
    public String formatException() {
        return String.format(FORMATO_EXCEPCION, this.errorCode.getCodigo(), this.reglaNegocio);
    }
    
}
