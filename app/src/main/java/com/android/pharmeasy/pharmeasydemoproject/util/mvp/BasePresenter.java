package com.android.pharmeasy.pharmeasydemoproject.util.mvp;

/**
 * Created by user on 12/10/2017.
 */

public class BasePresenter<ViewT> implements IBasePresenter<ViewT> {

    protected ViewT view;

    @Override
    public void onViewActive(ViewT view) {
        this.view = view;
    }

    @Override
    public void onViewInactive() {
        view = null;
    }
}