const mongoose = require('mongoose');

const userSchema = new mongoose.Schema({
  email: {
    type: String,
    unique: true,
  },
  password: {
    type: String,
  },
  team: {
    type: mongoose.Schema.Types.ObjectId,
    ref: 'team',
  },
});

module.exports = mongoose.model('user', userSchema);
