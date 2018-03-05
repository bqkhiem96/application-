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

public class MeshModule extends ReactContextBaseJavaModule {
    ReactApplicationContext context = getReactApplicationContext();
    public MeshModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }
    @Override
    public String getName() {
        return "MeshModule";
    }


    public void onApplicationStart(Application app) {

        requestHypeToStart();
    }
    public void onApplicationStop(Application app) {

        requestHypeToStop();
    }

    private void requestHypeToStop() {
    }

    private void requestHypeToStart() {

        Hype.getInstance().setContext(context);
        Hype.getInstance().addStateObserver((StateObserver) this);

    }


}
