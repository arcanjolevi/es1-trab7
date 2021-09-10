import { useHistory } from "react-router-dom";
import "../styles/MainMenu.scss";
import "../styles/menu.scss";

export function MenuEmpresa() {
  const his = useHistory();
  return (
    <div className="app-page">
      <div onClick={() => his.goBack()} className="go-back-btn">
        <h3>voltar</h3>
      </div>
      <h1 className="menu-title">Menu Empresa</h1>

      <div onClick={() => his.push("/cadastro/empresa")} className="menu-btn">
        <h2 className="menu-btn-text">Cadastrar empresa</h2>
      </div>
      <div onClick={() => his.push("/consulta/empresa")} className="menu-btn">
        <h2 className="menu-btn-text">Consultar empresa</h2>
      </div>
    </div>
  );
}
