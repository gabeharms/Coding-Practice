'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.default = undefined;

var _paliEditTable = require('../ducks/paliEditTable');

function mapDispatchToProps(dispatch) {
  return {
    updateWorkCompletedThisPeriod: function updateWorkCompletedThisPeriod(id, value) {
      return dispatch((0, _paliEditTable.updateWorkCompletedThisPeriod)(id, value));
    },
    updateMaterialsPresentlyStored: function updateMaterialsPresentlyStored(id, value) {
      return dispatch((0, _paliEditTable.updateMaterialsPresentlyStored)(id, value));
    },
    updatePercentWorkRetainageRetainedThisPeriod: function updatePercentWorkRetainageRetainedThisPeriod(id, value) {
      return dispatch((0, _paliEditTable.updatePercentWorkRetainageRetainedThisPeriod)(id, value));
    },
    updatePercentMaterialsRetainageRetainedThisPeriod: function updatePercentMaterialsRetainageRetainedThisPeriod(id, value) {
      return dispatch((0, _paliEditTable.updatePercentMaterialsRetainageRetainedThisPeriod)(id, value));
    },
    updateWorkRetainageReleasedThisPeriod: function updateWorkRetainageReleasedThisPeriod(id, value) {
      return dispatch((0, _paliEditTable.updateWorkRetainageReleasedThisPeriod)(id, value));
    },
    updateMaterialsRetainageReleasedThisPeriod: function updateMaterialsRetainageReleasedThisPeriod(id, value) {
      return dispatch((0, _paliEditTable.updateMaterialsRetainageReleasedThisPeriod)(id, value));
    }
  };
}

exports.default = mapDispatchToProps;