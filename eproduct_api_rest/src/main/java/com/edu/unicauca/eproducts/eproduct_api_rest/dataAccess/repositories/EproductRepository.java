package com.edu.unicauca.eproducts.eproduct_api_rest.dataAccess.repositories;

import com.edu.unicauca.eproducts.eproduct_api_rest.dataAccess.models.EproductEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Repository
public class EproductRepository {
    private final ArrayList<EproductEntity> listProducts;

    public EproductRepository() {
        this.listProducts = new ArrayList<>();
        cargarProductos();
    }

    public List<EproductEntity> findAll() {
        System.out.println("Invocando a Listar Productos");
        return this.listProducts;
    }

    public EproductEntity findById(Integer id) {
        System.out.println("Invocando a Buscar Producto por ID");
        for (EproductEntity product : this.listProducts) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public boolean existByName(String name) {
        System.out.println("Invocando a Buscar Producto por Nombre");
        for (EproductEntity product : this.listProducts) {
            if (product.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public EproductEntity save(EproductEntity product) {
        System.out.println("Invocando a Guardar Producto");
        product.setId(this.listProducts.size() + 1);
        if (this.listProducts.add(product)) {
            return product;
        }
        return null;
    }

    public EproductEntity update(Integer id, EproductEntity product) {
        System.out.println("Invocando a Actualizar Producto");
        for (int i = 0; i < this.listProducts.size(); i++) {
            if (Objects.equals(this.listProducts.get(i).getId(), id)) {
                this.listProducts.set(i, product);
                return product;
            }
        }
        System.out.println("Producto no encontrado");
        return null;
    }

    public boolean delete(Integer id) {
        System.out.println("Invocando a Eliminar Producto");
        for (int i = 0; i < this.listProducts.size(); i++) {
            if (Objects.equals(this.listProducts.get(i).getId(), id)) {
                this.listProducts.remove(i);
                return true;
            }
        }
        return false;
    }

    private void cargarProductos(){
        EproductEntity product1 = new EproductEntity();
        product1.setId(1);
        product1.setName("MACBOOK PRO");
        product1.setMarca(EproductEntity.MarcaEntity.APPLE);
        product1.setCategoria(EproductEntity.CategoriaEntity.COMPUTO);
        product1.setPrecioBase(2000.0f);
        product1.setPrecioFinal(2000.0f + 2000.0f * 0.20f);
        product1.setCreateAt(new Date());
        this.listProducts.add(product1);

        EproductEntity product2 = new EproductEntity();
        product2.setId(2);
        product2.setName("IPHONE 12");
        product2.setMarca(EproductEntity.MarcaEntity.APPLE);
        product2.setCategoria(EproductEntity.CategoriaEntity.TELEFONIA);
        product2.setPrecioBase(1000.0f);
        product2.setPrecioFinal(1000.0f + 1000.0f * 0.15f);
        product2.setCreateAt(new Date());
        this.listProducts.add(product2);

        EproductEntity product3 = new EproductEntity();
        product3.setId(3);
        product3.setName("GALAXY S21");
        product3.setMarca(EproductEntity.MarcaEntity.SAMSUNG);
        product3.setCategoria(EproductEntity.CategoriaEntity.TELEFONIA);
        product3.setPrecioBase(900.0f);
        product3.setPrecioFinal(900.0f + 900.0f * 0.10f);
        product3.setCreateAt(new Date());
        this.listProducts.add(product3);
    }
}
