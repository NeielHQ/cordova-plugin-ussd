package ng.neiel.cordova.plugins;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.content.pm.PackageManager;
import android.util.Log;
import android.os.Handler;

public class Ussd extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {

        if (action.equals("dial")) {

            String ussdCode = data.getString(0);
            int sim=data.getInt(1);
//             String name = data.getString(0);
//             String message = "Hello, " + name;
//todo add check for valid USSD code
//todo add option to request for needed permission if its not granted

            //use SIM 1
          TelephonyManager manager = (TelephonyManager) cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
          //get SIM 2 subscription ID
//            SubscriptionManager localSubscriptionManager = cordova.getActivity().SubscriptionManager.from(Context);
           SubscriptionManager localSubscriptionManager = SubscriptionManager.from(cordova.getActivity());
                  if (localSubscriptionManager.getActiveSubscriptionInfoCount() > 1) {
                      List localList = localSubscriptionManager.getActiveSubscriptionInfoList();

                      SubscriptionInfo simInfo1 = (SubscriptionInfo) localList.get(0);
                      SubscriptionInfo simInfo2 = (SubscriptionInfo) localList.get(1);


                  }
          // USE SIM 2
          TelephonyManager manager2 = manager.createForSubscriptionId(simInfo1.getSubscriptionId());

          TelephonyManager managerMain = (sim == 1) ? manager : manager2;

          managerMain.sendUssdRequest(ussdCode, new TelephonyManager.UssdResponseCallback() {
              @Override
              public void onReceiveUssdResponse(TelephonyManager telephonyManager, String request, CharSequence response) {
                  super.onReceiveUssdResponse(telephonyManager, request, response);

                  Log.e("TAG", "onReceiveUssdResponse:  Ussd Response = " + response.toString().trim() );
//                     callbackContext.success(response.toString().trim());
                    callbackContext.sendPluginResult(new PluginResult(Status.OK, response.toString().trim()));



              }

              @Override
              public void onReceiveUssdResponseFailed(TelephonyManager telephonyManager, String request, int failureCode) {
                  super.onReceiveUssdResponseFailed(telephonyManager, request, failureCode);

                  Log.e("TAG", "onReceiveUssdResponseFailed: " + "" + failureCode + request);
//                   callbackContext.error("onReceiveUssdResponseFailed: " + "" + failureCode + request);
                    callbackContext.sendPluginResult(new PluginResult(Status.ERROR,failureCode + request));


              }
          }, new Handler());
//             callbackContext.success(message);

            return true;

        } else {

            return false;

        }
    }
}
