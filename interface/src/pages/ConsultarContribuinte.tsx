import { useHistory } from "react-router-dom";
import "../styles/menu.scss";
import "../styles/form.scss";
import { useState } from "react";
import { api } from "../services/api";
import toast from "react-hot-toast";

export type ContribuinteProps = {
  nome: string;
  telefones: [
    {
      ddd: string;
      ddi: string;
      numero: string;
    }
  ];
  emails: [
    {
      email: string;
    }
  ];
  enderecoResidencial: {
    nroCasa: string;
    complemento: string;
    endereco: {
      cep: string;
      logradouro: {
        nome: string;
        tipoLogradouro: {
          nome: string;
          sigla: string;
        };
      };
      bairro: {
        nome: string;
      };
      cidade: {
        nome: string;
        uf: {
          nome: string;
          sigla: string;
        };
      };
    };
  };
  sobrenome: string;
  nomeSocial: string;
  cpf: string;
  rg: string;
  sexo: string;
};

export function ConsultarContribuinte() {
  const his = useHistory();

  const [cpf, setCpf] = useState("");
  const [contribuinte, setContribuinte] = useState<ContribuinteProps>();

  function hadleConsulta() {
    if (cpf.length > 0) {
      api
        .get("contribuinte", {
          params: {
            cpf,
          },
        })
        .then((r) => {
          if (typeof r.data.nome != undefined) {
            setContribuinte(r.data);
          }
        })
        .catch((e) => {
          toast.error("Verifique os dados da conuslta ou sua conexao");
          console.log(e);
        });
    }
  }

  return (
    <div className="app-page">
      <div onClick={() => his.goBack()} className="go-back-btn">
        <h3>voltar</h3>
      </div>
      <h1 className="menu-title">Consulta de contribuinte</h1>

      <form className="app-form">
        <div className="form-field">
          <p className="label">CPF do contribuinte</p>
          <input
            onChange={(e) => setCpf(e.target.value)}
            placeholder="123.123.123.00"
            type="text"
          />
        </div>

        <div onClick={hadleConsulta} className="form-btn">
          <h3>Consultar</h3>
        </div>

        <div className="form-field">
          <div className="form-field">
            <p className="label">nome</p>
            <input
              disabled
              placeholder={contribuinte && contribuinte.nome}
              type="text"
            />
          </div>
          <div className="form-field">
            <p className="label">sobrenome</p>
            <input
              disabled
              placeholder={contribuinte && contribuinte.sobrenome}
              type="text"
            />
          </div>
          <div className="form-field">
            <p className="label">nome social</p>
            <input
              disabled
              placeholder={contribuinte && contribuinte.nomeSocial}
              type="text"
            />
          </div>
          <div className="form-field">
            <p className="label">Logradouro</p>
            <input
              disabled
              placeholder={
                contribuinte &&
                contribuinte.enderecoResidencial.endereco.logradouro.nome
              }
              type="text"
            />
          </div>
          <div className="form-field">
            <p className="label">Nro endereço</p>
            <input
              disabled
              placeholder={
                contribuinte && contribuinte.enderecoResidencial.nroCasa
              }
              type="text"
            />
          </div>
          <div className="form-field">
            <p className="label">Bairro</p>
            <input
              disabled
              placeholder={
                contribuinte &&
                contribuinte.enderecoResidencial.endereco.bairro.nome
              }
              type="text"
            />
          </div>
          <div className="form-field">
            <p className="label">Cidade</p>
            <input
              disabled
              placeholder={
                contribuinte &&
                contribuinte.enderecoResidencial.endereco.cidade.nome
              }
              type="text"
            />
          </div>
          <div className="form-field">
            <p className="label">Estado</p>
            <input
              disabled
              placeholder={
                contribuinte &&
                contribuinte.enderecoResidencial.endereco.cidade.uf.nome
              }
              type="text"
            />
          </div>
          <div className="form-field">
            <p className="label">Complemento</p>
            <input
              disabled
              placeholder={
                contribuinte && contribuinte.enderecoResidencial.complemento
              }
              type="text"
            />
          </div>
          {contribuinte &&
            contribuinte.emails.map((i) => {
              return (
                <div key={i.email} className="form-field">
                  <p className="label">email</p>
                  <input disabled placeholder={i.email} type="text" />
                </div>
              );
            })}

          {contribuinte &&
            contribuinte.telefones.map((i) => {
              <div key={i.ddi + i.ddd + i.numero} className="telefone-area">
                <div className="form-field">
                  <p className="label">DDI</p>
                  <input disabled placeholder={i.ddi} type="text" />
                </div>
                <div className="form-field">
                  <p className="label">DDD</p>
                  <input disabled placeholder={i.ddd} type="text" />
                </div>

                <div className="form-field">
                  <p className="label">Fone</p>
                  <input disabled placeholder={i.numero} type="text" />
                </div>
              </div>;
            })}

          <div className="form-field">
            <p className="label">CPF</p>
            <input
              disabled
              placeholder={contribuinte && contribuinte.cpf}
              type="text"
            />
          </div>

          <div className="form-field">
            <p className="label">Sexo</p>
            <input
              disabled
              placeholder={contribuinte && contribuinte.sexo}
              type="text"
            />
          </div>

          <div className="form-field">
            <p className="label">RG</p>
            <input
              disabled
              placeholder={contribuinte && contribuinte.rg}
              type="text"
            />
          </div>
        </div>
      </form>
    </div>
  );
}
