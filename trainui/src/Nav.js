import React from "react";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import MenuOutlined from "@material-ui/icons/MenuOutlined";
import { Typography } from "@material-ui/core";
import Logout from './Components/Authorization/Logout';

function Nav() {
  return (
    <div>
      <AppBar>
        <Toolbar>
          <MenuOutlined />
          <Typography variant="h6">Welcome</Typography>
          <Logout />
        </Toolbar>
      </AppBar>
    </div>
  );
}
export default Nav;
