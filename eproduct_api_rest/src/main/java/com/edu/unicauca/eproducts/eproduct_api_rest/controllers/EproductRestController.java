package com.edu.unicauca.eproducts.eproduct_api_rest.controllers;

import com.edu.unicauca.eproducts.eproduct_api_rest.facade.DTO.EproductDTO;
import com.edu.unicauca.eproducts.eproduct_api_rest.facade.services.IEproductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class EproductRestController {
    @Autowired
    @Lazy
    private IEproductService eproductService;

    @GetMapping("/eproducts")
    public List<EproductDTO> findAll() {
        return eproductService.findAll();
    }

    @GetMapping("/eproducts/{id}")
    public EproductDTO findById(@PathVariable Integer id) {
        return eproductService.findById(id);
    }

    @GetMapping("/eproducts2/{name}/{marca}")
    public String getMessage(@PathVariable String name,
            @PathVariable("marca") String marca) {
        String msg = String.format("%s es de la marca %s", name, marca);
        System.out.println(msg);
        return msg;
    }

    @PostMapping("/eproducts")
    public EproductDTO save(@RequestBody EproductDTO eproduct) {
        return eproductService.save(eproduct);
    }

    @PutMapping("/eproducts")
    public EproductDTO update(@RequestParam Integer id, @RequestBody EproductDTO eproduct) {
        if (!Objects.equals(id, eproduct.getId())) {
            throw new IllegalArgumentException(("El id del producto no coincide con el id de la URL"));
        } else {
            return eproductService.update(id, eproduct);
        }
    }

    @DeleteMapping("/eproducts")
    public boolean delete(@RequestParam Integer id) {
        EproductDTO eproduct = eproductService.findById(id);
        if (eproduct == null) {
            return false;
        } else {
            return eproductService.delete(id);
        }
    }

    @GetMapping("/eproducts/listarCabeceras")
    public void listarCabeceras(@RequestHeader Map<String, String> headers) {
        System.out.println("Listando cabeceras");
        headers.forEach((key, value) -> {
            System.out.printf("Header '%s' = %s%n", key, value);
        });
    }
}
