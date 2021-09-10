package com.main.services;

import com.main.bo.pessoa.Rendimento;
import com.main.view.RendimentoView;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rendimento")
public class WSRendimento {

    @PostMapping
    public RendimentoView criarRendimento(@RequestBody RendimentoView value) {

        return null;
    }

    @GetMapping
    public RendimentoView consultarRendimento(@RequestParam String cpfContribuinte) {
        System.out.println(cpfContribuinte);
        return null;

    }

    public Rendimento renderRendimento(RendimentoView view) {
        return new Rendimento(view.valor, view.inss, view.irrf, view.decimoTerceiro, null);
    }

}
