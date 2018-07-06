// We need some tests for the teams!
const db = require('../db');
const { runQuery } = require('../run');
const teamModules = require('../../src/api/team/team.modules');
const teamResolvers = require('../../src/api/team/team.resolvers');

// tests are executed using Jest :~)

describe('Team', () => {
  beforeAll(db.connectToDB); // eslint-disable-line
  afterAll(db.disconnectDB); // eslint-disable-line
  afterEach(db.cleanDB);

  describe('resolvers', () => {
    describe('teams', () => {
      test('should resolve correctly', async () => {
        // Put some specs in here :)
      });
      test('should have correct query for all Pokemons', async () => {
        // Put some specs in here :)
      });
    });
  });
});
