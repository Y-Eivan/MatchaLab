package com.example.matchalaab;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class BranchAdapter extends RecyclerView.Adapter<BranchAdapter.BranchViewHolder> {

    //branch list passed from BranchActivity
    private final List<BranchItem> branchList;

    public BranchAdapter(List<BranchItem> branchList) {
        this.branchList = branchList;
    }

    @NonNull
    @Override
    public BranchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate branch card layout for each store
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_branch, parent, false);
        return new BranchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BranchViewHolder holder, int position) {
        BranchItem item = branchList.get(position);
        //fill card with store name, address and opening hours
        holder.tvName.setText(item.getName());
        holder.tvAddress.setText(item.getAddress());
        holder.tvHours.setText(item.getOpeningHours());
    }

    @Override
    public int getItemCount() {
        return branchList.size();
    }

    static class BranchViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvAddress, tvHours;

        BranchViewHolder(View itemView) {
            super(itemView);
            //bind views from item_branch.xml
            tvName    = itemView.findViewById(R.id.tvBranchName);
            tvAddress = itemView.findViewById(R.id.tvBranchAddress);
            tvHours   = itemView.findViewById(R.id.tvBranchHours);
        }
    }
}
