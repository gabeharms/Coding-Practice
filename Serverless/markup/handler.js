
'use strict';

module.exports.generateMarkupLineItems = (event, context, callback) => {
  return callback(null, { statusCode: 200, body: JSON.stringify({ message: 'yooo' }) });
};
