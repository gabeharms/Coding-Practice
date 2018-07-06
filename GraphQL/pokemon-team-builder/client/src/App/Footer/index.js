import React from 'react';

import Link from '../../components/Link';

import './style.css';

const Footer = () => (
  <div className="Footer">
    <div>
      <small>
        <span className="Footer-text">Built by Procore Pokemon Fans</span>{' '}
        <span className="Footer-text">with &hearts;</span>
      </small>
    </div>
    <div>
      <small>
        <Link
          className="Footer-link"
          href="https://ryanyogan.gitbooks.io/graphql-dev-academy/content/"
        >
          Class Materials and Portal
        </Link>{' '}
        <span className="Footer-text">.</span>
      </small>
    </div>
  </div>
);

export default Footer;
