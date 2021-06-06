// window.ussd = function(str, callback) {
//     cordova.exec(callback, function(err) {
//         callback('Nothing to echo.');
//     }, "ussd", "dial", [str]);
// };
// cordova.define
var exec = require('cordova/exec');

module.exports={
    ussd : function (str, success, error) {
        exec(success, error, 'ussd', 'dial', [str])
    }
}

