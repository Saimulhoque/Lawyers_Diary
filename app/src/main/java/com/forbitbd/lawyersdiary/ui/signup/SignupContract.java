package com.forbitbd.lawyersdiary.ui.signup;

public interface SignupContract {

    interface Presenter{

        void signUp(String email, String password);

        boolean validate(String email, String password);
    }

    interface View{

        void clearPreError();

        void showErrorMessage(String email_is_not_valid, int i);

        void showDialog();

        void showSignupSuceesDialog();

        void hideDialog();

        void showToast(String failed_to_send_varification_email, int i);
    }

}
