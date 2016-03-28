
const palis = (state=[], action) => {
  switch(action.type) {
    case 'UpdateAll': return action.palis;
    case 'UpdateWorkCompletedThisPeriod':                     return updateWorkCompletedThisPeriod(state, action.id, action.value);
    case 'UpdateMaterialsPresentlyStored':                    return updateMaterialsPresentlyStored(state, action.id, action.value);
    case 'UpdatePercentWorkRetainageRetainedThisPeriod':      return updatePercentWorkRetainageRetainedThisPeriod(state, action.id, action.value);
    case 'UpdatePercentMaterialsRetainageRetainedThisPeriod': return updatePercentMaterialsRetainageRetainedThisPeriod(state, action.id, action.value);
    case 'UpdateWorkRetainageReleasedThisPeriod':             return updateWorkRetainageReleasedThisPeriod(state, action.id, action.value);
    case 'UpdateMaterialsRetainageReleasedThisPeriod':        return updateMaterialsRetainageReleasedThisPeriod(state, action.id, action.value);

    default:
      return state;
  }
}

const { combineReducers } = Redux;
const payApp = combineReducers({
  palis: palis
});

const { createStore } = Redux;
const store = createStore(payApp);


const { Component } = React;
class PayAppTable extends Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <table id="myTable">
        <PayAppHeaders></PayAppHeaders>
         <tbody>
          {this.props.value.palis.map(function(pali) {
            return <PayAppLineItem key={pali.id} item={pali} updateLineItem={updateLineItem}></PayAppLineItem>;
          })}
        </tbody>
      </table>
    )
  }
}

class PayAppHeaders extends Component {
  constructor(props) {
    super(props)
  }
  render() {
    return (
      <thead>
        <th>Item No.</th>
        <th>Cost Code</th>
        <th>Description of Work</th>
        <th>Scheduled Value</th>
        <th>Work Completed From Previous Application</th>
        <th>Work Completed From This Period</th>
        <th>Materials Presently Stored</th>
        <th>Total Completed And Stored To Date</th>
        <th>Percent Complete</th>
        <th>Balance To Finish</th>
        <th>From Previous Application Work Retainage</th>
        <th>From Previous Application Materials Retainage</th>
        <th>From Previous Application Total</th>
        <th>Retained This Period Work Retainage Percent</th>
        <th>Retained This Period Work Retainage Dollar</th>
        <th>Retained This Period Materials Retainage Percent</th>
        <th>Retained This Period Materials Retainage Dollar</th>
        <th>Released This Period Work Retainage</th>
        <th>Released This Period Materials Retainage</th>
        <th>Currently Retained Work Retainage</th>
        <th>Currently Retained Materials Retainage</th>
        <th>Currently Retained Total</th>
      </thead>
    )
  }

}

class PayAppLineItem extends Component {
  constructor(props) {
    super(props)
  }

  render() {
    return(
      <tr>
        <LabelCell value={this.props.item.pali_id}></LabelCell>
        <LabelCell value={this.props.item.cost_code}></LabelCell>
        <LabelCell value={this.props.item.description_of_work}></LabelCell>
        <LabelCell value={this.props.item.scheduled_value}></LabelCell>
        <LabelCell value={this.props.item.work_completed_from_previous_app}></LabelCell>
        <TextBoxCell
          value={this.props.item.work_completed_this_period}
          onChange={(e) => {store.dispatch({type:'UpdateWorkCompletedThisPeriod', id: this.props.item.pali_id, value: e.target.value});}}>
        </TextBoxCell>
        <TextBoxCell
          value={this.props.item.materials_presently_stored}
          onChange={(e) => {store.dispatch({type:'UpdateMaterialsPresentlyStored', id: this.props.item.pali_id, value: e.target.value});}}>
        </TextBoxCell>
        <LabelCell value={this.props.item.total_completed_and_stored_presently}></LabelCell>
        <LabelCell value={this.props.item.percent_complete}></LabelCell>
        <LabelCell value={this.props.item.balance_to_finish}></LabelCell>
        <LabelCell value={this.props.item.work_retainage_from_previous_app}></LabelCell>
        <LabelCell value={this.props.item.materials_retainage_from_previous_app}></LabelCell>
        <LabelCell value={this.props.item.total_from_previous_app}></LabelCell>
        <TextBoxCell
          value={this.props.item.percent_work_retainage_retained_this_period}
          onChange={(e) => {store.dispatch({type:'UpdatePercentWorkRetainageRetainedThisPeriod', id: this.props.item.pali_id, value: e.target.value});}}>
        </TextBoxCell>
        <LabelCell value={this.props.item.dollar_work_retainage_retained_this_period}></LabelCell>
        <TextBoxCell
          value={this.props.item.percent_materials_retainage_retained_this_period}
          onChange={(e) => {store.dispatch({type:'UpdatePercentMaterialsRetainageRetainedThisPeriod', id: this.props.item.pali_id, value: e.target.value});}}>
        </TextBoxCell>
        <LabelCell value={this.props.item.dollar_materials_retainage_retained_this_period}></LabelCell>
        <TextBoxCell
          value={this.props.item.work_retainage_released_this_period}
          onChange={(e) => {store.dispatch({type:'UpdateWorkRetainageReleasedThisPeriod', id: this.props.item.pali_id, value: e.target.value});}}>
        </TextBoxCell>
        <TextBoxCell
          value={this.props.item.materials_retainage_released_this_period}
          onChange={(e) => {store.dispatch({type:'UpdateMaterialsRetainageReleasedThisPeriod', id: this.props.item.pali_id, value: e.target.value});}}>
        </TextBoxCell>
        <LabelCell value={this.props.item.work_retainage_currently_retained}></LabelCell>
        <LabelCell value={this.props.item.materials_retainage_currently_retained}></LabelCell>
        <LabelCell value={this.props.item.total_currently_retained}></LabelCell>
      </tr>
    )
  }
}

class Cell extends Component {
  getValue() {
    debugger;
    return this.value;
  }
}

class LabelCell extends Cell {
  constructor(props) {
    super(props)
  }
  render() {
    return(
      <td>{this.props.value}</td>
    )
  }
}

class TextBoxCell extends Cell {
  render() {
    return (
      <td><input type="text" value={this.props.value} onChange={this.props.onChange} /></td>
    )
  }
}

const render = () => {
  ReactDOM.render(<PayAppTable palis={store.getState()} />,
                  document.getElementById('content'));
}

loadPalisFromServer('api/pay-app');
store.subscribe(render);
render();








function loadPalisFromServer(url) {
  $.ajax({
    url: url,
    dataType: 'json',
    cache: false,
    success: (data) => {
      store.dispatch({type: 'UpdateAll', palis: data});
    },
    error: (xhr, status, err) => {
      console.error(url, status, err.toString());
    }
  });
}

function updateLineItem(updatedLine) {
  var paliToUpdate = _.find(this.state.palis, {pali_id: updatedLine.pali_id});
  paliToUpdate = updatedLine;
  store.setState({palis: this.state.palis});
}

function takeTwo(val) {
  var parsedVal = parseFloat(val);
  return (Math.floor(parsedVal * 100) / 100).toFixed(2);
}
function parseDollar(val) {
  if (isNaN(parseFloat(val))) { val = 0.00}
  return takeTwo(val);
}
function parsePercent(target) {
  var start = $(target)[0].selectionStart;
  var end = $(target)[0].selectionEnd;
  if (isNaN(parseFloat(target.value))) { target.value = 0.00}
  $(target).val(this.takeTwo(target.value));
  $(target)[0].setSelectionRange(start, end);
}


function updateCalculatedFields() {
  var lineItem = this.props.item;
  lineItem.total_completed_and_stored_presently = parseFloat(lineItem.work_completed_from_previous_app) +
                                                    parseFloat(lineItem.work_completed_this_period) +
                                                    parseFloat(lineItem.materials_presently_stored);
  lineItem.percent_complete = this.takeTwo(parseFloat(lineItem.total_completed_and_stored_presently) /
                                            parseFloat(lineItem.scheduled_value) *
                                            100.00
                                          );
  lineItem.balance_to_finish = (parseFloat(lineItem.scheduled_value) -
                                  parseFloat(lineItem.total_completed_and_stored_presently)).toFixed(2);
  lineItem.dollar_work_retainage_retained_this_period = (parseFloat(lineItem.work_completed_this_period) *
                                                          (parseFloat(lineItem.percent_work_retainage_retained_this_period / 100.00))).toFixed(2);
  lineItem.dollar_materials_retainage_retained_this_period = (parseFloat(lineItem.materials_presently_stored) *
                                                                (parseFloat(lineItem.percent_materials_retainage_retained_this_period / 100.00))).toFixed(2);

  lineItem.materials_retainage_currently_retained = (parseFloat(lineItem.materials_retainage_from_previous_app) +
                                                      parseFloat(lineItem.dollar_materials_retainage_retained_this_period) -
                                                      parseFloat(lineItem.materials_retainage_released_this_period)).toFixed(2);
  lineItem.total_currently_retained = (parseFloat(lineItem.work_retainage_currently_retained) +
                                        parseFloat(lineItem.materials_retainage_currently_retained)).toFixed(2);
}

function getDollarMaterialsRetainageRetainedThisPeriod(materials_presently_stored, percent_materials_retainage_retained_this_period) {
  return (parseFloat(materials_presently_stored) *
      (parseFloat(percent_materials_retainage_retained_this_period / 100.00))).toFixed(2);
}

function getTotalCompletedAndStoredPresently(work_completed_from_previous_app, work_completed_this_period, materials_presently_stored) {
  return parseDollar(parseFloat(work_completed_from_previous_app) +
    parseFloat(work_completed_this_period) +
    parseFloat(materials_presently_stored));
}
function getDollarWorkRetainageRetainedThisPeriod(work_completed_this_period, percent_work_retainage_retained_this_period) {
  return (parseFloat(work_completed_this_period) *
    (parseFloat(percent_work_retainage_retained_this_period / 100.00))).toFixed(2);
}

function getWorkRetainageCurrentlyRetained(work_retainage_from_previous_app, dollar_work_retainage_retained_this_period, work_retainage_released_this_period) {
  return (parseFloat(work_retainage_from_previous_app) +
    parseFloat(dollar_work_retainage_retained_this_period) -
    parseFloat(work_retainage_released_this_period)).toFixed(2);
}

function getMaterialsRetainageCurrentlyRetained(materials_retainage_from_previous_app, dollar_materials_retainage_retained_this_period, materials_retainage_released_this_period) {
  return (parseFloat(materials_retainage_from_previous_app) +
    parseFloat(dollar_materials_retainage_retained_this_period) -
    parseFloat(materials_retainage_released_this_period)).toFixed(2);
}
function getTotalCurrentlyRetained(work_retainage_currently_retained, materials_retainage_currently_retained) {
  return (parseFloat(work_retainage_currently_retained) +
    parseFloat(materials_retainage_currently_retained)).toFixed(2);
}
function getBalanceToFinish(scheduled_value, total_completed_and_stored_presently) {
  return (parseFloat(scheduled_value) -
    parseFloat(total_completed_and_stored_presently)).toFixed(2);
}
function getPercentComplete(scheduled_value, total_completed_and_stored_presently) {
  return takeTwo(parseFloat(total_completed_and_stored_presently) /
                  parseFloat(scheduled_value) *
                  100.00
                );
}

function updateWorkCompletedThisPeriod(state, id, value) {
  return state.map(pali => {
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
  return state.map(pali => {
    if (pali.pali_id !== id) { return pali; }
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
  return state.map(pali => {
    if (pali.pali_id !== id) { return pali; }
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
  return state.map(pali => {
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
  return state.map(pali => {
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
  return state.map(pali => {
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
