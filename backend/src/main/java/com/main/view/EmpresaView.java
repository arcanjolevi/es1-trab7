package com.main.view;

import java.util.ArrayList;

import com.main.bo.comunicacao.Email;
import com.main.bo.comunicacao.Telefone;
import com.main.bo.pessoa.Empresa;

import com.main.bo.endereco.Bairro;
import com.main.bo.endereco.Cidade;
import com.main.bo.endereco.Endereco;
import com.main.bo.endereco.EnderecoEspecifico;
import com.main.bo.endereco.Logradouro;
import com.main.bo.endereco.TipoLogradouro;
import com.main.bo.endereco.Uf;

import com.main.view.EmpresaView;

public class EmpresaView {
    private String nome;
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
    private String cnpj;

    public Empresa renderEmpresa() throws Exception {

        if (this.nome != null && this.telefones != null && this.emails != null && this.nroCasa != null
                && this.complemento != null && this.cep != null && this.nomeLogradouro != null
                && this.tipoLogradouro != null && this.nomeBairro != null && this.nomeCidade != null
                && this.nomeUf != null && this.siglaUf != null && this.cnpj != null) {
            ArrayList<Telefone> telefones = new ArrayList<Telefone>();
            for (TelefoneView t : this.telefones) {
                telefones.add(new Telefone(t.getDdd(), t.getDdi(), t.getNumero()));
            }
            ArrayList<Email> emails = new ArrayList<Email>();
            for (String t : this.emails) {
                emails.add(new Email(t));
            }

            Uf uf = new Uf(this.nomeUf, this.siglaUf);
            Cidade cidade = new Cidade(this.nomeCidade, uf);
            TipoLogradouro tipoLogradouro = new TipoLogradouro(this.nomeLogradouro, this.tipoLogradouro);
            Logradouro logradouro = new Logradouro(this.tipoLogradouro + ". " + this.nomeLogradouro, tipoLogradouro);
            Bairro bairro = new Bairro(this.nomeBairro);
            Endereco endereco = new Endereco(this.cep, logradouro, bairro, cidade);
            EnderecoEspecifico endExpe = new EnderecoEspecifico(nroCasa, this.complemento, endereco);
            Empresa empresa = new Empresa(this.nome, telefones, emails, endExpe, this.cnpj);
            return empresa;
        }

        throw new Exception("Dados invalidos");
    }

    public void setEmpresa(Empresa empresa) {

        this.nome = empresa.getNome();
        ArrayList<TelefoneView> telefones = new ArrayList<TelefoneView>();
        ArrayList<Telefone> t = empresa.getTelefones();
        for (Telefone tel : t) {
            telefones.add(new TelefoneView(tel.getDdd(), tel.getDdi(), tel.getNumero()));
        }
        this.telefones = telefones;
        ArrayList<String> emails = new ArrayList<String>();
        ArrayList<Email> e = empresa.getEmails();
        for (Email email : e) {
            emails.add(email.getEmail());
        }
        this.emails = emails;
        this.nroCasa = empresa.getEnderecoResidencial().getNroCasa();
        this.complemento = empresa.getEnderecoResidencial().getComplemento();
        this.cep = empresa.getEnderecoResidencial().getEndereco().getCep();
        this.nomeLogradouro = empresa.getEnderecoResidencial().getEndereco().getLogradouro().getNome();
        this.tipoLogradouro = empresa.getEnderecoResidencial().getEndereco().getLogradouro().getTipoLogradouro()
                .getSigla();
        this.nomeBairro = empresa.getEnderecoResidencial().getEndereco().getBairro().getNome();
        this.nomeCidade = empresa.getEnderecoResidencial().getEndereco().getCidade().getNome();
        this.nomeUf = empresa.getEnderecoResidencial().getEndereco().getCidade().getUf().getNome();
        this.siglaUf = empresa.getEnderecoResidencial().getEndereco().getCidade().getUf().getSigla();
        this.cnpj = empresa.getCnpj();
    }

}
