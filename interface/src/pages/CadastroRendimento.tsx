import { useHistory } from "react-router-dom";
import "../styles/menu.scss";
import "../styles/form.scss";

export function CadastroRendimento() {
  const his = useHistory();
  return (
    <div className="app-page">
      <div onClick={() => his.goBack()} className="go-back-btn">
        <h3>voltar</h3>
      </div>
      <h1 className="menu-title">Cadastro de rendimento</h1>

      <form className="app-form">
        <div className="form-field">
          <p className="label">CPF do contribuinte</p>
          <input type="text" />
        </div>

        <div className="form-field">
          <p className="label">Rendimentos:</p>

          <div className="form-field-item">
            <p className="label">CNPJ Empresa</p>
            <input type="text" />
          </div>

          <div className="form-field-item">
            <p className="label">Nome Empresa</p>
            <input type="text" />
          </div>

          <div className="form-field-item">
            <p className="label">Valor recebido</p>
            <input type="text" />
          </div>

          <div className="form-field-item">
            <p className="label">INSS</p>
            <input type="text" />
          </div>

          <div className="form-field-item">
            <p className="label">IRRF Pago</p>
            <input type="text" />
          </div>

          <div className="form-field-item">
            <p className="label">Valor 13º</p>
            <input type="text" />
          </div>

          <div className="form-field-add">
            <p className="label">Adicionar outro rendimento</p>
          </div>
        </div>

        <div className="form-btn">
          <h3>Finalizar</h3>
        </div>
      </form>
    </div>
  );
}
