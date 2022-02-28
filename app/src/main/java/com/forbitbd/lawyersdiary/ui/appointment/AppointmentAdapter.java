package com.forbitbd.lawyersdiary.ui.appointment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.forbitbd.lawyersdiary.R;
import com.forbitbd.lawyersdiary.model.AppointmentRequest;
import com.forbitbd.lawyersdiary.model.AppointmentResponse;
import com.forbitbd.lawyersdiary.utils.MyUtil;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder> {

    private List<AppointmentResponse> appointmentResponseList;
    private CallClickListener listener;

    public AppointmentAdapter(List<AppointmentResponse> appointmentResponseList, CallClickListener listener) {
        this.appointmentResponseList = appointmentResponseList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AppointmentAdapter.AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_appointment,parent,false);
        return new AppointmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentAdapter.AppointmentViewHolder holder, int position) {
        AppointmentResponse appointmentResponse = appointmentResponseList.get(position);
        holder.bind(appointmentResponse);
    }

    @Override
    public int getItemCount() {
        return appointmentResponseList.size();
    }

    public void AddAppointments(AppointmentResponse x) {
        appointmentResponseList.add(x);
        int position = appointmentResponseList.indexOf(x);

        notifyItemInserted(position);
    }

    public class AppointmentViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView circleImageView;
        private TextView tv_client_name, tv_appointment_date, tv_purpose;
        private ImageView ic_call;

        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);

            circleImageView = itemView.findViewById(R.id.client_image);
            tv_client_name = itemView.findViewById(R.id.client_name);
            tv_appointment_date = itemView.findViewById(R.id.appointment_date);
            tv_purpose = itemView.findViewById(R.id.appointment_purpose);
            ic_call = itemView.findViewById(R.id.ic_call);

            ic_call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnCallClick(appointmentResponseList.get(getAdapterPosition()));
                }
            });
        }

        public void bind(AppointmentResponse appointmentResponse) {
            tv_client_name.setText(appointmentResponse.getClient().getName());
            tv_appointment_date.setText(MyUtil.dateToStr(appointmentResponse.getAppointment_date()));
            tv_purpose.setText(appointmentResponse.getPurpose());
        }
    }

    public interface CallClickListener{
        void OnCallClick(AppointmentResponse appointmentResponse);
    }
}
