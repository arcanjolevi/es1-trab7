package com.main.services;

import java.sql.Date;
import java.text.DateFormat;
import java.util.ArrayList;

import com.main.bo.bensedireitos.BensEDireitos;
import com.main.bo.comunicacao.Email;
import com.main.bo.comunicacao.Telefone;
import com.main.bo.endereco.Bairro;
import com.main.bo.endereco.Cidade;
import com.main.bo.endereco.Endereco;
import com.main.bo.endereco.EnderecoEspecifico;
import com.main.bo.endereco.Logradouro;
import com.main.bo.endereco.TipoLogradouro;
import com.main.bo.endereco.Uf;
import com.main.bo.pessoa.Contribuinte;
import com.main.bo.pessoa.Rendimento;
import com.main.view.ContribuinteView;
import com.main.view.TelefoneView;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contribuinte")
public class WSContribuinte {

    @PostMapping
    public ResponseEntity<ContribuinteView> criarContribuinte(@RequestBody ContribuinteView contriView) {
        try {
            Contribuinte contribuinte = this.renderContruibuinte(contriView);
            ContribuinteView returnView = this.renderContribuinteView(contribuinte);
            return ResponseEntity.status(HttpStatus.OK).body(returnView);
        } catch (Exception e) {
            System.out.println("@post /contribuinte - Dados invalidos - Erro 400 - DAD REQUEST");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<ContribuinteView> consultarContribuinte(@RequestBody ContribuinteView contriView) {
        String cpf = contriView.getCpf();
        if (cpf != null) {
            // Chama lucas

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return null;

    }

    public Contribuinte renderContruibuinte(ContribuinteView contriView) throws Exception {
        if (contriView.getId() != null && contriView.getNome() != null && contriView.getNomeSocial() != null
                && contriView.getSobreNome() != null && contriView.getTelefones() != null
                && contriView.getEmails() != null && contriView.getNroCasa() != null
                && contriView.getComplemento() != null && contriView.getCep() != null
                && contriView.getNomeLogradouro() != null && contriView.getTipoLogradouro() != null
                && contriView.getNomeBairro() != null && contriView.getNomeCidade() != null
                && contriView.getNomeUf() != null && contriView.getSiglaUf() != null && contriView.getRg() != null
                && contriView.getCpf() != null && contriView.getSexo() != null) {

            ArrayList<Telefone> telefones = new ArrayList<Telefone>();
            for (TelefoneView t : contriView.getTelefones()) {
                telefones.add(new Telefone(t.getDdd(), t.getDdi(), t.getNumero()));
            }

            ArrayList<Email> emails = new ArrayList<Email>();
            for (String e : contriView.getEmails()) {
                emails.add(new Email(e));
            }

            TipoLogradouro tipoLogradouro = new TipoLogradouro(contriView.getNomeLogradouro(),
                    contriView.getTipoLogradouro());
            Logradouro logradouro = new Logradouro(contriView.getNomeLogradouro(), tipoLogradouro);

            Uf uf = new Uf(contriView.getNome(), contriView.getSiglaUf());

            Cidade cidade = new Cidade(contriView.getNomeCidade(), uf);
            Bairro bairro = new Bairro(contriView.getNomeBairro());
            Endereco endereco = new Endereco(contriView.getCep(), logradouro, bairro, cidade);
            EnderecoEspecifico enderecoResidencial = new EnderecoEspecifico(contriView.getNroCasa(),
                    contriView.getComplemento(), endereco);
            ArrayList<BensEDireitos> bens = new ArrayList<BensEDireitos>();
            ArrayList<Rendimento> rendimentos = new ArrayList<Rendimento>();
            Contribuinte con = new Contribuinte(contriView.getNome(), telefones, emails, enderecoResidencial,
                    contriView.getSobreNome(), contriView.getNomeSocial(), contriView.getCpf(), contriView.getRg(),
                    contriView.getSexo(), bens, rendimentos);
            return con;
        }

        throw new Exception("Dados invalidos");

    }

    public ContribuinteView renderContribuinteView(Contribuinte contribuinte) {
        ContribuinteView contribuinteView = new ContribuinteView();

        contribuinteView.setNome(contribuinte.getNome());
        ArrayList<TelefoneView> telefones = new ArrayList<TelefoneView>();

        ArrayList<Telefone> t = contribuinte.getTelefones();
        for (Telefone tel : t) {
            telefones.add(new TelefoneView(tel.getDdi(), tel.getDdd(), tel.getNumero()));
        }
        contribuinteView.setTelefones(telefones);
        ArrayList<String> emails = new ArrayList<String>();
        ArrayList<Email> e = contribuinte.getEmails();
        for (Email email : e) {
            emails.add(email.getEmail());
        }
        contribuinteView.setEmails(emails);
        contribuinteView.setNroCasa(contribuinte.getEnderecoResidencial().getNroCasa());
        contribuinteView.setComplemento(contribuinte.getEnderecoResidencial().getComplemento());
        contribuinteView.setCep(contribuinte.getEnderecoResidencial().getEndereco().getCep());
        contribuinteView
                .setNomeLogradouro(contribuinte.getEnderecoResidencial().getEndereco().getLogradouro().getNome());
        contribuinteView.setTipoLogradouro(
                contribuinte.getEnderecoResidencial().getEndereco().getLogradouro().getTipoLogradouro().getSigla());
        contribuinteView.setNomeBairro(contribuinte.getEnderecoResidencial().getEndereco().getBairro().getNome());
        contribuinteView.setNomeCidade(contribuinte.getEnderecoResidencial().getEndereco().getCidade().getNome());
        contribuinteView.setNomeUf(contribuinte.getEnderecoResidencial().getEndereco().getCidade().getUf().getNome());
        contribuinteView.setSiglaUf(contribuinte.getEnderecoResidencial().getEndereco().getCidade().getUf().getSigla());

        return contribuinteView;
    }

}
