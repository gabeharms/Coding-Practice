import React, { Fragment } from 'react';
import { Link, withRouter } from 'react-router-dom';

import * as routes from '../../constants/routes';
import { AUTH_TOKEN } from '../../constants';

import './style.css';

const authToken = localStorage.getItem(AUTH_TOKEN);

const Navigation = props => (
  <header className="Navigation">
    <div className="Navigation-link">
      <Link to={routes.POKEMON_LIST}>Pokemon List</Link>
    </div>
    {authToken && (
      <Fragment>
        <div className="Navigation-link">
          <Link to={routes.MY_TEAM}>My Team</Link>
        </div>
        <div className="Navigation-link">
          <Link to={routes.ARENA}>Battle Arena</Link>
        </div>
      </Fragment>
    )}
    <div className="Navigation-link">
      {authToken ? (
        <a
          href="/"
          onClick={() => {
            localStorage.removeItem(AUTH_TOKEN);
            props.history.push('/');
          }}
        >
          Logout
        </a>
      ) : (
        <Link to={routes.LOGIN}>Login</Link>
      )}
    </div>
  </header>
);

export default withRouter(Navigation);
