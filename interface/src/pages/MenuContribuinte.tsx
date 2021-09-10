import { useHistory } from "react-router-dom";
import "../styles/MainMenu.scss";
import "../styles/menu.scss";

export function MenuComtribuinte() {
  const his = useHistory();
  return (
    <div className="app-page">
      <div onClick={() => his.goBack()} className="go-back-btn">
        <h3>voltar</h3>
      </div>
      <h1 className="menu-title">Menu Contribuinte</h1>

      <div onClick={() => his.push("/Contribuintes")} className="menu-btn">
        <h2 className="menu-btn-text">Contribuintes</h2>
      </div>
      <div onClick={() => his.push("/Rendimentos")} className="menu-btn">
        <h2 className="menu-btn-text">Rendimentos</h2>
      </div>
      <div onClick={() => his.push("/Dependentes")} className="menu-btn">
        <h2 className="menu-btn-text">Dependentes</h2>
      </div>
      <div onClick={() => his.push("/BensAndDireitos")} className="menu-btn">
        <h2 className="menu-btn-text">Bens e direitos</h2>
      </div>
    </div>
  );
}
