package com.forbitbd.lawyersdiary.ui.addappointment;

import com.forbitbd.lawyersdiary.model.AppointmentRequest;
import com.forbitbd.lawyersdiary.model.AppointmentResponse;
import com.forbitbd.lawyersdiary.model.OthersAppointmentRequest;

public interface AddAppointmentContract {

    interface Presenter{
        boolean validate(AppointmentRequest appointmentRequest);
        boolean validateOthersAppointment(OthersAppointmentRequest appointmentRequest);
        void saveAppointment(AppointmentRequest appointmentRequest);
        void saveOthersAppointment(OthersAppointmentRequest appointmentRequest);
    }

    interface View{
        void clearError();
        void setError(int fieldId, String message);
        void closeDialog(AppointmentResponse appointmentResponse);
    }
}
