package com.adanlm.series.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adanlm.series.R;
import com.adanlm.series.data.model.Show;
import com.adanlm.series.ui.main.MainContract;
import com.adanlm.series.utils.CommonUtils;
import com.bumptech.glide.RequestManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.ViewHolder> implements Filterable {

    private static final String TAG = "ShowAdapter";
    private List<Show> showList = new ArrayList<>();
    private List<Show> showListAll = new ArrayList<>();
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
        String summary = CommonUtils.cleanHTMLText(currentShow.getSummary());

        holder.getTxtTitle().setText(currentShow.getTitle());
        holder.getTxtSummary().setText(summary);
        holder.getTxtGenres().setText(CommonUtils.cleanBracketsText(currentShow.getGenres().toString()));
        glide.load(currentShow.getImage().getOriginal())
                .into(holder.getImgPreview());
        holder.setObjectShow(currentShow);
    }

    public void updateData(List<Show> showList) {
        this.showList = showList;
        this.showListAll = new ArrayList<>(showList);
        notifyDataSetChanged();
    }

    public void setListener(MainContract.OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return showList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    /*
        Esta funcion nos sirve para poder filtrar los elementos de nuestro ArrayList
     */
    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Show> filteredList = new ArrayList<>();
            //Convertimos nuestra palabra a string y a minusculas para que la busquedad sea lo mas exacta posible
            String searchString = charSequence.toString().toLowerCase();
            if (searchString.isEmpty()) {
                filteredList.addAll(showListAll);
            } else {
                for (Show show : showListAll) {
                    if (show.getTitle().toLowerCase().contains(searchString)) {
                        filteredList.add(show);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            showList.clear();
            showList.addAll((List<Show>) filterResults.values);
            notifyDataSetChanged();
        }
    };

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
