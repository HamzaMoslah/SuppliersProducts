package com.example.moslah_hamza.stam;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Moslah_Hamza on 17/04/2017.
 */

public class ProductsFragment extends Fragment {
    List<Product> mProducts;
    DataBaseHandler db;
    int supid;
    FloatingActionButton add;
    UserLocalStore userLocalStore;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.suppliers, container, false);
        db = new DataBaseHandler(getActivity());
        //shared preferences class'
        userLocalStore = new UserLocalStore(getActivity());
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        supid = bundle.getInt("sup");
        add = (FloatingActionButton) view.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.frame, productsInstance(), "NewFragmentTag");
                ft.commit();
            }
        });

        final RecyclerView rv = (RecyclerView) view.findViewById(R.id.supp_view);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(mLayoutManager);
        mProducts = new ArrayList<>();
        mProducts = db.getAllUserProduct(userLocalStore.getLoggedInUser().get_id(),supid);

        rv.setAdapter(new ProdAdapter(mProducts, getActivity(),this, supid));
    }

    public AddProd productsInstance() {
        AddProd productsFragment = new AddProd();
        Bundle args = new Bundle();
        args.putInt("sup", supid);
        productsFragment.setArguments(args);

        return productsFragment;
    }
}
