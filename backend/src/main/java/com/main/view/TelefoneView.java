package com.main.view;

public class TelefoneView {
    private String ddi;
    private String ddd;
    private String numero;

    public TelefoneView() {

    }

    public TelefoneView(String ddd, String ddi, String numero) {
        this.ddd = ddd;
        this.ddi = ddi;
        this.numero = numero;
    }

    public String getDdi() {
        return ddi;
    }

    public void setDdi(String ddi) {
        this.ddi = ddi;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getFormated() {
        return "+" + this.ddi + " (" + this.ddd + ") " + this.numero;
    }

}
