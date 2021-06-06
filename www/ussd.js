// window.ussd = function(str, callback) {
//     cordova.exec(callback, function(err) {
//         callback('Nothing to echo.');
//     }, "ussd", "dial", [str]);
// };

var exec = require('cordova/exec');

exports.ussd = function (str, success, error) {
    exec(success, error, 'ussd', 'dial', [str])
};
