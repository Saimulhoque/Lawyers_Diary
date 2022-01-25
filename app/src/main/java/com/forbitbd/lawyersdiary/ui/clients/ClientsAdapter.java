package com.forbitbd.lawyersdiary.ui.clients;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.forbitbd.lawyersdiary.R;
import com.forbitbd.lawyersdiary.model.Clients;

import java.util.List;

public class ClientsAdapter extends RecyclerView.Adapter<ClientsAdapter.ClientsViewHolder> {

    private List<Clients> clientsList;
    private ClientsClickListener listener;

    public ClientsAdapter(List<Clients> clientsList, ClientsClickListener listener) {
        this.clientsList = clientsList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ClientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_client,parent,false);
        return new ClientsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientsViewHolder holder, int position) {
        Clients clients = clientsList.get(position);
        holder.bind(clients);
    }

    @Override
    public int getItemCount() {
        return clientsList.size();
    }

    public class ClientsViewHolder extends RecyclerView.ViewHolder {

        ImageView image, call;
        TextView name, phone, regdate, email;

        public ClientsViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.client_image);
            name = itemView.findViewById(R.id.client_name);
            phone = itemView.findViewById(R.id.client_phone);
            regdate = itemView.findViewById(R.id.client_reg_date);
            email = itemView.findViewById(R.id.client_email);
            call = itemView.findViewById(R.id.ic_call);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItemClick(clientsList.get(getAdapterPosition()));
                }
            });
            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnCallClick(clientsList.get(getAdapterPosition()));
                }
            });
        }

        public void bind(Clients clients) {
            image.setImageResource(clients.getImage());
            name.setText(clients.getName());
            phone.setText(clients.getPhone1());
            regdate.setText(clients.getReg_date());
            email.setText(clients.getEmail());
        }
    }
}
