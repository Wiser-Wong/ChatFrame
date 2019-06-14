package com.wiser.chatframe;

import android.app.Application;

public class ChatApplication extends Application {

    private static ChatApplication instance;

    private int keyboardHeight;

    public void setKeyboardHeight(int keyboardHeight){
        this.keyboardHeight = keyboardHeight;
    }

    public int getKeyboardHeight() {
        return keyboardHeight;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static ChatApplication getInstance() {
        return instance;
    }
}
