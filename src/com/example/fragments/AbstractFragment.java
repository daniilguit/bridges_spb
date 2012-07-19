package com.example.fragments;

import android.app.Activity;
import android.os.Handler;
import com.actionbarsherlock.app.SherlockFragment;
import com.example.BridgesActivity;
import com.example.BridgesListAdapter;
import com.example.OptionsListener;
import com.example.core.Bridge;
import com.example.core.BridgesList;
import com.example.core.BridgesListener;
import com.example.core.SortingOrder;

/**
 * Created with IntelliJ IDEA.
 * User: daniil
 * Date: 17.07.12
 * Time: 22:11
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractFragment extends SherlockFragment implements OptionsListener, BridgesListener {
    protected BridgesActivity activity;
    protected BridgesList bridgesList;
    protected BridgesListAdapter adapter;
    protected Handler updateHandler = new Handler();

    protected SortingOrder sortingOrder;

    protected AbstractFragment() {
        super();
    }

    @Override
    public void onAttach(Activity bridgesActivity) {
        super.onAttach(bridgesActivity);
        this.activity = (BridgesActivity) bridgesActivity;
        bridgesList = activity.getBridgesList();
        bridgesList.addListener(this);
        activity.addOptionsListener(this);
        adapter = createAdapter();
        sortingOrder = activity.getSortingOrder();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity.removeOptionsListener(this);
        bridgesList.removeListener(this);
    }

    protected abstract BridgesListAdapter createAdapter() ;

    @Override
    public void sortingOrderChanged(SortingOrder order) {
        this.sortingOrder = order;
        adapter.sort(order);
        adapter.notifyDataSetChanged();
    }

    protected void updateInUi() {
        adapter.notifyDataSetChanged();
    }

    private final Runnable updateRunnable = new Runnable() {
        @Override
        public void run() {
            updateInUi();
        }
    };

    @Override
    public void updated() {
        updateHandler.post(updateRunnable);
    }

    @Override
    public void updated(Bridge bridge) {
    }
}
