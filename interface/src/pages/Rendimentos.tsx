import { useHistory } from "react-router-dom";
import "../styles/MainMenu.scss";
import "../styles/menu.scss";

export function Rendimentos() {
  const his = useHistory();
  return (
    <div className="app-page">
      <div onClick={() => his.goBack()} className="go-back-btn">
        <h3>voltar</h3>
      </div>
      <h1 className="menu-title">Rendimentos</h1>

      <div
        onClick={() => his.push("/cadastro/rendimento")}
        className="menu-btn"
      >
        <h2 className="menu-btn-text">Cadastrar rendimento</h2>
      </div>
      <div
        onClick={() => his.push("/consulta/rendimento")}
        className="menu-btn"
      >
        <h2 className="menu-btn-text">Consultar rendimento</h2>
      </div>
    </div>
  );
}
