package com.main.view;

import com.main.bo.bensedireitos.BensEDireitos;
import com.main.bo.bensedireitos.TipoBensEDireitos;

public class BemDireitoView {
    private String nomeBemDireito;
    private String tipoBemDireito;
    private Double valorTotal;
    private String cpfContribuinte;

    public void setRendimento(BensEDireitos value) {
        this.nomeBemDireito = value.getTipoBem().getNome();
        this.tipoBemDireito = value.getTipoBem().getDescricao();
        this.valorTotal = value.getValor();
    }

    public BensEDireitos renderBemDireito() throws Exception {

        if (this.nomeBemDireito != null && this.tipoBemDireito != null && this.valorTotal != null) {

            BensEDireitos value = new BensEDireitos(this.valorTotal,
                    new TipoBensEDireitos(this.nomeBemDireito, this.tipoBemDireito));
            return value;
        }
        throw new Exception("Dados invalidos");
    }
}
