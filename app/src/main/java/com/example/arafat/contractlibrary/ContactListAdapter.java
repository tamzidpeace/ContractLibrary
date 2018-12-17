package com.example.arafat.contractlibrary;

import android.content.Context;
import android.content.Intent;
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
    private static final String debug = "TEST";
    private static final String debug2 = "TEST2";
    private OnItemClickListener listener;
    private OnLongItemClickListener listener2;


    // class constructor
    ContactListAdapter(@NonNull Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }


    //super class
    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView contactImage;
        TextView contactName;
        private final Context context;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            contactImage = itemView.findViewById(R.id.contact_image);
            contactName = itemView.findViewById(R.id.contract_name);
            context = itemView.getContext();

            // work for showing information

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Intent sentPositon = new Intent(view.getContext(), MainActivity.class);
                    sentPositon.putExtra("halarPosition", String.valueOf(position));
                    context.startActivity(sentPositon);
                    Log.d(debug2, "working from on click");
                    //Toast.makeText(context, String.valueOf(position), Toast.LENGTH_SHORT).show();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(mContract.get(position));
                    }
                }
            });

            // end of work for showing information

            // work for update information

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Log.d(debug, "onLongClick: working fro long click");
                    //Toast.makeText(context.getApplicationContext())
                    int position2 = getAdapterPosition();
                    if (listener2 != null && position2 != RecyclerView.NO_POSITION) {
                        listener2.onLongItemClick(mContract.get(position2));
                    }
                    return true;
                }
            });

        }

    }
    // work for update information

    public interface OnItemClickListener {
        void onItemClick(Contact contact);
    }

    void setOnItemClickListener(OnItemClickListener listener) {

        this.listener = listener;
    }

    //end of work for update information

    // work for update information

    public interface OnLongItemClickListener {
        void onLongItemClick(Contact contact);
    }

    void setOnLongItemClickListener(OnLongItemClickListener listener2) {
        this.listener2 = listener2;
    }

    // end of work for update infromation(it it go to MyViewHolder Class from here)


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mLayoutInflater.inflate(R.layout.recyclerviewitemimage, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (mContract != null) {
            Contact current = mContract.get(position);
            holder.contactName.setText(current.getContactName());
        }
    }

    @Override
    public int getItemCount() {
        if (mContract != null) {
            ///Log.d("DEBUG", String.valueOf(mContract.size()));
            return mContract.size();
        } else
            return 0;
    }

    void setmContract(List<Contact> contract) {
        mContract = contract;
        notifyDataSetChanged();
    }

    Contact getContactPostion(int position) {
        return mContract.get(position);
    }

}
