import React from 'react';
import ReactDOM from 'react-dom';
import { ApolloProvider } from 'react-apollo';
import { ApolloClient } from 'apollo-client';
import { ApolloLink } from 'apollo-link';
import { HttpLink } from 'apollo-link-http';
import { InMemoryCache } from 'apollo-cache-inmemory';
import registerServiceWorker from './registerServiceWorker';

import { TEMP_URI, AUTH_TOKEN } from './constants';

import App from './App';
import './index.css';

const httpLink = new HttpLink({
  uri: TEMP_URI,
});

const authLink = new ApolloLink((operation, forward) => {
  const token = localStorage.getItem(AUTH_TOKEN);
  const authorizationHeader = token ? `Bearer ${token}` : null;

  operation.setContext({
    headers: {
      authorization: authorizationHeader,
    },
  });

  return forward(operation);
});

const httpLinkWithAuth = authLink.concat(httpLink);

const client = new ApolloClient({
  link: httpLinkWithAuth,
  cache: new InMemoryCache(),
});

ReactDOM.render(
  <ApolloProvider client={client}>
    <App />
  </ApolloProvider>,
  document.getElementById('root'),
);

registerServiceWorker();
