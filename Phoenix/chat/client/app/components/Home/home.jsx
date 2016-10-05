import React from "react";
import cssModules from "react-css-modules";
import Sidebar from "../Sidebar/sidebar.jsx";
import styles from "./home.css";

const Home = (props) => {
  return (
    <div>
      <Sidebar />
      <div className={styles.chatWrapper}>{props.message}</div>
    </div>
  );
};

export default Home;
