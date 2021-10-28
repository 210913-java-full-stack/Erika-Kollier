/**
  * @author Erika Johnson & Kollier Martin
  * @description
  *  When User logs in or registers payload should return with a token
  * Token will be stored in the local storage
  * First create variables to pull information from the DOM to use within fetch. Those variables are within the form element. userData variable contains the userInfo object containing the data the user inputs.
  * fetch from the loginUrl/signinurl using the POST method and set the headers sets the Content type to expect JSON
  * body is set to the userData variable, which will turn into a JSON string
  * send a reponse and the result will be a JSON object
  * setItem method sets the token to the local storage, token is converted into a json object
  */

window.onload = (function(){populateField()});

function userLogin() {
  let userUsername = document.getElementById("username").value;
  let userPassword = document.getElementById("password").value;

  let userData = {
    username: userUsername,
    password: userPassword
  };

  let loginUrl = "http://localhost:8080/Erika-Kollier/login?username=" + userData.username + "&password=" + userData.password;

  fetch(loginUrl, {
    method: "POST",
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
    body: JSON.stringify(userData),
  })
      .then((response) => response.json())
      .then(function (response) {
        let responseValues = Object.values(response);
        let roleID = responseValues.pop();
        let userExists = responseValues.pop();
        let token = responseValues.pop();

        localStorage.setItem("Token", token);
        localStorage.setItem("Current User", userUsername);

          if (userExists && roleID === 1) {
              window.location.href = "adminView.html";
          } else if (userExists && roleID === 0) {
              window.location.href = "passengerView.html";
          } else {
              alert("Login information is incorrect!");
          }
      })
      .catch((err) => {
        console.log(err);
      });
}

function userRegister() {
  let userFirstname = document.getElementById("firstname").value;
  let userLastname = document.getElementById("lastname").value;
  let userUsername = document.getElementById("username").value;
  let userPassword = document.getElementById("password").value;

  let userData = {
      firstname: userFirstname,
      lastname: userLastname,
      username: userUsername,
      password: userPassword,
  };

  let signinUrl = "http://localhost:8080/Erika-Kollier/register";

  fetch(signinUrl, {
    method: "POST",
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
    body: JSON.stringify(userData),
  })
    .then((response) => response.json())
    .then(function (response){
      let token = Object.values(response).pop();
      if (token === "Failed"){
          alert("Account Creation Failed!");
      } else {
          localStorage.setItem("Token", token);
          window.location.href = "passengerView.html";
      }
    })
    .catch((err) => {
      console.log(err);
    });
}

let username = document.getElementById("username");
let password = document.getElementById("password");

function rememberMe() {
    localStorage.username = username.value;
    localStorage.password = password.value;
}

function populateField() {
    let username = document.getElementById("username");
    let password = document.getElementById("password");

    if (localStorage.getItem("username") !== null) {
        username.value = localStorage.username;
        password.value = localStorage.password;
    }
}
