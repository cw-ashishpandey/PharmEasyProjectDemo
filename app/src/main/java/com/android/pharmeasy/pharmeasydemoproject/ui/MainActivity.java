package com.android.pharmeasy.pharmeasydemoproject.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.android.pharmeasy.pharmeasydemoproject.AppController;
import com.android.pharmeasy.pharmeasydemoproject.R;
import com.android.pharmeasy.pharmeasydemoproject.ui.pharmeasylist.PharmEasyListFragment;
import com.android.pharmeasy.pharmeasydemoproject.util.BaseActivity;
import com.android.pharmeasy.pharmeasydemoproject.util.BaseFragmentInteractionListener;
import com.android.pharmeasy.pharmeasydemoproject.util.NetworkHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.net.ConnectivityManager.CONNECTIVITY_ACTION;

/**
 * Created by user on 12/10/2017.
 */

public class MainActivity extends BaseActivity implements BaseFragmentInteractionListener, NetworkHelper.ConnectivityReceiverListener {

    @BindView(R.id.fl_place_holder)
    FrameLayout fragmentPlaceholder;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_offline_mode)
    TextView tvOfflineMode;

    @BindView(R.id.appbarlayout)
    AppBarLayout appBarLayout;

    private IntentFilter connectivityIntentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        showFragment(PharmEasyListFragment.class);
    }


    @Override
    protected void onResume() {
        super.onResume();
        AppController.getInstance().setConnectivityListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if (isConnected) {
            tvOfflineMode.setVisibility(View.VISIBLE);
        } else {
            tvOfflineMode.setVisibility(View.GONE);
        }
    }
}