package com.example.matchalaab;

import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
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

        holder.tvNumber.setText(String.valueOf(position + 1));
        holder.tvName.setText(item.getName());
        holder.tvAddress.setText(item.getAddress());
        holder.tvHours.setText(item.getOpeningHours());

        String tag = item.getTag();
        if (tag == null || tag.isEmpty()) {
            holder.tvTag.setVisibility(View.GONE);
        } else {
            holder.tvTag.setVisibility(View.VISIBLE);
            holder.tvTag.setText(tag);
            int colorRes = "Flagship".equals(tag) ? R.color.accent : R.color.secondary;
            holder.tvTag.setBackgroundTintList(
                    ColorStateList.valueOf(
                            ContextCompat.getColor(holder.tvTag.getContext(), colorRes)));
        }
    }

    @Override
    public int getItemCount() {
        return branchList.size();
    }

    static class BranchViewHolder extends RecyclerView.ViewHolder {
        TextView tvNumber, tvName, tvAddress, tvHours, tvTag;

        BranchViewHolder(View itemView) {
            super(itemView);
            tvNumber  = itemView.findViewById(R.id.tvBranchNumber);
            tvName    = itemView.findViewById(R.id.tvBranchName);
            tvAddress = itemView.findViewById(R.id.tvBranchAddress);
            tvHours   = itemView.findViewById(R.id.tvBranchHours);
            tvTag     = itemView.findViewById(R.id.tvBranchTag);
        }
    }
}
