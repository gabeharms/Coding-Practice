const bcrypt = require('bcrypt');
const jwt = require('jsonwebtoken');
const User = require('./user.model');
const Team = require('../team/team.model');

const signup = async (_, { input: { email, password } }) => {
  // What is horribly wrong here?  Does it seem like a resolver should
  // be concerned with how to signup?  Seperation of concerns, this
  // method would be "not fun" to test ;)
  try {
    const userExists = await User.findOne({ email }).lean();

    if (userExists) {
      throw new Error('Email is already in use.');
    }

    const _password = await bcrypt.hash(password, 10);

    const user = await new User({
      email,
      password: _password,
    });

    user.save(err => {
      if (err) throw err;

      const team = new Team({
        name: `${email}'s Team!`,
        owner: user._id,
      });

      team.save(err => {
        if (err) throw err;
      });
    });

    const token = jwt.sign({ userId: user._id }, 'sillysecret');

    return { token, user };
  } catch (error) {
    throw new Error(error);
  }
};

const login = async (_, { input: { email, password } }) => {
  // What is horribly wrong here?  Does it seem like a resolver should
  // be concerned with how to login?  Or simply log a user in?
  try {
    const user = await User.findOne({ email }).lean();
    if (!user) {
      throw new Error('Invalid Credentials');
    }

    const valid = await bcrypt.compare(password, user.password);
    if (!valid) {
      throw new Error('Invalid Credentials');
    }

    user.password = undefined;

    return {
      token: jwt.sign({ userId: user._id }, 'sillysecret'),
      user,
    };
  } catch (error) {
    throw new Error(error);
  }
};

module.exports = {
  Mutation: {
    signup,
    login,
  },

  User: {
    // Why did we need to create a "virtual" field here?
    // Template strings are the same as `.toString()`
    id: user => `${user._id}`,
  },
};
