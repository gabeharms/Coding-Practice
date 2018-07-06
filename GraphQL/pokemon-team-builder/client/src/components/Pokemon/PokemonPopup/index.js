import React from 'react';
import { Popover } from '@procore/core-react';

import './style.css';

const PokemonPopup = ({ classification, placement = 'top', ...props }) => (
  <Popover {...props} placement={placement}>
    <div className="PokemonPopup">{classification}</div>
  </Popover>
);

export default PokemonPopup;
