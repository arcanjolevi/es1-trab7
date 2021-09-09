package com.main.view;

import java.util.ArrayList;

import com.main.bo.comunicacao.Telefone;
import com.main.bo.comunicacao.Email;
import com.main.bo.pessoa.Dependente;
import com.main.bo.pessoa.TipoDependente;
import com.main.bo.endereco.Bairro;
import com.main.bo.endereco.Cidade;
import com.main.bo.endereco.Endereco;
import com.main.bo.endereco.EnderecoEspecifico;
import com.main.bo.endereco.Logradouro;
import com.main.bo.endereco.TipoLogradouro;
import com.main.bo.endereco.Uf;

public class DependenteView {
    public String id;
    public String nome;
    public String nomeSocial;
    public String sobreNome;
    public ArrayList<TelefoneView> telefones;
    public ArrayList<String> emails;
    public Integer nroCasa;
    public String complemento;
    public String cep;
    public String nomeLogradouro;
    public String tipoLogradouro;
    public String nomeBairro;
    public String nomeCidade;
    public String nomeUf;
    public String siglaUf;
    public String rg;
    public String cpf;
    public Character sexo;
    public String tipoDependente;

    public void setDependente(Dependente dependente) throws Exception {

        if (this.nome != null && this.nomeSocial != null && this.sobreNome != null && this.telefones != null
                && this.emails != null && this.nroCasa != null && this.complemento != null && this.cep != null
                && this.nomeLogradouro != null && this.tipoLogradouro != null && this.nomeBairro != null
                && this.nomeCidade != null && this.nomeUf != null && this.siglaUf != null && this.rg != null
                && this.cpf != null && this.tipoDependente != null) {
            this.nome = dependente.getNome();
            ArrayList<TelefoneView> telefones = new ArrayList<TelefoneView>();

            ArrayList<Telefone> t = dependente.getTelefones();
            for (Telefone tel : t) {
                telefones.add(new TelefoneView(tel.getDdi(), tel.getDdd(), tel.getNumero()));
            }
            this.telefones = telefones;
            ArrayList<String> emails = new ArrayList<String>();
            ArrayList<Email> e = dependente.getEmails();
            for (Email email : e) {
                emails.add(email.getEmail());
            }
            this.emails = emails;
            this.nroCasa = dependente.getEnderecoResidencial().getNroCasa();
            this.complemento = dependente.getEnderecoResidencial().getComplemento();
            this.cep = dependente.getEnderecoResidencial().getEndereco().getCep();
            this.nomeLogradouro = dependente.getEnderecoResidencial().getEndereco().getLogradouro().getNome();
            this.tipoLogradouro = dependente.getEnderecoResidencial().getEndereco().getLogradouro().getTipoLogradouro()
                    .getSigla();
            this.nomeBairro = dependente.getEnderecoResidencial().getEndereco().getBairro().getNome();
            this.nomeCidade = dependente.getEnderecoResidencial().getEndereco().getCidade().getNome();
            this.nomeUf = dependente.getEnderecoResidencial().getEndereco().getCidade().getUf().getNome();
            this.siglaUf = dependente.getEnderecoResidencial().getEndereco().getCidade().getUf().getSigla();
        }

        throw new Exception("Dados invalidos");
    }

    public Dependente renderDependente() {
        ArrayList<Telefone> tells = new ArrayList<Telefone>();
        for (TelefoneView t : this.telefones) {
            tells.add(new Telefone(t.getDdd(), t.getDdi(), t.getNumero()));
        }

        ArrayList<Email> emailsArray = new ArrayList<Email>();
        for (String e : this.emails) {
            emailsArray.add(new Email(e));
        }

        TipoLogradouro tipoLogradouro = new TipoLogradouro(this.nomeLogradouro, this.tipoLogradouro);
        Logradouro logradouro = new Logradouro(this.nomeLogradouro, tipoLogradouro);

        Uf uf = new Uf(this.nome, this.siglaUf);

        Cidade cidade = new Cidade(this.nomeCidade, uf);
        Bairro bairro = new Bairro(this.nomeBairro);
        Endereco endereco = new Endereco(this.cep, logradouro, bairro, cidade);
        EnderecoEspecifico enderecoResidencial = new EnderecoEspecifico(this.nroCasa, this.complemento, endereco);
        TipoDependente tipo = new TipoDependente(this.tipoDependente);
        Dependente dependente = new Dependente(nome, tells, emailsArray, enderecoResidencial, this.sobreNome,
                this.nomeSocial, this.cpf, this.rg, this.sexo, tipo);

        return dependente;
    }

}
