package com.ia.moviedb;

import android.os.Handler;
import android.os.Looper;

import com.ia.executor.PostExecutionThread;

public class UIThread implements PostExecutionThread{

    private final Handler handler;

    public UIThread() {
        this.handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void post(Runnable runnable) {
        handler.post(runnable);
    }
}
