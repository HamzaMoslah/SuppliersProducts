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
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Moslah_Hamza on 23/04/2017.
 */

public class AddSupplier extends Fragment {
    List<String> mSuppliers;
    DataBaseHandler db;
    Button fab;
    EditText supname, suptel, supad;
    UserLocalStore userLocalStore;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_supplier, container, false);
        db = new DataBaseHandler(getActivity());
        //shared preferences class'
        userLocalStore = new UserLocalStore(getActivity());
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        supad = (EditText) view.findViewById(R.id.sup_adress);
        suptel = (EditText) view.findViewById(R.id.sup_tel);
        fab = (Button) view.findViewById(R.id.add_sup);
        supname = (EditText) view.findViewById(R.id.sup_name);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Supplier supplier = new Supplier(supname.getText().toString(), userLocalStore.getLoggedInUser().get_id());
                supplier.setTel(suptel.getText().toString());
                supplier.setAdress(supad.getText().toString());
                String DATE_FORMAT = "yyyy-MM-dd";
                Date date = new Date();
                DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
                supplier.setCreated_at(dateFormat.format(date));
                db.addSupplier(supplier);
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.frame, new SuppliersFragment(), "NewFragmentTag");
                ft.commit();
            }
        });
    }
}
