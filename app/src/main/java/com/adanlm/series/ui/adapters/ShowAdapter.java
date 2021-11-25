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
import com.adanlm.series.ui.main.MainContract;
import com.bumptech.glide.RequestManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.ViewHolder> {

    private static final String TAG = "ShowAdapter";
    private List<Show> showList = new ArrayList<>();
    private final RequestManager glide;
    private MainContract.OnItemClickListener listener;

    @Inject
    public ShowAdapter(RequestManager glide) {
        this.glide = glide;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_show, parent, false);
        return new ViewHolder(view, listener);
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
        holder.setObjectShow(currentShow);
    }

    public void updateData(List<Show> showList) {
        this.showList = showList;
        notifyDataSetChanged();
    }

    public void setListener(MainContract.OnItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return showList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imgPreview;
        private TextView txtTitle, txtSummary, txtGenres;
        private MainContract.OnItemClickListener listener;
        private Show currentShow;

        public ViewHolder(@NonNull View itemView, MainContract.OnItemClickListener listener) {
            super(itemView);
            imgPreview = itemView.findViewById(R.id.img_preview_show);
            txtTitle = itemView.findViewById(R.id.txt_title_show);
            txtSummary = itemView.findViewById(R.id.txt_sumary_show);
            txtGenres = itemView.findViewById(R.id.txt_genres_show);
            itemView.setOnClickListener(this);
            this.listener = listener;
        }

        public void setObjectShow(Show currentShow) {
            this.currentShow = currentShow;
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

        @Override
        public void onClick(View view) {
            this.listener.onClick(currentShow);
        }
    }
}
