package com.forbitbd.lawyersdiary.ui.appointment;

import com.forbitbd.lawyersdiary.model.AppointmentResponse;

import java.util.List;

public interface AppointmentContract {

    interface Presenter{
        void getAppointments(String lawyerId);
    }

    interface View{

        void addAppointments(List<AppointmentResponse> appointmentResponses);
    }
}
