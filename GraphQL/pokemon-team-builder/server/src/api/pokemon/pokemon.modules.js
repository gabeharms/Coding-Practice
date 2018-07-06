const getPokemon = ({ id, pokemonModel }) => pokemonModel.findById(id);

const getAllPokemon = ({ limit, pokemonModel }) =>
  pokemonModel
    .find({})
    .limit(limit)
    .exec();

module.exports = {
  getPokemon,
  getAllPokemon,
};
