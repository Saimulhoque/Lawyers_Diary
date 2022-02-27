package com.forbitbd.lawyersdiary.ui.addappointment;

import com.forbitbd.lawyersdiary.model.Appointment;

public interface AddAppointmentContract {

    interface Presenter{
        boolean validate(Appointment appointment);
        void saveAppointment(Appointment appointment);
    }

    interface View{
        void clearError();
        void setError(int fieldId, String message);
        void addAppointment(Appointment appointment);
    }
}
