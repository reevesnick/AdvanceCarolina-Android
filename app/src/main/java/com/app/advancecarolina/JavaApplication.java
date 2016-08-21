package com.app.advancecarolina;

import android.app.Application;

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
    }
}
