package com.main.services;

import com.main.bo.pessoa.Dependente;
import com.main.view.DependenteView;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dependente")
public class WSDependente {

    @PostMapping
    public ResponseEntity<DependenteView> criarDependente(@RequestBody DependenteView depeView) {
        try {
            Dependente contribuinte = depeView.renderDependente();
            // chama lucas
            return ResponseEntity.status(HttpStatus.OK).body(depeView);
        } catch (Exception e) {
            System.out.println("@post /dependente - Dados invalidos - Erro 400 - DAD REQUEST");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(depeView);
        }
    }

    @GetMapping
    public DependenteView consultarDependente(@RequestBody DependenteView value) {
        return null;
    }

    @RequestMapping("/remove")
    @PostMapping
    public DependenteView removerDependente(@RequestBody DependenteView value) {
        return null;
    }

    @RequestMapping("/tipo")
    @GetMapping
    public DependenteView consultarTipoDependente(@RequestBody DependenteView value) {
        return null;
    }

}
