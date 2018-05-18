package com.bypay.yifu.bean;

/**
 * Created by tc on 2017/7/7.
 */

public class BaseBeanClass<T> extends BaseBeanInfo {

    private T result;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "BaseBeanClass{" +
                "result=" + result +
                '}';
    }
}
