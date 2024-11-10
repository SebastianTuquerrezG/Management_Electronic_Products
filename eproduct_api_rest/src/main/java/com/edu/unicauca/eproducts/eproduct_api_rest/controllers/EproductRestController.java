package com.edu.unicauca.eproducts.eproduct_api_rest.controllers;

import com.edu.unicauca.eproducts.eproduct_api_rest.facade.DTO.EproductDTO;
import com.edu.unicauca.eproducts.eproduct_api_rest.facade.services.IEproductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<EproductDTO>> findAll() {
        List<EproductDTO> eproducts = eproductService.findAll();
        ResponseEntity<List<EproductDTO>> response = new ResponseEntity<List<EproductDTO>>(eproducts, HttpStatus.OK);
        return response;
    }

    @GetMapping("/eproducts/{id}")
    public ResponseEntity<EproductDTO> findById(@PathVariable Integer id) {
        EproductDTO eproduct = eproductService.findById(id);
        ResponseEntity<EproductDTO> response = new ResponseEntity<EproductDTO>(eproduct, HttpStatus.OK);
        return response;
    }

    @GetMapping("/eproducts2/{name}/{marca}")
    public ResponseEntity<String> getMessage(@PathVariable String name,
            @PathVariable("marca") String marca) {
        String msg = String.format("%s es de la marca %s", name, marca);
        ResponseEntity<String> response = new ResponseEntity<String>(msg, HttpStatus.OK);
        System.out.println(msg);
        return response;
    }

    @PostMapping("/eproducts")
    public ResponseEntity<EproductDTO> save(@RequestBody EproductDTO eproduct) {
        EproductDTO eproductSaved = eproductService.save(eproduct);
        ResponseEntity<EproductDTO> response = new ResponseEntity<EproductDTO>(eproductSaved, HttpStatus.CREATED);
        return response;
    }

    @PutMapping("/eproducts")
    public ResponseEntity<EproductDTO> update(@RequestParam Integer id, @RequestBody EproductDTO eproduct) {
        EproductDTO eproductUpdated = eproductService.update(id, eproduct);
        if (!Objects.equals(id, eproduct.getId())) {
            throw new IllegalArgumentException(("El id del producto no coincide con el id de la URL"));
        } else {
            ResponseEntity<EproductDTO> response = new ResponseEntity<EproductDTO>(eproductUpdated, HttpStatus.OK);
            return response;
        }
    }

    @DeleteMapping("/eproducts")
    public ResponseEntity<Boolean> delete(@RequestParam Integer id) {
        Boolean deleted = eproductService.delete(id);
        return new ResponseEntity<Boolean>(deleted, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/eproducts/listarCabeceras")
    public void listarCabeceras(@RequestHeader Map<String, String> headers) {
        System.out.println("Listando cabeceras");
        headers.forEach((key, value) -> {
            System.out.printf("Header '%s' = %s%n", key, value);
        });
    }
}
