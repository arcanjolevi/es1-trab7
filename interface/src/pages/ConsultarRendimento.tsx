import { useHistory } from "react-router-dom";
import "../styles/menu.scss";
import "../styles/form.scss";

export function ConsultarRendimento() {
  const his = useHistory();
  return (
    <div className="app-page">
      <div onClick={() => his.goBack()} className="go-back-btn">
        <h3>voltar</h3>
      </div>
      <h1 className="menu-title">Consulta de rendimentos</h1>

      <form className="app-form">
        <div className="form-field">
          <p className="label">CPF do contribuinte</p>
          <input placeholder="123.123.123.00" type="text" />
        </div>

        <div className="form-btn">
          <h3>Consultar</h3>
        </div>

        <div className="form-field">
          <p className="label">Rendimento</p>

          <div className="form-field-item">
            <p className="label">CNPJ Empresa</p>
            <input disabled placeholder="00.000.000/0000-00" type="text" />
          </div>

          <div className="form-field-item">
            <p className="label">Nome Empresa</p>
            <input disabled placeholder="Nome empresa" type="text" />
          </div>

          <div className="form-field-item">
            <p className="label">Valor recebido</p>
            <input disabled placeholder="12345" type="text" />
          </div>

          <div className="form-field-item">
            <p className="label">INSS</p>
            <input disabled placeholder="12345" type="text" />
          </div>

          <div className="form-field-item">
            <p className="label">IRRF Pago</p>
            <input disabled placeholder="132465" type="text" />
          </div>

          <div className="form-field-item">
            <p className="label">Valor 13º</p>
            <input disabled placeholder="12345" type="text" />
          </div>
        </div>

        <div className="form-field">
          <p className="label">Rendimento:</p>

          <div className="form-field-item">
            <p className="label">CNPJ Empresa</p>
            <input disabled placeholder="00.000.000/0000-00" type="text" />
          </div>

          <div className="form-field-item">
            <p className="label">Nome Empresa</p>
            <input disabled placeholder="Nome empresa" type="text" />
          </div>

          <div className="form-field-item">
            <p className="label">Valor recebido</p>
            <input disabled placeholder="12345" type="text" />
          </div>

          <div className="form-field-item">
            <p className="label">INSS</p>
            <input disabled placeholder="12345" type="text" />
          </div>

          <div className="form-field-item">
            <p className="label">IRRF Pago</p>
            <input disabled placeholder="132465" type="text" />
          </div>

          <div className="form-field-item">
            <p className="label">Valor 13º</p>
            <input disabled placeholder="12345" type="text" />
          </div>
        </div>
      </form>
    </div>
  );
}
