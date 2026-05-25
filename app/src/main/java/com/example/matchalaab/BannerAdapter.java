package com.example.matchalaab;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.BannerViewHolder> {

    private final List<BannerItem> banners;

    public BannerAdapter(List<BannerItem> banners) {
        this.banners = banners;
    }

    @NonNull
    @Override
    public BannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_banner, parent, false);
        return new BannerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BannerViewHolder holder, int position) {
        BannerItem banner = banners.get(position);
        holder.tvTag.setText(banner.getTag());
        holder.tvTitle.setText(banner.getTitle());
        holder.tvSubtitle.setText(banner.getSubtitle());
        holder.root.setBackgroundColor(banner.getBackgroundColor());
    }

    @Override
    public int getItemCount() {
        return banners.size();
    }

    static class BannerViewHolder extends RecyclerView.ViewHolder {
        View root;
        TextView tvTag, tvTitle, tvSubtitle;

        BannerViewHolder(View itemView) {
            super(itemView);
            root = itemView;
            tvTag = itemView.findViewById(R.id.tvBannerTag);
            tvTitle = itemView.findViewById(R.id.tvBannerTitle);
            tvSubtitle = itemView.findViewById(R.id.tvBannerSubtitle);
        }
    }
}
