'use strict';

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

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

var _Redux = Redux;
var combineReducers = _Redux.combineReducers;

var payApp = combineReducers({
  palis: palis
});

var _Redux2 = Redux;
var createStore = _Redux2.createStore;

var store = createStore(payApp);

var _React = React;
var Component = _React.Component;

var PayAppTable = function (_Component) {
  _inherits(PayAppTable, _Component);

  function PayAppTable(props) {
    _classCallCheck(this, PayAppTable);

    return _possibleConstructorReturn(this, Object.getPrototypeOf(PayAppTable).call(this, props));
  }

  _createClass(PayAppTable, [{
    key: 'render',
    value: function render() {
      return React.createElement(
        'table',
        { id: 'myTable' },
        React.createElement(PayAppHeaders, null),
        React.createElement(
          'tbody',
          null,
          this.props.value.palis.map(function (pali) {
            return React.createElement(PayAppLineItem, { key: pali.id, item: pali, updateLineItem: updateLineItem });
          })
        )
      );
    }
  }]);

  return PayAppTable;
}(Component);

var PayAppHeaders = function (_Component2) {
  _inherits(PayAppHeaders, _Component2);

  function PayAppHeaders(props) {
    _classCallCheck(this, PayAppHeaders);

    return _possibleConstructorReturn(this, Object.getPrototypeOf(PayAppHeaders).call(this, props));
  }

  _createClass(PayAppHeaders, [{
    key: 'render',
    value: function render() {
      return React.createElement(
        'thead',
        null,
        React.createElement(
          'th',
          null,
          'Item No.'
        ),
        React.createElement(
          'th',
          null,
          'Cost Code'
        ),
        React.createElement(
          'th',
          null,
          'Description of Work'
        ),
        React.createElement(
          'th',
          null,
          'Scheduled Value'
        ),
        React.createElement(
          'th',
          null,
          'Work Completed From Previous Application'
        ),
        React.createElement(
          'th',
          null,
          'Work Completed From This Period'
        ),
        React.createElement(
          'th',
          null,
          'Materials Presently Stored'
        ),
        React.createElement(
          'th',
          null,
          'Total Completed And Stored To Date'
        ),
        React.createElement(
          'th',
          null,
          'Percent Complete'
        ),
        React.createElement(
          'th',
          null,
          'Balance To Finish'
        ),
        React.createElement(
          'th',
          null,
          'From Previous Application Work Retainage'
        ),
        React.createElement(
          'th',
          null,
          'From Previous Application Materials Retainage'
        ),
        React.createElement(
          'th',
          null,
          'From Previous Application Total'
        ),
        React.createElement(
          'th',
          null,
          'Retained This Period Work Retainage Percent'
        ),
        React.createElement(
          'th',
          null,
          'Retained This Period Work Retainage Dollar'
        ),
        React.createElement(
          'th',
          null,
          'Retained This Period Materials Retainage Percent'
        ),
        React.createElement(
          'th',
          null,
          'Retained This Period Materials Retainage Dollar'
        ),
        React.createElement(
          'th',
          null,
          'Released This Period Work Retainage'
        ),
        React.createElement(
          'th',
          null,
          'Released This Period Materials Retainage'
        ),
        React.createElement(
          'th',
          null,
          'Currently Retained Work Retainage'
        ),
        React.createElement(
          'th',
          null,
          'Currently Retained Materials Retainage'
        ),
        React.createElement(
          'th',
          null,
          'Currently Retained Total'
        )
      );
    }
  }]);

  return PayAppHeaders;
}(Component);

var PayAppLineItem = function (_Component3) {
  _inherits(PayAppLineItem, _Component3);

  function PayAppLineItem(props) {
    _classCallCheck(this, PayAppLineItem);

    return _possibleConstructorReturn(this, Object.getPrototypeOf(PayAppLineItem).call(this, props));
  }

  _createClass(PayAppLineItem, [{
    key: 'render',
    value: function render() {
      var _this4 = this;

      return React.createElement(
        'tr',
        null,
        React.createElement(LabelCell, { value: this.props.item.pali_id }),
        React.createElement(LabelCell, { value: this.props.item.cost_code }),
        React.createElement(LabelCell, { value: this.props.item.description_of_work }),
        React.createElement(LabelCell, { value: this.props.item.scheduled_value }),
        React.createElement(LabelCell, { value: this.props.item.work_completed_from_previous_app }),
        React.createElement(TextBoxCell, {
          value: this.props.item.work_completed_this_period,
          onChange: function onChange(e) {
            store.dispatch({ type: 'UpdateWorkCompletedThisPeriod', id: _this4.props.item.pali_id, value: e.target.value });
          } }),
        React.createElement(TextBoxCell, {
          value: this.props.item.materials_presently_stored,
          onChange: function onChange(e) {
            store.dispatch({ type: 'UpdateMaterialsPresentlyStored', id: _this4.props.item.pali_id, value: e.target.value });
          } }),
        React.createElement(LabelCell, { value: this.props.item.total_completed_and_stored_presently }),
        React.createElement(LabelCell, { value: this.props.item.percent_complete }),
        React.createElement(LabelCell, { value: this.props.item.balance_to_finish }),
        React.createElement(LabelCell, { value: this.props.item.work_retainage_from_previous_app }),
        React.createElement(LabelCell, { value: this.props.item.materials_retainage_from_previous_app }),
        React.createElement(LabelCell, { value: this.props.item.total_from_previous_app }),
        React.createElement(TextBoxCell, {
          value: this.props.item.percent_work_retainage_retained_this_period,
          onChange: function onChange(e) {
            store.dispatch({ type: 'UpdatePercentWorkRetainageRetainedThisPeriod', id: _this4.props.item.pali_id, value: e.target.value });
          } }),
        React.createElement(LabelCell, { value: this.props.item.dollar_work_retainage_retained_this_period }),
        React.createElement(TextBoxCell, {
          value: this.props.item.percent_materials_retainage_retained_this_period,
          onChange: function onChange(e) {
            store.dispatch({ type: 'UpdatePercentMaterialsRetainageRetainedThisPeriod', id: _this4.props.item.pali_id, value: e.target.value });
          } }),
        React.createElement(LabelCell, { value: this.props.item.dollar_materials_retainage_retained_this_period }),
        React.createElement(TextBoxCell, {
          value: this.props.item.work_retainage_released_this_period,
          onChange: function onChange(e) {
            store.dispatch({ type: 'UpdateWorkRetainageReleasedThisPeriod', id: _this4.props.item.pali_id, value: e.target.value });
          } }),
        React.createElement(TextBoxCell, {
          value: this.props.item.materials_retainage_released_this_period,
          onChange: function onChange(e) {
            store.dispatch({ type: 'UpdateMaterialsRetainageReleasedThisPeriod', id: _this4.props.item.pali_id, value: e.target.value });
          } }),
        React.createElement(LabelCell, { value: this.props.item.work_retainage_currently_retained }),
        React.createElement(LabelCell, { value: this.props.item.materials_retainage_currently_retained }),
        React.createElement(LabelCell, { value: this.props.item.total_currently_retained })
      );
    }
  }]);

  return PayAppLineItem;
}(Component);

var Cell = function (_Component4) {
  _inherits(Cell, _Component4);

  function Cell() {
    _classCallCheck(this, Cell);

    return _possibleConstructorReturn(this, Object.getPrototypeOf(Cell).apply(this, arguments));
  }

  _createClass(Cell, [{
    key: 'getValue',
    value: function getValue() {
      debugger;
      return this.value;
    }
  }]);

  return Cell;
}(Component);

var LabelCell = function (_Cell) {
  _inherits(LabelCell, _Cell);

  function LabelCell(props) {
    _classCallCheck(this, LabelCell);

    return _possibleConstructorReturn(this, Object.getPrototypeOf(LabelCell).call(this, props));
  }

  _createClass(LabelCell, [{
    key: 'render',
    value: function render() {
      return React.createElement(
        'td',
        null,
        this.props.value
      );
    }
  }]);

  return LabelCell;
}(Cell);

var TextBoxCell = function (_Cell2) {
  _inherits(TextBoxCell, _Cell2);

  function TextBoxCell() {
    _classCallCheck(this, TextBoxCell);

    return _possibleConstructorReturn(this, Object.getPrototypeOf(TextBoxCell).apply(this, arguments));
  }

  _createClass(TextBoxCell, [{
    key: 'render',
    value: function render() {
      return React.createElement(
        'td',
        null,
        React.createElement('input', { type: 'text', value: this.props.value, onChange: this.props.onChange })
      );
    }
  }]);

  return TextBoxCell;
}(Cell);

var render = function render() {
  ReactDOM.render(React.createElement(PayAppTable, { value: store.getState() }), document.getElementById('content'));
};

loadPalisFromServer('api/pay-app');
store.subscribe(render);
render();

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

function updateLineItem(updatedLine) {
  var paliToUpdate = _.find(this.state.palis, { pali_id: updatedLine.pali_id });
  paliToUpdate = updatedLine;
  store.setState({ palis: this.state.palis });
}

function takeTwo(val) {
  var parsedVal = parseFloat(val);
  return (Math.floor(parsedVal * 100) / 100).toFixed(2);
}
function parseDollar(val) {
  if (isNaN(parseFloat(val))) {
    val = 0.00;
  }
  return takeTwo(val);
}
function parsePercent(target) {
  var start = $(target)[0].selectionStart;
  var end = $(target)[0].selectionEnd;
  if (isNaN(parseFloat(target.value))) {
    target.value = 0.00;
  }
  $(target).val(this.takeTwo(target.value));
  $(target)[0].setSelectionRange(start, end);
}

function updateCalculatedFields() {
  var lineItem = this.props.item;
  lineItem.total_completed_and_stored_presently = parseFloat(lineItem.work_completed_from_previous_app) + parseFloat(lineItem.work_completed_this_period) + parseFloat(lineItem.materials_presently_stored);
  lineItem.percent_complete = this.takeTwo(parseFloat(lineItem.total_completed_and_stored_presently) / parseFloat(lineItem.scheduled_value) * 100.00);
  lineItem.balance_to_finish = (parseFloat(lineItem.scheduled_value) - parseFloat(lineItem.total_completed_and_stored_presently)).toFixed(2);
  lineItem.dollar_work_retainage_retained_this_period = (parseFloat(lineItem.work_completed_this_period) * parseFloat(lineItem.percent_work_retainage_retained_this_period / 100.00)).toFixed(2);
  lineItem.dollar_materials_retainage_retained_this_period = (parseFloat(lineItem.materials_presently_stored) * parseFloat(lineItem.percent_materials_retainage_retained_this_period / 100.00)).toFixed(2);

  lineItem.materials_retainage_currently_retained = (parseFloat(lineItem.materials_retainage_from_previous_app) + parseFloat(lineItem.dollar_materials_retainage_retained_this_period) - parseFloat(lineItem.materials_retainage_released_this_period)).toFixed(2);
  lineItem.total_currently_retained = (parseFloat(lineItem.work_retainage_currently_retained) + parseFloat(lineItem.materials_retainage_currently_retained)).toFixed(2);
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