var exec = require('cordova/exec');

exports.thermalPrint = function (success, error) {
    exec(success, error, "printing", "thermalPrint", ["D8:80:39:F0:88:7C","print sample"]);
};