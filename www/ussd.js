window.ussd = function(str, callback) {
    cordova.exec(callback, function(err) {
        callback('Nothing to echo.');
    }, "ussd", "dial", [str]);
};