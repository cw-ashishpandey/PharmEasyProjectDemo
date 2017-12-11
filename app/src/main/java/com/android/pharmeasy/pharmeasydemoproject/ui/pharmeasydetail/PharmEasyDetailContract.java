package com.android.pharmeasy.pharmeasydemoproject.ui.pharmeasydetail;

import android.content.Context;

import com.android.pharmeasy.pharmeasydemoproject.data.model.Data;
import com.android.pharmeasy.pharmeasydemoproject.util.mvp.IBasePresenter;
import com.android.pharmeasy.pharmeasydemoproject.util.mvp.IBaseView;

/**
 * Created by user on 12/10/2017.
 */

public interface PharmEasyDetailContract {

    interface View extends IBaseView {

        void showPharmEasyData(Data data);

        void shouldShowPlaceholderText();
    }

    interface Presenter extends IBasePresenter<View> {

        void getPharmEasyData(Context context);
    }
}
