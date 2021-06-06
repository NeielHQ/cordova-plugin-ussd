/*global cordova, module*/

module.exports = {
    dial: function (ussdCode,SIM, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "Ussd", "dial", [ussdCode,SIM]);
    }
};
