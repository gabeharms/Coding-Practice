import React, { Fragment } from 'react';

import ErrorMessage from '../Error';

const Arena = () => (
  <Fragment>
    <ErrorMessage
      heading="An error has occured!"
      error="Please restart your computer thirty times for maximum results."
    />
  </Fragment>
);

export default Arena;
