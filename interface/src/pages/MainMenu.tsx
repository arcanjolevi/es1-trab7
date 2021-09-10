import "../styles/MainMenu.scss";
import "../styles/menu.scss";
import { useHistory } from "react-router-dom";

export function MainMenu() {
  const his = useHistory();
  return (
    <div className="app-page">
      <h1 className="menu-title">Menu Principal</h1>

      <div onClick={() => his.push("/MenuContribuinte")} className="menu-btn">
        <h2 className="menu-btn-text">Área do contribuinte</h2>
      </div>
      <div onClick={() => his.push("/MenuEmpresa")} className="menu-btn">
        <h2 className="menu-btn-text">Área da empresa</h2>
      </div>
    </div>
  );
}
