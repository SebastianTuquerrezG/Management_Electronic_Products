package com.edu.unicauca.eproducts.eproduct_api_rest.facade.services;

import com.edu.unicauca.eproducts.eproduct_api_rest.controllers.exceptionsController.exceptionsLocal.EntidadNoExisteException;
import com.edu.unicauca.eproducts.eproduct_api_rest.controllers.exceptionsController.exceptionsLocal.EntidadYaExisteException;
import com.edu.unicauca.eproducts.eproduct_api_rest.controllers.exceptionsController.exceptionsLocal.ReglaNegocioException;
import com.edu.unicauca.eproducts.eproduct_api_rest.dataAccess.models.EproductEntity;
import com.edu.unicauca.eproducts.eproduct_api_rest.dataAccess.models.EproductEntity.CategoriaEntity;
import com.edu.unicauca.eproducts.eproduct_api_rest.dataAccess.models.EproductEntity.MarcaEntity;
import com.edu.unicauca.eproducts.eproduct_api_rest.dataAccess.repositories.EproductRepository;
import com.edu.unicauca.eproducts.eproduct_api_rest.facade.DTO.EproductDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EproductServiceImpl implements IEproductService{
    private final EproductRepository eproductRepository;
    private final ModelMapper modelMapper;

    public EproductServiceImpl(EproductRepository eproductRepository, ModelMapper modelMapper) {
        this.eproductRepository = eproductRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<EproductDTO> findAll() {
        return this.modelMapper.map(
                eproductRepository.findAll(),
                new TypeToken<List<EproductDTO>>() {
        }.getType());
    }

    @Override
    public EproductDTO findById(Integer id) {
        if(this.eproductRepository.findById(id) == null){
            throw new EntidadNoExisteException("El producto no existe, no se puede buscar");
        } else {
            return this.modelMapper.map(this.eproductRepository.findById(id), EproductDTO.class);
        }
    }

    @Override
    public EproductDTO save(EproductDTO eproduct) {
        if (this.eproductRepository.existByName(eproduct.getName())) {
            throw new EntidadYaExisteException("El producto ya existe, no se puede guardar");
        }
        if (eproduct.getPrecioBase() < 0) {
            throw new ReglaNegocioException("El precio base no puede ser negativo");
        }
        if (eproduct.getPrecioBase() < 50) {
            throw new ReglaNegocioException("El precio base no puede ser menor a 50");
        }
        if (!eproduct.getName().equals(eproduct.getName().toUpperCase())) {
            throw new ReglaNegocioException("El nombre del producto debe estar en mayúsculas");
        }

        try {
            MarcaEntity.valueOf(eproduct.getMarca());
        } catch (IllegalArgumentException e) {
            throw new ReglaNegocioException("Marca no válida. Debe ser una de las marcas definidas.");
        }

        try {
            CategoriaEntity.valueOf(eproduct.getCategoria());
        } catch (IllegalArgumentException e) {
            throw new ReglaNegocioException("Categoría no válida. Debe ser una de las categorías definidas.");
        }

        float precioFinal = calcularPrecioFinal(eproduct);
        eproduct.setPrecioFinal(precioFinal);
        EproductEntity eproductEntity = this.modelMapper.map(eproduct, EproductEntity.class);
        eproductEntity.setCreateAt(new Date());

        EproductEntity eproductEntitySaved = this.eproductRepository.save(eproductEntity);
        return this.modelMapper.map(eproductEntitySaved, EproductDTO.class);
    }


    @Override
    public EproductDTO update(Integer id, EproductDTO eproduct) {
        if (this.eproductRepository.findById(id) == null) {
            throw new EntidadNoExisteException("El producto no existe, no se puede actualizar");
        }
        if (eproduct.getPrecioBase() < 0) {
            throw new ReglaNegocioException("El precio base no puede ser negativo");
        }
        if (eproduct.getPrecioBase() < 50) {
            throw new ReglaNegocioException("El precio base no puede ser menor a 50");
        }
        if (!eproduct.getName().equals(eproduct.getName().toUpperCase())) {
            throw new ReglaNegocioException("El nombre del producto debe estar en mayúsculas");
        }

        try {
            MarcaEntity.valueOf(eproduct.getMarca());
        } catch (IllegalArgumentException e) {
            throw new ReglaNegocioException("Marca no válida. Debe ser una de las marcas definidas.");
        }

        try {
            CategoriaEntity.valueOf(eproduct.getCategoria());
        } catch (IllegalArgumentException e) {
            throw new ReglaNegocioException("Categoría no válida. Debe ser una de las categorías definidas.");
        }

        String nameExist = this.eproductRepository.findById(id).getName();
        String nameNew = eproduct.getName();
        if (!nameExist.equals(nameNew) && this.eproductRepository.existByName(nameNew)) {
            throw new EntidadYaExisteException("El producto ya existe, no se puede actualizar");
        }

        float precioFinal = calcularPrecioFinal(eproduct);
        eproduct.setPrecioFinal(precioFinal);
        EproductEntity eproductEntity = this.modelMapper.map(eproduct, EproductEntity.class);
        EproductEntity eproductEntityUpdated = this.eproductRepository.update(id, eproductEntity);
        if (eproductEntityUpdated == null) {
            return null;
        }
        return this.modelMapper.map(eproductEntityUpdated, EproductDTO.class);
    }



    @Override
    public boolean delete(Integer id) {
        if (this.eproductRepository.findById(id) == null) {
            throw new EntidadNoExisteException("El producto no existe, no se puede eliminar");            
        } else {
            return this.eproductRepository.delete(id);
        }
    }

    public float calcularPrecioFinal(EproductDTO eproduct) {
        float precioBase = eproduct.getPrecioBase();
        float impuesto = precioBase * 0.20f;
        return precioBase + (precioBase > 1000 ? impuesto : precioBase * 0.10f);
    }
}
