const mongoose = require('mongoose');
const Pokemon = require('../src/api/pokemon/pokemon.model');

mongoose.Promise = global.Promise;

const models = {
  pokemon: Pokemon,
};

const cleanDB = async done => {
  // await models.team.remove();
  await models.pokemon.remove();
  done();
};

const connectToDB = async () =>
  await mongoose.connect('mongodb://localhost/pokemon-test');

const disconnectDB = (done = () => {}) => {
  mongoose.disconnect(done);
};

const generateMongooseId = () => mongoose.Types.ObjectId();

module.exports = {
  cleanDB,
  connectToDB,
  disconnectDB,
  generateMongooseId,
  models,
};
