package com.lucas.oz.eventify;

import com.parse.Parse;
import android.app.Application;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("xWDBLv8bLWKdpAVtR2JSoO0aMLGeZVcy3ZvVcBHp")
                // if defined
                .clientKey("KSYPKCiw20lUrv0kv8R04wacHfMRDvgCfNXR7pM7")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}