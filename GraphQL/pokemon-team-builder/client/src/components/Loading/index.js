import React from 'react';
import { Spinner } from '@procore/core-react';

import './style.css';

const Loading = ({ loading, label = 'Loading', size = 'lg', ...props }) => (
  <div className="Loading">
    <Spinner {...props} size={size} label={label} loading={loading} />
  </div>
);

export default Loading;
