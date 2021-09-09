package com.main.services;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bens-e-direitos")
public class WSBensEDireitos {

    @RequestMapping("/listar")
    @GetMapping
    public ArrayList<String> consultarBensEDireitos() {
        return new ArrayList<String>();
    }

    @PostMapping
    public String cadastrarBemEDireito(@RequestParam String nomeBemDireito, @RequestParam String tipoBemDireito,
            @RequestParam Double valorTotal, @RequestParam String cpfContribuinte) {

        return "Cadastro bem e direito";
    }
}
