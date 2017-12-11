package com.android.pharmeasy.pharmeasydemoproject.util.mvp;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.pharmeasy.pharmeasydemoproject.R;

import butterknife.BindView;

/**
 * Created by user on 12/10/2017.
 */

public abstract class BaseView extends Fragment implements IBaseView {
    @Nullable
    @BindView(R.id.pb_loader)
    protected ProgressBar progressBar;

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setProgressBar(boolean show) {
        if (progressBar != null) {
            if (show) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }
        }
    }
}