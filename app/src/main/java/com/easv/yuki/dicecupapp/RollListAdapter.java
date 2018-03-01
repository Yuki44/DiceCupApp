package com.easv.yuki.dicecupapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yuki on 01/03/2018.
 */

public class RollListAdapter extends RecyclerView.Adapter<RollListAdapter.ViewHolder> {

    public List<BERoll> diceList;

    public RollListAdapter(List<BERoll> diceList) {

        this.diceList = diceList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.rollResultText.setText(diceList.get(position).getTime().toString() + " " + Arrays.toString(diceList.get(position).getmEyes()));
    }

    @Override
    public int getItemCount() {
        return diceList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView rollResultText;
        View mView;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            rollResultText = mView.findViewById(R.id.roll_result_text);
        }
    }
}
