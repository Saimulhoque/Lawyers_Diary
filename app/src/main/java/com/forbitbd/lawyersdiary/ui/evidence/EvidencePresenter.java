package com.forbitbd.lawyersdiary.ui.evidence;

public class EvidencePresenter implements EvidenceContract.Presenter{

    private EvidenceContract.View mView;

    public EvidencePresenter(EvidenceContract.View mView) {
        this.mView = mView;
    }
}
