package com.adanlm.series.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adanlm.series.R;
import com.adanlm.series.data.model.Season;
import com.adanlm.series.ui.detailshow.DetailShowContract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class SeasonAdapter extends RecyclerView.Adapter<SeasonAdapter.ViewHolder> {

    private List<Season> seasonList = new ArrayList<>();
    private DetailShowContract.OnItemClickListener listener;


    @Inject
    public SeasonAdapter() {
    }

    @NonNull
    @Override
    public SeasonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_season, parent, false);
        return new SeasonAdapter.ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull SeasonAdapter.ViewHolder holder, int position) {
        Season currentSeason = seasonList.get(position);
        holder.getTxtSeason().setText("Temporada " + currentSeason.getNumberSeason());
        holder.setObjectShow(currentSeason);
    }

    @Override
    public int getItemCount() {
        return seasonList.size();
    }

    public void updateData(List<Season> seasonList) {
        this.seasonList = seasonList;
        notifyDataSetChanged();
    }

    public void setListener(DetailShowContract.OnItemClickListener listener) {
        this.listener = listener;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView txtSeason;
        private Season currentSeason;
        private DetailShowContract.OnItemClickListener listener;

        public ViewHolder(@NonNull View itemView, DetailShowContract.OnItemClickListener listener) {
            super(itemView);
            txtSeason = itemView.findViewById(R.id.txt_detail_season);
            itemView.setOnClickListener(this);
            this.listener = listener;
        }

        public void setObjectShow(Season currentSeason) {
            this.currentSeason = currentSeason;
        }

        public TextView getTxtSeason() {
            return txtSeason;
        }

        @Override
        public void onClick(View view) {
            listener.onClick(currentSeason);
        }
    }
}
