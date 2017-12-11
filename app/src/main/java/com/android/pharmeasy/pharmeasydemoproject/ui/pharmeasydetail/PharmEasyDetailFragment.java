package com.android.pharmeasy.pharmeasydemoproject.ui.pharmeasydetail;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.pharmeasy.pharmeasydemoproject.R;
import com.android.pharmeasy.pharmeasydemoproject.data.DataRepository;
import com.android.pharmeasy.pharmeasydemoproject.data.Injector;
import com.android.pharmeasy.pharmeasydemoproject.data.model.Data;
import com.android.pharmeasy.pharmeasydemoproject.util.BaseFragmentInteractionListener;
import com.android.pharmeasy.pharmeasydemoproject.util.Properties;
import com.android.pharmeasy.pharmeasydemoproject.util.mvp.BaseView;
import com.android.pharmeasy.pharmeasydemoproject.util.threading.MainUiThread;
import com.android.pharmeasy.pharmeasydemoproject.util.threading.ThreadExecutor;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 12/10/2017.
 */

public class PharmEasyDetailFragment extends BaseView implements PharmEasyDetailContract.View {


    @BindView(R.id.tv_first_name)
    TextView tvFirstName;

    @BindView(R.id.tv_last_name)
    TextView tvLastName;

    private PharmEasyDetailContract.Presenter presenter;
    private Data data;
    private BaseFragmentInteractionListener fragmentInteractionListener;

    public PharmEasyDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            data = Parcels.unwrap(getArguments().getParcelable(Properties.BUNDLE_KEY_PHARMEASY_DATA));
        }
        ThreadExecutor threadExecutor = ThreadExecutor.getInstance();
        MainUiThread mainUiThread = MainUiThread.getInstance();
        DataRepository dataRepository = Injector.provideDataRepository(mainUiThread,
                threadExecutor);
        presenter = new PharmEasyDetailPresenter(this, dataRepository, threadExecutor, mainUiThread);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pharmeasy_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        showPharmEasyData(data);
    }

    /*private void showPharmEasyDetail(Data data) {
        presenter.getPharmEasyData(getContext().getApplicationContext());
    }*/

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentInteractionListener = (BaseFragmentInteractionListener) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onPause() {
        presenter.onViewInactive();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onViewActive(this);
    }

    @Override
    public void showPharmEasyData(Data data) {
        tvFirstName.setText(data.getFirst_name());
        tvLastName.setText(data.getLast_name());
    }


    @Override
    public void shouldShowPlaceholderText() {

    }
}
