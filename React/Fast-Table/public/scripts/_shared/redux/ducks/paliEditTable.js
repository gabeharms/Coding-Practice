import { createAction, handleActions } from 'redux-actions';
import { Map } from 'immutable';
import { updateCalculatedLineItemColumns } from '../helpers/paliEditTable';

const UPDATE_WORK_COMPLETED_THIS_PERIOD = 'UPDATE_WORK_COMPLETED_THIS_PERIOD';
const UPDATE_MATERIALS_PRESENTLY_STORED = 'UPDATE_MATERIALS_PRESENTLY_STORED';
const UPDATE_PERCENT_WORK_RETAINAGE_RETAINED_THIS_PERIOD = 'UPDATE_PERCENT_WORK_RETAINAGE_RETAINED_THIS_PERIOD';
const UPDATE_PERCENT_MATERIALS_RETAINAGE_RETAINED_THIS_PERIOD = 'UPDATE_PERCENT_MATERIALS_RETAINAGE_RETAINED_THIS_PERIOD';
const UPDATE_WORK_RETAINAGE_RELEASED_THIS_PERIOD = 'UPDATE_WORK_RETAINAGE_RELEASED_THIS_PERIOD';
const UPDATE_MATERIALS_RETAINAGE_RELEASED_THIS_PERIOD = 'UPDATE_MATERIALS_RETAINAGE_RELEASED_THIS_PERIOD';

const updateWorkCompletedThisPeriod = createAction(
  UPDATE_WORK_COMPLETED_THIS_PERIOD,
  (id, value) => ({id, value}));

const updateMaterialsPresentlyStored = createAction(
  UPDATE_MATERIALS_PRESENTLY_STORED,
  (id, value) => ({id, value}));

const updatePercentWorkRetainageRetainedThisPeriod = createAction(
  UPDATE_PERCENT_WORK_RETAINAGE_RETAINED_THIS_PERIOD,
  (id, value) => ({id, value}));

const updatePercentMaterialsRetainageRetainedThisPeriod = createAction(
  UPDATE_PERCENT_MATERIALS_RETAINAGE_RETAINED_THIS_PERIOD,
  (id, value) => ({id, value}));

const updateWorkRetainageReleasedThisPeriod = createAction(
  UPDATE_WORK_RETAINAGE_RELEASED_THIS_PERIOD,
  (id, value) => ({id, value}));

const updateMaterialsRetainageReleasedThisPeriod = createAction(
  UPDATE_MATERIALS_RETAINAGE_RELEASED_THIS_PERIOD,
  (id, value) => ({id, value}));



const reducer = handleActions(
  {
    [UPDATE_WORK_COMPLETED_THIS_PERIOD]: (state, action) => {
      const { payload: { id, value } } = action;
      const newState = state.setIn([id, 'work_completed_this_period'], value);
      return state.set(id, updateCalculatedLineItemColumns(newState.get(id)));
    },
    [UPDATE_MATERIALS_PRESENTLY_STORED]: (state, action) => {
      const { payload: { id, value } } = action;
      const newState = state.setIn([id, 'materials_presently_stored'], value);
      return state.set(id, updateCalculatedLineItemColumns(newState.get(id)));
    },
    [UPDATE_PERCENT_WORK_RETAINAGE_RETAINED_THIS_PERIOD]: (state, action) => {
      const { payload: { id, value } } = action;
      const newState = state.setIn([id, 'percent_work_retainage_retained_this_period'], value);
      return state.set(id, updateCalculatedLineItemColumns(newState.get(id)));
    },
    [UPDATE_PERCENT_MATERIALS_RETAINAGE_RETAINED_THIS_PERIOD]: (state, action) => {
      const { payload: { id, value } } = action;
      const newState = state.setIn([id, 'percent_materials_retainage_retained_this_period'], value);
      return state.set(id, updateCalculatedLineItemColumns(newState.get(id)));
    },
    [UPDATE_WORK_RETAINAGE_RELEASED_THIS_PERIOD]: (state, action) => {
      const { payload: { id, value } } = action;
      const newState = state.setIn([id, 'work_retainage_released_this_period'], value);
      return state.set(id, updateCalculatedLineItemColumns(newState.get(id)));
    },
    [UPDATE_MATERIALS_RETAINAGE_RELEASED_THIS_PERIOD]: (state, action) => {
      const { payload: { id, value } } = action;
      const newState = state.setIn([id, 'materials_retainage_released_this_period'], value);
      return state.set(id, updateCalculatedLineItemColumns(newState.get(id)));
    },
  },
  Map();
);

const { combineReducers } = Redux;
const payApp = combineReducers({
  palis: palis
});

const { createStore } = Redux;
const store = createStore(payApp);


export {
  updateWorkCompletedThisPeriod,
  updateMaterialsPresentlyStored,
  updatePercentWorkRetainageRetainedThisPeriod,
  updatePercentMaterialsRetainageRetainedThisPeriod,
  updateWorkRetainageReleasedThisPeriod,
  updateMaterialsRetainageReleasedThisPeriod,
  reducer as default,
};
