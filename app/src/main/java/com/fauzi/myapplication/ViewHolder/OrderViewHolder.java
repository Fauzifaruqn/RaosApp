package com.fauzi.myapplication.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fauzi.myapplication.Interface.ItemClickListerner;
import com.fauzi.myapplication.R;

public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtOrderId, txtOrderStatus, txtOrderAddress, txtOrderPhone;

    private ItemClickListerner itemClickListerner;

    public OrderViewHolder(@NonNull View itemView) {
        super(itemView);

        txtOrderPhone = (TextView)itemView.findViewById(R.id.order_phone);
        txtOrderStatus = (TextView)itemView.findViewById(R.id.order_status);
        txtOrderAddress = (TextView)itemView.findViewById(R.id.order_address);
        txtOrderId = (TextView)itemView.findViewById(R.id.order_id);


        itemView.setOnClickListener(this);
    }

    public void setItemClickListerner(ItemClickListerner itemClickListerner) {
        this.itemClickListerner = itemClickListerner;
    }

    @Override
    public void onClick(View view) {

        itemClickListerner.onClik(view,getAdapterPosition(),false);
    }
}
