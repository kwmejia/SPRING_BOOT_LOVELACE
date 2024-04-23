package com.riwi.operations.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.operations.entities.Operation;

@RestController
@RequestMapping("/api/v1/operations")
public class OperationController {

    @PostMapping
    public String sum(@RequestBody Operation objOperation) {
        int num1 = objOperation.getNum1();
        int num2 = objOperation.getNum2();

        return num1 + " + " + num2 + " = " + (num1 + num2);
    }

    @PostMapping("/subtract")
    public String subtract(@RequestBody Operation objOperation) {
        int num1 = objOperation.getNum1();
        int num2 = objOperation.getNum2();

        return num1 + " - " + num2 + " = " + (num1 - num2);
    }

    @PostMapping("/multiply")
    public String multiply(@RequestBody Operation objOperation) {
        int num1 = objOperation.getNum1();
        int num2 = objOperation.getNum2();

        return num1 + " x " + num2 + " = " + (num1 * num2);
    }

    @PostMapping("/split")
    public String split(@RequestBody Operation objOperation) {
        int num1 = objOperation.getNum1();
        int num2 = objOperation.getNum2();

        if (num2 == 0) {
            return "ERROR :No se puede dividir un número entre cero";
        }

        return num1 + " / " + num2 + " = " + (num1 / num2);
    }
}
