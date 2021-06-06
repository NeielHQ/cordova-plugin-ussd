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
import android.content.pm.PackageManager;
import android.util.Log;
import android.os.Handler;

public class Ussd extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {

        if (action.equals("dial")) {

            String code = data.getString(0);
//             String name = data.getString(0);
//             String message = "Hello, " + name;

          TelephonyManager manager = (TelephonyManager) cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
          TelephonyManager manager2 = manager.createForSubscriptionId(2);

          TelephonyManager managerMain = (sim == 0) ? manager : manager2;

          managerMain.sendUssdRequest(ussdCode, new TelephonyManager.UssdResponseCallback() {
              @Override
              public void onReceiveUssdResponse(TelephonyManager telephonyManager, String request, CharSequence response) {
                  super.onReceiveUssdResponse(telephonyManager, request, response);

                  Log.e("TAG", "onReceiveUssdResponse:  Ussd Response = " + response.toString().trim() );
                    callbackContext.success(new PluginResult(PluginResult.Status.OK, response.toString().trim()));




              }

              @Override
              public void onReceiveUssdResponseFailed(TelephonyManager telephonyManager, String request, int failureCode) {
                  super.onReceiveUssdResponseFailed(telephonyManager, request, failureCode);

                  Log.e("TAG", "onReceiveUssdResponseFailed: " + "" + failureCode + request);
                  callbackContext.error(new PluginResult(PluginResult.Status.OK, "We Bad"));

              }
          }, new Handler());
//             callbackContext.success(message);

            return true;

        } else {

            return false;

        }
    }
}
