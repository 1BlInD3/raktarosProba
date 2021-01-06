package com.example.myrecview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>
{
    private OnItemLongClickListener mListener;

    public interface OnItemLongClickListener
    {
        void onItemLongClick(int position);
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener)
    {
        mListener = listener;
    }


    private ArrayList<Item> mItemList; // (3) változó az adapter konstruktornak

    public static class ItemViewHolder extends RecyclerView.ViewHolder //ITT CASTOLOM A WIDGETEKET (1)
    {
        public TextView textView;
        public TextView textView2;
        public TextView textView3;

        public ItemViewHolder(@NonNull View itemView, OnItemLongClickListener listener) {
            super(itemView);

            textView = (TextView)itemView.findViewById(R.id.text);
            textView2 = (TextView)itemView.findViewById(R.id.text2);
            textView3 = (TextView)itemView.findViewById(R.id.text3);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(listener != null)
                    {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION);
                            listener.onItemLongClick(position);
                    }
                    return true;
                }
            });

        }
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { // Ide rakom be a keletkezéskor a layoutot amit csniáltam!!! (2)
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(v,mListener);
        return itemViewHolder;
    }

    public ItemAdapter(ArrayList<Item> items) // (4) Adapter konstruktor
    {
        mItemList = items;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position)  //(5) átadni az értékeket
    {
        Item currentItem = mItemList.get(position);
        holder.textView.setText(currentItem.getmMertErtek());
        holder.textView2.setText(currentItem.getmDatum());
        holder.textView3.setText(currentItem.getmRajzszam());
    }

    @Override
    public int getItemCount() {  //(6) megadni a terjedelmet
        return mItemList.size();
    }

}
