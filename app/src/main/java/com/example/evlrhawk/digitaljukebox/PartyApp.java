package com.example.evlrhawk.digitaljukebox;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class PartyApp extends Application {
    /**
     * Log or request TAG
     */
    public static final String TAG = "VolleyPatterns";

    /**
     * Global request queue for Volley
     */
    private RequestQueue mRequestQueue;

    /**
     * A singleton instance of the application class for easy access in other places
     */
    private static PartyApp sInstance;
    private static Bus mBus;

    @Override
    public void onCreate() {
        super.onCreate();

        // initialize the singleton
        sInstance = this;
    }

    /**
     * @return PartyApp singleton instance
     */
    public static synchronized PartyApp getInstance() {
        return sInstance;
    }

    /**
     * @return The Volley SongRequest queue, the queue will be created if it is null
     */
    public RequestQueue getRequestQueue() {
        // lazy initialize the request queue, the queue instance will be
        // created when it is accessed for the first time
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    /**
     * @return The Otto Event Bus
     */
    public Bus getBus() {
        // lazy initialize the bus, the bus instance will be
        // created when it is accessed for the first time
        if (mBus == null) {
            mBus = new Bus();
        }

        return mBus;
    }

    /**
     * Adds the specified request to the global queue, if tag is specified
     * then it is used else Default TAG is used.
     *  @param req Volley Request
     * @param tag Activity Tag
     */
    public <T> void addToRequestQueue(StringRequest req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);

        VolleyLog.d("Adding request to queue: %s", req.getUrl());

        getRequestQueue().add(req);
    }

    /**
     * Adds the specified request to the global queue using the Default TAG.
     *
     * @param req Volley Request
     */
    public <T> void addToRequestQueue(StringRequest req) {
        // set the default tag if tag is empty
        req.setTag(TAG);

        getRequestQueue().add(req);
    }

    /**
     * Cancels all pending requests by the specified TAG, it is important
     * to specify a TAG so that the pending/ongoing requests can be cancelled.
     *
     * @param tag Activity Tag
     */
    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}
