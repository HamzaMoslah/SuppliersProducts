package com.example.moslah_hamza.stam;

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
 * Created by Moslah_Hamza on 24/04/2017.
 */

public class ProdAdapter extends RecyclerView.Adapter<ProdAdapter.MyViewHolder> {
    private List<Product> mtitles;
    private Context context;
    private Fragment fragment;

    public ProdAdapter(List<Product> myFilieres, Context context, Fragment fragment) {
        mtitles = myFilieres;
        this.context = context;
        this.fragment = fragment;
    }

    @Override
    public ProdAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.products_cell, parent, false);
        return new ProdAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProdAdapter.MyViewHolder holder, int position) {
        holder.name.setText(mtitles.get(position).get_name());
        holder.qte.setText(mtitles.get(position).getQte());
        holder.pu.setText(""+mtitles.get(position).get_PU());
    }

    @Override
    public int getItemCount() {
        return mtitles.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView qte;
        private final TextView pu;

        public MyViewHolder(final View itemView) {
            super(itemView);
            name = ((TextView) itemView.findViewById(R.id.name));
            qte = ((TextView) itemView.findViewById(R.id.qte));
            pu = ((TextView) itemView.findViewById(R.id.pu));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, name.getText().toString(), Toast.LENGTH_LONG).show();
//                    final FragmentTransaction ft = fragment.getFragmentManager().beginTransaction();
//                    ft.replace(R.id.frame, updateInstance(name.getText().toString()), "NewFragmentTag");
//                    ft.commit();
                }
            });
        }
    }
}
