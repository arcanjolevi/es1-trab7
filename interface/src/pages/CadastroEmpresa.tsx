import { useHistory } from "react-router-dom";
import "../styles/menu.scss";
import "../styles/form.scss";
import { useEffect, useRef, useState } from "react";
import ReactInputMask from "react-input-mask";
import { api } from "../services/api";
import toast from "react-hot-toast";

type CadastroEmpresa = {
  nome: string;
  telefones: [
    {
      ddi: string;
      ddd: string;
      numero: string;
    }
  ];
  emails: string[];
  nroCasa: number;
  complemento: string;
  cep: string;
  nomeLogradouro: string;
  tipoLogradouro: string;
  nomeBairro: string;
  nomeCidade: string;
  nomeUf: string;
  siglaUf: string;
  cnpj: string;
};

type Telefone = {
  ddi: string;
  ddd: string;
  numero: string;
};

export function CadastroEmpresa() {
  const his = useHistory();

  const [nEmails, setNemails] = useState([0]);
  const [nFones, setNfones] = useState([0]);
  const [idFone, setIdfone] = useState(0);
  const [idEmail, setIdEmail] = useState(0);
  const [nome, setnome] = useState("");
  const [telefones, settelefones] = useState<string[]>([""]);
  const [emails, setemails] = useState<string[]>([]);
  const [nroCasa, setnroCasa] = useState<number>();
  const [complemento, setcomplemento] = useState<string>();
  const [cep, setcep] = useState("");
  const [nomeLogradouro, setnomeLogradouro] = useState("");
  const [tipoLogradouro, settipoLogradouro] = useState("");
  const [nomeBairro, setnomeBairro] = useState("");
  const [nomeCidade, setnomeCidade] = useState("");
  const [nomeUf, setnomeUf] = useState("");
  const [siglaUf, setsiglaUf] = useState("");
  const [cnpj, setcnpj] = useState("");

  function handleChangeFone(id: number, text: string) {
    let novo: string[] = [];
    for (let i = 0; i < nFones.length; i++) {
      if (id === i) {
        novo.push(text);
      } else {
        novo.push(telefones[i]);
      }
    }

    settelefones(novo);
  }

  function handleChangeEmail(id: number, text: string) {
    let novo: string[] = [];
    for (let i = 0; i < nEmails.length; i++) {
      if (id === i) {
        novo.push(text);
      } else {
        novo.push(emails[i]);
      }
    }

    setemails(novo);
  }

  function handleCadastro() {
    const fonesObj = telefones.filter((i) => i.length === 16);

    const fones = fonesObj.map((i) => {
      return {
        ddi: i.substring(0, 3),
        ddd: i.substring(4, 6),
        numero: i.substring(6),
      };
    });

    const dadosEmpresa = {
      nome,
      telefones: fones,
      emails,
      nroCasa,
      complemento,
      cep,
      nomeLogradouro,
      tipoLogradouro,
      nomeBairro,
      nomeCidade,
      nomeUf,
      siglaUf,
      cnpj,
    };

    api
      .post("/empresa", dadosEmpresa)
      .then((r) => {
        toast.success("Empresa cadastrada!");
      })
      .catch((e) => {
        toast.error("Erro no cadastro, verifique seus dados");
        console.log(e);
      });
  }

  return (
    <div className="app-page">
      <div onClick={() => his.goBack()} className="go-back-btn">
        <h3>voltar</h3>
      </div>
      <h1 className="menu-title">Cadastro de Empresa</h1>

      <form className="app-form">
        <div className="form-field">
          <p className="label">nome</p>
          <input onChange={(e) => setnome(e.target.value)} type="text" />
        </div>
        <div className="form-field">
          <p className="label">Tipo Logradouro(Av, Rua...)</p>
          <input
            onChange={(e) => settipoLogradouro(e.target.value)}
            type="text"
          />
        </div>
        <div className="form-field">
          <p className="label">Logradouro</p>
          <input
            onChange={(e) => setnomeLogradouro(e.target.value)}
            type="text"
          />
        </div>
        <div className="form-field">
          <p className="label">Nro endereço</p>
          <input
            onChange={(e) => setnroCasa(parseInt(e.target.value))}
            type="number"
          />
        </div>
        <div className="form-field">
          <p className="label">Bairro</p>
          <input onChange={(e) => setnomeBairro(e.target.value)} type="text" />
        </div>
        <div className="form-field">
          <p className="label">Cidade</p>
          <input onChange={(e) => setnomeCidade(e.target.value)} type="text" />
        </div>
        <div className="form-field">
          <p className="label">Estado</p>
          <input onChange={(e) => setnomeUf(e.target.value)} type="text" />
        </div>
        <div className="form-field">
          <p className="label">Sigla estado (PR, SP...)</p>
          <input onChange={(e) => setsiglaUf(e.target.value)} type="text" />
        </div>
        <div className="form-field">
          <p className="label">Cep</p>
          <input onChange={(e) => setcep(e.target.value)} type="number" />
        </div>
        <div className="form-field">
          <p className="label">Complemento</p>
          <input onChange={(e) => setcomplemento(e.target.value)} type="text" />
        </div>

        {nEmails.map((i) => {
          return (
            <div className="email-area">
              <div className="form-field">
                <p className="label">email</p>
                <input
                  onChange={(e) => handleChangeEmail(i, e.target.value)}
                  type="text"
                />
              </div>
            </div>
          );
        })}

        <div
          onClick={() => {
            setNemails([...nEmails, idEmail + 1]);
            setIdEmail(idEmail + 1);
          }}
          className="form-field-add"
        >
          <p className="label">Adicionar outro email</p>
        </div>

        {nFones.map((i) => {
          return (
            <div className="telefone-area">
              <div className="form-field">
                <p className="label">Fone (ddi+dd+Numero)</p>
                <ReactInputMask
                  onChange={(e) => handleChangeFone(i, e.target.value)}
                  mask="999 99 9999-9999"
                />
              </div>
            </div>
          );
        })}

        <div
          onClick={() => {
            setNfones([...nFones, idFone + 1]);
            setIdfone(idFone + 1);
          }}
          className="form-field-add"
        >
          <p className="label">Adicionar outro Telefone</p>
        </div>

        <div className="form-field">
          <p className="label">CNPJ</p>
          <input onChange={(e) => setcnpj(e.target.value)} type="text" />
        </div>

        <div onClick={handleCadastro} className="form-btn">
          <h3>Finalizar</h3>
        </div>
      </form>
    </div>
  );
}
