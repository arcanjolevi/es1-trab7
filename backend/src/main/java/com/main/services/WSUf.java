package com.main.services;

import com.main.bo.endereco.Uf;
import com.main.controller.UsUf;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/uf")
public class WSUf {

    @PostMapping
    public ResponseEntity<String> cadastrarUf(@RequestParam String nomeUf, @RequestParam String siglaUf) {

        Uf estado = new Uf(nomeUf, siglaUf);
        UsUf ufController = new UsUf();
        boolean sucess = ufController.cadastrar(estado);

        if (sucess) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
