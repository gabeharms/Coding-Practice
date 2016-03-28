

const updateCalculatedLineItemColumns = (pali) => {
  pali = pali.set('', getTotalCompletedAndStoredPresentlyPeriod(pali.get('work_completed_from_previous_app'), pali.get('work_completed_this_period'), pali.get('materials_presently_stored')));
  pali = pali.set('', getPercentCompletePeriod(pali.get('scheduled_value'), pali.get('total_completed_and_stored_presently')));
  pali = pali.set('', getBalanceToFinish(pali.get('scheduled_value'), pali.get('total_completed_and_stored_presently')));
  pali = pali.set('', getDollarWorkRetainageRetainedThisPeriod(pali.get('work_completed_this_period'), pali.get('percent_work_retainage_retained_this_period')));
  pali = pali.set('', getDollarMaterialsRetainageRetainedThisPeriod(pali.get('materials_presently_stored'), pali.get('percent_materials_retainage_retained_this_period')));
  pali = pali.set('', getWorkRetainageCurrentlyRetained(pali.get('work_retainage_from_previous_app'), pali.get('dollar_work_retainage_retained_this_period'), pali.get('work_retainage_released_this_period')));
  return pali.set('', getMaterialsRetainageCurrentlyRetained(pali.get('materials_retainage_from_previous_app'), pail.get('dollar_materials_retainage_retained_this_period'), pali.get('materials_retainage_released_this_period')));
}

const getDollarMaterialsRetainageRetainedThisPeriod = (materials_presently_stored, percent_materials_retainage_retained_this_period) => {
  return (parseFloat(materials_presently_stored) *
      (parseFloat(percent_materials_retainage_retained_this_period / 100.00))).toFixed(2);
}

const getTotalCompletedAndStoredPresently = (work_completed_from_previous_app, work_completed_this_period, materials_presently_stored) => {
  return parseDollar(parseFloat(work_completed_from_previous_app) +
    parseFloat(work_completed_this_period) +
    parseFloat(materials_presently_stored));
}
const getDollarWorkRetainageRetainedThisPeriod = (work_completed_this_period, percent_work_retainage_retained_this_period) => {
  return (parseFloat(work_completed_this_period) *
    (parseFloat(percent_work_retainage_retained_this_period / 100.00))).toFixed(2);
}
const getWorkRetainageCurrentlyRetained = (work_retainage_from_previous_app, dollar_work_retainage_retained_this_period, work_retainage_released_this_period) => {
  return (parseFloat(work_retainage_from_previous_app) +
    parseFloat(dollar_work_retainage_retained_this_period) -
    parseFloat(work_retainage_released_this_period)).toFixed(2);
}
const getMaterialsRetainageCurrentlyRetained = (materials_retainage_from_previous_app, dollar_materials_retainage_retained_this_period, materials_retainage_released_this_period) => {
  return (parseFloat(materials_retainage_from_previous_app) +
    parseFloat(dollar_materials_retainage_retained_this_period) -
    parseFloat(materials_retainage_released_this_period)).toFixed(2);
}
const getTotalCurrentlyRetained = (work_retainage_currently_retained, materials_retainage_currently_retained) => {
  return (parseFloat(work_retainage_currently_retained) +
    parseFloat(materials_retainage_currently_retained)).toFixed(2);
}
const getBalanceToFinish = (scheduled_value, total_completed_and_stored_presently) => {
  return (parseFloat(scheduled_value) -
    parseFloat(total_completed_and_stored_presently)).toFixed(2);
}
const getPercentComplete = (scheduled_value, total_completed_and_stored_presently) => {
  return takeTwo(parseFloat(total_completed_and_stored_presently) /
                  parseFloat(scheduled_value) *
                  100.00
                );
}



export {
  updateCalculatedLineItemColumns as default;
};
