package com.android.pharmeasy.pharmeasydemoproject.ui.pharmeasylist;

import android.content.Context;

import com.android.pharmeasy.pharmeasydemoproject.data.model.PharmEasyDataResponse;
import com.android.pharmeasy.pharmeasydemoproject.util.mvp.IBasePresenter;
import com.android.pharmeasy.pharmeasydemoproject.util.mvp.IBaseView;

/**
 * Created by user on 12/10/2017.
 */

public interface PharmEasyListContract {

    interface View extends IBaseView {

        void showPharmEasyListData(PharmEasyDataResponse listPharmEasyData);

        void shouldShowPlaceholderText();
    }

    interface Presenter extends IBasePresenter<View> {

        void getPharmEasyListData(Context context, int page);
    }
}
