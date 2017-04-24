package com.example.moslah_hamza.stam;

/**
 * Created by Moslah_Hamza on 20/04/2017.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Moslah_Hamza on 08/02/2017.
 */

public class SupAdapter extends RecyclerView.Adapter<SupAdapter.MyViewHolder> {
    private List<Supplier> mtitles;
    private Context context;
    private Fragment fragment;

    public SupAdapter(List<Supplier> myFilieres, Context context, Fragment fragment) {
        mtitles = myFilieres;
        this.context = context;
        this.fragment = fragment;
    }

    @Override
    public SupAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_cell, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.name.setText(mtitles.get(position).get_name());
    }

    @Override
    public int getItemCount() {
        return mtitles.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;

        public MyViewHolder(final View itemView) {
            super(itemView);
            name = ((TextView) itemView.findViewById(R.id.name));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, name.getText().toString(), Toast.LENGTH_LONG).show();
                    final FragmentTransaction ft = fragment.getFragmentManager().beginTransaction();
                    ft.replace(R.id.frame, updateInstance(name.getText().toString()), "NewFragmentTag");
                    ft.commit();
                }
            });
        }

        public SupplierUpdate updateInstance(String name) {
            SupplierUpdate supplierUpdate = new SupplierUpdate();
            Bundle args = new Bundle();
            args.putString("name", name);
            args.putInt("id", mtitles.get(this.getLayoutPosition()).get_id());
            supplierUpdate.setArguments(args);

            return supplierUpdate;
        }
    }
}
