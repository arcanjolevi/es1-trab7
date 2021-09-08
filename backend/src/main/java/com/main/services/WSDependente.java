package com.main.services;

import com.main.bo.pessoa.Dependente;
import com.main.json.DependenteJson;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dependente")
public class WSDependente {

    @PostMapping
    public Dependente criarAmbiente(@RequestBody DependenteJson value) {
        return null;
    }


    public Dependente consultarContribuinte(@RequestBody DependenteJson value){
        return null;
    }
}
