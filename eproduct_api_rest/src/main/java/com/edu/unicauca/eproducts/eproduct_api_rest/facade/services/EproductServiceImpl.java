package com.edu.unicauca.eproducts.eproduct_api_rest.facade.services;

import com.edu.unicauca.eproducts.eproduct_api_rest.dataAccess.models.EproductEntity;
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
        EproductEntity eproductEntity = this.eproductRepository.findById(id);
        return this.modelMapper.map(eproductEntity, EproductDTO.class);
    }

    @Override
    public EproductDTO save(EproductDTO eproduct) {
        float precioFinal = calcularPrecioFinal(eproduct);
        eproduct.setPrecioFinal(precioFinal);
        EproductEntity eproductEntity = this.modelMapper.map(eproduct, EproductEntity.class);
        eproductEntity.setCreateAt(new Date());
        EproductEntity eproductEntitySaved = this.eproductRepository.save(eproductEntity);
        return this.modelMapper.map(eproductEntitySaved, EproductDTO.class);
    }

    @Override
    public EproductDTO update(Integer id, EproductDTO eproduct) {
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
        return this.eproductRepository.delete(id);
    }

    public float calcularPrecioFinal(EproductDTO eproduct) {
        float precioBase = eproduct.getPrecioBase();
        float impuesto = precioBase * 0.20f;
        return precioBase + (precioBase > 1000 ? impuesto : precioBase * 0.10f);
    }
}
