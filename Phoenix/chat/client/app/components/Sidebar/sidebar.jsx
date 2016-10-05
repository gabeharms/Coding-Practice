import React from "react";
import cssModules from "react-css-modules";
import styles from "./sidebar.css";

const Sidebar = (props) => {
  return (
    <div className={styles.sidebar}>
      <h3>Gabe Harms</h3>
      <p>Last Active: Now</p>
    </div>
  );
};

export default cssModules(Sidebar, styles);
