"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var PayAppHeaders = function (_React$Component) {
  _inherits(PayAppHeaders, _React$Component);

  function PayAppHeaders(props) {
    _classCallCheck(this, PayAppHeaders);

    return _possibleConstructorReturn(this, Object.getPrototypeOf(PayAppHeaders).call(this, props));
  }

  _createClass(PayAppHeaders, [{
    key: "render",
    value: function render() {
      return React.createElement(
        "thead",
        null,
        React.createElement(
          "th",
          null,
          "Item No."
        ),
        React.createElement(
          "th",
          null,
          "Cost Code"
        ),
        React.createElement(
          "th",
          null,
          "Description of Work"
        ),
        React.createElement(
          "th",
          null,
          "Scheduled Value"
        ),
        React.createElement(
          "th",
          null,
          "Work Completed From Previous Application"
        ),
        React.createElement(
          "th",
          null,
          "Work Completed From This Period"
        ),
        React.createElement(
          "th",
          null,
          "Materials Presently Stored"
        ),
        React.createElement(
          "th",
          null,
          "Total Completed And Stored To Date"
        ),
        React.createElement(
          "th",
          null,
          "Percent Complete"
        ),
        React.createElement(
          "th",
          null,
          "Balance To Finish"
        ),
        React.createElement(
          "th",
          null,
          "From Previous Application Work Retainage"
        ),
        React.createElement(
          "th",
          null,
          "From Previous Application Materials Retainage"
        ),
        React.createElement(
          "th",
          null,
          "From Previous Application Total"
        ),
        React.createElement(
          "th",
          null,
          "Retained This Period Work Retainage Percent"
        ),
        React.createElement(
          "th",
          null,
          "Retained This Period Work Retainage Dollar"
        ),
        React.createElement(
          "th",
          null,
          "Retained This Period Materials Retainage Percent"
        ),
        React.createElement(
          "th",
          null,
          "Retained This Period Materials Retainage Dollar"
        ),
        React.createElement(
          "th",
          null,
          "Released This Period Work Retainage"
        ),
        React.createElement(
          "th",
          null,
          "Released This Period Materials Retainage"
        ),
        React.createElement(
          "th",
          null,
          "Currently Retained Work Retainage"
        ),
        React.createElement(
          "th",
          null,
          "Currently Retained Materials Retainage"
        ),
        React.createElement(
          "th",
          null,
          "Currently Retained Total"
        )
      );
    }
  }]);

  return PayAppHeaders;
}(React.Component);

exports.default = PayAppHeaders;