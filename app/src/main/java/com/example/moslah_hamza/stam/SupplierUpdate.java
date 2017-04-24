package com.example.moslah_hamza.stam;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Moslah_Hamza on 24/04/2017.
 */

public class SupplierUpdate extends Fragment {
    DataBaseHandler db;
    UserLocalStore userLocalStore;
    String name;
    int id;
    EditText nameText, telText, adText;
    Button delbt, upbt, prod;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.supplier_update, container, false);
        db = new DataBaseHandler(getActivity());
        //shared preferences class'
        userLocalStore = new UserLocalStore(getActivity());
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nameText = (EditText) view.findViewById(R.id.sup_name);
        adText = (EditText) view.findViewById(R.id.sup_adress);
        telText = (EditText) view.findViewById(R.id.sup_tel);
        upbt = (Button) view.findViewById(R.id.update_sup);
        delbt = (Button) view.findViewById(R.id.delete_sup);
        prod = (Button) view.findViewById(R.id.list_prod);
        Bundle bundle = getArguments();
        name = bundle.getString("name");
        String tel = bundle.getString("tel");
        String adress = bundle.getString("adress");
        nameText.setText(name);
        telText.setText(tel);
        adText.setText(adress);
        id = bundle.getInt("id");

        upbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.updateSupplier(nameText.getText().toString(), id, telText.getText().toString(), adText.getText().toString());
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.frame, new SuppliersFragment(), "NewFragmentTag");
                ft.commit();
            }
        });

        delbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteSupplier(id);
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.frame, new SuppliersFragment(), "NewFragmentTag");
                ft.commit();
            }
        });

        prod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.frame, productsInstance(), "NewFragmentTag");
                ft.commit();
            }
        });
    }

    public ProductsFragment productsInstance() {
        ProductsFragment productsFragment = new ProductsFragment();
        Bundle args = new Bundle();
        args.putInt("sup", id);
        productsFragment.setArguments(args);

        return productsFragment;
    }
}