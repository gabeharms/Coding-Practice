import React, { Component, Fragment } from 'react';
import { BrowserRouter as Router, Route } from 'react-router-dom';

import Navigation from './Navigation';
import Footer from './Footer';

import PokemonList from '../components/Pokemon';
import Team from '../components/Team';
import Arena from '../components/Arena';
import Login from '../App/Login';

import * as routes from '../constants/routes';

import './style.css';

const Routes = () => (
  <Fragment>
    <Route
      exact
      path={routes.POKEMON_LIST}
      component={() => (
        <div className="App-content_small-header">
          <PokemonList />
        </div>
      )}
    />

    <Route
      exact
      path={routes.MY_TEAM}
      component={() => (
        <div className="App-content_small-header">
          <Team />
        </div>
      )}
    />

    <Route
      exact
      path={routes.ARENA}
      component={() => (
        <div className="App-content_small-header">
          <Arena />
        </div>
      )}
    />

    <Route
      exact
      path={routes.LOGIN}
      component={() => (
        <div className="App-content_small-header">
          <Login />
        </div>
      )}
    />
  </Fragment>
);

class App extends Component {
  state = {};
  render() {
    return (
      <Router>
        <div className="App">
          <Navigation />

          <div className="App-main">
            <Routes />
          </div>

          <Footer />
        </div>
      </Router>
    );
  }
}

export default App;
