package com.example.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.example.BridgesActivity;
import com.example.BridgesListAdapter;
import com.example.R;
import com.example.core.BridgesList;

/**
 * Created with IntelliJ IDEA.
 * User: daniil
 * Date: 17.07.12
 * Time: 12:05
 * To change this template use File | Settings | File Templates.
 */
public class AllBridgesFragment extends AbstractFragment {

    public AllBridgesFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.all_bridges_fragment, container, false);
        ListView bridgesListView = (ListView) view.findViewById(R.id.all_bridges_list);
        bridgesListView.setAdapter(adapter);
        return view;
    }

    @Override
    protected BridgesListAdapter createAdapter() {
        return new BridgesListAdapter(activity, bridgesList);
    }
}
