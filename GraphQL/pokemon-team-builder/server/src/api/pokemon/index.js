module.exports = {
  resolvers: require('./pokemon.resolvers'),
  typeDefs: require('../../utils/gqlLoader')('pokemon/pokemon.graphql'),
  model: require('./pokemon.model'),
  modules: require('./pokemon.modules'),
};
