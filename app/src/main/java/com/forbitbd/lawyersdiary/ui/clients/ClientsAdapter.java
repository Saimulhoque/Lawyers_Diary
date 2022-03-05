package com.forbitbd.lawyersdiary.ui.clients;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.forbitbd.lawyersdiary.R;
import com.forbitbd.lawyersdiary.model.Client;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ClientsAdapter extends RecyclerView.Adapter<ClientsAdapter.ClientsViewHolder> {

    private List<Client> clientList;
    private ClientsClickListener listener;

    public ClientsAdapter(List<Client> clientList, ClientsClickListener listener) {
        this.clientList = clientList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ClientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_client, parent, false);
        return new ClientsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientsViewHolder holder, int position) {
        Client client = clientList.get(position);
        holder.bind(client);
    }

    @Override
    public int getItemCount() {
        return clientList.size();
    }

    public void AddClients(Client x) {
        clientList.add(x);
        int position = clientList.indexOf(x);
        notifyItemInserted(position);
    }

    public class ClientsViewHolder extends RecyclerView.ViewHolder {

        ImageView image, call;
        TextView name, phone, address, email;

        public ClientsViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.client_image);
            name = itemView.findViewById(R.id.client_name);
            phone = itemView.findViewById(R.id.client_phone);
            address = itemView.findViewById(R.id.client_address);
            email = itemView.findViewById(R.id.client_email);
            call = itemView.findViewById(R.id.ic_call);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItemClick(clientList.get(getAdapterPosition()));
                }
            });

            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnCallClick(clientList.get(getAdapterPosition()));
                }
            });
        }

        public void bind(Client client) {
//            Picasso.get().load(clients.getImage()).into(image);
            name.setText(client.getName());
            phone.setText(client.getPhone_two());
            email.setText(client.getEmail());
            address.setText(client.getAddress());
        }
    }

    public interface ClientsClickListener {
        void OnItemClick(Client clients);
        void OnCallClick(Client clients);
    }
}
