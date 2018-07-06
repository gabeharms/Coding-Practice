const DataLoader = require('dataloader');
const _ = require('lodash');
const Pokemon = require('./pokemon/pokemon.model');
const Team = require('./team/team.model');

const createPokemonLoader = () =>
  new DataLoader(pokemonIds =>
    Pokemon.find({ _id: { $in: pokemonIds } })
      .exec()
      .then(pokemons => {
        console.log('Pokemon Loader Batch: ', pokemonIds.length); // eslint-disable-line
        const pokemonsById = _.keyBy(pokemons, '_id');
        return pokemonIds.map(pokemonId => pokemonsById[pokemonId]);
      }),
  );

const createTeamLoader = () =>
  new DataLoader(teamIds =>
    Team.find({ _id: { $in: teamIds } })
      .exec()
      .then(teams => {
        console.log('Team Loader Batch: ', teamIds.length); // eslint-disable-line
        const teamsById = _.keyBy(teams, '_id');
        return teamIds.map(teamId => teamsById[teamId]);
      }),
  );

module.exports = () => ({
  pokemon: createPokemonLoader(),
  team: createTeamLoader(),
});
