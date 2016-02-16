'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var PayAppLineItem = function (_React$Component) {
  _inherits(PayAppLineItem, _React$Component);

  function PayAppLineItem(props) {
    _classCallCheck(this, PayAppLineItem);

    return _possibleConstructorReturn(this, Object.getPrototypeOf(PayAppLineItem).call(this, props));
  }

  _createClass(PayAppLineItem, [{
    key: 'render',
    value: function render() {
      var _this2 = this;

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
            store.dispatch({ type: 'UpdateWorkCompletedThisPeriod', id: _this2.props.item.pali_id, value: e.target.value });
          } }),
        React.createElement(TextBoxCell, {
          value: this.props.item.materials_presently_stored,
          onChange: function onChange(e) {
            store.dispatch({ type: 'UpdateMaterialsPresentlyStored', id: _this2.props.item.pali_id, value: e.target.value });
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
            store.dispatch({ type: 'UpdatePercentWorkRetainageRetainedThisPeriod', id: _this2.props.item.pali_id, value: e.target.value });
          } }),
        React.createElement(LabelCell, { value: this.props.item.dollar_work_retainage_retained_this_period }),
        React.createElement(TextBoxCell, {
          value: this.props.item.percent_materials_retainage_retained_this_period,
          onChange: function onChange(e) {
            store.dispatch({ type: 'UpdatePercentMaterialsRetainageRetainedThisPeriod', id: _this2.props.item.pali_id, value: e.target.value });
          } }),
        React.createElement(LabelCell, { value: this.props.item.dollar_materials_retainage_retained_this_period }),
        React.createElement(TextBoxCell, {
          value: this.props.item.work_retainage_released_this_period,
          onChange: function onChange(e) {
            store.dispatch({ type: 'UpdateWorkRetainageReleasedThisPeriod', id: _this2.props.item.pali_id, value: e.target.value });
          } }),
        React.createElement(TextBoxCell, {
          value: this.props.item.materials_retainage_released_this_period,
          onChange: function onChange(e) {
            store.dispatch({ type: 'UpdateMaterialsRetainageReleasedThisPeriod', id: _this2.props.item.pali_id, value: e.target.value });
          } }),
        React.createElement(LabelCell, { value: this.props.item.work_retainage_currently_retained }),
        React.createElement(LabelCell, { value: this.props.item.materials_retainage_currently_retained }),
        React.createElement(LabelCell, { value: this.props.item.total_currently_retained })
      );
    }
  }]);

  return PayAppLineItem;
}(React.Component);

exports.default = PayAppLineItem;