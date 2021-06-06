// window.ussd = function(str, callback) {
//     cordova.exec(callback, function(err) {
//         callback('Nothing to echo.');
//     }, "ussd", "dial", [str]);
// };
// cordova.define
// var exec = require('cordova/exec');
//
// module.exports={
//     ussd : function (str, success, error) {
//         exec(success, error, 'ussd', 'dial', [str])
//     }
// }


// Empty constructor
function USSDPlugin() {}

// The function that passes work along to native shells
// Message is a string, duration may be 'long' or 'short'
USSDPlugin.prototype.dial = function(str, successCallback, errorCallback) {
    // var options = {};
    // options.ussdCode = ussdCode;
    // options.sim = sim;

    cordova.exec(successCallback, errorCallback, 'ussd', 'dial', [str]);
}

// Installation constructor that binds ToastyPlugin to window
USSDPlugin.install = function() {
    if (!window.plugins) {
        window.plugins = {};
    }
    window.plugins.ussd= new USSDPlugin();
    return window.plugins.ussd;
};
cordova.addConstructor(USSDPlugin.install);