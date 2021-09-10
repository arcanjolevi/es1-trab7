package com.main.services;

import java.sql.SQLException;
import java.util.ArrayList;

import com.main.bo.bensedireitos.BensEDireitos;
import com.main.bo.bensedireitos.TipoBensEDireitos;
import com.main.controller.UsBensEDireitos;
import com.main.view.BemDireitoView;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bens-e-direitos")
public class WSBensEDireitos {

    @GetMapping
    public ResponseEntity<BensEDireitos> consultarBensEDireitos(@RequestParam String id) {
        UsBensEDireitos controllerBenDireitos = new UsBensEDireitos();
        try {
            BensEDireitos bemDireito = controllerBenDireitos.Consultar(Integer.parseInt(id));
            return ResponseEntity.status(HttpStatus.OK).body(bemDireito);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @RequestMapping("/listar")
    @GetMapping
    public ArrayList<String> consultarTodosBensEDireitos(@RequestParam String cpf) {
        UsBensEDireitos controllerBenDireitos = new UsBensEDireitos();

        controllerBenDireitos.c
        // controllerBenDireitos
        return new ArrayList<String>();
    }

    @PostMapping
    public ResponseEntity<Integer> cadastrarBemEDireito(@RequestParam String nomeBemDireito,
            @RequestParam String tipoBemDireito, @RequestParam Double valorTotal,
            @RequestParam String cpfContribuinte) {

        TipoBensEDireitos tipoBemDireitoInstance = new TipoBensEDireitos(nomeBemDireito, tipoBemDireito);
        BensEDireitos bemDireito = new BensEDireitos(valorTotal, tipoBemDireitoInstance);
        UsBensEDireitos controllerBenDireitos = new UsBensEDireitos();
        try {
            Integer id = controllerBenDireitos.Cadastrar(bemDireito, cpfContribuinte);
            return ResponseEntity.status(HttpStatus.OK).body(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    public BensEDireitos renderBemDireito(BemDireitoView view) {
        BensEDireitos value = new BensEDireitos(view.valorTotal,
                new TipoBensEDireitos(view.nomeBemDireito, view.tipoBemDireito));
        return value;
    }
}
