package com.forbitbd.lawyersdiary.ui.cases;

import static android.provider.Settings.System.DATE_FORMAT;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.forbitbd.lawyersdiary.R;
import com.forbitbd.lawyersdiary.model.Case;
import com.forbitbd.lawyersdiary.model.CaseType;
import com.forbitbd.lawyersdiary.model.Client;
import com.forbitbd.lawyersdiary.model.Dashboard;
import com.forbitbd.lawyersdiary.utils.MyUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CasesAdapter extends RecyclerView.Adapter<CasesAdapter.CaseViewHolder> {

    private List<Case> casesList;
    private CaseClickListener listener;
    private Dashboard dashboard;

    public CasesAdapter(List<Case> casesList, CaseClickListener listener) {
        this.casesList = casesList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_case, parent, false);
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

    public void AddCases(Case x) {
        casesList.add(x);
        int position = casesList.indexOf(x);

        notifyItemInserted(position);
    }

    public class CaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvTitle, tvCaseType, tvCourtName, tvStartingDate,
                tvParty, tvOppPartyName, tvOppLawyer;

        ImageView btnEdit, btnClose, btnDelete;

        public CaseViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.case_title);
            tvCaseType = itemView.findViewById(R.id.case_type);
            tvCourtName = itemView.findViewById(R.id.court_name);
            tvStartingDate = itemView.findViewById(R.id.starting_date);
            tvParty = itemView.findViewById(R.id.complainant_or_defendant);
            tvOppPartyName = itemView.findViewById(R.id.opp_party_name);
            tvOppLawyer = itemView.findViewById(R.id.opp_lawyer_name);

            btnEdit = itemView.findViewById(R.id.btn_edit);
            btnClose = itemView.findViewById(R.id.btn_close);
            btnDelete = itemView.findViewById(R.id.btn_delete);

            btnEdit.setOnClickListener(this);
            btnClose.setOnClickListener(this);
            btnDelete.setOnClickListener(this);
        }

        public void bind(Case cases) {
            tvTitle.setText(cases.getCase_title());
            tvCaseType.setText(listener.getCaseType(cases.getCase_type()).getCase_type());
            tvParty.setText(listener.getClient(cases.getClient()).getName());
            tvOppPartyName.setText(cases.getOpposition_party_name());
            tvOppLawyer.setText(cases.getOpposition_lawyer_name());
            tvStartingDate.setText(MyUtil.dateToStr(cases.getCase_reg_date()));
        }

        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.btn_edit) {
                listener.onEditClick(casesList.get(getAdapterPosition()));
            } else if (id == R.id.btn_close) {
                listener.onCloseClick(casesList.get(getAdapterPosition()));
            } else if (id == R.id.btn_delete) {
                listener.onDeleteClick(casesList.get(getAdapterPosition()));
            }
        }
    }

    public interface CaseClickListener {
        void onEditClick(Case aCase);
        void onCloseClick(Case aCase);
        void onDeleteClick(Case aCase);
        Client getClient(String objectId);
        CaseType getCaseType(String objectId);
    }
}
