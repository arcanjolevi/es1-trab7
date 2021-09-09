package com.main.view;

import com.main.bo.pessoa.Rendimento;

public class RendimentoView {
    public String id;
    public String cpf;
    private Double valor;
    private Double inss;
    private Double irrf;
    private Double decimoTerceiro;
    private String cnpj;

    public void setRendimento(Rendimento rend) {
        this.valor = rend.getValor();
        this.inss = rend.getInss();
        this.irrf = rend.getIffr();
        this.decimoTerceiro = rend.getDecimoTerceiro();
        this.cnpj = rend.getEmpresa().getCnpj();
    }

    public Rendimento renderRendimento() {
        return new Rendimento(this.valor, this.inss, this.irrf, this.decimoTerceiro, null);
    }
}
