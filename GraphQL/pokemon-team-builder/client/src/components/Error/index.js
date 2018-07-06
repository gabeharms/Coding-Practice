import React, { Fragment } from 'react';

import './style.css';

const ErrorMessage = ({ error, heading = null }) => (
  <Fragment>
    {heading && (
      <div className="ErrorMessage-heading">
        <h3>{heading}</h3>
      </div>
    )}
    <div className="ErrorMessage">{error.toString()}</div>
  </Fragment>
);

export default ErrorMessage;
