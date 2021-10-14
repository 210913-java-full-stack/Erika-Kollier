import React, { useState } from "react";
import axios from "axios";
import "./Auth.css";
import { API_URL } from "../Environment";
import Button from "@material-ui/core/Button";
import LockOpenIcon from "@material-ui/icons/LockOpen";
import TextField from "@material-ui/core/TextField";
import "@fontsource/roboto";
import Typography from "@material-ui/core/Typography";
import { Grid, Link, Paper } from "@material-ui/core";

//Can do a toggle feature to switch between login and signup

const paperStyle = {
  padding: 20,
  height: "70vh",
  width: 280,
  margin: "20px auto",
};
const btnStyle = { margin: "16px 0" };
const textStyle = { color: "#3f51b5" };

/**
 * Initalize calling useState, accepts the inital value and returns 2 values the current state and function updates the state. Props(properties) passing into the function
 * @param {*} props
 * @returns
 */

const Auth = (props) => {
  const [firstname, setFirstName] = useState("");
  const [lastname, setLastName] = useState("");
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [login, setLogin] = useState(true);

  const loginToggle = () => {
    setLogin(!login);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("button clicked");

    //if login is true, hit signin endpoint
    const baseUrl = `${API_URL}/${login ? "signin" : "signup"} `;

    const data = {
      username: username,
      password: password,
    };
    console.log(data);

    //fetch using axios

    axios
      .post(baseUrl, data)
      .then((res) => {
        console.log(res);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const signupForm = () => {
    if (login) {
      return null;
    } else {
      return (
        <Grid>
          <TextField
            variant="standard"
            color="primary"
            type="text"
            label="Firstname"
            fullWidth
            required
            value={firstname}
            onChange={(e) => {
              setFirstName(e.target.value);
            }}
          />
          <TextField
            variant="standard"
            color="primary"
            type="text"
            label="Lastname"
            fullWidth
            required
            value={lastname}
            onChange={(e) => {
              setLastName(e.target.value);
            }}
          />
        </Grid>
      );
    }
  };

  return (
    <div className="auth">
      <Typography variant="h2" style={textStyle}>
        Welcome to the Train Company
      </Typography>
      <Grid>
        <Paper elevation={10} style={paperStyle}>
          <Grid align="center">
            <h2 style={textStyle}>{login ? "Sign in" : "Sign up"}</h2>
          </Grid>
          {signupForm()}
          <TextField
            variant="standard"
            color="primary"
            type="text"
            label="Username"
            fullWidth
            required
            value={username}
            onChange={(e) => {
              setUsername(e.target.value);
            }}
          />
          <TextField
            variant="standard"
            color="primary"
            type="password"
            label="Password"
            fullWidth
            required
            value={password}
            onChange={(e) => {
              setPassword(e.target.value);
            }}
          />

          <Button
            href="#"
            startIcon={<LockOpenIcon />}
            variant="contained"
            style={btnStyle}
            size="large"
            color="primary"
            fullWidth
            onClick={handleSubmit}
          >
            Submit
          </Button>
          <Typography>
            {" "}
            <Link href="#" onClick={loginToggle}>
              {login
                ? "Don't have an account? Sign Up"
                : "Already have an account? Sign In"}
            </Link>
          </Typography>
        </Paper>
      </Grid>
    </div>
  );
};

export default Auth;
