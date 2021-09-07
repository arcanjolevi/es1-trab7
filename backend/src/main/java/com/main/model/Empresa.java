package com.main.model;

import java.util.ArrayList;

public class Empresa extends Classe {
    private String name;
    public String cnpj;
    public ArrayList<String> vetor = new ArrayList<String>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

}
