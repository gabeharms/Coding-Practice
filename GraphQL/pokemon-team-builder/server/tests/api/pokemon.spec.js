const db = require('../db');
const { runQuery } = require('../run');
const pokemonModules = require('../../src/api/pokemon/pokemon.modules');
const pokemonResolvers = require('../../src/api/pokemon/pokemon.resolvers');

// tests are executed using Jest :~)

describe('Pokemon', () => {
  beforeAll(db.connectToDB); // eslint-disable-line
  afterAll(db.disconnectDB); // eslint-disable-line
  afterEach(db.cleanDB);

  describe('resolvers', () => {
    describe('pokemons', () => {
      test('should resolve correctly', async () => {
        const expectedPokemon = await db.models.pokemon.create([
          {
            name: 'Test 1',
            img: 'http://img.pokemondb.net/artwork/caterpie.jpg',
          },
          {
            name: 'Test 2',
            img: 'http://img.pokemondb.net/artwork/caterpie.jpg',
          },
        ]);

        const results = await pokemonResolvers.Query.allPokemons(
          null,
          { input: {} },
          {
            models: {
              pokemon: db.models.pokemon,
            },
            modules: {
              pokemon: pokemonModules,
            },
          },
        );
        expect(results).toHaveLength(2);
        expect(results[0].name).toEqual(expectedPokemon[0].name);
        expect(results[1].img).toEqual(expectedPokemon[1].img);
      });

      test('should have correct query for all Pokemons', async () => {
        await db.models.pokemon.create([
          {
            name: 'Test 1',
            img: 'http://img.pokemondb.net/artwork/caterpie.jpg',
          },
          {
            name: 'Test 2',
            img: 'http://img.pokemondb.net/artwork/caterpie.jpg',
          },
        ]);

        const query = `
          query AllPokemons {
            pokemons: allPokemons(limit: 2) {
              id
              name
              img
            }
          }
        `;

        const results = await runQuery(query);
        expect(results.errors).toBeUndefined();
        expect(results.data.pokemons).toHaveLength(2);
        expect(results.data.pokemons[0].img).toBeDefined();
        expect(results.data.pokemons[0].type).toBeUndefined();
        expect(results.data.pokemons[1].name).toEqual('Test 2');
      });
    });
  });
});
