package com.riwi.primeraweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    /**
     * Método para mostrar la vista de formulario y además
     * Enviar una instancia de Coder vacia
     */
    @GetMapping("form")
    public String showViewForm(Model model) {
        model.addAttribute("coder", new Coder());
        model.addAttribute("action", "/create-coder");
        return "viewForm";
    }

    /**
     * Método para recibir todos la información del formulario
     * 
     * @ModelAttribute lo utilizamos para recibir información de la vista
     */
    @PostMapping("create-coder")
    public String createCoder(@ModelAttribute Coder objCoder) {
        this.objCoderService.create(objCoder);
        return "redirect:/";
    }

    /**
     * Controlador para eliminar recibirá como parametro el id por URL
     * 
     * @PathVariable funciona para obtener el valor de una variable en la URL
     *               Solo si es de tipo path (ejm: /delete/10) donde el 10 es
     *               dinamico
     */
    @GetMapping("/delete/{id}")
    public String deleteCoder(@PathVariable Long id) {
        this.objCoderService.delete(id);

        // Redireccionar a la lista de coders
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String updateCoder(@PathVariable Long id, Model model) {
        Coder objCoder = this.objCoderService.findById(id);
        model.addAttribute("coder", objCoder);
        model.addAttribute("action", "");
        return "viewForm";
    }

}
