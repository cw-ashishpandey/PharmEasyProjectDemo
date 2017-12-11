package com.android.pharmeasy.pharmeasydemoproject.util.mvp;

import android.content.Context;

/**
 * Created by user on 12/10/2017.
 */

public interface IBaseView {

    void showToastMessage(String message);

    void setProgressBar(boolean show);

    Context getContext();
}
