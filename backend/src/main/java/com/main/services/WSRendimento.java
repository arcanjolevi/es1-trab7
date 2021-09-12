package com.main.services;

import java.sql.SQLException;
import java.util.ArrayList;

import com.main.bo.pessoa.Rendimento;
import com.main.controller.UsRendimento;
import com.main.view.RendimentoView;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/rendimento")
public class WSRendimento {

    @PostMapping
    public ResponseEntity<Integer> criarRendimento(@RequestBody RendimentoView value) {
        try {
            Rendimento rendimento = this.renderRendimento(value);
            UsRendimento rendimentoController = new UsRendimento();
            Integer id = rendimentoController.Cadastrar(rendimento, value.cnpj, value.cpf);
            return ResponseEntity.status(HttpStatus.OK).body(id);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("@post /rendimento - Dados invalidos - Erro 400 - BAD REQUEST");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<ArrayList<Rendimento>> listarRendimentosContribuinte(@RequestParam String cpf) {
        UsRendimento rendimentoController = new UsRendimento();
        try {
            ArrayList<Rendimento> array = rendimentoController.Consultar(cpf);
            return ResponseEntity.status(HttpStatus.OK).body(array);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @RequestMapping("/id")
    @GetMapping
    public ResponseEntity<Rendimento> consultarRendimento(@RequestParam String idRendimento) {
        UsRendimento rendimentoController = new UsRendimento();
        try {
            Rendimento rendimento = rendimentoController.Consultar(Integer.parseInt(idRendimento));
            return ResponseEntity.status(HttpStatus.OK).body(rendimento);
        } catch (NumberFormatException e) {
            System.out.println("@post /rendimento - 00 - Dados invalidos - Erro 400 - BAD REQUEST");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (SQLException e) {
            System.out.println("@post /rendimento - 01 - Dados invalidos - Erro 400 - BAD REQUEST");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            System.out.println("@post /rendimento - 02 -Dados invalidos - Erro 400 - BAD REQUEST");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    public Rendimento renderRendimento(RendimentoView view) {
        return new Rendimento(view.valor, view.inss, view.irrf, view.decimoTerceiro, null);
    }

}
