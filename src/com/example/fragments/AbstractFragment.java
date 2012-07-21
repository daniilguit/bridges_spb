package com.example.fragments;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.actionbarsherlock.app.SherlockFragment;
import com.example.BridgesActivity;
import com.example.BridgesListAdapter;
import com.example.OptionsListener;
import com.example.core.*;

import static com.example.LocationUtil.locationUri;

/**
 * Created with IntelliJ IDEA.
 * User: daniil
 * Date: 17.07.12
 * Time: 22:11
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractFragment extends SherlockFragment implements OptionsListener, BridgesListener, AdapterView.OnItemClickListener {
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

    protected abstract BridgesListAdapter createAdapter();

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
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        BridgeDescription bridgeDescription = adapter.getItem(position).getDescription();
        Location bridgeLocation = bridgeDescription.location;
        Intent locationIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(locationUri(bridgeLocation, 15)));
        activity.startActivity(locationIntent);
    }

    @Override
    public void updated() {
        updateHandler.post(updateRunnable);
    }

    @Override
    public void updated(Bridge bridge) {
    }

    protected void initListView(ListView bridgesListView) {
        bridgesListView.setAdapter(adapter);
        bridgesListView.setOnItemClickListener(this);
    }
}
