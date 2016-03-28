'use strict';

var palis = function palis() {
  var state = arguments.length <= 0 || arguments[0] === undefined ? [] : arguments[0];
  var action = arguments[1];

  switch (action.type) {
    case 'UpdateAll':
      return action.palis;
    case 'UpdateWorkCompletedThisPeriod':
      return updateWorkCompletedThisPeriod(state, action.id, action.value);
    case 'UpdateMaterialsPresentlyStored':
      return updateMaterialsPresentlyStored(state, action.id, action.value);
    case 'UpdatePercentWorkRetainageRetainedThisPeriod':
      return updatePercentWorkRetainageRetainedThisPeriod(state, action.id, action.value);
    case 'UpdatePercentMaterialsRetainageRetainedThisPeriod':
      return updatePercentMaterialsRetainageRetainedThisPeriod(state, action.id, action.value);
    case 'UpdateWorkRetainageReleasedThisPeriod':
      return updateWorkRetainageReleasedThisPeriod(state, action.id, action.value);
    case 'UpdateMaterialsRetainageReleasedThisPeriod':
      return updateMaterialsRetainageReleasedThisPeriod(state, action.id, action.value);

    default:
      return state;
  }
};

loadPalisFromServer('api/pay-app');
store.subscribe(render);
render();

var render = function render() {
  ReactDOM.render(React.createElement(PayAppTable, { value: store.getState() }), document.getElementById('content'));
};

function loadPalisFromServer(url) {
  $.ajax({
    url: url,
    dataType: 'json',
    cache: false,
    success: function success(data) {
      store.dispatch({ type: 'UpdateAll', palis: data });
    },
    error: function error(xhr, status, err) {
      console.error(url, status, err.toString());
    }
  });
}

function getDollarMaterialsRetainageRetainedThisPeriod(materials_presently_stored, percent_materials_retainage_retained_this_period) {
  return (parseFloat(materials_presently_stored) * parseFloat(percent_materials_retainage_retained_this_period / 100.00)).toFixed(2);
}

function getTotalCompletedAndStoredPresently(work_completed_from_previous_app, work_completed_this_period, materials_presently_stored) {
  return parseDollar(parseFloat(work_completed_from_previous_app) + parseFloat(work_completed_this_period) + parseFloat(materials_presently_stored));
}
function getDollarWorkRetainageRetainedThisPeriod(work_completed_this_period, percent_work_retainage_retained_this_period) {
  return (parseFloat(work_completed_this_period) * parseFloat(percent_work_retainage_retained_this_period / 100.00)).toFixed(2);
}

function getWorkRetainageCurrentlyRetained(work_retainage_from_previous_app, dollar_work_retainage_retained_this_period, work_retainage_released_this_period) {
  return (parseFloat(work_retainage_from_previous_app) + parseFloat(dollar_work_retainage_retained_this_period) - parseFloat(work_retainage_released_this_period)).toFixed(2);
}

function getMaterialsRetainageCurrentlyRetained(materials_retainage_from_previous_app, dollar_materials_retainage_retained_this_period, materials_retainage_released_this_period) {
  return (parseFloat(materials_retainage_from_previous_app) + parseFloat(dollar_materials_retainage_retained_this_period) - parseFloat(materials_retainage_released_this_period)).toFixed(2);
}
function getTotalCurrentlyRetained(work_retainage_currently_retained, materials_retainage_currently_retained) {
  return (parseFloat(work_retainage_currently_retained) + parseFloat(materials_retainage_currently_retained)).toFixed(2);
}
function getBalanceToFinish(scheduled_value, total_completed_and_stored_presently) {
  return (parseFloat(scheduled_value) - parseFloat(total_completed_and_stored_presently)).toFixed(2);
}
function getPercentComplete(scheduled_value, total_completed_and_stored_presently) {
  return takeTwo(parseFloat(total_completed_and_stored_presently) / parseFloat(scheduled_value) * 100.00);
}

function updateWorkCompletedThisPeriod(state, id, value) {
  return state.map(function (pali) {
    if (pali.pali_id !== id) {
      return pali;
    }
    var dollarAmount = parseDollar(value);
    var total_completed_and_stored_presently = getTotalCompletedAndStoredPresently(pali.work_completed_from_previous_app, dollarAmount, pali.materials_presently_stored);
    var percent_complete = getPercentComplete(pali.scheduled_value, total_completed_and_stored_presently);
    var balance_to_finish = getBalanceToFinish(pali.scheduled_value, total_completed_and_stored_presently);
    var dollar_work_retainage_retained_this_period = getDollarWorkRetainageRetainedThisPeriod(dollarAmount, pali.percent_work_retainage_retained_this_period);
    var work_retainage_currently_retained = getWorkRetainageCurrentlyRetained(pali.work_retainage_from_previous_app, dollar_work_retainage_retained_this_period, dollarAmount);
    var total_currently_retained = getTotalCurrentlyRetained(work_retainage_currently_retained, pali.materials_retainage_currently_retained);
    return Object.assign({}, pali, {
      work_completed_this_period: dollarAmount,
      total_completed_and_stored_presently: total_completed_and_stored_presently,
      percent_complete: percent_complete,
      balance_to_finish: balance_to_finish,
      dollar_work_retainage_retained_this_period: dollar_work_retainage_retained_this_period,
      work_retainage_currently_retained: work_retainage_currently_retained,
      total_currently_retained: total_currently_retained
    });
  });
}
function updateMaterialsPresentlyStored(state, id, value) {
  return state.map(function (pali) {
    if (pali.pali_id !== id) {
      return pali;
    }
    var dollarAmount = parseDollar(value);
    var total_completed_and_stored_presently = getTotalCompletedAndStoredPresently(pali.work_completed_from_previous_app, pali.work_completed_this_period, value);
    var percent_complete = getPercentComplete(pali.scheduled_value, total_completed_and_stored_presently);
    var balance_to_finish = getBalanceToFinish(pali.scheduled_value, total_completed_and_stored_presently);
    var dollar_materials_retainage_retained_this_period = getDollarMaterialsRetainageRetainedThisPeriod(dollarAmount, pali.percent_materials_retainage_retained_this_period);
    var materials_retainage_currently_retained = getMaterialsRetainageCurrentlyRetained(pali.materials_retainage_from_previous_app, dollar_materials_retainage_retained_this_period, dollarAmount);
    var total_currently_retained = getTotalCurrentlyRetained(pali.work_retainage_currently_retained, materials_retainage_currently_retained);
    return Object.assign({}, pali, {
      materials_presently_stored: dollarAmount,
      total_completed_and_stored_presently: total_completed_and_stored_presently,
      percent_complete: percent_complete,
      balance_to_finish: balance_to_finish,
      dollar_materials_retainage_retained_this_period: dollar_materials_retainage_retained_this_period,
      materials_retainage_currently_retained: materials_retainage_currently_retained,
      total_currently_retained: total_currently_retained
    });
  });
}
function updatePercentWorkRetainageRetainedThisPeriod(state, id, value) {
  return state.map(function (pali) {
    if (pali.pali_id !== id) {
      return pali;
    }
    var dollarAmount = parseDollar(value);
    var dollar_work_retainage_retained_this_period = getDollarWorkRetainageRetainedThisPeriod(pali.work_completed_this_period, dollarAmount);
    var work_retainage_currently_retained = getWorkRetainageCurrentlyRetained(pali.work_retainage_from_previous_app, pali.dollar_work_retainage_retained_this_period, dollarAmount);
    var total_currently_retained = getTotalCurrentlyRetained(work_retainage_currently_retained, pali.materials_retainage_currently_retained);
    return Object.assign({}, pali, {
      percent_work_retainage_retained_this_period: dollarAmount,
      dollar_work_retainage_retained_this_period: dollar_work_retainage_retained_this_period,
      work_retainage_currently_retained: work_retainage_currently_retained,
      total_currently_retained: total_currently_retained
    });
  });
}
function updatePercentMaterialsRetainageRetainedThisPeriod(state, id, value) {
  return state.map(function (pali) {
    if (pali.pali_id !== id) {
      return pali;
    }
    var dollarAmount = parseDollar(value);
    var dollar_materials_retainage_retained_this_period = getDollarMaterialsRetainageRetainedThisPeriod(pali.materials_presently_stored, dollarAmount);
    var materials_retainage_currently_retained = getMaterialsRetainageCurrentlyRetained(pali.materials_retainage_from_previous_app, dollar_materials_retainage_retained_this_period, dollarAmount);
    var total_currently_retained = getTotalCurrentlyRetained(pali.work_retainage_currently_retained, materials_retainage_currently_retained);
    return Object.assign({}, pali, {
      percent_materials_retainage_retained_this_period: dollarAmount,
      dollar_materials_retainage_retained_this_period: dollar_materials_retainage_retained_this_period,
      materials_retainage_currently_retained: materials_retainage_currently_retained,
      total_currently_retained: total_currently_retained
    });
  });
}
function updateWorkRetainageReleasedThisPeriod(state, id, value) {
  return state.map(function (pali) {
    if (pali.pali_id !== id) {
      return pali;
    }
    var dollarAmount = parseDollar(value);
    var work_retainage_currently_retained = getWorkRetainageCurrentlyRetained(pali.work_retainage_from_previous_app, pali.dollar_work_retainage_retained_this_period, dollarAmount);
    var total_currently_retained = getTotalCurrentlyRetained(work_retainage_currently_retained, pali.materials_retainage_currently_retained);
    return Object.assign({}, pali, {
      work_retainage_released_this_period: dollarAmount,
      work_retainage_currently_retained: work_retainage_currently_retained,
      total_currently_retained: total_currently_retained
    });
  });
}

function updateMaterialsRetainageReleasedThisPeriod(state, id, value) {
  return state.map(function (pali) {
    if (pali.pali_id !== id) {
      return pali;
    }
    var dollarAmount = parseDollar(value);
    var materials_retainage_currently_retained = getMaterialsRetainageCurrentlyRetained(pali.materials_retainage_from_previous_app, pali.dollar_materials_retainage_retained_this_period, dollarAmount);
    var total_currently_retained = getTotalCurrentlyRetained(pali.work_retainage_currently_retained, materials_retainage_currently_retained);
    return Object.assign({}, pali, {
      materials_retainage_released_this_period: dollarAmount,
      materials_retainage_currently_retained: materials_retainage_currently_retained,
      total_currently_retained: total_currently_retained
    });
  });
}