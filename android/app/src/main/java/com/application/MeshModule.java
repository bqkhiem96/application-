package com.application;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import com.hypelabs.hype.Error;
import com.hypelabs.hype.Hype;
import com.hypelabs.hype.Instance;
import com.hypelabs.hype.Message;
import com.hypelabs.hype.MessageInfo;
import com.hypelabs.hype.MessageObserver;
import com.hypelabs.hype.NetworkObserver;
import com.hypelabs.hype.StateObserver;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by bqkhiem96 on 04-Mar-18.
 */

public class MeshModule extends ReactContextBaseJavaModule implements StateObserver, NetworkObserver, MessageObserver  {


    ReactApplicationContext applicationContext = getReactApplicationContext();

    public MeshModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "MeshModule";
    }

    private static final String TAG = MeshModule.class.getName();
    ReactApplicationContext context = getReactApplicationContext();

    @Override
    public void onMessageReceived(Hype hype, Message message, Instance instance) {

    }

    @Override
    public void onMessageFailedSending(Hype hype, MessageInfo messageInfo, Instance instance, Error error) {
        Log.i(TAG, String.format("Failed to send message: %d [%s]", messageInfo.getIdentifier(), error.getDescription()));
    }



    @Override
    public void onMessageSent(Hype hype, MessageInfo messageInfo, Instance instance, float progress, boolean b) {
        Log.i(TAG, String.format("Message being sent: %f", progress));
    }

    @Override
    public void onMessageDelivered(Hype hype, MessageInfo messageInfo, Instance instance, float progress, boolean b) {
        Log.i(TAG, String.format("Message being delivered: %f", progress));
    }

    @Override
    public void onInstanceFound(Hype hype, Instance instance) {
        Log.i(TAG, String.format("Found instance: %s", instance.getStringIdentifier()));

    }

    @Override
    public void onInstanceLost(Hype hype, Instance instance, Error error) {
        Log.i(TAG, String.format("Lost instance: %s [%s]", instance.getStringIdentifier(), error.getDescription()));

    }

    @Override
    public void onStart(Hype hype) {
        Log.i(TAG, "Hype started!");
    }

    @Override
    public void onStop(Hype hype, Error error) {
        String description = "";
        if (error != null) {
            description = String.format("[%s]", error.getDescription());
        }
        Log.i(TAG, String.format("Hype stopped [%s]", description));
    }

    @Override
    public void onFailedStarting(Hype hype, Error error) {
        Log.i(TAG, String.format("Hype failed starting [%s]", error.getDescription()));
    }

    @Override
    public void onReady(Hype hype) {
        requestHypeToStart();
    }

    private void requestHypeToStart() {
        Hype.getInstance().setContext(applicationContext);
        Hype.getInstance().addStateObserver(this);
        Hype.getInstance().addNetworkObserver(this);
        Hype.getInstance().addMessageObserver(this);
        Hype.getInstance().start(new HashMap<String, Object>() {{

            put(Hype.OptionRealmKey, "f0e3f9c5");
        }});
    }
    protected void requestHypeToStop() {
        Hype.getInstance().stop();
    }

    @Override
    public void onStateChange(Hype hype) {
        Hype.getInstance().setContext(applicationContext);

    }
}
