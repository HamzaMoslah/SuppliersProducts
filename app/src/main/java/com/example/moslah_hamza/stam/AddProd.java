package com.example.moslah_hamza.stam;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Moslah_Hamza on 24/04/2017.
 */

public class AddProd extends Fragment {
    List<String> mSuppliers;
    DataBaseHandler db;
    Button fab;
    int sup;
    EditText prodname, prodqte, prodpu;
    UserLocalStore userLocalStore;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_prod, container, false);
        db = new DataBaseHandler(getActivity());
        //shared preferences class'
        userLocalStore = new UserLocalStore(getActivity());
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prodname = (EditText) view.findViewById(R.id.prod_name);
        prodpu = (EditText) view.findViewById(R.id.prod_pu);
        fab = (Button) view.findViewById(R.id.add_prod);
        Bundle bundle = getArguments();
        sup = bundle.getInt("sup");
        prodqte = (EditText) view.findViewById(R.id.prod_qte);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product product = new Product(prodname.getText().toString(), Double.valueOf(prodpu.getText().toString()));
                product.setUser(userLocalStore.getLoggedInUser().get_id());
                product.setSup(sup);
                product.setQte(Integer.valueOf(prodqte.getText().toString()));

                db.addProduct(product);
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.frame, productsInstance(), "NewFragmentTag");
                ft.commit();
            }
        });
    }

    public ProductsFragment productsInstance() {
        ProductsFragment productsFragment = new ProductsFragment();
        Bundle args = new Bundle();
        args.putInt("sup", sup);
        productsFragment.setArguments(args);

        return productsFragment;
    }
}

