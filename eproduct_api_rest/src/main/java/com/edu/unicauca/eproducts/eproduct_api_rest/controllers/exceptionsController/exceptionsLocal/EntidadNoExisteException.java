package com.edu.unicauca.eproducts.eproduct_api_rest.controllers.exceptionsController.exceptionsLocal;

import com.edu.unicauca.eproducts.eproduct_api_rest.controllers.exceptionsController.exceptionsEstructure.ErrorCode;

import lombok.Getter;

@Getter
public class EntidadNoExisteException extends RuntimeException {
    private final String llaveMensaje;
    private final String codigo;

    public EntidadNoExisteException(ErrorCode errorCode) {
        super(errorCode.getCodigo());
        this.llaveMensaje = errorCode.getLlaveMensaje();
        this.codigo = errorCode.getLlaveMensaje();
    }

    public EntidadNoExisteException(final String message) {
        super(message);
        this.llaveMensaje = ErrorCode.ENTITY_NOT_FOUND.getLlaveMensaje();
        this.codigo = ErrorCode.ENTITY_NOT_FOUND.getCodigo();
    }
}
