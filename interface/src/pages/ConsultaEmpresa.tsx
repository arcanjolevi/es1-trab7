import { useHistory } from "react-router-dom";
import "../styles/menu.scss";
import "../styles/form.scss";

export function ConsultarEmpresa() {
  const his = useHistory();
  return (
    <div className="app-page">
      <div onClick={() => his.goBack()} className="go-back-btn">
        <h3>voltar</h3>
      </div>
      <h1 className="menu-title">Consulta de empresa</h1>

      <form className="app-form">
        <div className="form-field">
          <p className="label">CNPJ da empresa</p>
          <input placeholder="123.123.123.00" type="text" />
        </div>

        <div className="form-btn">
          <h3>Consultar</h3>
        </div>

        <div className="form-field">
          <div className="form-field">
            <p className="label">nome</p>
            <input disabled placeholder="Empresa tal" type="text" />
          </div>
          <div className="form-field">
            <p className="label">Logradouro</p>
            <input disabled placeholder="Av. Brasil" type="text" />
          </div>
          <div className="form-field">
            <p className="label">Nro endereço</p>
            <input disabled placeholder="345" type="text" />
          </div>
          <div className="form-field">
            <p className="label">Bairro</p>
            <input disabled placeholder="Centro" type="text" />
          </div>
          <div className="form-field">
            <p className="label">Cidade</p>
            <input disabled placeholder="Cascavel" type="text" />
          </div>
          <div className="form-field">
            <p className="label">Estado</p>
            <input disabled placeholder="Parana" type="text" />
          </div>
          <div className="form-field">
            <p className="label">Complemento</p>
            <input disabled placeholder="AP 07" type="text" />
          </div>
          <div className="form-field">
            <p className="label">email</p>
            <input disabled placeholder="fulano@tmail.com" type="text" />
          </div>

          <div className="form-field">
            <p className="label">DDD</p>
            <input disabled placeholder="45" type="text" />
          </div>

          <div className="form-field">
            <p className="label">Fone</p>
            <input disabled placeholder="9342-2343" type="text" />
          </div>

          <div className="form-field">
            <p className="label">CNPJ</p>
            <input disabled placeholder="123.123.123-12" type="text" />
          </div>
        </div>
      </form>
    </div>
  );
}
