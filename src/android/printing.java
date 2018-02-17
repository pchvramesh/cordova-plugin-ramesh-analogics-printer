package cordova.plugin.ramesh.printing;

import com.analogics.thermalprinter.AnalogicsThermalPrinter;
import com.analogics.thermalAPI.Bluetooth_Printer_2inch_ThermalAPI;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class printing extends CordovaPlugin {
    String address = null;
    String CallbackMessage = "";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("thermalPrint")) {
            CallbackMessage += "Message : " + args.getString(1) + "\n";
            String message = args.getString(1);
            CallbackMessage += "Address : " + args.getString(0) + "\n";
            address = args.getString(0);
            this.thermalPrint(message, callbackContext);
            return true;
        }
        return false;
    }

    private void thermalPrint(String message, CallbackContext callbackContext) {
        try {
            AnalogicsThermalPrinter connection = new AnalogicsThermalPrinter();
            try {
                connection.openBT(address);
                try {
                    Thread.sleep(1000);
                    try {
                        Bluetooth_Printer_2inch_ThermalAPI printer = new Bluetooth_Printer_2inch_ThermalAPI();
                        try {
                            String printdata = "";
                            printdata = printer.font_Courier_10(message);
                            printdata += printer.font_Courier_19(message);
                            printdata += printer.font_Courier_20(message);
                            printdata += printer.font_Courier_24(message);
                            printdata += printer.font_Courier_25(message);
                            printdata += printer.font_Courier_27(message);
                            printdata += printer.font_Courier_29(message);
                            printdata += printer.font_Courier_32(message);
                            printdata += printer.font_Courier_34(message);
                            printdata += printer.font_Courier_38(message);
                            printdata += printer.font_Courier_42(message);
                            printdata += printer.font_Courier_48(message);
                            try {
                                connection.printData(printdata);
                                callbackContext.success("Success");
                            } catch (Exception e) {
                                callbackContext.error("Print Data " + e.getMessage() + " Data: " + printdata);
                            }
                            connection.closeBT();
                        } catch (Exception e) {
                            callbackContext.error("Create Print Data " + e.getMessage());
                        }
                    } catch (Exception e) {
                        callbackContext.error("Bluetoorh printer object failed : " + e.getMessage());
                    }
                } catch (Exception e) {
                    callbackContext.error("Thread pool error");
                }
            } catch (Exception e) {
                callbackContext.error("Bluetoorh connectionection failed : " + e.getMessage());
            }
        } catch (Exception e) {
            callbackContext.error("Connection create failed : " + e.getMessage());
        }
    }
}
