package com.edu.unicauca.eproducts.eproduct_api_rest.controllers.exceptionsController.exceptionsEstructure;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    GENERIC_ERROR("", ""),
    ENTITY__ALREADY_EXIST("", ""),
    ENTITY_NOT_FOUND("", ""),
    BUSINESS_RULE_VALIDATION("", "");

    private final String codigo;
    private final String llaveMensaje;
}
