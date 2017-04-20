package com.example.moslah_hamza.stam;

/**
 * Created by Moslah_Hamza on 20/04/2017.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Moslah_Hamza on 08/02/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<String> mtitles;

    public MyAdapter(List<String> myFilieres) {
        mtitles = myFilieres;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_cell, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.name.setText(mtitles.get(position));
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

                }
            });
        }
    }
}
