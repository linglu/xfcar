package com.xfcar.driver.network;

/**
 * @author Linky
 *
 */
public interface ResultCallback<T> {

    void onSuccess(T t);
    void onFail(String msg);
}
