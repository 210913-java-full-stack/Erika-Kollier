import "./App.css";
import Auth from "./Components/Auth";
import Nav from "./Nav";

const App = () => {
  const clearToken = () => {
    localStorage.clear();
  };
  return (
    <div className="App">
      <header className="App-header">
        ?<Auth /> :
        <div className="main">
          <Nav clearToken={clearToken} />
          </div>
      </header>
    </div>
  );
};

export default App;
