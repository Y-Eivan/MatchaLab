package com.example.matchalaab;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class BranchAdapter extends RecyclerView.Adapter<BranchAdapter.BranchViewHolder> {
    private final List<BranchItem> branchList;

    public BranchAdapter(List<BranchItem> branchList) {
        this.branchList = branchList;
    }

    @NonNull
    @Override
    public BranchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_branch, parent, false);
        return new BranchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BranchViewHolder holder, int position) {
        BranchItem item = branchList.get(position);

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
            tvName    = itemView.findViewById(R.id.tvBranchName);
            tvAddress = itemView.findViewById(R.id.tvBranchAddress);
            tvHours   = itemView.findViewById(R.id.tvBranchHours);
        }
    }
}
