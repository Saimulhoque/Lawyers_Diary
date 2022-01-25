package com.forbitbd.lawyersdiary.ui.settings;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.forbitbd.lawyersdiary.R;
import com.forbitbd.lawyersdiary.model.Settings;

import java.util.List;

public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.SettingsViewHolder> {

    private List<Settings> settingsList;
    private SettingsClickListener listener;

    public SettingsAdapter(List<Settings> settingsList, SettingsClickListener listener) {
        this.settingsList = settingsList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SettingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_settings_layout,parent,false);
        return new SettingsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SettingsViewHolder holder, int position) {
        Settings settings = settingsList.get(position);
        holder.bind(settings);
    }

    @Override
    public int getItemCount() {
        return settingsList.size();
    }

    public class SettingsViewHolder extends RecyclerView.ViewHolder {

        private ImageView option_img;
        private TextView option_title;

        public SettingsViewHolder(@NonNull View itemView) {
            super(itemView);

            option_img = itemView.findViewById(R.id.option_icon);
            option_title = itemView.findViewById(R.id.option_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItemClick(getAdapterPosition());
                }
            });

        }

        public void bind(Settings settings) {
            option_img.setImageResource(settings.getOption_icon());
            option_title.setText(settings.getOption_title());
        }
    }

    public interface SettingsClickListener {
        void OnItemClick(int adapterPosition);
    }
}
