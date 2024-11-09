package com.edu.unicauca.eproducts.eproduct_api_rest.facade.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class EproductDTO {
    private Integer id;
    private String name;
    private String marca;
    private String categoria;
    private float precioBase;
    private float precioFinal;
    private Date createAt;

    public EproductDTO() {
    }
}
