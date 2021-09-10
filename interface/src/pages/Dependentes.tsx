import { useHistory } from "react-router-dom";
import "../styles/MainMenu.scss";
import "../styles/menu.scss";

export function Dependentes() {
  const his = useHistory();
  return (
    <div className="app-page">
      <div onClick={() => his.goBack()} className="go-back-btn">
        <h3>voltar</h3>
      </div>
      <h1 className="menu-title">Dependentes</h1>

      <div className="menu-btn">
        <h2 className="menu-btn-text">Consultar dependente</h2>
      </div>
      <div className="menu-btn">
        <h2 className="menu-btn-text">Adicionar dependente</h2>
      </div>
      <div className="menu-btn">
        <h2 className="menu-btn-text">Remover dependente</h2>
      </div>
    </div>
  );
}
