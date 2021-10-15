import React from "react";
import Link from "@material-ui/core/Link";
import { Button } from "@material-ui/core";

const btnStyle = { margin: "16px 0" };


const Logout = (props) => {

  const logoutUser = (e) => {
    e.preventDefault();
    props.clearToken();
  };

  return (
    <div>

        <Button
            href="#"
            variant="contained"
            style={btnStyle}
            size="medium"
            color="secondary"
            fullWidth
            onClick={logoutUser}
          >
            Logout
          </Button>
 
    </div>
  );
};

export default Logout;
