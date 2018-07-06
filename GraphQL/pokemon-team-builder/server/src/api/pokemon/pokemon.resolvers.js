// Take note of what we have done here, we extracted any logic specific to the
// lookup, we may now not only augment Resolvers, however very easily
// in one location change from say MongoDB to Dynamao, PG, a Rest API, etc...
// That concern lives in the `pokemon.modules.js` file, one area, passed around
// via the context.  Talk about making testing super easy, we may test the
// Module, the Resolver, the Context object, and do a full E2E pass...

const pokemon = (_, { id }, ctx) =>
  ctx.modules.pokemon.getPokemon({
    id,
    pokemonModel: ctx.models.pokemon,
  });

const allPokemons = (_, { limit = 20 }, ctx) =>
  ctx.modules.pokemon.getAllPokemon({
    limit,
    pokemonModel: ctx.models.pokemon,
  });

module.exports = {
  Query: {
    pokemon,
    allPokemons,
  },
  Pokemon: {
    id: parent => `${parent._id}`,
    classification: parent => parent.misc.classification,
    height: parent => parent.misc.height,
    weight: parent => parent.misc.weight,
    happiness: parent => parent.misc.happiness,
  },
};
