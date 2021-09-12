import { useHistory } from "react-router-dom";
import "../styles/menu.scss";
import "../styles/form.scss";
import { api } from "../services/api";
import toast from "react-hot-toast";
import { useState } from "react";
import ReactInputMask from "react-input-mask";

type Telefone = {
  ddi: string;
  ddd: string;
  numero: string;
};

type CadastroContribuinte = {
  nome: string;
  nomeSocial: string;
  sobreNome: string;
  telefones: Telefone[];
  emails: string[];
  nroCasa: string;
  complemento: string;
  cep: string;
  nomeLogradouro: string;
  tipoLogradouro: string;
  nomeBairro: string;
  nomeCidade: string;
  nomeUf: string;
  siglaUf: string;
  cpf: string;
  rg: string;
  sexo: string;
};

export function CadastroContribuinte() {
  const his = useHistory();

  const [nEmails, setNemails] = useState([0]);
  const [nFones, setNfones] = useState([0]);
  const [idFone, setIdfone] = useState(0);
  const [idEmail, setIdEmail] = useState(0);
  const [nome, setnome] = useState("");
  const [nomeSocial, setnomeSocial] = useState("");
  const [sobreNome, setsobrenome] = useState("");
  const [telefones, settelefones] = useState<string[]>([""]);
  const [emails, setemails] = useState<string[]>([]);
  const [nroCasa, setnroCasa] = useState<string>("");
  const [complemento, setcomplemento] = useState<string>("");
  const [cep, setcep] = useState("");
  const [nomeLogradouro, setnomeLogradouro] = useState("");
  const [tipoLogradouro, settipoLogradouro] = useState("");
  const [nomeBairro, setnomeBairro] = useState("");
  const [nomeCidade, setnomeCidade] = useState("");
  const [nomeUf, setnomeUf] = useState("");
  const [siglaUf, setsiglaUf] = useState("");
  const [cpf, setcpf] = useState("");
  const [sexo, setSexo] = useState("");
  const [rg, setRg] = useState("");

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

    const dadosContribuinte: CadastroContribuinte = {
      nome,
      nomeSocial,
      sobreNome,
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
      cpf,
      rg,
      sexo,
    };

    //console.log(dadosContribuinte);

    api
      .post("/contribuinte", dadosContribuinte)
      .then((r) => {
        toast.success("Contribuinte cadastrado!");
        his.goBack();
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
      <h1 className="menu-title">Cadastro de contribuinte</h1>
      <form className="app-form">
        <div className="form-field">
          <p className="label">nome</p>
          <input onChange={(e) => setnome(e.target.value)} type="text" />
        </div>
        <div className="form-field">
          <p className="label">sobrenome</p>
          <input onChange={(e) => setsobrenome(e.target.value)} type="text" />
        </div>
        <div className="form-field">
          <p className="label">nome social</p>
          <input onChange={(e) => setnomeSocial(e.target.value)} type="text" />
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
          <input onChange={(e) => setnroCasa(e.target.value)} type="number" />
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
          <p className="label">CPF</p>
          <ReactInputMask
            mask="999.999.999-99"
            onChange={(e) => setcpf(e.target.value)}
            type="text"
          />
        </div>

        <div className="form-field">
          <p className="label">Sexo (M ou F)</p>
          <ReactInputMask
            mask="a"
            onChange={(e) => setSexo(e.target.value)}
            type="text"
          />
        </div>
        <div className="form-field">
          <p className="label">RG</p>
          <ReactInputMask
            mask="99.999.999-9"
            onChange={(e) => setRg(e.target.value)}
            type="text"
          />
        </div>

        <div onClick={handleCadastro} className="form-btn">
          <h3>Finalizar</h3>
        </div>
      </form>
    </div>
  );
}
