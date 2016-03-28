import { createSelector, createStructuredSelector } from 'reselect';
import { Map } from 'immutable';

const propsSelector = (state, props) => props;
const keySelector = createSelector(
  propsSelector,
  props => props.__key
);


const paliEditTableSelector = state => state.palis;

const paliEditTableSelector = createSelector(
  paliEditTableSelector,
  keySelector,
  (paliEditTables, key) => {
    return palis.get(key, Map()).toJS();
  }
);

export {
  paliEditTableSelector as default
};
