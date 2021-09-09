package com.main.services;
import com.main.bo.pessoa.Contribuinte;
import com.main.bo.pessoa.Empresa;
import com.main.view.ContribuinteView;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contribuinte")
public class WSContribuinte {

    @PostMapping
    public ContribuinteView criarContribuinte(@RequestBody ContribuinteView value) {
        
        return null;
    }

    @GetMapping
    public ContribuinteView consultarContribuinte(@RequestBody ContribuinteView value){

        return null;

    }
}
