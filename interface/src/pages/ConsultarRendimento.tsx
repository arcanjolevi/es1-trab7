import { useHistory } from "react-router-dom";
import "../styles/menu.scss";
import "../styles/form.scss";
import { useState } from "react";
import { api } from "../services/api";
import toast from "react-hot-toast";

type Rendimento = {
  valor: string;
  inss: string;
  irrf: string;
  decimoTerceiro: string;
  empresa: {
    nome: string;
    cnpj: string;
  };
};

export function ConsultarRendimento() {
  const his = useHistory();

  const [cpf, setCpf] = useState("");
  const [rendimentos, setRendimentos] = useState<Rendimento[]>([]);

  //Função executada ao clicar no botao "Consultar"
  function hadleConsult() {
    if (cpf.length > 0) {
      api
        .get("rendimento", {
          params: {
            cpf,
          },
        })
        .then((r) => {
          console.log(r);
          if (typeof r.data[0].valor !== "undefined") {
            setRendimentos(r.data);
          }
        })
        .catch((e) => {
          console.log(e);
          toast.error("Ocorreu um problema!! Verifique os dados e sua conexão");
        });
    }
  }

  return (
    <div className="app-page">
      <div onClick={() => his.goBack()} className="go-back-btn">
        <h3>voltar</h3>
      </div>
      <h1 className="menu-title">Consulta de rendimentos</h1>

      <form className="app-form">
        <div className="form-field">
          <p className="label">CPF do contribuinte</p>
          <input
            onChange={(e) => setCpf(e.target.value)}
            placeholder="123.123.123.00"
            type="text"
          />
        </div>

        <div onClick={hadleConsult} className="form-btn">
          <h3>Consultar</h3>
        </div>

        {rendimentos &&
          rendimentos.map((i) => {
            return (
              <div key={i.valor + i.empresa} className="form-field">
                <p className="label">Rendimento:</p>

                <div className="form-field-item">
                  <p className="label">CNPJ Empresa</p>
                  <input disabled placeholder={i.empresa.cnpj} type="text" />
                </div>

                <div className="form-field-item">
                  <p className="label">Nome Empresa</p>
                  <input disabled placeholder={i.empresa.nome} type="text" />
                </div>

                <div className="form-field-item">
                  <p className="label">Valor recebido</p>
                  <input disabled placeholder={i.valor} type="text" />
                </div>

                <div className="form-field-item">
                  <p className="label">INSS</p>
                  <input disabled placeholder={i.inss} type="text" />
                </div>

                <div className="form-field-item">
                  <p className="label">IRRF Pago</p>
                  <input disabled placeholder={i.irrf} type="text" />
                </div>

                <div className="form-field-item">
                  <p className="label">Valor 13º</p>
                  <input disabled placeholder={i.decimoTerceiro} type="text" />
                </div>
              </div>
            );
          })}
      </form>
    </div>
  );
}
