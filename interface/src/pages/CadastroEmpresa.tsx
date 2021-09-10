import { useHistory } from "react-router-dom";
import "../styles/menu.scss";
import "../styles/form.scss";

export function CadastroEmpresa() {
  const his = useHistory();
  return (
    <div className="app-page">
      <div onClick={() => his.goBack()} className="go-back-btn">
        <h3>voltar</h3>
      </div>
      <h1 className="menu-title">Cadastro de Empresa</h1>

      <form className="app-form">
        <div className="form-field">
          <p className="label">nome</p>
          <input type="text" />
        </div>
        <div className="form-field">
          <p className="label">Logradouro</p>
          <input type="text" />
        </div>
        <div className="form-field">
          <p className="label">Nro endereço</p>
          <input type="text" />
        </div>
        <div className="form-field">
          <p className="label">Bairro</p>
          <input type="text" />
        </div>
        <div className="form-field">
          <p className="label">Cidade</p>
          <input type="text" />
        </div>
        <div className="form-field">
          <p className="label">Estado</p>
          <input type="text" />
        </div>
        <div className="form-field">
          <p className="label">Complemento</p>
          <input type="text" />
        </div>
        <div className="form-field">
          <p className="label">email</p>
          <input type="text" />
        </div>

        <div className="form-field-add">
          <p className="label">Adicionar outro email</p>
        </div>

        <div className="form-field">
          <p className="label">DDD</p>
          <input type="text" />
        </div>

        <div className="form-field">
          <p className="label">Fone</p>
          <input type="text" />
        </div>

        <div className="form-field-add">
          <p className="label">Adicionar outro Telefone</p>
        </div>

        <div className="form-field">
          <p className="label">CNPJ</p>
          <input type="text" />
        </div>

        <div className="form-btn">
          <h3>Finalizar</h3>
        </div>
      </form>
    </div>
  );
}
