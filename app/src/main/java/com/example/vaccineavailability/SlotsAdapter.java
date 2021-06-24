package com.example.vaccineavailability;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SlotsAdapter extends RecyclerView.Adapter<SlotsAdapter.SlotsViewHolder> {

    ArrayList<Slots> slotsArrayList;
    SlotsAdapter(ArrayList<Slots> slotsArrayList){
        this.slotsArrayList=slotsArrayList;
    }

    @NonNull
    @Override
    public SlotsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_particular_slot,parent,false);
        return new SlotsViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull SlotsViewHolder holder, int position) {
        Slots slots=slotsArrayList.get(position);
        holder.name.setText("Name: "+slots.name);
        holder.vaccine.setText("Vaccine: "+slots.vaccine);
        holder.address.setText("Address: "+slots.address);
        holder.available_capacity.setText("Total available: "+slots.available_capacity);
        holder.available_capacity_dose1.setText("Total available Dose 1: "+slots.available_capacity_dose1);
        holder.available_capacity_dose2.setText("Total available Dose 2: "+slots.available_capacity_dose2);
        holder.min_age_limit.setText("Minimum Age: "+slots.min_age_limit);
        holder.fee.setText("Fees: "+slots.fee);
    }

    @Override
    public int getItemCount() {
        return slotsArrayList.size();
    }

    public static class SlotsViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView vaccine;
        TextView available_capacity;
        TextView available_capacity_dose1;
        TextView available_capacity_dose2;
        TextView min_age_limit;
        TextView address;
        TextView fee;

        public SlotsViewHolder(@NonNull View itemView) {

            super(itemView);
            name=itemView.findViewById(R.id.textView3);
            vaccine=itemView.findViewById(R.id.textView4);
            available_capacity=itemView.findViewById(R.id.textView5);
            available_capacity_dose1=itemView.findViewById(R.id.textView6);
            available_capacity_dose2=itemView.findViewById(R.id.textView7);
            min_age_limit=itemView.findViewById(R.id.textView8);
            address=itemView.findViewById(R.id.textView9);
            fee=itemView.findViewById(R.id.textView10);

        }
    }

}
