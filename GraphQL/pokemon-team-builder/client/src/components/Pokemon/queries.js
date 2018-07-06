import gql from 'graphql-tag';

const GET_POKEMON_QUERY = gql`
  {
    pokemons: allPokemons(limit: 30) {
      id
      name
      img
    }
  }
`;

export { GET_POKEMON_QUERY };
