package com.example.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import com.example.BridgesListAdapter;
import com.example.R;
import com.example.core.Bridge;
import com.example.core.BridgesListener;
import com.example.core.SortingOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: daniil
 * Date: 17.07.12
 * Time: 12:20
 * To change this template use File | Settings | File Templates.
 */
public class FavouriteBridgesFragment extends AbstractFragment {
    private Button chooseFavouritesButton;

    public FavouriteBridgesFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.filtered_bridges_fragment, container, false);
        ListView bridgesListView = (ListView) view.findViewById(R.id.filtered_bridges_list);
        chooseFavouritesButton = (Button) view.findViewById(R.id.chouse_favourites_button);
        chooseFavouritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.showFavouriteDialog();
            }
        });
        updateButtonVisibility();
        initListView(bridgesListView);
        return view;
    }

    @Override
    public void onAttach(Activity bridgesActivity) {
        super.onAttach(bridgesActivity);
    }

    private void updateButtonVisibility() {
        chooseFavouritesButton.setVisibility(adapter.getCount() == 0 ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    protected BridgesListAdapter createAdapter() {
        List<Bridge> result = new ArrayList<Bridge>();
        for (Bridge bridge : bridgesList) {
            if (bridge.isFavourite()) {
                result.add(bridge);
            }
        }
        return new BridgesListAdapter(activity, result);
    }

    @Override
    public void sortingOrderChanged(SortingOrder order) {
        super.sortingOrderChanged(order);
    }

    @Override
    public void updated(Bridge bridge) {
        if (bridge.isFavourite()) {
            adapter.add(bridge);
        } else {
            adapter.remove(bridge);
        }
        adapter.sort(sortingOrder);
        updateButtonVisibility();
        updated();
    }
}
