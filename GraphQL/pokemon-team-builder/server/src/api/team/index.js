module.exports = {
  resolvers: require('./team.resolvers'),
  typeDefs: require('../../utils/gqlLoader')('team/team.graphql'),
  model: require('./team.model'),
  modules: require('./team.modules'),
};
