package com.edu.unicauca.eproducts.eproduct_api_rest.facade.services;

import com.edu.unicauca.eproducts.eproduct_api_rest.facade.DTO.EproductDTO;

import java.util.List;

public interface IEproductService {
    public List<EproductDTO> findAll();
    public EproductDTO findById(Integer id);
    public EproductDTO save(EproductDTO eproduct);
    public EproductDTO update(Integer id, EproductDTO eproduct);
    public boolean delete(Integer id);
}
