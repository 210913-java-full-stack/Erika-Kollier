/**
 * @author Erika Johnson & Kollier Martin
 * @description
 * When User logs in or registers payload should return with a token
 * Token will be stored in the local storage
 * First create variables to pull information from the DOM to use within fetch. Those variables are within the form element. userData variable contains the userInfo object containing the data the user inputs.
 * fetch from the loginUrl/signinurl using the POST method and set the headers sets the Content type to expect JSON
 * body is set to the userData variable, which will turn into a JSON string
 * send a reponse and the result will be a JSON object
 * setItem method sets the token to the local storage, token is converted into a json object
 */

function userLogin() {
  let userUsername = document.getElementById("username").value;
  let userPassword = document.getElementById("password").value;
  console.log(userUsername, userPassword);

  let userData = { users: { username: userUsername, password: userPassword } };
  console.log(
    `userInfo = ${userData.users.username} ${userData.users.password}`
  );

  let loginUrl = "http://localhost:8080/Erika-Kollier/login";

  fetch(loginUrl, {
    method: "POST",
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
    body: JSON.stringify(userData),
  })
    .then((response) => response.json())
    .then((token) => {
      console.log(Object.values(token));
      console.log(userData);
      localStorage.setItem("Token", Object.values(token).toString());
    })
    .catch((err) => {
      console.log(err);
    });

  /**
   * User Roles when logging in
   */

  if (username.value === "KMART" && password.value === "password") {
    window.location.href = "adminView.html";
  } else {
    window.location.href = "passengerView.html";
  }
}

function userRegister() {
  let userFirstname = document.getElementById("firstname").value;
  let userLastname = document.getElementById("lastname").value;
  let userUsername = document.getElementById("username").value;
  let userPassword = document.getElementById("password").value;
  console.log(userFirstname, userLastname, userUsername, userPassword);

  let userData = {
    users: {
      firstname: userFirstname,
      lastname: userLastname,
      username: userUsername,
      password: userPassword,
    },
  };
  console.log(
    `newUserInfo = ${userData.users.firstname} ${userData.users.lastname} ${userData.users.username} ${userData.users.password}`
  );

  let signinUrl = "http://localhost:8080/Erika-Kollier/register";

  fetch(signinUrl, {
    method: "POST",
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
    body: JSON.stringify(userData),
  })
    .then((response) => response.json())
    .then((token) => {
      console.log(Object.values(token));
      console.log(userData);
      localStorage.setItem("Token", Object.values(token).toString());
    })
    .catch((err) => {
      console.log(err);
    });
  window.location.href = "passengerView.html";
}
