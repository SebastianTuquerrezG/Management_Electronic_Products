package com.edu.unicauca.eproducts.eproduct_api_rest.controllers.exceptionsController.exceptionsLocal;

import com.edu.unicauca.eproducts.eproduct_api_rest.controllers.exceptionsController.exceptionsEstructure.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public abstract class GestionProductsRuntimeException extends RuntimeException {
    protected ErrorCode errorCode;

    public abstract String formatException();    
}
