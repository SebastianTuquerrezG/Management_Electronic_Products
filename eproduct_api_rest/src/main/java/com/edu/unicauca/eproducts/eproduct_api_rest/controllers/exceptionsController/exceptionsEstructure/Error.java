package com.edu.unicauca.eproducts.eproduct_api_rest.controllers.exceptionsController.exceptionsEstructure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Error {
    private String codigoError;
    private String mensaje;
    private Integer codigoHttp;

    @Accessors(chain=true)
    private String url;

    @Accessors(chain=true)
    private String metodo;
}
