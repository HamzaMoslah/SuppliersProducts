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

import java.util.List;

/**
 * Created by Moslah_Hamza on 24/04/2017.
 */

public class ProdUpdate extends Fragment {
    List<String> mSuppliers;
    DataBaseHandler db;
    Button fab, del;
    int sup, qte, id;
    double pu;
    String name;
    EditText prodname, prodqte, prodpu;
    UserLocalStore userLocalStore;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.prod_update, container, false);
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
        fab = (Button) view.findViewById(R.id.up_prod);
        del = (Button) view.findViewById(R.id.del_prod);
        prodqte = (EditText) view.findViewById(R.id.prod_qte);
        Bundle bundle = getArguments();
        sup = bundle.getInt("sup");
        name = bundle.getString("name");
        qte = bundle.getInt("qte");
        id = bundle.getInt("id");
        pu = bundle.getDouble("pu");
        prodname.setText(name);
        prodpu.setText(""+pu);
        prodqte.setText(""+qte);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.updateProduct(prodname.getText().toString(), id,
                        Double.valueOf(prodpu.getText().toString()), Integer.valueOf(prodqte.getText().toString()));
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.frame, productsInstance(), "NewFragmentTag");
                ft.commit();
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteProduct(id);

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
