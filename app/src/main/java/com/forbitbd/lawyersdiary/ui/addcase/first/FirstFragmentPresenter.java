package com.forbitbd.lawyersdiary.ui.addcase.first;

import com.forbitbd.lawyersdiary.model.Case;

public class FirstFragmentPresenter implements FirstFragmentContract.Presenter{

    private FirstFragmentContract.View mView;

    public FirstFragmentPresenter(FirstFragmentContract.View mView) {
        this.mView = mView;
    }

    @Override
    public boolean validate(Case ca_se) {
        mView.clearError();
        if(ca_se.getCase_title().equals("")){
            mView.setError(1,"Empty Field Not Allowed");
            return false;
        }else if (ca_se.getCase_number().equals("")){
            mView.setError(2,"Empty Field Not Allowed");
            return false;
        }else if (ca_se.getFile_number().equals("")){
            mView.setError(3,"Empty Field Not Allowed");
            return false;
        }else if (ca_se.getCase_type()==null ||ca_se.getCase_type().equals("")){
            mView.setError(4,"Empty Field Not Allowed");
            return false;
        }else if (ca_se.getCourt()==null ||ca_se.getCourt().equals("")){
            mView.setError(5,"Empty Field Not Allowed");
            return false;
        }else if (ca_se.getClient()==null || ca_se.getClient().equals("")){
            mView.setError(6,"Empty Field Not Allowed");
            return false;
        }else if(ca_se.getCase_reg_date()==null){
            mView.setError(7,"Please Set a Proper Registration Date");
            return false;
        }
        return true;
    }

    @Override
    public void saveCase(Case ca_se) {
        mView.saveCaseInActivity(ca_se);
    }
}
