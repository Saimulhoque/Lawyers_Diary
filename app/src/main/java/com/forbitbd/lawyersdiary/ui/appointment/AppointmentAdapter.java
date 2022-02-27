package com.forbitbd.lawyersdiary.ui.appointment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.forbitbd.lawyersdiary.R;
import com.forbitbd.lawyersdiary.model.Appointment;
import com.forbitbd.lawyersdiary.utils.MyUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder> {

    private List<Appointment> appointmentList;

    public AppointmentAdapter(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    @NonNull
    @Override
    public AppointmentAdapter.AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_appointment,parent,false);
        return new AppointmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentAdapter.AppointmentViewHolder holder, int position) {
        Appointment appointment = appointmentList.get(position);
        holder.bind(appointment);
    }

    @Override
    public int getItemCount() {
        return appointmentList.size();
    }

    public class AppointmentViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView circleImageView;
        private TextView tv_client_name, tv_appointment_date, tv_purpose;

        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);

            circleImageView = itemView.findViewById(R.id.client_image);
            tv_client_name = itemView.findViewById(R.id.client_name);
            tv_appointment_date = itemView.findViewById(R.id.appointment_date);
            tv_purpose = itemView.findViewById(R.id.appointment_purpose);
        }

        public void bind(Appointment appointment) {
            tv_client_name.setText(appointment.getClient());
            tv_appointment_date.setText(MyUtil.dateToStr(appointment.getAppointment_date()));
            tv_purpose.setText(appointment.getPurpose());
        }
    }
}
