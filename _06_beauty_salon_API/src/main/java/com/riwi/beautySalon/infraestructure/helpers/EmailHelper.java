package com.riwi.beautySalon.infraestructure.helpers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class EmailHelper {

    
    private String readHTMLTemplate(String nameClient, String nameEmployee, String date){

        //Indicar en donde se encuentra nuestro template
        Path path = Paths.get("src/main/resources/emails/email_template.html");

        try (var lines = Files.lines(path)){

            var html = lines.collect(Collectors.joining());
            
            return html
                    .replace("{name}", nameClient)
                    .replace("{employee}", nameEmployee)
                    .replace("{dateTime}",date);
        
        } catch (Exception e) {
            System.out.println("No se pudo leer el html");
            throw new RuntimeException();
        }
    }
}
