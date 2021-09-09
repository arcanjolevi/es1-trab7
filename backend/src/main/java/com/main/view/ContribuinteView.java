package com.main.view;

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

import org.springframework.web.bind.annotation.RequestParam;

public class ContribuinteView {
    private String id;
    private String nome;
    private String nomeSocial;
    private String sobreNome;
    private ArrayList<TelefoneView> telefones;
    private ArrayList<String> emails;
    private Integer nroCasa;
    private String complemento;
    private String cep;
    private String nomeLogradouro;
    private String tipoLogradouro;
    private String nomeBairro;
    private String nomeCidade;
    private String nomeUf;
    private String siglaUf;
    private String rg;
    private String cpf;
    private Character sexo;

    public void setContribuinte(Contribuinte contribuinte) {

        this.nome = contribuinte.getNome();
        this.sobreNome = contribuinte.getSobrenome();
        this.nomeSocial = contribuinte.getNomeSocial();
        this.rg = contribuinte.getRg();
        this.cpf = contribuinte.getCpf();
        ArrayList<TelefoneView> telefones = new ArrayList<TelefoneView>();

        ArrayList<Telefone> t = contribuinte.getTelefones();
        for (Telefone tel : t) {
            telefones.add(new TelefoneView(tel.getDdi(), tel.getDdd(), tel.getNumero()));
        }
        this.telefones = telefones;
        ArrayList<String> emails = new ArrayList<String>();
        ArrayList<Email> e = contribuinte.getEmails();
        for (Email email : e) {
            emails.add(email.getEmail());
        }
        this.emails = emails;
        this.nroCasa = contribuinte.getEnderecoResidencial().getNroCasa();
        this.complemento = contribuinte.getEnderecoResidencial().getComplemento();
        this.cep = contribuinte.getEnderecoResidencial().getEndereco().getCep();
        this.nomeLogradouro = contribuinte.getEnderecoResidencial().getEndereco().getLogradouro().getNome();
        this.tipoLogradouro = contribuinte.getEnderecoResidencial().getEndereco().getLogradouro().getTipoLogradouro()
                .getSigla();
        this.nomeBairro = contribuinte.getEnderecoResidencial().getEndereco().getBairro().getNome();
        this.nomeCidade = contribuinte.getEnderecoResidencial().getEndereco().getCidade().getNome();
        this.nomeUf = contribuinte.getEnderecoResidencial().getEndereco().getCidade().getUf().getNome();
        this.siglaUf = contribuinte.getEnderecoResidencial().getEndereco().getCidade().getUf().getSigla();
    }

    public Contribuinte renderContruibuinte() throws Exception {

        if (this.nome != null && this.nomeSocial != null && this.sobreNome != null && this.telefones != null
                && this.emails != null && this.nroCasa != null && this.complemento != null && this.cep != null
                && this.nomeLogradouro != null && this.tipoLogradouro != null && this.nomeBairro != null
                && this.nomeCidade != null && this.nomeUf != null && this.siglaUf != null && this.rg != null
                && this.cpf != null && this.sexo != null) {

            ArrayList<Telefone> telefones = new ArrayList<Telefone>();
            for (TelefoneView t : this.telefones) {
                telefones.add(new Telefone(t.getDdd(), t.getDdi(), t.getNumero()));
            }

            ArrayList<Email> emails = new ArrayList<Email>();
            for (String e : this.emails) {
                emails.add(new Email(e));
            }

            TipoLogradouro tipoLogradouro = new TipoLogradouro(this.nomeLogradouro, this.tipoLogradouro);
            Logradouro logradouro = new Logradouro(this.nomeLogradouro, tipoLogradouro);

            Uf uf = new Uf(this.nome, this.siglaUf);

            Cidade cidade = new Cidade(this.nomeCidade, uf);
            Bairro bairro = new Bairro(this.nomeBairro);
            Endereco endereco = new Endereco(this.cep, logradouro, bairro, cidade);
            EnderecoEspecifico enderecoResidencial = new EnderecoEspecifico(this.nroCasa, this.complemento, endereco);
            ArrayList<BensEDireitos> bens = new ArrayList<BensEDireitos>();
            ArrayList<Rendimento> rendimentos = new ArrayList<Rendimento>();
            Contribuinte con = new Contribuinte(this.nome, telefones, emails, enderecoResidencial, this.sobreNome,
                    this.nomeSocial, this.cpf, this.rg, this.sexo, bens, rendimentos);

            return con;
        }

        throw new Exception("Dados invalidos");
    }
}
