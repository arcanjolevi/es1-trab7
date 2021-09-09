package com.main.services;

import java.util.ArrayList;

import com.main.bo.comunicacao.Email;
import com.main.bo.comunicacao.Telefone;
import com.main.bo.endereco.Bairro;
import com.main.bo.endereco.Cidade;
import com.main.bo.endereco.Endereco;
import com.main.bo.endereco.EnderecoEspecifico;
import com.main.bo.endereco.Logradouro;
import com.main.bo.endereco.TipoLogradouro;
import com.main.bo.endereco.Uf;
import com.main.bo.pessoa.Empresa;
import com.main.view.EmpresaView;
import com.main.view.TelefoneView;

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
            Empresa empresa = this.renderEmrpesa(empresaView);
            EmpresaView returnView = this.renderEmpresaView(empresa);
            return ResponseEntity.status(HttpStatus.OK).body(returnView);
        } catch (Exception e) {
            System.out.println("@post /empresa - Dados invalidos - Erro 400 - DAD REQUEST");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<EmpresaView> consultarEmpresa(@RequestBody EmpresaView viewEmpresa) {

        String cnpj = viewEmpresa.getCnpj();
        if (cnpj != null) {
            // Chama lucas
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return null;
    }

    public EmpresaView renderEmpresaView(Empresa empresa) {
        EmpresaView empresaView = new EmpresaView();
        empresaView.setNome(empresa.getNome());
        ArrayList<TelefoneView> telefones = new ArrayList<TelefoneView>();
        ArrayList<Telefone> t = empresa.getTelefones();
        for (Telefone tel : t) {
            telefones.add(new TelefoneView(tel.getDdi(), tel.getDdd(), tel.getNumero()));
        }
        empresaView.setTelefones(telefones);
        ArrayList<String> emails = new ArrayList<String>();
        ArrayList<Email> e = empresa.getEmails();
        for (Email email : e) {
            emails.add(email.getEmail());
        }
        empresaView.setEmails(emails);
        empresaView.setNroCasa(empresa.getEnderecoResidencial().getNroCasa());
        empresaView.setComplemento(empresa.getEnderecoResidencial().getComplemento());
        empresaView.setCep(empresa.getEnderecoResidencial().getEndereco().getCep());
        empresaView.setNomeLogradouro(empresa.getEnderecoResidencial().getEndereco().getLogradouro().getNome());
        empresaView.setTipoLogradouro(
                empresa.getEnderecoResidencial().getEndereco().getLogradouro().getTipoLogradouro().getSigla());
        empresaView.setNomeBairro(empresa.getEnderecoResidencial().getEndereco().getBairro().getNome());
        empresaView.setNomeCidade(empresa.getEnderecoResidencial().getEndereco().getCidade().getNome());
        empresaView.setNomeUf(empresa.getEnderecoResidencial().getEndereco().getCidade().getUf().getNome());
        empresaView.setSiglaUf(empresa.getEnderecoResidencial().getEndereco().getCidade().getUf().getSigla());
        empresaView.setCnpj(empresa.getCnpj());

        return empresaView;
    }

    public Empresa renderEmrpesa(EmpresaView viewEmpresa) throws Exception {
        if (viewEmpresa.getNome() != null && viewEmpresa.getTelefones() != null && viewEmpresa.getEmails() != null
                && viewEmpresa.getNroCasa() != null && viewEmpresa.getComplemento() != null
                && viewEmpresa.getCep() != null && viewEmpresa.getNomeLogradouro() != null
                && viewEmpresa.getTipoLogradouro() != null && viewEmpresa.getNomeBairro() != null
                && viewEmpresa.getNomeCidade() != null && viewEmpresa.getNomeUf() != null
                && viewEmpresa.getSiglaUf() != null && viewEmpresa.getCnpj() != null) {
            ArrayList<Telefone> telefones = new ArrayList<Telefone>();
            for (TelefoneView t : viewEmpresa.getTelefones()) {
                telefones.add(new Telefone(t.getDdd(), t.getDdi(), t.getNumero()));
            }
            ArrayList<Email> emails = new ArrayList<Email>();
            for (String t : viewEmpresa.getEmails()) {
                emails.add(new Email(t));
            }

            Uf uf = new Uf(viewEmpresa.getNomeUf(), viewEmpresa.getSiglaUf());
            Cidade cidade = new Cidade(viewEmpresa.getNomeCidade(), uf);
            TipoLogradouro tipoLogradouro = new TipoLogradouro(viewEmpresa.getNomeLogradouro(),
                    viewEmpresa.getTipoLogradouro());
            Logradouro logradouro = new Logradouro(
                    viewEmpresa.getTipoLogradouro() + ". " + viewEmpresa.getNomeLogradouro(), tipoLogradouro);
            Bairro bairro = new Bairro(viewEmpresa.getNomeBairro());
            Endereco endereco = new Endereco(viewEmpresa.getCep(), logradouro, bairro, cidade);
            EnderecoEspecifico endExpe = new EnderecoEspecifico(viewEmpresa.getNroCasa(), viewEmpresa.getComplemento(),
                    endereco);
            Empresa empresa = new Empresa(viewEmpresa.getNome(), telefones, emails, endExpe, viewEmpresa.getCnpj());
            return empresa;
        }

        throw new Exception("Dados invalidos");
    }
}
