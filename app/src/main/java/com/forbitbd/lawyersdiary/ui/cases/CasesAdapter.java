package com.forbitbd.lawyersdiary.ui.cases;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.forbitbd.lawyersdiary.R;
import com.forbitbd.lawyersdiary.model.Case;

import java.util.List;

public class CasesAdapter extends RecyclerView.Adapter<CasesAdapter.CaseViewHolder> {

    private List<Case> casesList;

    public CasesAdapter(List<Case> casesList) {
        this.casesList = casesList;
    }

    @NonNull
    @Override
    public CaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_case,parent,false);
        return new CaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CaseViewHolder holder, int position) {
        Case cases = casesList.get(position);
        holder.bind(cases);
    }

    @Override
    public int getItemCount() {
        return casesList.size();
    }

    public class CaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvTitle, tvCaseType, tvCourtName, tvStartingDate,
        tvJudgeName, tvParty, tvOppPartyName, tvOppLawyer;

        ImageView btnEdit,btnClose;

        public CaseViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.case_title);
            tvCaseType = itemView.findViewById(R.id.case_type);
            tvCourtName = itemView.findViewById(R.id.court_name);
            tvStartingDate = itemView.findViewById(R.id.starting_date);
            tvJudgeName = itemView.findViewById(R.id.judge_name);
            tvParty = itemView.findViewById(R.id.complainant_or_defendant);
            tvOppPartyName = itemView.findViewById(R.id.opp_party_name);
            tvOppLawyer = itemView.findViewById(R.id.opp_lawyer_name);

            btnEdit = itemView.findViewById(R.id.btn_edit);
            btnClose = itemView.findViewById(R.id.btn_close);

            btnEdit.setOnClickListener(this);
            btnClose.setOnClickListener(this);
        }

        public void bind(Case cases) {
            tvTitle.setText(cases.getCase_title());
            tvCaseType.setText(cases.getCase_type());
            tvCourtName.setText(cases.getCourt_name());
            tvStartingDate.setText(cases.getStarting_date());
            tvJudgeName.setText(cases.getJudge_name());
            tvParty.setText(cases.getParty());
            tvOppPartyName.setText(cases.getOpp_party_name());
            tvOppLawyer.setText(cases.getOpp_lawyer());
        }

        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.btn_edit){

            }else if (id == R.id.btn_close){

            }
        }
    }
}
