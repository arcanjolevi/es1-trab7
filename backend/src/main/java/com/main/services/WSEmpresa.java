package com.main.services;

import com.main.bo.pessoa.Empresa;
import com.main.view.EmpresaView;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/empresa")
public class WSEmpresa {

    @PostMapping
    public ResponseEntity<EmpresaView> criarEmpresa(@RequestBody EmpresaView empresaView) {

        try {
            Empresa empresa = empresaView.renderEmpresa();
            empresaView.setEmpresa(empresa);
            return ResponseEntity.status(HttpStatus.OK).body(empresaView);
        } catch (Exception e) {
            System.out.println("@post /empresa - Dados invalidos - Erro 400 - DAD REQUEST");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(empresaView);
        }
    }

    @GetMapping
    public ResponseEntity<EmpresaView> consultarEmpresa(@RequestBody EmpresaView viewEmpresa) {
        /*
         * String cnpj = viewEmpresa.getCnpj(); if (cnpj != null) { // Chama lucas }
         * else { return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); }
         */
        return null;
    }

}
