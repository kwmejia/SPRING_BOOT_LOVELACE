package com.riwi.holamundo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//Indica que esta clase será un controlador
@Controller
// Request Mapping crea la ruta base para acceder al controlador
@RequestMapping("/controller")
public class Holamundo {

    // GetMapping crea la ruta especifica donde se disparará el método
    @GetMapping("/saludar")
    // Permite responder en formato texto o json
    @ResponseBody
    public String mostrarMensaje() {

        return "Hola Mundo";
    }
}
