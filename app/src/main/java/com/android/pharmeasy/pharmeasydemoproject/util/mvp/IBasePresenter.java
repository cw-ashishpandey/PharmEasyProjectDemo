package com.android.pharmeasy.pharmeasydemoproject.util.mvp;

/**
 * Created by user on 12/10/2017.
 */

public interface IBasePresenter<ViewT> {

    void onViewActive(ViewT view);

    void onViewInactive();
}