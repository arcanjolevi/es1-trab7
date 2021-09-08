package com.main.services;
import com.main.bo.pessoa.Rendimento;
import com.main.json.RendimentoJson;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rendimento")
public class WSRendimento {

    @PostMapping
    public Rendimento criarAmbiente(@RequestBody RendimentoJson value) {
        
      
        return null;
    }


    public Rendimento consultarContribuinte(@RequestBody Rendimento value){

        return null;

    }
}
