     /**
    * User Login function if access token doesn't return, need to pass a grant_Type param
    * When User logs in payload should return with an Access Token
    * Token will be stored in the local storage
    * First create variables to pull information from the DOM to use within fetch. Those vairables are within the form element. userData variable containes the userInfo object containing the data the user inputs.
     * fetch from the loginUrl using the POST method and set the headers sets the Content type to expect JSON
     * body is set to the userData variable, which will turn into a JSON string
     * sned a reponse and the result will be JSON
     * the access token is set to the token varaible
     * setItem method sets the token to the local storage
     * call the tokenChecker function
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


    let response = fetch(loginUrl, {
      method: "POST",
      headers: {
        "Content-Type": "application/json;charset=utf-8",
      },
      body: JSON.stringify(userData)
    }).then((response) => {
      let result = response.json();
      console.log(response.accessToken)
      let token = response.accessToken
      localStorage.setItem('AccessToken', token)
      console.log(userData)
    }).catch((error) => {
      console.log(error);
    })

    //Determine UserRoles either an if statment or a switch statment

    if(username === userUsername && password === userPassword){
      window.location.href = "adminView.html"
    } else {
      window.location.href = "passengerView.html"
    }
  }

  //another way to write fetch method
 //   fetch(loginUrl, {
  //   method: "POST",
  //   headers: {
  //     "Content-Type": "application/json;charset=utf-8",
  //   },
  //   body: JSON.stringify(userData)
  // })
  //   .then(response => response.json())
  //   .then(function (response) {
  //       localStorage.setItem('AccessToken', token);
        
  //   })
  //   .catch((err) => {
  //       console.log(err)
  //  
