package com.example.arafat.contractlibrary;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.MyViewHolder> {


    private List<Contact> mContract;
    private final LayoutInflater mLayoutInflater;
    private static final String DEBUG = "TEST";


    // class constructor
    ContactListAdapter(@NonNull Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    //super class
    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView contactImage;
        TextView contactName;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            contactImage = itemView.findViewById(R.id.contact_image);
            contactName = itemView.findViewById(R.id.contract_name);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mLayoutInflater.inflate(R.layout.recyclerviewitemimage, viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if(mContract!=null) {
            Contact current = mContract.get(position);
            holder.contactName.setText(current.getContactName());
        }
    }

    @Override
    public int getItemCount() {
        if(mContract!=null) {
            ///Log.d("DEBUG", String.valueOf(mContract.size()));
            return mContract.size();
        }
        else
            return 0;
    }

    void setmContract(List<Contact> contract) {
        mContract = contract;
        notifyDataSetChanged();
    }

    public Contact getContactPostion(int position) {
        return mContract.get(position);
    }

}
