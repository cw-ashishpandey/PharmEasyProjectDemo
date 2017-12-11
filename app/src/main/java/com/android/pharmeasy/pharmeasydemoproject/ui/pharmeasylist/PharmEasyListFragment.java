package com.android.pharmeasy.pharmeasydemoproject.ui.pharmeasylist;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.pharmeasy.pharmeasydemoproject.AppController;
import com.android.pharmeasy.pharmeasydemoproject.R;
import com.android.pharmeasy.pharmeasydemoproject.data.DataRepository;
import com.android.pharmeasy.pharmeasydemoproject.data.Injector;
import com.android.pharmeasy.pharmeasydemoproject.data.model.Data;
import com.android.pharmeasy.pharmeasydemoproject.data.model.PharmEasyDataResponse;
import com.android.pharmeasy.pharmeasydemoproject.ui.pharmeasydetail.PharmEasyDetailFragment;
import com.android.pharmeasy.pharmeasydemoproject.util.BaseFragmentInteractionListener;
import com.android.pharmeasy.pharmeasydemoproject.util.EndlessRecyclerViewScrollListener;
import com.android.pharmeasy.pharmeasydemoproject.util.ItemClickSupport;
import com.android.pharmeasy.pharmeasydemoproject.util.NetworkHelper;
import com.android.pharmeasy.pharmeasydemoproject.util.Properties;
import com.android.pharmeasy.pharmeasydemoproject.util.mvp.BaseView;
import com.android.pharmeasy.pharmeasydemoproject.util.threading.MainUiThread;
import com.android.pharmeasy.pharmeasydemoproject.util.threading.ThreadExecutor;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 11/10/2017.
 */

public class PharmEasyListFragment extends BaseView implements PharmEasyListContract.View, NetworkHelper.ConnectivityReceiverListener {

    @BindView(R.id.rv_list_page)
    RecyclerView rvListPage;

    @BindView(R.id.tv_place_holder)
    TextView tvPlaceholder;

    @BindView(R.id.srl_list_page)
    SwipeRefreshLayout swipeRefreshLayout;


    public static final int STARTING_PAGE_INDEX = 1;

    private PharmEasyListAdapter recyclerAdapter;
    private List<Data> pharmEasyData;
    private EndlessRecyclerViewScrollListener endlessScrollListener;
    private PharmEasyListContract.Presenter presenter;
    private BaseFragmentInteractionListener fragmentInteractionListener;
    private boolean shouldRefreshList, loadMore = true;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pharmEasyData = new ArrayList<>();
        ThreadExecutor threadExecutor = ThreadExecutor.getInstance();
        MainUiThread mainUiThread = MainUiThread.getInstance();
        DataRepository dataRepository = Injector.provideDataRepository(mainUiThread, threadExecutor);
        presenter = new PharmEasyListPresenter(this, dataRepository, threadExecutor, mainUiThread);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_page, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        recyclerAdapter = new PharmEasyListAdapter(this, pharmEasyData);
        rvListPage.setAdapter(recyclerAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvListPage.setLayoutManager(linearLayoutManager);

        endlessScrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager,
                STARTING_PAGE_INDEX) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (loadMore) {
                    getPharmEasyListData(page);
                }
            }
        };

        rvListPage.addOnScrollListener(endlessScrollListener);

        ItemClickSupport.addTo(rvListPage).setOnItemClickListener(
                new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        showDetailFragment(position);
                    }
                });


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadMore = true;
                refreshPharmEasyData();
            }
        });

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimaryDark, R.color.colorPrimary);

        getPharmEasyListData(STARTING_PAGE_INDEX);
    }

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
    public void showPharmEasyListData(PharmEasyDataResponse listPharmEasyData) {
        if (shouldRefreshList) {
            recyclerAdapter.clear();
            endlessScrollListener.resetState();
            shouldRefreshList = false;
        }
        if (listPharmEasyData.getData() == null || listPharmEasyData.getData().size() == 0) {
            loadMore = false;
        } else {
            recyclerAdapter.addAll(listPharmEasyData.getData());
        }
    }

    @Override
    public void shouldShowPlaceholderText() {
        if (pharmEasyData.isEmpty()) {
            tvPlaceholder.setVisibility(View.VISIBLE);
        } else {
            tvPlaceholder.setVisibility(View.GONE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onViewActive(this);
        AppController.getInstance().setConnectivityListener(this);
        //fragmentInteractionListener.resetToolBarScroll();
    }

    @Override
    public void onPause() {
        presenter.onViewInactive();
        super.onPause();
    }

    private void getPharmEasyListData(int page) {
        presenter.getPharmEasyListData(getContext().getApplicationContext(), page);
    }

    private void refreshPharmEasyData() {
        shouldRefreshList = true;
        getPharmEasyListData(STARTING_PAGE_INDEX);
    }

    @Override
    public void setProgressBar(boolean show) {
        swipeRefreshLayout.setRefreshing(show);
    }

    private void showDetailFragment(int photoPosition) {
        Data data = pharmEasyData.get(photoPosition);
        Parcelable parcelable = Parcels.wrap(data);
        Bundle bundle = new Bundle();
        bundle.putParcelable(Properties.BUNDLE_KEY_PHARMEASY_DATA, parcelable);
        fragmentInteractionListener.showFragment(PharmEasyDetailFragment.class, bundle,
                true);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if (loadMore) {
            refreshPharmEasyData();
        }
    }
}