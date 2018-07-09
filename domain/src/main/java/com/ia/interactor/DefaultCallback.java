package com.ia.interactor;


import com.ia.exception.ErrorBundle;

public interface DefaultCallback<T> {

    void onError(ErrorBundle errorBundle);

    void onSuccess(T returnParam);
}
