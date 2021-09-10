import "./styles/global.scss";
import { MainMenu } from "./pages/MainMenu";
import { Rendimentos } from "./pages/Rendimentos";
import { Dependentes } from "./pages/Dependentes";
import { BensAndDireitos } from "./pages/BensAndDireitos";
import { MenuEmpresa } from "./pages/MenuEmpresa";
import { MenuComtribuinte } from "./pages/MenuContribuinte";
import { BrowserRouter, Route } from "react-router-dom";
import { Contribuintes } from "./pages/Contribuinte";
import { CadastroRendimento } from "./pages/CadastroRendimento";
import { ConsultarRendimento } from "./pages/ConsultarRendimento";
import { CadastroContribuinte } from "./pages/CadastrarContribuinte";
import { ConsultarContribuinte } from "./pages/ConsultarContribuinte";
import { CadastroEmpresa } from "./pages/CadastroEmpresa";
import { ConsultarEmpresa } from "./pages/ConsultaEmpresa";
import { Toaster } from "react-hot-toast";

function App() {
  return (
    <div className="App">
      <div>
        <Toaster />
      </div>
      <BrowserRouter>
        <Route path="/" exact component={MainMenu} />
        <Route path="/MenuContribuinte" exact component={MenuComtribuinte} />
        <Route path="/Contribuintes" exact component={Contribuintes} />
        <Route path="/Dependentes" exact component={Dependentes} />
        <Route path="/BensAndDireitos" exact component={BensAndDireitos} />
        <Route path="/Rendimentos" exact component={Rendimentos} />
        <Route path="/MenuEmpresa" exact component={MenuEmpresa} />
        <Route path="/consulta/empresa" exact component={ConsultarEmpresa} />
        <Route path="/cadastro/empresa" exact component={CadastroEmpresa} />
        <Route
          path="/consultar/contribuinte"
          exact
          component={ConsultarContribuinte}
        />
        <Route
          path="/cadastro/contribuinte"
          exact
          component={CadastroContribuinte}
        />
        <Route
          path="/consulta/rendimento"
          exact
          component={ConsultarRendimento}
        />
        <Route
          path="/cadastro/rendimento"
          exact
          component={CadastroRendimento}
        />
      </BrowserRouter>
    </div>
  );
}

export default App;
