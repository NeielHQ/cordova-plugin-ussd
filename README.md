# cordova-plugin-ussd
An awesome cordova plugin for dialing USSD code without leaving the app, with option to select SIM in dual SIM phones !! . 

## Features
1. Dial USSD codes like *123# , you don't need to encode the string
2. Dual SIM support 
3. Get feedback of USSD code

## Requirements 
- Android 8.0+ 
>**Note**: Its restricted to Android 8.0+ because in the native code I implemented ```sendUssdRequest``` and this is only included in API 26+ (Android 8+) I chose this method because other methods/plugins load/make a request via the default phone/calling app, which sometimes sends your Cordova app to the background.

## Installation
```shell 
cordova plugin add cordova-plugin-ussd 
```

```shell 
cordova plugin add https://github.com/NeielHQ/cordova-plugin-ussd
```

## Usage

```javascript
ussd.dial(ussdCode, SIM, successCallBack, errorCallback);

ussd.dial('*123#',1,
    function(response){
    //response is the message returned from dialing the USSD code . 
        // For example "Your data balance is 12GB" 
        console.log(response);
},
function(error){
    //error message from the native code, it contains:
    // failureCode + request
    console.log(error)
}
)
```
>**Note**: ``SIM`` can be ``1`` (representing the SIM card in SIM 1 slot of your device) or ``2`` (representing the SIM card in SIM 2 slot of your device) 
> 
> If your device uses a single SIM, kindly make use of ``1``


Made with  &#9829; by [@NaijaSteveJobs](https://github.com/NaijaSteveJobs)
