package com.riwi.primeraweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.riwi.primeraweb.entity.Coder;
import com.riwi.primeraweb.service.CoderService;

@Controller
/**
 * Crear la ruta donde se activará este controlador
 */
@RequestMapping("/")
public class CoderController {
    /* Esteblecer la inyección de depedencias */
    @Autowired
    private CoderService objCoderService;

    /**
     * Método para mostrar la vista y enviarle toda la lista de coders
     */
    @GetMapping
    public String showViewCoder(Model objModel) {
        /* Obtenemos la lista de coders */
        List<Coder> listCoders = this.objCoderService.findAll();

        /* Cargamos la lista en el modelo */
        objModel.addAttribute("listCoders", listCoders); // LLave-valor
        return "viewCoders";
    }

    @GetMapping("form")
    public String showViewForm() {
        return "viewForm";
    }
}
