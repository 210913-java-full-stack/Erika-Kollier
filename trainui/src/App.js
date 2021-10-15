import React, {useState, useEffect} from 'react';
import "./App.css";
import Auth from "./Components/Auth";
import Nav from "./Nav";

const App = () => {

  const [accessToken, setAccessToken] = useState(undefined);
  const [currentUser, setCurrentUser] = useState(undefined);

  useEffect(
    () => {
      const token = localStorage.getItem('token')
      if(token) {
        setAccessToken(token)
      }
    }, [] //empty bracket allows code to run continuously
  )

  useEffect(
    () => {
      const currentUserID = localStorage.getItem('userID')
      if(currentUserID){
        setCurrentUser(currentUserID);
      }
    }, []
    )
/**
 * user will remain logged in if page refreshes or if the user leaves the page. Run logic to get the id of the current user
 * @param {*} newToken 
 * @param {*} userID 
 */
    const updateToken = (newToken, userID) => {
      setAccessToken(newToken);
      localStorage.setItem('token', newToken);
      localStorage.setItem('userID', userID)
    }

  const clearToken = () => {
    setAccessToken(undefined)
    setCurrentUser(undefined)
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
