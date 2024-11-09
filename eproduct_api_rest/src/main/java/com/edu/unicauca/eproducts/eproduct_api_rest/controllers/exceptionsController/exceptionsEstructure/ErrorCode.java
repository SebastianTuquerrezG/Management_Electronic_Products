package com.edu.unicauca.eproducts.eproduct_api_rest.controllers.exceptionsController.exceptionsEstructure;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    GENERIC_ERROR("E#001", "ERROR GENERICO"),
    ENTITY_ALREADY_EXIST("E#002", "ERROR ENTIDAD YA EXISTE"),
    ENTITY_NOT_FOUND("E#003", "ENTIDAD NO ENCONTRADA"),
    BUSINESS_RULE_VALIDATION("E#004", "REGLA DE NEGOCIO VIOLADA");

    private final String codigo;
    private final String llaveMensaje;
}
