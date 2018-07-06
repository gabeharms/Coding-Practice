import React from 'react';

import ErrorMessage from '../Error';

const Team = () => (
  <div>
    <ErrorMessage
      heading="An error has occured!"
      error="Please restart your computer thirty times for maximum results."
    />
  </div>
);

export default Team;
