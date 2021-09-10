import { useHistory } from "react-router-dom";
import "../styles/MainMenu.scss";
import "../styles/menu.scss";

export function Contribuintes() {
  const his = useHistory();
  return (
    <div className="app-page">
      <div onClick={() => his.goBack()} className="go-back-btn">
        <h3>voltar</h3>
      </div>
      <h1 className="menu-title">Contribuintes</h1>

      <div
        onClick={() => his.push("/cadastro/contribuinte")}
        className="menu-btn"
      >
        <h2 className="menu-btn-text">Cadastrar contribuinte</h2>
      </div>
      <div
        onClick={() => his.push("/consultar/contribuinte")}
        className="menu-btn"
      >
        <h2 className="menu-btn-text">Consultar contribuinte</h2>
      </div>
    </div>
  );
}
