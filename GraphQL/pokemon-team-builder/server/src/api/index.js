const merge = require('lodash/merge');
const pokemon = require('./pokemon');
const team = require('./team');
const user = require('./user');
const loaders = require('./loaders');

module.exports = {
  typeDefs: [pokemon.typeDefs, team.typeDefs, user.typeDefs].join(' '),
  resolvers: merge({}, pokemon.resolvers, team.resolvers, user.resolvers),
  context: req => ({
    ...req,
    models: {
      pokemon: pokemon.model,
      team: team.model,
      user: user.model,
    },
    modules: {
      pokemon: pokemon.modules,
      team: team.modules,
    },
    loaders: loaders(),
  }),
};
