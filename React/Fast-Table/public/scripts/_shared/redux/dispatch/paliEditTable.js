import {
  updateWorkCompletedThisPeriod,
  updateMaterialsPresentlyStored,
  updatePercentWorkRetainageRetainedThisPeriod,
  updatePercentMaterialsRetainageRetainedThisPeriod,
  updateWorkRetainageReleasedThisPeriod,
  updateMaterialsRetainageReleasedThisPeriod
} from '../ducks/paliEditTable';

function mapDispatchToProps(dispatch) {
  return {
    updateWorkCompletedThisPeriod: (id, value)                     => dispatch(updateWorkCompletedThisPeriod(id, value)),
    updateMaterialsPresentlyStored: (id, value)                    => dispatch(updateMaterialsPresentlyStored(id, value)),
    updatePercentWorkRetainageRetainedThisPeriod: (id, value)      => dispatch(updatePercentWorkRetainageRetainedThisPeriod(id, value)),
    updatePercentMaterialsRetainageRetainedThisPeriod: (id, value) => dispatch(updatePercentMaterialsRetainageRetainedThisPeriod(id, value)),
    updateWorkRetainageReleasedThisPeriod: (id, value)             => dispatch(updateWorkRetainageReleasedThisPeriod(id, value)),
    updateMaterialsRetainageReleasedThisPeriod: (id, value)        => dispatch(updateMaterialsRetainageReleasedThisPeriod(id, value))
  };
}

export {
  mapDispatchToProps as default,
};
