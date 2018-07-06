/* eslint-disable react/no-array-index-key */
import React from 'react';
import { Box, Card, Flex, Modal, Button, FlexList } from '@procore/core-react';
import { Query } from 'react-apollo';

import Loading from '../../Loading';
import ErrorMessage from '../../Error';

import { GET_POKEMON_QUERY } from './queries';

import './style.css';

const PokemonCard = ({ id, name, img }) => (
  <Box margin="md">
    <Card
      variant="hoverable"
      style={{ width: '200px' }}
      className="PokemonCard"
    >
      <Box padding="md">
        <Flex direction="column">
          <Modal.State>
            {({ isShowing, show, hide }) => (
              <div>
                <img
                  alt={name}
                  src={img}
                  style={{ height: 75, width: 75, marginBottom: 10 }}
                  onClick={show}
                />
                <h4 className="capitalize">{name}</h4>

                <Modal open={isShowing} onClickOverlay={hide} placement="top">
                  <Modal.Header onClose={hide}>{name}</Modal.Header>
                  {isShowing && (
                    <Query
                      query={GET_POKEMON_QUERY}
                      notifyOnNetworkStatusChange
                      variables={{ id }}
                    >
                      {({ data, loading, error }) => {
                        if (error) {
                          return <ErrorMessage error={error} />;
                        }

                        const { pokemon } = data;

                        if (loading && !pokemon) {
                          return <Loading />;
                        }

                        return (
                          <Modal.Body>
                            <Flex direction="column">
                              <img
                                alt={name}
                                src={img}
                                style={{
                                  height: 100,
                                  width: 100,
                                  marginBottom: 20,
                                }}
                              />
                            </Flex>
                            <FlexList
                              size="sm"
                              direction="column"
                              alignItems="flex-start"
                              style={{ marginBottom: '10px' }}
                            >
                              <h5>Classification</h5>
                              <span style={{ fontSize: '10pt' }}>
                                {pokemon.classification}
                              </span>
                            </FlexList>
                            <FlexList
                              size="sm"
                              direction="column"
                              alignItems="flex-start"
                              style={{ marginBottom: '10px' }}
                            >
                              <h5>Weaknesses</h5>
                            </FlexList>
                          </Modal.Body>
                        );
                      }}
                    </Query>
                  )}
                  <Modal.Footer>
                    <Modal.FooterButtons>
                      <Button variant="tertiary" onClick={hide}>
                        Cancel
                      </Button>
                      <Button variant="primary" onClick={hide}>
                        Add To Team
                      </Button>
                    </Modal.FooterButtons>
                  </Modal.Footer>
                </Modal>
              </div>
            )}
          </Modal.State>
        </Flex>
      </Box>
    </Card>
  </Box>
);

export default PokemonCard;
