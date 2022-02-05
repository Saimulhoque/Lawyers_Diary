package com.forbitbd.lawyersdiary.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.forbitbd.lawyersdiary.R;
import com.forbitbd.lawyersdiary.model.Features;

import java.util.List;

public class FeatureAdapter extends RecyclerView.Adapter<FeatureAdapter.FeatureViewHolder> {

    private List<Features> featuresList;
    private FeatureClickListener listener;

    public FeatureAdapter(List<Features> featuresList, FeatureClickListener listener) {
        this.featuresList = featuresList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FeatureAdapter.FeatureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        return new FeatureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeatureAdapter.FeatureViewHolder holder, int position) {
        Features features = featuresList.get(position);
        holder.bind(features);
    }

    @Override
    public int getItemCount() {
        return featuresList.size();
    }

    public class FeatureViewHolder extends RecyclerView.ViewHolder {

        private ImageView icon;
        private TextView title;

        public FeatureViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.feature_icon);
            title = itemView.findViewById(R.id.feature_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItemClick(getAdapterPosition());
                }
            });
        }

        public void bind(Features features) {
            icon.setImageResource(features.getIcon());
            title.setText(features.getTitle());
        }
    }

    public interface FeatureClickListener{
        void OnItemClick(int adapterPosition);
    }
}
