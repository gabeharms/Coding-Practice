const jwt = require('jsonwebtoken');

const authenticate = context => {
  const Authorization = context.request.headers.authorization;

  if (Authorization) {
    const token = Authorization.replace('Bearer ', '');
    const { userId } = jwt.verify(token, 'sillysecret');
    return userId;
  }

  throw new Error('Not Authorized');
};

module.exports = {
  authenticate,
};
