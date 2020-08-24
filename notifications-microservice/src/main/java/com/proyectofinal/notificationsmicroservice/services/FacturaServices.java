package com.proyectofinal.notificationsmicroservice.services;

import com.proyectofinal.notificationsmicroservice.entities.Factura;
import com.proyectofinal.notificationsmicroservice.repositories.FacturaRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaServices {
    @Autowired
    FacturaRepositories facturaRepositories;

    public List<Factura> getFacturasByUsername(String username){
        return facturaRepositories.findAllByUsername(username);
    }

    @Transactional
    public boolean createFactura(Factura factura){
        if(facturaRepositories.findById(factura.getId()) != null){
            return false;
        }
        facturaRepositories.save(factura);
        return true;
    }

    @Transactional
    public boolean deleteFactura(long id){
        Factura factura = facturaRepositories.findById(id);
        if(factura != null){
            facturaRepositories.delete(factura);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean editFactura(Factura factura){
        Optional<Factura> e = Optional.ofNullable(facturaRepositories.findById(factura.getId()));
        if(e.isPresent()){
            Factura newFactura = e.get();
            newFactura.setUsername(factura.getUsername());
            newFactura.setProducts(factura.getProducts());
            newFactura.setTotal(factura.getTotal());
            return true;
        }
        return false;
    }
}
