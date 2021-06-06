/*global cordova, module*/

module.exports = {
    dial: function (name, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "Ussd", "dial", [name]);
    }
};
