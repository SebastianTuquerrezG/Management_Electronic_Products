package com.edu.unicauca.eproducts.eproduct_api_rest.dataAccess.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class EproductEntity {
    private Integer id;
    private String name;
    private MarcaEntity marca;
    private CategoriaEntity categoria;
    private Float precioBase;
    private Float precioFinal;
    private Date createAt;

    public EproductEntity() {

    }

    public enum MarcaEntity {
        SONY, SAMSUNG, LG, APPLE, HUAWEI, XIAOMI, MOTOROLA,
        NOKIA, ALCATEL, ZTE, BLU, LENOVO, HP, DELL, ACER, ASUS,
        TOSHIBA,
    }

    public enum CategoriaEntity {
        TELEFONIA, COMPUTO, LINEA_BLANCA, AUDIO_VIDEO, GAMING,
        ACCESORIOS, IMPRESION, SOFTWARE, REDES, SEGURIDAD,
        ENERGIA, ALMACENAMIENTO, COMPONENTES, SERVICIOS,
    }
}
