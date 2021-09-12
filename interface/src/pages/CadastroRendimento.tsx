import { useHistory } from "react-router-dom";
import "../styles/menu.scss";
import "../styles/form.scss";
import { useState } from "react";
import { api } from "../services/api";
import toast from "react-hot-toast";

type Rendimento = {
  cpf: string;
  valor: string;
  inss: string;
  irrf: string;
  decimoTerceiro: string;
  cnpj: string;
};

export function CadastroRendimento() {
  const his = useHistory();

  const [cnpj, setcnpj] = useState("");
  const [cpf, setcpf] = useState("");
  const [decimoTerceiro, setdecimoTerceiro] = useState("");
  const [inss, setinss] = useState("");
  const [irrf, setirrf] = useState("");
  const [valor, setvalor] = useState("");

  function handleCadastro() {
    const rendimento: Rendimento = {
      cnpj,
      cpf,
      decimoTerceiro,
      inss,
      irrf,
      valor,
    };

    api
      .post("rendimento", rendimento)
      .then((r) => {
        toast.success("Rendimento cadastrado!");
        his.goBack();
      })
      .catch((e) => {
        toast.error("Erro no cadastro, tente novamente");
      });
  }
  return (
    <div className="app-page">
      <div onClick={() => his.goBack()} className="go-back-btn">
        <h3>voltar</h3>
      </div>
      <h1 className="menu-title">Cadastro de rendimento</h1>

      <form className="app-form">
        <div className="form-field">
          <p className="label">CPF do contribuinte</p>
          <input onChange={(e) => setcpf(e.target.value)} type="text" />
        </div>

        <div className="form-field">
          <p className="label">Rendimentos:</p>

          <div className="form-field-item">
            <p className="label">CNPJ Empresa</p>
            <input onChange={(e) => setcnpj(e.target.value)} type="number" />
          </div>

          <div className="form-field-item">
            <p className="label">Valor recebido</p>
            <input onChange={(e) => setvalor(e.target.value)} type="number" />
          </div>

          <div className="form-field-item">
            <p className="label">INSS</p>
            <input onChange={(e) => setinss(e.target.value)} type="number" />
          </div>

          <div className="form-field-item">
            <p className="label">IRRF Pago</p>
            <input onChange={(e) => setirrf(e.target.value)} type="number" />
          </div>

          <div className="form-field-item">
            <p className="label">Valor 13º</p>
            <input
              onChange={(e) => setdecimoTerceiro(e.target.value)}
              type="number"
            />
          </div>

          <div onClick={handleCadastro} className="form-btn">
            <h3>Finalizar</h3>
          </div>
        </div>
      </form>
    </div>
  );
}
