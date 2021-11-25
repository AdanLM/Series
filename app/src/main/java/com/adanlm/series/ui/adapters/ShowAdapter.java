package com.adanlm.series.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adanlm.series.R;
import com.adanlm.series.data.model.Show;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.ViewHolder> {

    private static final String TAG = "ShowAdapter";
    private List<Show> showList = new ArrayList<>();
    private RequestManager glide;

    public ShowAdapter(RequestManager glide) {
        this.glide = glide;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_show, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Show currentShow = showList.get(position);
        String summary = currentShow.getSummary().replaceAll("\\<.*?\\>", "");

        holder.getTxtTitle().setText(currentShow.getTitle());
        holder.getTxtSummary().setText(summary);
        holder.getTxtGenres().setText(currentShow.getGenres().toString());
        glide.load(currentShow.getImage().getOriginal())
                .into(holder.getImgPreview());
    }

    public void updateData(List<Show> showList) {
        this.showList = showList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return showList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgPreview;
        private TextView txtTitle, txtSummary, txtGenres;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPreview = itemView.findViewById(R.id.img_preview_show);
            txtTitle = itemView.findViewById(R.id.txt_title_show);
            txtSummary = itemView.findViewById(R.id.txt_sumary_show);
            txtGenres = itemView.findViewById(R.id.txt_genres_show);
        }

        public ImageView getImgPreview() {
            return imgPreview;
        }

        public TextView getTxtTitle() {
            return txtTitle;
        }

        public TextView getTxtSummary() {
            return txtSummary;
        }

        public TextView getTxtGenres() {
            return txtGenres;
        }
    }
}
