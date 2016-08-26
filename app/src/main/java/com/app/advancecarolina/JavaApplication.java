package com.app.advancecarolina;

import android.app.Application;

import com.batch.android.Batch;
import com.batch.android.Config;
import com.parse.Parse;

/**
 * Created by neegbeahreeves on 8/20/16.
 */
public class JavaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //getApplicationContext();


        // Parse SDK
        Parse.initialize(new Parse.Configuration.Builder(this.getApplicationContext())
                .applicationId("Lvkh2vw9sSKCLk1R8SfKKlUZGUSFoEbMaLr0Fogf")
                .clientKey("VqzatKxu4742ZATP9mdgRSo7awrxLoBV8wBftgMw")
                .server("https://parseapi.back4app.com/")


                .build()
        );

        Batch.Push.setGCMSenderId("730703668742");

        // TODO : switch to live Batch Api Key before shipping
        Batch.setConfig(new Config("DEV57BFAB319F7F4B7BB0F3471EFFB")); // devloppement
        //Batch.setConfig(new Config("57BFAB31979A20ED50845D081BDB96")); // live
    }
}
