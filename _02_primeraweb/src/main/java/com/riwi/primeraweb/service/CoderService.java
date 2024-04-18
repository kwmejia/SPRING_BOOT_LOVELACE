package com.riwi.primeraweb.service;

import java.security.PublicKey;
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

    /**
     * 
     * Método para eliminar un coder (deleteById)
     */
    public void delete(Long id) {
        // Llamar al repositorio
        this.obCoderRepository.deleteById(id);
    }

    /**
     * Método para obtener por Id
     */
    public Coder findById(Long id) {
        /* Busca un coder por ID y encaso de no ser encontrado devuelve un null */
        return this.obCoderRepository.findById(id).orElse(null);
    }
}
