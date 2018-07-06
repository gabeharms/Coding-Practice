const mongoose = require('mongoose');

const connectToDB = (
  url = 'mongodb://dev-academy:pr0c0re@ds247830.mlab.com:47830/pokemon-prod',
) => mongoose.connect(url);

module.exports = connectToDB;
