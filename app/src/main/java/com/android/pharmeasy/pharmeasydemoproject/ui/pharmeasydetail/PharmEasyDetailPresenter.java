package com.android.pharmeasy.pharmeasydemoproject.ui.pharmeasydetail;

import android.content.Context;

import com.android.pharmeasy.pharmeasydemoproject.data.DataRepository;
import com.android.pharmeasy.pharmeasydemoproject.util.mvp.BasePresenter;
import com.android.pharmeasy.pharmeasydemoproject.util.threading.MainUiThread;
import com.android.pharmeasy.pharmeasydemoproject.util.threading.ThreadExecutor;

/**
 * Created by Admin on 12/10/2017.
 */

public class PharmEasyDetailPresenter extends BasePresenter<PharmEasyDetailContract.View> implements
        PharmEasyDetailContract.Presenter {

    private ThreadExecutor threadExecutor;
    private DataRepository dataRepository;
    private MainUiThread mainUiThread;

    public PharmEasyDetailPresenter(PharmEasyDetailContract.View view, DataRepository dataRepository,
                                    ThreadExecutor threadExecutor, MainUiThread mainUiThread) {
        this.view = view;
        this.dataRepository = dataRepository;
        this.threadExecutor = threadExecutor;
        this.mainUiThread = mainUiThread;
    }

    @Override
    public void getPharmEasyData(final Context context) {
        if (view == null) {
            return;
        }

        // view.setProgressBar(true);

        /*dataRepository.getPharmEasyDetailData(context, new DataSource.GetPharmEasyDetailCallback() {
            @Override
            public void onSuccess(Data data) {
                if (view != null) {
                    view.showPharmEasyData(data);
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
        });*/
    }
}