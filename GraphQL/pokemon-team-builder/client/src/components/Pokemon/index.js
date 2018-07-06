import React from 'react';
import { Query } from 'react-apollo';

import PokemonList from './PokemonList';
import ErrorMessage from '../Error';
import Loading from '../Loading';

import { GET_POKEMON_QUERY } from './queries';

const Pokemon = () => (
  <Query query={GET_POKEMON_QUERY} notifyOnNetworkStatusChange>
    {({ data, loading, error }) => {
      if (error) {
        return <ErrorMessage error={error} />;
      }

      const { pokemons } = data;

      if (loading && !pokemons) {
        return <Loading loading={loading} />;
      }

      return <PokemonList loading={loading} pokemons={pokemons} />;
    }}
  </Query>
);

export default Pokemon;
