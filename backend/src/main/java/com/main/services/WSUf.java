package com.main.services;

import com.main.bo.endereco.Uf;

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

        try {

        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return "deu certo";
    }
}
