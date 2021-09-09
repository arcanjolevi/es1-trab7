package com.main.services;

import java.util.ArrayList;

import com.main.bo.pessoa.Dependente;
import com.main.view.DependenteView;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping("/consulta/cpf")
    @GetMapping
    public DependenteView consultarDependente(@RequestParam String cpf) {
        System.out.println(cpf);
        return null;
    }

    @RequestMapping("/remove/{cpf}")
    @PostMapping
    public DependenteView removerDependente(@RequestParam String cpf) {
        System.out.println(cpf);
        return null;
    }

    @RequestMapping("/listar/tipos")
    @GetMapping
    public ArrayList<String> consultarTiposDependente() {
        return new ArrayList<String>();
    }

}
