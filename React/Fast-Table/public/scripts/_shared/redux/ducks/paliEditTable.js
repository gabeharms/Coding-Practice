import { createAction, handleActions } from 'redux-actions';
import { Map } from 'immutable';

const UPDATE_WORK_COMPLETED_THIS_PERIOD                       = 'UPDATE_WORK_COMPLETED_THIS_PERIOD';
const UPDATE_MATERIALS_PRESENTLY_STORED                       = 'UPDATE_MATERIALS_PRESENTLY_STORED';
const UPDATE_PERCENT_WORK_RETAINAGE_RETAINED_THIS_PERIOD      = 'UPDATE_PERCENT_WORK_RETAINAGE_RETAINED_THIS_PERIOD';
const UPDATE_PERCENT_MATERIALS_RETAINAGE_RETAINED_THIS_PERIOD = 'UPDATE_PERCENT_MATERIALS_RETAINAGE_RETAINED_THIS_PERIOD';
const UPDATE_WORK_RETAINAGE_RELEASED_THIS_PERIOD              = 'UPDATE_WORK_RETAINAGE_RELEASED_THIS_PERIOD';
const UPDATE_MATERIALS_RETAINAGE_RELEASED_THIS_PERIOD         = 'UPDATE_MATERIALS_RETAINAGE_RELEASED_THIS_PERIOD';

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
      return state.setIn([key, 'search'], query);
    },
    case 'UpdateWorkCompletedThisPeriod':                     return updateWorkCompletedThisPeriod(state, action.id, action.value);
    case 'UpdateMaterialsPresentlyStored':                    return updateMaterialsPresentlyStored(state, action.id, action.value);
    case 'UpdatePercentWorkRetainageRetainedThisPeriod':      return updatePercentWorkRetainageRetainedThisPeriod(state, action.id, action.value);
    case 'UpdatePercentMaterialsRetainageRetainedThisPeriod': return updatePercentMaterialsRetainageRetainedThisPeriod(state, action.id, action.value);
    case 'UpdateWorkRetainageReleasedThisPeriod':             return updateWorkRetainageReleasedThisPeriod(state, action.id, action.value);
    case 'UpdateMaterialsRetainageReleasedThisPeriod':        return updateMaterialsRetainageReleasedThisPeriod(state, action.id, action.value);

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
  setOpen,
  setSearchQuery,
  setValue,
  reducer as default,
};
