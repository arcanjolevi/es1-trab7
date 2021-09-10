import { useHistory } from "react-router-dom";
import "../styles/MainMenu.scss";
import "../styles/menu.scss";

export function BensAndDireitos() {
  const his = useHistory();
  return (
    <div className="app-page">
      <div onClick={() => his.goBack()} className="go-back-btn">
        <h3>voltar</h3>
      </div>
      <h1 className="menu-title">Bens e direitos</h1>

      <div className="menu-btn">
        <h2 className="menu-btn-text">Cadastrar bens ou direitos</h2>
      </div>
      <div className="menu-btn">
        <h2 className="menu-btn-text">Consultar bens ou direitos</h2>
      </div>
    </div>
  );
}
