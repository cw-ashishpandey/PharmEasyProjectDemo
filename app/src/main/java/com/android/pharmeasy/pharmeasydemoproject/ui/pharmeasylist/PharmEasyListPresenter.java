package com.android.pharmeasy.pharmeasydemoproject.ui.pharmeasylist;

import android.content.Context;

import com.android.pharmeasy.pharmeasydemoproject.R;
import com.android.pharmeasy.pharmeasydemoproject.data.DataRepository;
import com.android.pharmeasy.pharmeasydemoproject.data.DataSource;
import com.android.pharmeasy.pharmeasydemoproject.data.model.PharmEasyDataResponse;
import com.android.pharmeasy.pharmeasydemoproject.util.mvp.BasePresenter;
import com.android.pharmeasy.pharmeasydemoproject.util.threading.MainUiThread;
import com.android.pharmeasy.pharmeasydemoproject.util.threading.ThreadExecutor;

/**
 * Created by Admin on 12/10/2017.
 */

public class PharmEasyListPresenter extends BasePresenter<PharmEasyListContract.View> implements
        PharmEasyListContract.Presenter {

    private ThreadExecutor threadExecutor;
    private DataRepository dataRepository;
    private MainUiThread mainUiThread;

    public PharmEasyListPresenter(PharmEasyListContract.View view, DataRepository dataRepository,
                                  ThreadExecutor threadExecutor, MainUiThread mainUiThread) {
        this.view = view;
        this.dataRepository = dataRepository;
        this.threadExecutor = threadExecutor;
        this.mainUiThread = mainUiThread;
    }

    @Override
    public void getPharmEasyListData(final Context context, int page) {
        if (view == null) {
            return;
        }

        view.setProgressBar(true);

        dataRepository.getPharmEasyListData(context, page, new DataSource.GetPharmEasyListCallback() {
            @Override
            public void onSuccess(PharmEasyDataResponse listPharmEasyData) {
                if (view != null) {
                    view.showPharmEasyListData(listPharmEasyData);
                    view.setProgressBar(false);
                    view.shouldShowPlaceholderText();
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                if (view != null) {
                    view.setProgressBar(false);
                    view.showToastMessage(context.getString(R.string.error_msg));
                    view.shouldShowPlaceholderText();
                }
            }

            @Override
            public void onNetworkFailure() {
                if (view != null) {
                    view.setProgressBar(false);
                    view.showToastMessage(context.getString(R.string.network_failure_msg));
                    view.shouldShowPlaceholderText();
                }
            }
        });
    }
}