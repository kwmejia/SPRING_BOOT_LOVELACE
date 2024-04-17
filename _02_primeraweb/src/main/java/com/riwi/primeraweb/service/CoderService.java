package com.riwi.primeraweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.riwi.primeraweb.entity.Coder;
import com.riwi.primeraweb.repository.CoderRepository;

@Service
public class CoderService {

    /*
     * Autowired Registra la inyección de dependencias en SPRING BOOT
     */
    @Autowired
    private CoderRepository obCoderRepository;

    public List<Coder> findAll() {
        return this.obCoderRepository.findAll();
    }

    /*
     * Método para crear un nuevo coder, se hace uso del repositorio y
     * del método save, el cual se encarga de insertar en la base de datos
     */
    public Coder create(Coder objCoder) {
        return this.obCoderRepository.save(objCoder);
    }
}
