package com.cricketprediction.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.List;

public class Useradapter extends RecyclerView.Adapter<Useradapter.myholder> {


    private Context context;
    private Adapterinsnner adapterinsnner;
    private List<User> userslist;




    public Useradapter(Context context,Adapterinsnner adapterinsnner) {
        this.context = context;
        this.userslist = new ArrayList<>();
        this.adapterinsnner = adapterinsnner;
    }

    public void adduser(User user){
        userslist.add(user);
        notifyDataSetChanged();
    }

    public void removeuser(int pos){

        userslist.remove(pos);
        notifyDataSetChanged();

    }

    public void cleardata(){
        userslist.clear();
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public Useradapter.myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_item_recyclerview,parent,false);
        return new myholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Useradapter.myholder holder, int position) {

        User user=userslist.get(position);

        holder.name.setText(user.getName());
        holder.lastname.setText(user.getLastname());
        holder.address.setText(user.getAddress());
        holder.gender.setText(user.getGender());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterinsnner.OnDeleted(user.getId(),position);
            }
        });

        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterinsnner.OnUpdate(user);
            }
        });

    }

    @Override
    public int getItemCount() {
        return userslist.size();
    }

    public class myholder extends RecyclerView.ViewHolder {

        private TextView name,lastname,gender,address;
        private ShapeableImageView update,delete;
        public myholder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.user_Name);
            lastname=itemView.findViewById(R.id.user_Designation);
            address=itemView.findViewById(R.id.user_Company);
            gender=itemView.findViewById(R.id.user_lastname);
            update=itemView.findViewById(R.id.action_update);
            delete=itemView.findViewById(R.id.action_delete);
        }
    }
}
