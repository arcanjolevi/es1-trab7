package com.main.services;

import java.sql.SQLException;

import com.main.bo.pessoa.Contribuinte;
import com.main.model.database.DbConnection;
import com.main.view.ContribuinteView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contribuinte")
public class WSContribuinte {

    @PostMapping
    public ResponseEntity<ContribuinteView> criarContribuinte(@RequestBody ContribuinteView contriView) {

        try {
            Contribuinte contribuinte = contriView.renderContruibuinte();
            // chama lucas
            return ResponseEntity.status(HttpStatus.OK).body(contriView);
        } catch (Exception e) {
            System.out.println("@post /contribuinte - Dados invalidos - Erro 400 - DAD REQUEST");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(contriView);
        }
    }

    @GetMapping
    public ResponseEntity<ContribuinteView> consultarContribuinte(@RequestParam String cpf) {
        try {
            DbConnection db = new DbConnection("root", "123");
            System.out.println(cpf);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.OK).body(null);

    }

}
