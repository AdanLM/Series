package com.adanlm.series.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adanlm.series.R;
import com.adanlm.series.data.model.Episode;
import com.adanlm.series.utils.CommonUtils;
import com.bumptech.glide.RequestManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.ViewHolder> {

    private List<Episode> episodeList = new ArrayList<>();
    private RequestManager glide;

    @Inject
    public EpisodeAdapter(RequestManager glide) {
        this.glide = glide;
    }

    @NonNull
    @Override
    public EpisodeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_episode, parent, false);
        return new EpisodeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeAdapter.ViewHolder holder, int position) {
        Episode currentEpisode = episodeList.get(position);

        holder.getTxtName().setText(currentEpisode.getName());
        holder.getTxtNumberEpisode().setText(currentEpisode.getNumEpisode());
        holder.getTxtSummary().setText(CommonUtils.cleanHTMLText(currentEpisode.getSummary()));
        glide.load(currentEpisode.getImage()).into(holder.getImgEpisode());
    }

    @Override
    public int getItemCount() {
        return episodeList.size();
    }

    public void updateData(List<Episode> episodeList) {
        this.episodeList = episodeList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName, txtNumberEpisode, txtSummary;
        private ImageView imgEpisode;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_title_episode);
            txtNumberEpisode = itemView.findViewById(R.id.txt_number_episode);
            txtSummary = itemView.findViewById(R.id.txt_summary_episode);
            imgEpisode = itemView.findViewById(R.id.img_preview_episode);

        }

        public TextView getTxtName() {
            return txtName;
        }

        public TextView getTxtNumberEpisode() {
            return txtNumberEpisode;
        }

        public TextView getTxtSummary() {
            return txtSummary;
        }

        public ImageView getImgEpisode() {
            return imgEpisode;
        }
    }
}
