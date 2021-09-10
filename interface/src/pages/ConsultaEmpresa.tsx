import { useHistory } from "react-router-dom";
import "../styles/menu.scss";
import "../styles/form.scss";
import { useEffect, useState } from "react";
import { api } from "../services/api";
import toast from "react-hot-toast";

export type EmpresaProps = {
  cnpj: string;
  emails: {
    email: string;
  }[];
  nome: string;
  enderecoResidencial: {
    complemento: string;
    nroCasa: string;
    endereco: {
      bairro: {
        nome: string;
      };
      cep: string;
      cidade: {
        nome: string;
        uf: {
          nome: string;
          sigla: string;
        };
      };
      logradouro: {
        nome: string;
        tipoLogradouro: {
          nome: string;
          sigla: string;
        };
      };
    };
  };
  telefones: {
    ddd: string;
    ddi: string;
    numero: string;
  }[];
};

export function ConsultarEmpresa() {
  const his = useHistory();

  const [cnpj, setCnpj] = useState("");
  const [empresa, setEmpresa] = useState<EmpresaProps>();

  function handleConsult() {
    if (cnpj.length > 0) {
      api
        .get("/empresa", {
          params: {
            cnpj: cnpj,
          },
        })
        .then((r) => {
          if (typeof r.data.nome != "undefined") {
            setEmpresa(r.data);
          } else {
            toast.error("Dados invalidos");
          }
        })
        .catch((e) => {
          console.log(e);
          toast.error("Dados invalidos ou falta de conexao");
        });
    }
  }

  return (
    <div className="app-page">
      <div onClick={() => his.goBack()} className="go-back-btn">
        <h3>voltar</h3>
      </div>
      <h1 className="menu-title">Consulta de empresa</h1>

      <form className="app-form">
        <div className="form-field">
          <p className="label">CNPJ da empresa</p>
          <input
            onChange={(e) => setCnpj(e.target.value)}
            placeholder="123.123.123.00"
            type="text"
          />
        </div>

        <div onClick={handleConsult} className="form-btn">
          <h3>Consultar</h3>
        </div>
        {empresa && (
          <div className="form-field">
            <div className="form-field">
              <p className="label">nome</p>
              <input
                disabled
                placeholder={empresa && empresa.nome}
                type="text"
              />
            </div>
            <div className="form-field">
              <p className="label">Logradouro</p>
              <input
                disabled
                placeholder={
                  empresa &&
                  empresa.enderecoResidencial.endereco.logradouro.nome
                }
                type="text"
              />
            </div>
            <div className="form-field">
              <p className="label">Nro endereço</p>
              <input
                disabled
                placeholder={empresa && empresa.enderecoResidencial.nroCasa}
                type="text"
              />
            </div>
            <div className="form-field">
              <p className="label">Bairro</p>
              <input
                disabled
                placeholder={
                  empresa && empresa.enderecoResidencial.endereco.bairro.nome
                }
                type="text"
              />
            </div>
            <div className="form-field">
              <p className="label">Cidade</p>
              <input
                disabled
                placeholder={
                  empresa && empresa.enderecoResidencial.endereco.cidade.nome
                }
                type="text"
              />
            </div>
            <div className="form-field">
              <p className="label">Estado</p>
              <input
                disabled
                placeholder={
                  empresa && empresa.enderecoResidencial.endereco.cidade.uf.nome
                }
                type="text"
              />
            </div>
            <div className="form-field">
              <p className="label">Complemento</p>
              <input
                disabled
                placeholder={empresa && empresa.enderecoResidencial.complemento}
                type="text"
              />
            </div>
            {empresa?.emails &&
              empresa.emails.map((i) => {
                return (
                  <div key={i.email} className="form-field">
                    <p className="label">email</p>
                    <input disabled placeholder={i.email} type="text" />
                  </div>
                );
              })}

            {empresa &&
              empresa.telefones.map((i) => {
                return (
                  <>
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
                  </>
                );
              })}

            <div className="form-field">
              <p className="label">CNPJ</p>
              <input
                disabled
                placeholder={empresa && empresa.cnpj}
                type="text"
              />
            </div>
          </div>
        )}
      </form>
    </div>
  );
}
