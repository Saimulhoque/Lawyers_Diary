package com.forbitbd.lawyersdiary.ui.cases;

public class CasesPresenter implements CasesContract.Presenter{

    private CasesContract.View mView;

    public CasesPresenter(CasesContract.View mView) {
        this.mView = mView;
    }
}
