/**
 * User Login function if access token doesn't return, need to pass a grant_Type param
 * When User logs in payload should return with an Access Token
 * Token will be stored in the local storage
 * First create variables to pull information from the DOM to use within fetch. Those variables are within the form element. userData variable contains the userInfo object containing the data the user inputs.
 * fetch from the loginUrl using the POST method and set the headers sets the Content type to expect JSON
 * body is set to the userData variable, which will turn into a JSON string
 * send a response and the result will be JSON
 * the access token is set to the token variable
 * setItem method sets the token to the local storage
 * call the tokenChecker function
 */

function userLogin() {
    let userUsername = document.getElementById("username").value;
    let userPassword = document.getElementById("password").value;

    let userData = {users: {username: userUsername, password: userPassword}};
    let loginUrl = "http://localhost:8080/Erika-Kollier/login";

    fetch(loginUrl, {
        method: "POST",
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
        body: JSON.stringify(userData)
    })
        .then(response => response.json())
        .then(token => {
            localStorage.setItem('Token', Object.values(token).toString())
        })
        .catch((error) => {
            console.log(error);
        })
}



//Determine UserRoles either an if statement or a switch statement
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
