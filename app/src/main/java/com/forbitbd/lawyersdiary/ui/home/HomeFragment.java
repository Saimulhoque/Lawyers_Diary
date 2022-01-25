package com.forbitbd.lawyersdiary.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.forbitbd.lawyersdiary.R;
import com.forbitbd.lawyersdiary.model.Features;
import com.forbitbd.lawyersdiary.ui.addcase.AddCaseActivity;
import com.forbitbd.lawyersdiary.ui.addcasetype.AddCaseTypeFragment;
import com.forbitbd.lawyersdiary.ui.addclient.AddClientFragment;
import com.forbitbd.lawyersdiary.ui.addcourt.AddCourtFragment;
import com.forbitbd.lawyersdiary.ui.appointment.AppointmentActivity;
import com.forbitbd.lawyersdiary.ui.calender.CalenderActivity;
import com.forbitbd.lawyersdiary.ui.cases.CasesActivity;
import com.forbitbd.lawyersdiary.ui.clients.ClientsActivity;
import com.forbitbd.lawyersdiary.ui.evidence.EvidenceActivity;
import com.forbitbd.lawyersdiary.ui.features.FeatureAdapter;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private TextView tvCaseAdded, tvScheduleToday, tvActiveCases, tvCaseClosed;
    private MaterialCardView cardCaseAdded, cardScheduleToday, cardActiveCases, cardCaseClosed;
    private RecyclerView recyclerView;
    private ArrayList<Features> featuresList;
    private FeatureAdapter adapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        tvCaseAdded = view.findViewById(R.id.case_added);
        tvScheduleToday = view.findViewById(R.id.schedule_today);
        tvActiveCases = view.findViewById(R.id.active_cases);
        tvCaseClosed = view.findViewById(R.id.closed_cases);

        cardCaseAdded = view.findViewById(R.id.card_case_added);
        cardScheduleToday = view.findViewById(R.id.card_schedule_today);
        cardActiveCases = view.findViewById(R.id.card_active_cases);
        cardCaseClosed = view.findViewById(R.id.card_closed_cases);

        cardCaseAdded.setOnClickListener(this);
        cardScheduleToday.setOnClickListener(this);
        cardActiveCases.setOnClickListener(this);
        cardCaseClosed.setOnClickListener(this);

        recyclerView = view.findViewById(R.id.recyclerview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        featuresList = new ArrayList<>();
        featuresList.add(new Features(R.drawable.add_user,"Add Client"));
        featuresList.add(new Features(R.drawable.add_file,"Add Case"));
        featuresList.add(new Features(R.drawable.court,"Add Court"));
        featuresList.add(new Features(R.drawable.add_case_type,"Add Case Type"));
        featuresList.add(new Features(R.drawable.client,"Clients"));
        featuresList.add(new Features(R.drawable.cases,"Cases"));
        featuresList.add(new Features(R.drawable.appointment,"Appointment"));
        featuresList.add(new Features(R.drawable.fingerprint,"Evidence"));
        featuresList.add(new Features(R.drawable.calendar,"Calender"));

        adapter = new FeatureAdapter(featuresList, new FeatureClickListener() {
            @Override
            public void OnItemClick(int adapterPosition) {
                if (adapterPosition == 0){
                    startAddClientDialog();
                }else if (adapterPosition == 1){
                    startAddCaseDialog();
                }else if (adapterPosition == 2){
                    startAddCourtDialog();
                }else if (adapterPosition == 3){
                    startAddCaseTypeDialog();
                }else if (adapterPosition == 4){
                    startClientActivity();
                }else if (adapterPosition == 5){
                    startCasesActvity();
                }else if (adapterPosition == 6){
                    startAppointmentActivity();
                }else if (adapterPosition == 7){
                    startEvidenceActivity();
                }else if (adapterPosition == 8){
                    startCalenderActivity();
                }
            }
        });

        recyclerView.setAdapter(adapter);
    }

    private void startCalenderActivity() {
        startActivity(new Intent(getContext(), CalenderActivity.class));
    }

    private void startEvidenceActivity() {
        startActivity(new Intent(getContext(), EvidenceActivity.class));
    }

    private void startAppointmentActivity() {
        startActivity(new Intent(getContext(), AppointmentActivity.class));
    }

    private void startCasesActvity() {
        startActivity(new Intent(getContext(), CasesActivity.class));
    }

    private void startClientActivity() {
        startActivity(new Intent(getContext(), ClientsActivity.class));
    }

    private void startAddCourtDialog() {
        AddCourtFragment addCourtFragment = new AddCourtFragment();
        addCourtFragment.setCancelable(true);
        addCourtFragment.show(getChildFragmentManager(),"kkkkkkkk");
    }

    private void startAddCaseDialog() {
        startActivity(new Intent(getContext(), AddCaseActivity.class));
    }

    private void startAddCaseTypeDialog() {
        AddCaseTypeFragment caseTypeFragment = new AddCaseTypeFragment();
        caseTypeFragment.setCancelable(true);
        caseTypeFragment.show(getChildFragmentManager(),"jjjjjjjj");
    }

    private void startAddClientDialog() {
        AddClientFragment addClientFragment = new AddClientFragment();
        addClientFragment.setCancelable(true);
        addClientFragment.show(getChildFragmentManager(),"jjjjjjjj");
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.card_case_added){
            startActivity(new Intent(getContext(), CasesActivity.class));
        }else if (id == R.id.card_schedule_today){

        }else if (id == R.id.card_active_cases){

        }else if (id == R.id.card_closed_cases){

        }
    }
}