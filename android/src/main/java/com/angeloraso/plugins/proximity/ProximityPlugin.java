package com.angeloraso.plugins.proximity;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "Proximity")
public class ProximityPlugin extends Plugin {

    private Proximity proximity;

    public void load() {
        AppCompatActivity activity = getActivity();
        Context context = getContext();
        proximity = new Proximity(activity, context);
    }

    @PluginMethod
    public void getProximity(PluginCall call) {
        SensorResult sensorResult = proximity.getProximity();
        if (sensorResult != null) {
            JSObject res = new JSObject();
            res.put("proximity", sensorResult.name());
            call.resolve(res);
        } else {
            String pluginNotRunningMsg = getActivity().getString(R.string.plugin_not_running);
            call.reject(pluginNotRunningMsg);
        }
    }

    @PluginMethod
    public void getStatus(PluginCall call) {
        SensorStatus sensorStatus = proximity.getStatus();
        JSObject res = new JSObject();
        res.put("status", sensorStatus.name());
    }

    @PluginMethod
    public void start(PluginCall call) {
        if (getActivity().isFinishing()) {
            String appFinishingMsg = getActivity().getString(R.string.app_finishing);
            call.reject(appFinishingMsg);
            return;
        }

        proximity.start();
        call.resolve();
    }

    @PluginMethod
    public void stop(PluginCall call) {
        proximity.stop();
        call.resolve();
    }

    /**
     * Called when the activity will be destroyed.
     */
    @Override
    public void handleOnDestroy() {
        proximity.onDestroy();
    }
}
