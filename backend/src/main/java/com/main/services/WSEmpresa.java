package com.main.services;

import java.util.ArrayList;

import com.main.bo.pessoa.Rg;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/empresa")
public class WSEmpresa {

    @PostMapping
    public Rg hello(@RequestBody Rg value) {
        System.out.println(value.getNroRg());

        // value.vetor.add("valor 1");

        return value;
    }
}
