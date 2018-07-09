package com.ia.interactor;


public interface Interactor<InputType, ReturnType> extends Runnable {

    void run();

    <R extends DefaultCallback<ReturnType>> void execute(InputType input, R defaultCallback);
}
