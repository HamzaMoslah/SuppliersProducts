package com.example.moslah_hamza.stam;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Moslah_Hamza on 17/04/2017.
 */

public class ProductsFragment extends Fragment {
    List<String> mProducts;
    DataBaseHandler db;
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

        final RecyclerView rv = (RecyclerView) view.findViewById(R.id.supp_view);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(mLayoutManager);
        mProducts = new ArrayList<>();

//        List<Product> productList = db.getAllUserProduct(userLocalStore.getLoggedInUser().get_id());
//        for(Product product : productList)
//            mProducts.add(product.get_name());

        rv.setAdapter(new MyAdapter(mProducts));
    }
}
