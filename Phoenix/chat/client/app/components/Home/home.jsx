import React from "react";

function Home (props) {
  return (
    <div>
      <h1>You are Home</h1>
      <div>{props.message}</div>
    </div>
  );
}

export default Home;
