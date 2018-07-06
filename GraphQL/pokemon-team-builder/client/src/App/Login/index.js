import React, { Component } from 'react';
import { graphql } from 'react-apollo';
import { withRouter } from 'react-router-dom';
import { Button, FormGroup, FormControl, ControlLabel } from 'react-bootstrap';
import { LOGIN_MUTATION } from './mutations';
import { AUTH_TOKEN } from '../../constants';

import './style.css';

class Login extends Component {
  state = {
    email: '',
    password: '',
  };

  validateForm = () =>
    this.state.email.length > 0 && this.state.password.length > 0;

  handleChange = e => this.setState({ [e.target.id]: e.target.value });

  handleSubmit = async e => {
    e.preventDefault();

    const result = await this.props.loginMutation({
      variables: {
        input: {
          ...this.state,
        },
      },
    });

    const { token } = result.data.login;
    this.saveUserToken(token);
    this.props.history.push('/');
  };

  saveUserToken = token => localStorage.setItem(AUTH_TOKEN, token);

  render() {
    return (
      <div className="Login">
        <form onSubmit={this.handleSubmit}>
          <FormGroup controlId="email" bsSize="large">
            <ControlLabel>Email</ControlLabel>
            <FormControl
              autoFocus
              type="email"
              value={this.state.email}
              onChange={this.handleChange}
            />
          </FormGroup>
          <FormGroup controlId="password" bsSize="large">
            <ControlLabel>Password</ControlLabel>
            <FormControl
              value={this.state.password}
              onChange={this.handleChange}
              type="password"
            />
          </FormGroup>
          <Button
            block
            bsSize="large"
            disabled={!this.validateForm()}
            type="submit"
          >
            Login
          </Button>
        </form>
      </div>
    );
  }
}

export default graphql(LOGIN_MUTATION, {
  name: 'loginMutation',
})(withRouter(Login));
