import gql from 'graphql-tag';

const ADD_POKEMON_TO_TEAM = gql`
  mutation AddPokemon($pokemonId: ID!) {
    addPokemon(pokemonId: $pokemonId) {
      id
      name
      pokemons {
        id
        name
      }
    }
  }
`;

export { ADD_POKEMON_TO_TEAM };
