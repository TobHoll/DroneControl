package com.example.tobiashollarek.dronecontrol;

import android.util.Log;

/**
 * Created by TobiasHollarek on 07.07.2017.
 */

class DroneAPI {

    DronePositionCallback dronePositionCallback;

    public DroneAPI(DronePositionCallback dronePositionCallback){
        this.dronePositionCallback = dronePositionCallback;
    }

    public void flyTo(final Position position) {
        // dummy code to simulate network call
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Log.d(getClass().getName(), "sending drone to position " + position.toString());
                // TODO: network call to tell drone to fly to position "position"
                Log.d(this.getClass().getName(), "drone landed in position " + position.toString() + "... informing UI");
                dronePositionCallback.onPositionReached(position);
            }
        };
        runnable.run();
    }
}
