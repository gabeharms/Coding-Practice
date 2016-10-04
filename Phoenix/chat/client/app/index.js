import React from "react";
import ReactDOM from "react-dom";
import Home from "./components/Home/home.jsx";
import Settings from "./components/Settings/settings.jsx";

function HelloWorld (props) {
  return (
    <div>
      <Home message="Dis yo homepage"></Home>
      <Settings settings="There aren't any right meow"></Settings>
    </div>
  );
}

ReactDOM.render(<HelloWorld />, document.getElementById("root"));
