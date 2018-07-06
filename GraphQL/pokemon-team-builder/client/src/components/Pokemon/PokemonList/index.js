import React from 'react';
import { Page, Flex } from '@procore/core-react';

import PokeCard from '../PokemonCard';

const PokemonList = ({ pokemons }) => (
  <Page>
    <Page.Body>
      <div style={{ width: '100%' }}>
        <Flex style={{ flexWrap: 'wrap', justifyContent: 'center' }}>
          {pokemons.map(pokemon => <PokeCard key={pokemon.id} {...pokemon} />)}
        </Flex>
      </div>
    </Page.Body>
  </Page>
);

export default PokemonList;
